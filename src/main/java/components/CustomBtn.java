package components;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

public class CustomBtn  extends Button {

    public CustomBtn(double w, double h, String title, String bgColor, String txtColor, String className){
            setPrefHeight(h);
            setPrefWidth(w);
            setBackground(Background.fill(Color.web(bgColor)));
            setText(title);
            setTextFill(Color.web(txtColor));
            getStyleClass().add(className);
    }
    public CustomBtn(String title, String className){
            setPrefHeight(20);
            setPrefWidth(20);
            setText(title);
            getStyleClass().add(className);
    }
    public CustomBtn(double w, double h,String title, String className){
            setPrefHeight(h);
            setPrefWidth(w);
            setText(title);
            getStyleClass().add(className);
    }

}
