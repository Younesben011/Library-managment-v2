package com.example.librarymanagement;

import DatabaseManagment.Librarian.librarian;
import components.CustomHeader;
import components.CustomMenu;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibraryApp extends Stage {
    Scene scene;
    VBox window;
    double width=1200;
    double height=600;
    VBox MainContainer;
    LibraryApp(Controller controller ,librarian lib){
        setResizable(false);
        System.out.println("--------"+controller.getLanguage());
        String Css= LoginPage.class.getResource("style.css").toExternalForm();
        Group root= new Group() ;
        scene = new Scene(root,1200, 600);

         MainContainer = new VBox();
        MainContainer.setPrefWidth(scene.getWidth());
        MainContainer.setPrefHeight(scene.getHeight());
        HBox container = new HBox();
        controller.setRoot(container);
        scene.widthProperty().addListener(e->{
            MainContainer.setPrefWidth(scene.getWidth());

        });
        scene.heightProperty().addListener(e->{
            MainContainer.setPrefHeight(scene.getHeight());
        });
        Label librarianName =new Label("No user");
//        List<String> = new List<String>({"1","2","3"});
        String[] French={"Tableau de bord", "Gestionnaire de livres", "Gestionnaire de membres", "Emprunter un livre", "Rendre un livre", "Déconnexion"};
        String[] English= {"Dashboard","Book Manager","Members Manager","Issue Book","Return Book","Logout"};
        String[] MenuList;
        if(controller.getLanguage().equals("English"))
         MenuList =English;
        else
            MenuList=French;

        CustomHeader customHeader;
        if (lib!=null){
         customHeader =new CustomHeader(controller,lib);

        }else {
         customHeader =new CustomHeader(controller,null);

        }
        CustomMenu customMenu = new CustomMenu(MenuList,controller,scene.getWidth(),lib);
        if(lib!=null)
            librarianName.setText(lib.getName());
        Button btn = new Button("Logout");
        btn.setOnAction(e->{
            controller.setWindow("Logout",6,controller,scene.getWidth(),null);
        });

        MainContainer.getChildren().add(customHeader);
        container.getChildren().add(customMenu);
        container.getChildren().add(librarianName);
        controller.setWindow("Dashboard",0,controller,scene.getWidth(),null);
        MainContainer.getChildren().add(container);
        root.getChildren().add(MainContainer);
        scene.getStylesheets().add(Css);
        this.setScene(scene);
    }

}
