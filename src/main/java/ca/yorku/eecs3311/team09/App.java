package ca.yorku.eecs3311.team09;

import ca.yorku.eecs3311.team09.controller.AppController;
import ca.yorku.eecs3311.team09.controller.IAppController;
import ca.yorku.eecs3311.team09.controller.ILoginController;
import ca.yorku.eecs3311.team09.controller.LoginController;
import ca.yorku.eecs3311.team09.models.*;

import java.io.File;
import java.io.IOException;

public class App {
    public App(String environment) {
        String connectionString = environment.equals("DEV") ? DBContext.DEFAULT_TEST_DB : DBContext.DEFAULT_DB;

        IDBContext dbContext = DBContext.getInstance();
        dbContext.setConnectionString(connectionString);

        IUserModel userModel = new SQLUserModel(dbContext);
        ILoginController loginController = new LoginController(userModel);

        IPlotsModel plotsModel = new PlotsModel();
        IAppController appController = new AppController(userModel, plotsModel);

        loginController.showLoginView();
    }

    public static void main(String[] args) {
        // creating test db
        App.make_test_db();

        new App("DEV");
    }

    public static void make_test_db() {
        File dbFile = new File("src/main/resources/database/test/user_test.db");
        boolean dirCreated = dbFile.getParentFile().mkdirs();
        try {
            boolean dbFileCreate = dbFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
