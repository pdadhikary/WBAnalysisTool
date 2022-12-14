package ca.yorku.eecs3311.team09.data_fetchers;

import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

/**
 * The decorator component class of the {@link DataFetcher DataFetcher}.
 */
public final class ComponentFetcher implements DataFetcher {

    /**
     * Link to World Bank API
     */
    private String url = "https://api.worldbank.org/v2/country/%s/indicator/%s?date=%d:%d&format=json";

    /**
     * Child decorator
     */
    private final DataFetcher fetcher;
    private final Indicator indicator;
    private final Country country;
    private final int fromDate;
    private final int toDate;

    
    public ComponentFetcher(String url, Indicator indicator, DataFetcher fetcher) {
    	this.url = url;
    	this.fetcher = fetcher;
        this.indicator = indicator;

        this.country = fetcher.getCountry();
        this.fromDate = fetcher.getFromDate();
        this.toDate = fetcher.getToDate();
    }
    
    /**
     * Returns a new ComponentFetcher
     *
     * @param indicator the indicator for which to fetch data for
     * @param fetcher   the child decorator
     */
    public ComponentFetcher(Indicator indicator, DataFetcher fetcher) {
    	this(
    	"https://api.worldbank.org/v2/country/%s/indicator/%s?date=%d:%d&format=json",
    	indicator,
    	fetcher
    	);
    }

    /**
     * Returns the data for the indicator of this component and the child component.
     *
     * @return data
     */
    @Override
    public Map<Indicator, Map<Integer, Double>> getData() {
        String link = String.format(
                this.url,
                this.country.getCode(),
                this.indicator.getIndicator_token(),
                this.fromDate,
                this.toDate
        );

        try {
            URL url = new URL(link);

            JsonObject[] objects = this.parseData(url);

            assert objects != null;

            Map<Integer, Double> series = new TreeMap<>();

            for (JsonObject j : objects) {
                int date = j.get("date").getAsInt();
                Double value = j.get("value").isJsonNull() ? Double.NaN : j.get("value").getAsDouble();
                series.put(date, value);
            }

            Map<Indicator, Map<Integer, Double>> data = this.fetcher.getData();

            data.put(this.indicator, series);

            return data;
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL was incorrect: " + link);
        } catch (UnsupportedOperationException | NumberFormatException e) {
            throw new RuntimeException("Data could not be parsed for: " + this.indicator.getIndicator_token());
        }
    }

    @Override
    public Country getCountry() {
        return this.country;
    }

    @Override
    public int getFromDate() {
        return this.fromDate;
    }

    @Override
    public int getToDate() {
        return this.toDate;
    }

    /**
     * Helper function to parse JSON string retrieved from the given url.
     *
     * @param url URL to the JSON string to parse
     * @return parsed JSON objects
     */
    private JsonObject[] parseData(URL url) {
        JsonObject[] objects = null;
        try (InputStreamReader reader = new InputStreamReader(url.openStream())) {
            Gson gson = new GsonBuilder().setLenient().create();
            Type type = new TypeToken<JsonElement[]>() {
            }.getType();
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            JsonElement[] resultMap = gson.fromJson(jsonElement.toString(), type);

            objects = gson.fromJson(resultMap[1], JsonObject[].class);
        } catch (IOException e) {
            throw new RuntimeException("Could not establish connection to World Bank...");
        } catch (JsonIOException e) {
            throw new RuntimeException("World bank data corrupted...");
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException(String.format("Invalid parameters, countryCode=%s; from=%d; to=%d; indicator=%s",
                    this.country.getCode(),
                    this.fromDate,
                    this.toDate,
                    this.indicator.getIndicator_token()
            ));
        }

        return objects;
    }
}
