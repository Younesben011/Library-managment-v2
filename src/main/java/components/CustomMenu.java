package components;

import DatabaseManagment.Librarian.librarian;
import com.example.librarymanagement.Controller;
import com.example.librarymanagement.LoginPage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomMenu extends VBox {
    int index;
    String[] menuList;
    String[] iconsList;
    Map<String,Label> menuItem=new HashMap<>();
    String selected;
    public CustomMenu(String[] menuList, Controller controller, double width, librarian user){
        String lang =controller.getLanguage();
        String Css= LoginPage.class.getResource("Components.css").toExternalForm();
        getStylesheets().add(Css);
        getStyleClass().add("MenuContainer");
        setAlignment(Pos.CENTER);
        setSpacing(30);
        this.menuList=menuList;
        selected=menuList[0];

        index=0;
        for (String item: menuList) {
            int itemIndx=index;
            Label MenuItem = new Label(item);
            menuItem.put(item,MenuItem);
            if (item.equals(selected))
                setSelected(selected);

            MenuItem.setOnMouseClicked(e->{
                controller.setWindow(item,itemIndx,controller,width,user);
                resetSelected();
                setSelected(item);

            });
            MenuItem.getStyleClass().add("MenuItem");
            MenuItem.setAlignment(Pos.CENTER);
            getChildren().add(MenuItem);
            index++;
        }
//        setSelected(selected);
    }

    public void setSelected(String selected) {
//        Label item = menuItem.get(selected);
//        item.setBackground(Background.fill(Color.web("#68adea")));
        this.selected = selected;
        Label item = menuItem.get(selected);
        item.setBackground(Background.fill(Color.web("#4EA0EA3F")));
        item.getStyleClass().add("selectedItem");

    }
    public void resetSelected(){
        Label item = menuItem.get(selected);
        item.setBackground(Background.fill(Color.web("#FFFFFFFF")));
        item.getStyleClass().remove("selectedItem");

    }
    public void setSelected() {
        Label item = menuItem.get(selected);
        item.setBackground(Background.fill(Color.web("#4EA0EA3F")));
        item.getStyleClass().add("selectedItem");

    }

    public CustomMenu(String[] menuList, String[] iconsList){

    }
}
