package components;

import com.example.librarymanagement.Controller;
import com.example.librarymanagement.LoginPage;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.function.Consumer;

public class DropMenu extends Pane {
    ImageView MenuIcon;
    Label title;
    VBox Menu;
    String[] menu_list;
    Consumer func ;
    boolean toggel;
    String Css= LoginPage.class.getResource("style.css").toExternalForm();


    public DropMenu(String[] menu, Controller controller, Consumer func, String Title){
        getStylesheets().add(Css);
        menu_list=menu;
        title = new Label(Title);
        Menu =new VBox();
        this.func=func;
    }
    public DropMenu(String[] menu,Controller controller,Consumer func,String Title,String Icon){
        getStylesheets().add(Css);
        menu_list=menu;
        title = new Label(Title);

        this.func=func;
        Menu =new VBox();
        FileInputStream imageFile;
        try {
            imageFile = new FileInputStream(Icon);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image =new Image(imageFile);
        MenuIcon =new ImageView(image);
        MenuIcon.setFitWidth(14);
        MenuIcon.setFitHeight(14);

    }
    public void RenderMenu(){
        Menu.getStyleClass().add("DropMenu");
        Menu.setVisible(false);
        Menu.setTranslateY(30);
        Menu.setTranslateX(40);
        HBox MenuHeader = new HBox(5);
        MenuHeader.getChildren().add(title);
        title.getStyleClass().add("MenuTitle");
        title.setTextFill(Color.WHITE);
        title.setUnderline(true);
        title.setTranslateX(5);
        title.setTranslateY(5);
        if(MenuIcon!=null)
            MenuHeader.getChildren().add(MenuIcon);
        MenuHeader.setOnMouseClicked(e->{
            Menu.setVisible(true);

        });
        int i =0;
        for (String item :menu_list){
             Label itemLable = new Label(item);
             if(i==0){
                 itemLable.getStyleClass().add("roundB");
             }else{
                 itemLable.getStyleClass().add("item");
             }
             i++;
//            itemLable.setMaxWidth(100);
//            itemLable.setPrefWidth(100);
            Menu.getChildren().add(itemLable);
            itemLable.setOnMouseClicked(e->{
                func.accept(itemLable.getText());
            });

        }
        Menu.getChildren().get(Menu.getChildren().size()-1).getStyleClass().add("round");

        getChildren().add(MenuHeader);
        getChildren().add(Menu);

    }
}
