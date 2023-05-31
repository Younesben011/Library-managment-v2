package components;

import DatabaseManagment.Librarian.librarian;
import com.example.librarymanagement.Controller;
import com.example.librarymanagement.LoginPage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.MalformedURLException;
import java.net.URL;

public class CustomHeader extends HBox {
    public CustomHeader(Controller controller,librarian user){
        String lag= controller.getLanguage();
        setSpacing(800);
        setAlignment(Pos.CENTER);
        String Css= LoginPage.class.getResource("Components.css").toExternalForm();
        getStylesheets().add(Css);
        getStyleClass().add("header");
        setMaxHeight(200);
        String english ="Library management";
        String french = "Gestion de bibliothèque";

        Label title = new Label();
        if (lag.equals("English"))
            title.setText(english);
        else
            title.setText(french);

        title.setTranslateX(20);
        title.getStyleClass().add("MainTitle");

        HBox userContainer =new HBox();
        userContainer.setMaxWidth(300);
        userContainer.setPrefWidth(300);
        userContainer.setTranslateX(-20);

        userContainer.setAlignment(Pos.CENTER);
        URL delsUrl;
        try {
            delsUrl= new URL(LoginPage.class.getResource("/pics/user.png").toExternalForm());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Image userImage = new Image("G:\\coding\\library-management-appliction-master\\src\\main\\resources\\pics\\user.png" );
        ImageView imageView = new ImageView();
        imageView.setImage(userImage);
        imageView.setFitWidth(35);
        imageView.setFitHeight(35);
        imageView.setOnMouseClicked(e->{
            controller.setWindow("UserManager",5,controller,0,user);
        });
        Label userName;
        if(user==null){

            userName= new Label("No User");
        }else
            userName= new Label(user.getName());

        userName.getStyleClass().add("UserName");

        userContainer.getChildren().add(userName);
        userContainer.getChildren().add(imageView);


        getChildren().add(title);
        getChildren().add(userContainer);
    }
}
