package com.example.librarymanagement.Scenes;

import DatabaseManagment.Librarian.librarian;
import com.example.librarymanagement.Controller;
import com.example.librarymanagement.LoginPage;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

public class UserMnager extends VBox {
    int id;
    Pane PassCotainer;
    String name;
    String last_name;
    String email;
    librarian user;
    String password;
    int library_num;
    int is_admin;
    librarian user1;
    Image showPass;
    Image HidePass;
    String Hide_pass;
    boolean showPass_toggel= false ;
    String[] langList;
    String[] messagLag;
    TextField PasswordVisible;
    public UserMnager(Controller controller,librarian user){
        if(controller.getLanguage().equals("English")){
            langList = new String[]{"First Name", "Last Name", "Email", "Password", "Edit","ADD","Clear"};
            messagLag=  new String[]  {"secc: add acount ","err:somthng went wrong","duplicated Email","Update secc","Err : somthing went wrong "};

        } else {
            langList = new String[]{"Prénom", "Nom de famille", "E-mail", "Mot de passe", "Éditer","Ajouter","réinitialiser"};
            messagLag=new String[]  {"Succès: compte ajouté", "Erreur: quelque chose s'est mal passé", "Email en double", "Mise à jour réussie", "Erreur: quelque chose s'est mal passé"};
        }



        this.user =user;
        if (user ==null){
            user =new librarian(-1,"No user","cut out from a tree","NoLogin",-1,0);
        }
        id= user.getId();
        library_num=user.getLibraryNum();
        is_admin=user.isIs_admin();
        setSpacing(10);
        setPrefWidth(1000);

        String Css= LoginPage.class.getResource("style.css").toExternalForm();
        Image userImage = new Image("G:\\coding\\java\\LibraryManagement\\src\\main\\resources\\pics\\user.png");
        ImageView imageView = new ImageView();
        imageView.setImage(userImage);
        imageView.setFitWidth(148);
        imageView.setFitHeight(148);
        getStylesheets().add(Css);
        Label ChangeImage  = new Label("Change Picture");
        ChangeImage.setTextFill(Color.web("#5F9BF5"));


        VBox ImageContainer = new VBox(10);
        ImageContainer.setAlignment(Pos.CENTER);
        ImageContainer.getChildren().add(imageView);
//        ImageContainer.getChildren().add(ChangeImage);

        Label message = new Label("");

        Label NameLable =new Label(langList[0]);
        Label LastNameLable =new Label(langList[1]);
        Label EmailLable =new Label(langList[2]);
        Label PassLable =new Label(langList[3]);


        TextField Name = new TextField(user.getName());

        Name.setOnKeyReleased(e->{
            name=Name.getText();
        });
        TextField LastName = new TextField(user.getFamilyName());
        LastName.setOnKeyReleased(e->{
            last_name=LastName.getText();
        });
        TextField Email = new TextField(user.getLogin());
        Email.setDisable(true);
        Email.setOnKeyReleased(e->{
            email=Email.getText();
        });
        int Plength = controller.getPassword().length();
        Hide_pass = "";
        for (int i = 0; i<Plength;i++){
            Hide_pass+="* ";
        }
        PassCotainer=new Pane();
        PassCotainer.setMaxWidth(200);
        PassCotainer.setPrefWidth(200);
        FileInputStream delS;
        try {
            URL delsUrl= new URL(LoginPage.class.getResource("/pics/view.png").toExternalForm());
            delS = new FileInputStream(delsUrl.getPath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        FileInputStream hideS;
        try {
            URL delsUrl= new URL(LoginPage.class.getResource("/pics/hide.png").toExternalForm());

            hideS = new FileInputStream(delsUrl.getPath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        showPass =new Image(delS);
        HidePass =new Image(hideS);

        ImageView delimageView =new ImageView(showPass);
        delimageView.setFitWidth(25);
        delimageView.setFitHeight(25);
        delimageView.setTranslateX(170);
        delimageView.setTranslateY(3);
        delimageView.setOnMouseEntered(e->{
            delimageView.setCursor(Cursor.HAND);
        });

//        Button showPass=new Button("show");
//        showPass.setPrefWidth(20);
//        showPass.setPrefHeight(20);
//        showPass.getStyleClass().add("CustomBtn");
        PasswordField Password = new PasswordField();
        Password.setText(controller.getPassword());
        PasswordVisible = new TextField(controller.getPassword());
        PasswordVisible.setOnKeyReleased(e->{
//            Password.setText("");
            password=PasswordVisible.getText();
        });
        Password.setMaxWidth(200);
        Password.setPrefWidth(200);

        PasswordVisible.setMaxWidth(200);
        PasswordVisible.setPrefWidth(200);
        Password.managedProperty().bind(Password.visibleProperty());
//        Password.st
        Password.setOnKeyReleased(e->{
//            Password.setText("");
            password=Password.getText();
        });
        delimageView.setOnMouseClicked(e->{
//                Password.setVisible(showPass_toggel);
//                Password.setManaged(showPass_toggel);
                delimageView.setImage(showPass_toggel?showPass:HidePass);
            showPass_toggel=!showPass_toggel;
            if(showPass_toggel){
                PassCotainer.getChildren().remove(0);
                PassCotainer.getChildren().add(0,PasswordVisible);
                PasswordVisible.setText(password);
                PasswordVisible.requestFocus();


            }else {
                PassCotainer.getChildren().remove(0);
                PassCotainer.getChildren().add(0,Password);
                Password.setText(password);
                Password.requestFocus();
            }

        });
        PassCotainer.getChildren().add(Password);
        PassCotainer.getChildren().add(delimageView);
        name=Name.getText();
        last_name=LastName.getText();
        email=Email.getText();
        password=Password.getText();

        VBox INfoContainer = new VBox(10);
        INfoContainer.setMaxWidth(200);
        INfoContainer.setPrefWidth(200);

//        INfoContainer.setAlignment(Pos.CENTER);
        INfoContainer.getChildren().add(NameLable);
        INfoContainer.getChildren().add(Name);
        INfoContainer.getChildren().add(LastNameLable);
        INfoContainer.getChildren().add(LastName);
        INfoContainer.getChildren().add(EmailLable);
        INfoContainer.getChildren().add(Email);
        INfoContainer.getChildren().add(PassLable);
        INfoContainer.getChildren().add(PassCotainer);
        HBox BtnContainer = new HBox(20);
        BtnContainer.setMaxWidth(250);
        BtnContainer.setPrefWidth(250);
        Button submit =new Button(langList[4]);
        submit.setPrefSize(103,31);
        submit.getStyleClass().add("CustomBtn");
        BtnContainer.getChildren().add(submit);
        BtnContainer.setAlignment(Pos.CENTER);
        if(user.isIs_admin()==1){
            Button Clear =new Button(langList[6]);
            Clear.setPrefSize(103,31);
            Clear.getStyleClass().add("CustomBtn");
            Clear.setOnAction(e->{
                Name.setText("");
                LastName.setText("");
                Email.setText("");
                Password.setText("");
                PasswordVisible.setText("");
                password="";
                Email.setDisable(false);
                submit.setText(langList[5]);
            });
            BtnContainer.getChildren().add(Clear);

        }


        submit.setOnAction(e->{
            user1 = new librarian(id,name,last_name,email,library_num,is_admin);

            if(this.user.isIs_admin()==1&&submit.getText().equals(langList[5])){
                boolean email_check =controller.CheckEmail(user1);
                System.out.println("email"+email_check);
                if(!email_check){
                    message.setText(messagLag[2]);
                    message.setTextFill(Color.RED);
                }else {
                    boolean res= controller.AddLibrarian(user1,password);
                    if(res){
                        message.setText(messagLag[0]);
//                        message.setTextFill(Color.RED);
                        message.setTextFill(Color.GREEN);
                    }else{
                        message.setText(messagLag[1]);
                        message.setTextFill(Color.RED);
//                        message.setTextFill(Color.GREEN);
                    }
                }

            }else {

            if (id==-1){
                System.out.println("no user");
                message.setText("err: No user");
                message.setTextFill(Color.RED);
//                message.setTextFill(Color.GREEN);
                return;

            }
                boolean res = controller.Edituser(this.user,user1,password);
                if (res){
                    message.setText(messagLag[3]);
//                    message.setTextFill(Color.RED);
                    message.setTextFill(Color.GREEN);

                }else {
                    message.setText(messagLag[4]);
                    message.setTextFill(Color.RED);
//                    message.setTextFill(Color.GREEN);

                }

            System.out.println(name+" "+last_name+" "+email+" "+password);
            }
        });

        setAlignment(Pos.CENTER);
        getChildren().add(ImageContainer);
        getChildren().add(INfoContainer);
        getChildren().add(message);
        getChildren().add(BtnContainer);
    }
}
