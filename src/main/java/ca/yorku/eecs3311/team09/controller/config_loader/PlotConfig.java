package ca.yorku.eecs3311.team09.controller.config_loader;

import com.google.gson.annotations.SerializedName;

public class PlotConfig {
    @SerializedName("name")
    protected String name;
    @SerializedName("type")
    protected String type;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
