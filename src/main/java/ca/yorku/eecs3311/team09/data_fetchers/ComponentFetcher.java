package ca.yorku.eecs3311.team09.data_fetchers;

import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.Year;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public final class ComponentFetcher implements DataFetcher {

    private static final String WB_LINK = "https://api.worldbank.org/v2/country/%s/indicator/%s?date=%d:%d&format=json";

    private final DataFetcher fetcher;
    private final Indicator indicator;
    private final Country country;
    private final int fromDate;
    private final int toDate;


    public ComponentFetcher(Indicator indicator, DataFetcher fetcher) {
        this.fetcher = fetcher;
        this.indicator = indicator;

        this.country = fetcher.getCountry();
        this.fromDate = fetcher.getFromDate();
        this.toDate = fetcher.getToDate();

    }

    @Override
    public Map<Indicator, TimeSeries> getData() {
        String link = String.format(
                ComponentFetcher.WB_LINK,
                this.country.getCode(),
                this.indicator.getIndicator_token(),
                this.fromDate,
                this.toDate
        );

        try {
            URL url = new URL(link);

            JsonObject[] objects = ComponentFetcher.parseData(url);

            assert objects != null;

            TimeSeries timeSeries = new TimeSeries(this.indicator.getLabel());

            for (JsonObject j : objects) {
                int date = j.get("date").getAsInt();
                Double value = j.get("value").isJsonNull() ? null : j.get("value").getAsDouble();
                timeSeries.add(new Year(date), value);
            }

            Map<Indicator, TimeSeries> data = this.fetcher.getData();

            data.put(this.indicator, timeSeries);

            return data;
        } catch (MalformedURLException e) {
            System.out.println("URL was incorrect: " + link);
        } catch (UnsupportedOperationException | NumberFormatException e) {
            System.out.println("Data could not be parsed for: " + this.indicator.getIndicator_token());
        }

        return new HashMap<>();
    }

    public Country getCountry() {
        return this.country;
    }

    public int getFromDate() {
        return this.fromDate;
    }

    public int getToDate() {
        return this.toDate;
    }

    private static JsonObject[] parseData(URL url) {
        try {
            InputStreamReader reader = new InputStreamReader(url.openStream());
            Gson gson = new GsonBuilder().serializeNulls().create();
            Type type = new TypeToken<JsonElement[]>() {
            }.getType();
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            JsonElement[] resultMap = gson.fromJson(jsonElement.toString(), type);

            return gson.fromJson(resultMap[1], JsonObject[].class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
