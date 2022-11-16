package ca.yorku.eecs3311.team09.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AppConfig {
    private static final String CONFIG_FILE = "/src/main/resources/config/appsettings.json";

    public AppConfig() {
        GsonBuilder builder = new GsonBuilder();
        Gson reader = builder.create();
    }
}
