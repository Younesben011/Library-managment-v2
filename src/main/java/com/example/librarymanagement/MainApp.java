package com.example.librarymanagement;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(EmailController.isAddressValid("younesbenzaama011@gmail.com"));

        stage.setResizable(false);
        Routes routes = new Routes();
        Controller controller = new Controller(routes);
        LibraryApp libraryApp = new LibraryApp(controller,null);
        routes.addRout(libraryApp,"libraryApp");
        LoginPage loginPage = new LoginPage(controller,controller.getLanguage());
        routes.addRout(loginPage,"Login");
//        controller.ShowStage("libraryApp");
        controller.ShowStage("Login")   ;


    }
    public static void main(String[] args) {
        launch();
    }
}
