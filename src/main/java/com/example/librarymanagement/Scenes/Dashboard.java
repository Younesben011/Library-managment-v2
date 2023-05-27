package com.example.librarymanagement.Scenes;

import com.example.librarymanagement.Controller;
import com.example.librarymanagement.LoginPage;
import components.Box;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Dashboard extends VBox {
    String Css= LoginPage.class.getResource("Components.css").toExternalForm();
//    String[] english = {"Dashboard",};
    String[] english = {"Dashboard","Books Num","Members Num","Issued Books Num ","defaulter Num"};
    String[] french = {"Tableau de bord", "Nombre de livres", "Nombre de membres", "Nombre de livres empruntés", "en retard"};
    String[] langList=english;

    public Dashboard(Controller controller,double width){
        if(controller.getLanguage().equals("English"))
            langList=english;
            else
             langList=french;

        getStylesheets().add(Css);
        Label DashboardLable= new Label(langList[0]);
        HBox LableContainer =new HBox();
        LableContainer.setMaxWidth(1000);
        LableContainer.setPrefWidth(1000);
        LableContainer.setTranslateY(80);
//        LableContainer.setBackground(Background.fill(Color.BLACK));
        LableContainer.setAlignment(Pos.CENTER);
        LableContainer.getChildren().add(DashboardLable);
        DashboardLable.getStyleClass().add("DashboardLable");
        HBox boxContainer = new HBox(20);
        boxContainer.setAlignment(Pos.CENTER);
        boxContainer.setMaxSize(1000,500);
        boxContainer.setPrefSize(1000,500);
        setPrefHeight(500);
        setSpacing(20);
        Supplier<Integer> getBooksNum = ()-> {
            try {
                return controller.getBooksNumber();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
        Supplier<Integer> getMembersNum = ()-> {
            try {
                return controller.getMembersNum();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
        Supplier<Integer> getIssuedBookNum = ()->controller.getIssuedBooksNumber();
        Supplier<Integer> getdefaulterNum = ()->controller.getDefaulterNumber();

        Box box = new Box("#F80404C8",200,200,langList[1],getBooksNum);
        Box box1 = new Box("#F80404C8",200,200,langList[2],getMembersNum);
        Box box2 = new Box("#F80404C8",200,200,langList[3],getIssuedBookNum);
        Box box3 = new Box("#F80404C8",200,200,langList[4],getdefaulterNum);
        getChildren().add(LableContainer);
        boxContainer.getChildren().add(box);
        boxContainer.getChildren().add(box1);
        boxContainer.getChildren().add(box2);
        boxContainer.getChildren().add(box3);
        getChildren().add(boxContainer);
        setAlignment(Pos.CENTER);

    }
}
