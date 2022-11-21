package ca.yorku.eecs3311.team09.controller.config_loader;

import com.google.gson.annotations.SerializedName;

/**
 * Plot configurations.
 */
public class PlotConfig {
    /**
     * name of the plot.
     */
    @SerializedName("name")
    protected String name;

    /**
     * type of the plot.
     */
    @SerializedName("type")
    protected String type;

    /**
     * Get the name of the plot.
     *
     * @return name of the plot.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the type of the plot.
     *
     * @return type of the plot.
     */
    public String getType() {
        return type;
    }
}
