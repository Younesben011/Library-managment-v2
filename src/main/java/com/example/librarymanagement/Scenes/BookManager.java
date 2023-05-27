package com.example.librarymanagement.Scenes;

import DatabaseManagment.Author.Author;
import DatabaseManagment.Books.Book;
import DatabaseManagment.Librarian.LibrarianImp;
import DatabaseManagment.Librarian.librarian;
import com.example.librarymanagement.Controller;
import com.example.librarymanagement.LoginPage;
import components.CustomTable;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class BookManager extends VBox {
     Label PinBook;
    HBox PinContainer;
    Button submit;
    HBox editContainer;
    HBox deleteContainer;
    Button reset;
    TextField bokId;
    TextField bookName;
    TextField bookAuth;
    TextField bookQuantity;
    TextField bookEditor;
    String bok_id="";
    String book_name="";
    String book_auth="";
    String book_quantity="";
    String book_editor="";
    Label message;
    CustomTable table ;
    Button AddAuth;
    HBox AuthorSection;
    Pane container;
    boolean PinToggel;
    String addStyle="-fx-background-color: white;" +
            "-fx-pref-width: 300;" +
            "-fx-pref-height: 250;" +
            "-fx-background-radius: 10;";

    Label AddBook;
    Pane PopupMessage ;
    Label titLable;
    Label DeleteBook;
    Label EditBook;
    String[] messageFrench = {"Impossible de supprimer ce livre tant que toutes les \n"+"copies n'ont pas été retournées à la bibliothèque",
            "Une erreur est survenue lors de l'ajout de l'auteur","Nouvel auteur ajouté","Erreur : veuillez remplir le formulaire",
            "Erreur : ce livre existe déjà","Nouveau livre ajouté avec succès","Erreur : l'auteur n'existe pas",
            "Erreur : veuillez remplir le formulaire","Mise à jour réussie","Mise à jour échouée","Erreur : l'auteur n'existe pas"
    };
    String[] messageEnglish = {"cannot delete this book before All the \n"+"copies returns  to the library ",
            "Error occured in the author adding process","new author added","Error : please complete the form ",
            "Error : this book is already exist ","new Book added successfully","Error : author doesn't exist",
            "Error : please complete the form ","Update succ","Update get wrong","Error : author doesn't exist"
    };
    String[] langMessage;

    String[] english = {"ADD BOOK","Search","Add book","Submit","Delete","Edit","EDIT BOOK","Pin","Book Id","Enter Book Id","Book Name",
            "Enter Book Name","Book Author","Enter Book Author","Add Author","Book Quantity","Enter Book Quantity","Book Editor","Enter Book Editor","Reset"};

    String[] french = {"AJOUTER UN LIVRE", "Recherche", "Ajouter un livre", "Soumettre", "Supprimer", "Modifier", "MODIFIER LE LIVRE", "Épingle", "Identifiant du livre", "Entrer l'identifiant du livre", "Nom du livre",
            "Entrer le nom du livre", "Auteur du livre", "Entrer l'auteur du livre", "Ajouter un auteur", "Quantité de livres", "Entrer la quantité de livres", "Éditeur du livre", "Entrer l'éditeur du livre","Réinitialiser"};

    String[] langList ;
    String Css= LoginPage.class.getResource("Components.css").toExternalForm();

    public void hideMessage(){
        message.setText("");
    }

    public BookManager(Controller controller, double width, librarian user) throws SQLException {
        if(controller.getLanguage().equals("English")){
            langList=english;
            langMessage=messageEnglish;
        }else
            langList=french;
            langMessage=messageFrench;
        getStylesheets().add(Css);

        PinToggel=false;
        PopupMessage=new Pane();
        Label popup_message =new Label();
//        PopupMessage.getChildren().add(popup_message);
//        PopupMessage.getStyleClass().add("PopupMessage");
//        PopupMessage.setPrefWidth(100);
//        PopupMessage.setPrefHeight(100);
//        PopupMessage.setBackground(Background.fill(Color.BLACK));
//        PopupMessage.setTranslateX(500);
//        PopupMessage.setTranslateY(150);
        List<Book> books_lsit =controller.getAllBooks();
        container=new Pane();
        setPrefWidth(width-200.0);

//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        Future<String> future = (Future<String>) executor.submit(()->{
////            hideMessage();
////            System.out.println("sss");
//        });
        Label label = new Label("Book Manager");
        VBox book_form = new VBox(10);
        book_form.setPrefWidth(200);
        book_form.setAlignment(Pos.CENTER_LEFT);

        HBox title = new HBox();
        title.setAlignment(Pos.CENTER);
        title.setPrefWidth(300);
         titLable = new Label(langList[0]);
        titLable.setStyle("-fx-font-size: 30;" +
                "-fx-text-fill: #000000;" +
                "-fx-font-weight: bold");

        title.getChildren().add(titLable);
        HBox buttons= new HBox(10);

        HBox options =new HBox(10);
        options.getStyleClass().add("options");
        options.setPrefWidth(width-200);
        Label messageOp = new Label();
        messageOp.setMaxWidth(300);
        messageOp.setPrefWidth(300);
        TextField search = new TextField();
        search.setOnKeyReleased(e->{
            System.out.println(search.getText());
            List<Book> filtered_list = new ArrayList<Book>();
            for (Book book:
                 books_lsit) {
                int count =0;
                for (int i=0;i<search.getText().length()&&search.getText().length()<=book.getBookName().length();i++){
                    if (search.getText().charAt(i)==book.getBookName().charAt(i)){
                        count++;
                    }
                }
                if (count==search.getText().length())
                    filtered_list.add(book);
                else {
                    count=0;
                    for (int i=0;i<search.getText().length()&&search.getText().length()<=book.getBookId().length();i++){
                        if (search.getText().charAt(i)==book.getBookId().charAt(i)){
                            count++;
                        }
                    }
                    if (count==search.getText().length())
                        filtered_list.add(book);
                }
            }
            table.UpdateTable(controller,filtered_list,book_form);
        });
        if(controller.getLanguage().equals("English"))
            search.setTranslateX(150);
        else
            search.setTranslateX(30);
        search.setPromptText(langList[1]);
        search.getStyleClass().clear();
        search.getStyleClass().add("Search");
        HBox addContainer =new HBox(2);
        addContainer.setAlignment(Pos.CENTER);
        AddBook = new Label(langList[2]);
        Label Plus = new Label("+");
        AddBook.setAlignment(Pos.CENTER);
        Plus.setAlignment(Pos.CENTER);
        Plus.setStyle("-fx-text-fill: #07c907;" +
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-translate-y: -2");
        addContainer.getChildren().add(Plus);
        addContainer.getChildren().add(AddBook);
        AddBook.getStyleClass().add("AddBook");
        AddBook.setOnMouseClicked(e->{
            editContainer.setDisable(true);
            deleteContainer.setDisable(true);
            titLable.setText(langList[0]);
            for (int i=1;i<book_form.getChildren().size()-2;i++){
                if(i%2==0){
                    TextField textField= (TextField)book_form.getChildren().get(i);
                    textField.setText("");}
            }
            reset.setVisible(false);
            submit.setText(langList[3]);
            AuthorSection.setStyle(addStyle+"-fx-effect: dropshadow(three-pass-box, rgba(33,33,33,0.11), 10, 0, 0, 0);");
//            AuthorSection.setEffect();
            table.setEffect(new GaussianBlur());

            container.getChildren().add(AuthorSection);
        });

         DeleteBook = new Label(langList[4]);
        DeleteBook.setAlignment(Pos.CENTER);
         deleteContainer = new HBox(2);
         deleteContainer.setAlignment(Pos.CENTER);
        FileInputStream delS;
        try {
            URL delsUrl= new URL(LoginPage.class.getResource("/pics/minus.png").toExternalForm());
            delS = new FileInputStream(delsUrl.getPath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Image delimage =new Image(delS);
        ImageView delimageView =new ImageView(delimage);
        delimageView.setFitWidth(14);
        delimageView.setFitHeight(14);
         Label del = new Label("_");
         del.setAlignment(Pos.CENTER);
        del.setStyle("-fx-text-fill: #e01f1f;" +
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold");
        deleteContainer.getChildren().add(delimageView);
        deleteContainer.getChildren().add(DeleteBook);



        deleteContainer.setDisable(true);
        DeleteBook.setOnMouseClicked(e->{
            boolean res= controller.deleteBook(controller,book_form);
            if (!res){
                messageOp.setText(langMessage[0]);
                messageOp.setTextFill(Paint.valueOf("#fc3232"));

            }});
        
        DeleteBook.getStyleClass().add("AddBook");
         editContainer = new HBox(2);
        editContainer.setAlignment(Pos.CENTER);
        FileInputStream fileInputStream;
        try {
            URL delsUrl= new URL(LoginPage.class.getResource("/pics/pencil.png").toExternalForm());
            fileInputStream = new FileInputStream(delsUrl.getPath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Image image =new Image(fileInputStream);
        ImageView imageView =new ImageView(image);
        imageView.setFitHeight(14);
        imageView.setFitWidth(14);

        EditBook = new Label(langList[5]);

        editContainer.getChildren().add(imageView);

        editContainer.getChildren().add(EditBook);
        editContainer.setDisable(true);
        EditBook.setOnMouseClicked(e->{
            titLable.setText(langList[6]);
            AuthorSection.setStyle(addStyle+"-fx-effect: dropshadow(three-pass-box, rgba(33,33,33,0.11), 10, 0, 0, 0);");
//            AuthorSection.setEffect();
            table.setEffect(new GaussianBlur());
            container.getChildren().add(AuthorSection);
        });
        EditBook.getStyleClass().add("AddBook");

        PinBook = new Label(langList[7]);
        PinBook.getStyleClass().add("AddBook");
        PinBook.setAlignment(Pos.CENTER);
        PinContainer = new HBox(2);
        PinContainer.setAlignment(Pos.CENTER);
        FileInputStream PinF;
        try {
            URL delsUrl= new URL(LoginPage.class.getResource("/pics/pin.png").toExternalForm());
            PinF = new FileInputStream(delsUrl.getPath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Image Pinimage =new Image(PinF);
        ImageView PinimageView =new ImageView(Pinimage);
        PinimageView.setFitWidth(14);
        PinimageView.setFitHeight(14);
        PinContainer.getChildren().add(PinimageView);
        PinContainer.getChildren().add(PinBook);

        PinContainer.setDisable(true);
        PinBook.setOnMouseClicked(e->{
            if(!PinToggel){
                controller.setPinedBook(true);
                PinContainer.setDisable(true);}
            else
                controller.setPinedBook(false);
            PinToggel=!PinToggel;

        });




        options.getChildren().add(addContainer);
        options.getChildren().add(editContainer);
        options.getChildren().add(deleteContainer);
        options.getChildren().add(PinContainer);
        options.getChildren().add(messageOp);
        options.getChildren().add(search);



        table = new CustomTable(controller,width,options,books_lsit,book_form);
        message = new Label();
        Label idLable=new Label(langList[8]);
        bokId = new TextField();
        bokId.getStyleClass().add("textInput");
        bokId.setPromptText(langList[9]);
        bokId.setOnKeyReleased(e->{
            bok_id=bokId.getText();
        });
        bokId.setOnAction(e->{
            bokId.setBorder(Border.stroke(Paint.valueOf("#FF000000")));

        });
        Label NameLable=new Label(langList[10]);
        bookName = new TextField();
        bookName.getStyleClass().add("textInput");
        bookName.setPromptText(langList[11]);
        bookName.setOnKeyReleased(e->{
            book_name=bookName.getText();
        });


        AuthorSection= new HBox(10);

        AuthorSection.setStyle(addStyle);
        AuthorSection.setLayoutX(200);
        AuthorSection.setLayoutY(20);
        AuthorSection.setAlignment(Pos.CENTER);
        bookAuth = new TextField();
        bookAuth.getStyleClass().add("textInput");
        Label AuLable=new Label(langList[12]);
        bookAuth.setPromptText(langList[13]);
        bookAuth.setOnKeyReleased(e->{
            book_auth=bookAuth.getText();

        });
        AddAuth = new Button(langList[14]);
//        AddAuth.setVisible(false);
        AddAuth.setOnAction(e->{

            boolean res =controller.addAuthor(book_auth);
            if (!res){
                message.setText(langMessage[1]);
            }else
                message.setTextFill(Paint.valueOf("#84EE60FF"));
                message.setText(langMessage[2]);
            buttons.getChildren().remove(AddAuth);

        });
        table.setOnMouseClicked(e->{

            container.getChildren().remove(AuthorSection);
            table.setEffect(null);
        });
        bookQuantity = new TextField();
        Label quLable=new Label(langList[15]);

        bookQuantity.getStyleClass().add("textInput");
        bookQuantity.setPromptText(langList[16]);
        bookQuantity.setOnKeyReleased(e->{
            book_quantity=bookQuantity.getText();
        });

        bookEditor = new TextField();
        Label EdLable=new Label(langList[17]);
        bookEditor.getStyleClass().add("textInput");
        bookEditor.setPromptText(langList[18]);
        bookEditor.setOnKeyReleased(e->{
            book_editor=bookEditor.getText();
        });

        submit = new Button(langList[3]);
         reset = new Button(langList[19]);
        reset.setVisible(false);
        reset.setOnAction(e->{
            titLable.setText(langList[0]);
            for (int i=1;i<book_form.getChildren().size()-2;i++){
                if(i%2==0){
                    TextField textField= (TextField)book_form.getChildren().get(i);
                    textField.setText("");}
            }
                submit.setText(langList[3]);
                reset.setVisible(false);

        });
        submit.setOnAction(e->{
            List<Book> books_lsit2 = null;
            try {
                books_lsit2 = controller.getAllBooks();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (submit.getText().equals(langList[3])){
            Author author=null;
            if (bok_id.isEmpty()||book_name.isEmpty()||book_auth.isEmpty()||book_quantity.isEmpty()){
                message.setText(langMessage[3] );
                message.setTextFill(Paint.valueOf("#FF0000FF"));
                return;
            }
            for (Book book:
                    books_lsit2) {
                if (book.getBookId().equals(bok_id)){
                    message.setText(langMessage[4]);
                    bokId.setBorder(Border.stroke(Paint.valueOf("#FF0000FF")));
                    message.setTextFill(Paint.valueOf("#FF0000FF"));
                    return;
                }
            }
            try {
                author = controller.getAuthor(book_auth);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (!(author ==null)){
                Book  book =new Book(bok_id,book_name,book_editor,Integer.parseInt(book_quantity));
                boolean state= controller.AddBook(book,author );
                if (state)
                    message.setText(langMessage[5] );
                    message.setTextFill(Paint.valueOf("#84EE60FF"));
                for (int i =1;i<book_form.getChildren().size()-2;i++){
                    if(i%2==0){
                        TextField textField= (TextField)book_form.getChildren().get(i);
                        textField.setText("");}
                }

                try {
                    table.UpdateTable(controller,controller.getAllBooks(),book_form);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
//                table.addBookRow(book,controller,book_form);
                container.getChildren().remove(AuthorSection);
                table.setEffect(null);


            }
            else {
                    message.setText(langMessage[6]);
                    message.setTextFill(Paint.valueOf("#FF0000FF"));
                buttons.getChildren().add(AddAuth);



            }}
            else {
                String prev_id = bokId.getText();
                bok_id=bokId.getText();
                book_name=bookName.getText();
                book_auth=bookAuth.getText();
                book_quantity=bookQuantity.getText();
                book_editor=bookEditor.getText();
                Author author=null;
                if (bok_id.isEmpty()||book_name.isEmpty()||book_auth.isEmpty()||book_quantity.isEmpty()){
                    message.setText(langMessage[7] );
                    message.setTextFill(Paint.valueOf("#FF0000FF"));
                    return;
                }
                try {
                    author = controller.getAuthor(book_auth);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (!(author ==null)) {
                    Book book = new Book(bok_id, book_name, book_editor, Integer.parseInt(book_quantity));
                    boolean update_state = controller.updateBook(book, prev_id);
                    if (update_state) {
                        message.setText(langMessage[8]);
                        message.setTextFill(Paint.valueOf("#84EE60FF"));
                        for (int i = 1; i < book_form.getChildren().size() - 2; i++)
                        {
                            if (i % 2 == 0)
                            {
                                TextField textField = (TextField) book_form.getChildren().get(i);
                                textField.setText("");
                            }
                        }
                        try
                        {
                            table.UpdateTable(controller, controller.getAllBooks(), book_form);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                    } else {
                        message.setText(langMessage[9]);

                    }

                } else {
                    message.setText(langMessage[10]);
                    message.setTextFill(Paint.valueOf("#FF0000FF"));
                    buttons.getChildren().add(AddAuth);

                }
            }});
        ScrollPane scrollPane =new ScrollPane();
        scrollPane.setStyle("-fx-focus-color:transparent;-fx-border-width: 0");
        scrollPane.setPrefSize(width-200, 500);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setContent(table);
//        scrollPane.setPrefSize(width,600);
        scrollPane.setFitToHeight(true);
        book_form.getChildren().add(title);
        book_form.getChildren().add(idLable);
        book_form.getChildren().add(bokId);
        book_form.getChildren().add(NameLable);
        book_form.getChildren().add(bookName);
        book_form.getChildren().add(AuLable);
        book_form.getChildren().add(bookAuth);
        book_form.getChildren().add(EdLable);
        book_form.getChildren().add(bookEditor);
        book_form.getChildren().add(quLable);
        book_form.getChildren().add(bookQuantity);
        buttons.getChildren().add(submit);
        buttons.getChildren().add(reset);
        book_form.getChildren().add(buttons);
        book_form.getChildren().add(message);

        AuthorSection.getChildren().add(book_form);

        container.getChildren().add(scrollPane);
//        container.getChildren().add(PopupMessage);
        getChildren().add(options);
        getChildren().add(container);

    }
}
