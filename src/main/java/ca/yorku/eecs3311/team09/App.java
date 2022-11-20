package ca.yorku.eecs3311.team09;

import ca.yorku.eecs3311.team09.controller.AppController;
import ca.yorku.eecs3311.team09.controller.IAppController;
import ca.yorku.eecs3311.team09.controller.ILoginController;
import ca.yorku.eecs3311.team09.controller.LoginController;
import ca.yorku.eecs3311.team09.models.*;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class App {
    public App(String environment) {
        String baseDir = Objects.requireNonNull(App.class.getResource("/database")).getPath();
        String connectionSuffix = "jdbc:sqlite:";

        String connectionString;

        if (environment.equals("DEV")) {
            String dbPath = baseDir + "/test/user_test.db";
            App.make_test_db(dbPath);
            connectionString = connectionSuffix + dbPath;
        } else {
            connectionString = connectionSuffix + baseDir + "/user.db";
        }

        IDBContext dbContext = DBContext.getInstance();
        dbContext.setConnectionString(connectionString);

        IUserModel userModel = new SQLUserModel(dbContext);
        ILoginController loginController = new LoginController(userModel);

        IPlotsModel plotsModel = new PlotsModel();
        IAppController appController = new AppController(userModel, plotsModel);

        loginController.showLoginView();
    }

    public static void make_test_db(String path) {
        File dbFile = new File(path);
        boolean dirCreated = dbFile.getParentFile().mkdirs();
        try {
            boolean dbFileCreate = dbFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new App("DEV");
//        ConfigLoader loader = new ConfigLoader();
//        System.out.println(loader.getPlots());
    }

}
