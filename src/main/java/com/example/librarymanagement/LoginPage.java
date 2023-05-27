package com.example.librarymanagement;

import DatabaseManagment.Librarian.librarian;
import components.CustomBtn;
import components.DropMenu;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.function.Consumer;

public class LoginPage extends Stage  {
     boolean showPass_toggel;
    TextField PasswordVisible;
    Image showPass;
     Image HidePass;
    Pane PassCotainer;
    Consumer<String> func;
     DropMenu dropMenu;
    Label useNameLable;
    Label passwordLable;
    Scene scene;
    String[] English = new  String[]{"User Name","Password","choose Language"};
    String[] French = new  String[]{"nom utilisateur","mot de passe","Choisissez la langue"};

    String UserName="";
    String Passcode="";
    TextField userName;
    PasswordField Password;
//    TextField Password;
    String[] langList=English;
    Label message;
    Controller controller;
//    String Css= LoginPage.class.getResource("style.css").toExternalForm();

    public LoginPage(Controller controller,String langg){
        this.controller=controller;
        System.out.println(controller.getLanguage());
        message=new Label("");
        String Css= LoginPage.class.getResource("Login.css").toExternalForm();
//        System.out.println("sasdad"+IM);
        initStyle(StageStyle.UNDECORATED);
        Group root= new Group() ;
        root.getStylesheets().add(Css);
        scene = new Scene(root,600, 600);
        scene.getStylesheets().add(Css);
         func = lang->{
          controller.setLanguage(lang);
             System.out.println(lang);
          if (lang.equals("Français")){
              langList=French;
//            controller.ShowStage("Login");
          }else {
              langList=English;
          }
          useNameLable.setText(langList[0]);
          userName.setPromptText(langList[0]);
          passwordLable.setText(langList[1]);
//          Password.setPromptText(langList[1]);
          dropMenu = new DropMenu(new String[]{"English","Français"},controller,func,langList[2]);
          dropMenu.RenderMenu();
          root.getChildren().remove(0);
          root.getChildren().add(0,dropMenu);
          message.setText("");

        };


        PassCotainer=new Pane();
//        PassCotainer.setMaxWidth(200);
//        PassCotainer.setPrefWidth(200);
        FileInputStream delS;
        try {
            URL delsUrl= new URL(LoginPage.class.getResource("/pics/view.png").toExternalForm());
//            System.out.println(delsUrl.getPath());
            delS = new FileInputStream(delsUrl.getPath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileInputStream hideS;
        try {
            URL delsUrl= new URL(LoginPage.class.getResource("/pics/view.png").toExternalForm());
//            System.out.println(delsUrl.getPath());
            hideS = new FileInputStream(delsUrl.getPath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        showPass =new Image(delS);
        HidePass =new Image(hideS);

        ImageView delimageView =new ImageView(showPass);
        delimageView.setFitWidth(20);
        delimageView.setFitHeight(20);
        delimageView.setTranslateX(225);
        delimageView.setTranslateY(6);
        delimageView.setOnMouseEntered(e->{
            delimageView.setCursor(Cursor.HAND);
        });

//        Button showPass=new Button("show");
//        showPass.setPrefWidth(20);
//        showPass.setPrefHeight(20);
//        showPass.getStyleClass().add("CustomBtn");








        dropMenu = new DropMenu(new String[]{"English","Français"},controller,func,langList[2]);
        dropMenu.RenderMenu();
        scene.setFill(new LinearGradient(0,0,0.8,1,true,
                CycleMethod.NO_CYCLE,
                new Stop(0,Color.web("#073596")),
                new Stop(1,Color.web("#34b6f0")),
                new Stop(2,Color.web("#34b6f0"))
                ));
//        login form////////////////////////////////////////
        Label WellcomeTitel = new Label("");
        VBox loginForm = new VBox(40);
        loginForm.getStyleClass().add("LoginForm");
        loginForm.setLayoutX(getCenter(600,300));
        loginForm.setLayoutY(getCenter(600,400));
        loginForm.setAlignment(Pos.TOP_CENTER);

        VBox inputContainer = new VBox(40);
        inputContainer.getStyleClass().add("inputContainer");
//        login lable////////////////////////////////////////
        Label LoginLable = new Label("LOGIN");
        LoginLable.getStyleClass().add("loginLable");



        VBox userNameContainer =new VBox(10);
        useNameLable= new Label(langList[0]);
        userName = new TextField();
        userName.getStyleClass().add("textInput");
        userName.setPromptText(langList[0]);
        userName.setOnKeyReleased(e->{
            message.setText("");

            UserName=userName.getText();
        });
        userNameContainer.getChildren().add(useNameLable);


        VBox passwordContainer =new VBox(10);
        passwordLable= new Label(langList[1]);
//        Password = new PasswordField();
////        Password.visibleProperty().bind();
//        Password.getStyleClass().add("textInput");
//        Password.setOnKeyReleased(e->{
//            message.setText("");
//            Passcode=Password.getText();
//        });
        passwordContainer.getChildren().add(passwordLable);




        PasswordField Password = new PasswordField();
        Password.getStyleClass().add("textInput");
        Password.setMaxWidth(255);
        Password.setPrefWidth(255);
//        Password.setPromptText(langList[1]);
        PasswordVisible = new TextField();
        PasswordVisible.getStyleClass().add("textInput");
        PasswordVisible.setMaxWidth(255);
        PasswordVisible.setPrefWidth(255);
        PasswordVisible.setOnKeyReleased(e->{
//            Password.setText("");
            Passcode=PasswordVisible.getText();
        });

        Password.managedProperty().bind(Password.visibleProperty());
//        Password.st
        Password.setOnKeyReleased(e->{
//            Password.setText("");
            Passcode=Password.getText();
        });
        delimageView.setOnMouseClicked(e->{
//                Password.setVisible(showPass_toggel);
//                Password.setManaged(showPass_toggel);
            delimageView.setImage(showPass_toggel?showPass:HidePass);
            showPass_toggel=!showPass_toggel;
            if(showPass_toggel){
                PassCotainer.getChildren().remove(0);
                PassCotainer.getChildren().add(0,PasswordVisible);
                PasswordVisible.setText(Passcode);
                PasswordVisible.requestFocus();

            }else {
                PassCotainer.getChildren().remove(0);
                PassCotainer.getChildren().add(0,Password);
                Password.setText(Passcode);
                Password.requestFocus();

            }

        });
        PassCotainer.getChildren().add(Password);
        PassCotainer.getChildren().add(delimageView);



















        CustomBtn LoginBtn = new CustomBtn(200,40,"LOGIN","CustomBtn");
        LoginBtn.getStyleClass().add("CustomBtns");
        LoginBtn.setOnAction(e-> {
            message.setText("");

//            controller.ShowStage("libraryApp");
            System.out.println("userName: " + UserName + " Passcode: " + Passcode);
            boolean loginStatus;
            if (!(UserName.isEmpty() && Passcode.isEmpty())) {
                try {
                    loginStatus = controller.Login(UserName, Passcode);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (loginStatus) {
                    Password.setText("");
                    userName.setText("");
                    librarian lib;
                    try {
                        lib = controller.getLib(UserName);
                        System.out.println("lib-============="+lib.getLibraryNum());
                        controller.setLibrarian_number(lib.getId());
                        System.out.println(lib.getLibraryNum());
                        controller.setLibrary_number(lib.getLibraryNum());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    LibraryApp libraryApp = new LibraryApp(controller, lib);
                    Passcode = "";
                    UserName = "";
                    controller.routes.addRout(libraryApp, "libraryApp");
                    controller.ShowStage("libraryApp");
                } else {
                    String english = "Password incorrect or user doesn't exist";
                    String french = "Mot de passe incorrect ou utilisateur inexistant";
                    if(controller.getLanguage().equals("English")){

                        message.setText(english);
                    }else
                        message.setText(french);
                    message.setTextFill(Color.RED);
                    message.setTextAlignment(TextAlignment.CENTER);
                }

            }else {
                String english = "Please compleat the form";
                String french = "S'il vous plaît complétez le formulaire";
                if(controller.getLanguage().equals("English")){

                message.setText(english);
                }else
                message.setText(french);
                message.setTextFill(Color.RED);
                message.setTextAlignment(TextAlignment.CENTER);
            }
        });

        userNameContainer.getChildren().add(userName);
        passwordContainer.getChildren().add(PassCotainer);
        inputContainer.getChildren().add(userNameContainer);
        inputContainer.getChildren().add(passwordContainer);
        inputContainer.getChildren().add(message);
        loginForm.getChildren().add(LoginLable);
        loginForm.getChildren().add(inputContainer);
        loginForm.getChildren().add(LoginBtn);
//        loginForm.getChildren().add(Password);
        root.getChildren().add(dropMenu);
        root.getChildren().add(loginForm);

        this.setScene(scene);

    }

    public String[] getLangList() {
        return langList;
    }

    public void setLangList(String[] langList) {
        this.langList = langList;
    }

    public double getCenter(double x, double y){
        return (x/2-y/2);
    }

}
