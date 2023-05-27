package components;

import com.example.librarymanagement.LoginPage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Box extends VBox {

     public Box(String BarColor, int width, int height, String title, Supplier action){
         int actionReturn = (int) action.get();
         System.out.println("ssss"+actionReturn);
         String Css= LoginPage.class.getResource("Components.css").toExternalForm();
         setSpacing(5);
        setPrefWidth(width);
        setPrefHeight(height);
         Label BoxTitle = new Label(title);
         VBox innerBex = new VBox(10);
         innerBex.setAlignment(Pos.BASELINE_CENTER);
         Pane innerCircle = new Pane();
//         bar.setPrefWidth(200);
//         bar.setPrefHeight(20);
//         bar.setBackground(Background.fill(Color.web(BarColor)));
         innerCircle.getStyleClass().add("bar");
//         bar.set;

         Label resShow = new Label(String.valueOf(actionReturn));
         resShow.getStyleClass().add("resShow");

//         innerBex.getChildren().add(innerCircle);
         innerBex.getChildren().add(resShow);
         innerBex.setMaxHeight(150);
         innerBex.setMaxWidth(150);
         innerBex.setPrefWidth(150);
         innerBex.setPrefHeight(150);
         innerBex.getStylesheets().add(Css);
         innerBex.getStyleClass().add("box");
         getChildren().add(innerBex);
         getChildren().add(BoxTitle);
         setAlignment(Pos.CENTER);
//         setTranslateY(150);
//        Background.fill(Color.web(backgroundColor));
//         setBackground(Background.fill(Color.BLACK));
    }


}
