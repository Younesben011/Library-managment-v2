package com.example.librarymanagement.Scenes;

import DatabaseManagment.Books.Book;
import DatabaseManagment.Issue.IssueBook;
import DatabaseManagment.Librarian.librarian;
import DatabaseManagment.Members.Member;
import com.example.librarymanagement.Controller;
import com.example.librarymanagement.LoginPage;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.function.LongUnaryOperator;

public class BookIssue extends VBox {
    static Label NOBook;
    static Label NoMem;
    static HBox btnContainer;
    static  Label BookCat;
    private static Label Type;
    TextField BookId;
     TextField MemberId;
    static String[] infoList;
    String[] en_types={"dissertation","book","review"};
    String[] fr_types={"mémoire","livre","revue"};
    static String[] fr_categ={"master","lisence"};
    static String[] types;

    static Label pinedBookLable;
    static Label pinedMemLable;
    boolean first_time;
    static DatePicker Dateissue;
    static DatePicker Returnissue;
    static int member_id;
    static String book_id="";
    static Member member=null;
    static Book book=null;
    static Label message;
    static Label _MemberID = new Label();
    static Label MemberFirstName = new Label();
    static Label MemberLastName = new Label();
    static Label MemberAddress = new Label();
    static Label _BookID= new Label();
    static Label BookName= new Label();
    static Label BookCopies = new Label();
    static Label AviliableBooks = new Label();
    static Label BookEditor = new Label();
    static Label BookAuthor=new Label();

    static VBox MemberInfo;
    static String[] messageList;
    static VBox InfoContainer =new VBox();

    static VBox BookInfo ;
    LocalDate issue_date=null;
    LocalDate return_date=null;
    static int copy_id;
    static  int submit_count=0 ;
    static VBox BookInfocontainer;
    static VBox MemInfocontainer;

        static String language ;
        static String[] detailList;

    String Css= LoginPage.class.getResource("style.css").toExternalForm();

    static Button Order ;
    public static  boolean bookController(Controller controller,TextField BookId){

        book_id=BookId.getText();
        book=controller.getBookByID(book_id);
        if (book!=null){
            copy_id= controller.bookAvailableCheck(book_id);
            if (copy_id==-1){
                btnContainer.getChildren().add(Order);
                message.setText(messageList[0]);
                if(message.getStyleClass().get(1).equals("message_done")){
                    message.getStyleClass().remove(0);
                    message.getStyleClass().add("message_err");
                }
//                   BookAuthor=book.
            }else if (copy_id==0){
                message.setText(messageList[1]);
                if(message.getStyleClass().get(1).equals("message_done")){
                    message.getStyleClass().remove(0);
                    message.getStyleClass().add("message_err");
                }
            }else {
                message.setText("");
                int aviliable_books = controller.getAviliableBooks(book_id);
                int Author_id = controller.getWrite(book_id).getAuthor_id();

                BookInfocontainer.setTranslateX(20);
                BookInfocontainer.getChildren().clear();
                pinedBookLable.setDisable(true);


                _BookID.setText(book.getBookId());
                _BookID.getStyleClass().add("CustomText");
                BookName.setText(book.getBookName());
                BookName.getStyleClass().add("CustomText");
                BookCopies.setText(String.valueOf(book.getBookQuantity()));
                BookCopies.getStyleClass().add("CustomText");
                AviliableBooks.setText(String.valueOf(aviliable_books));
                AviliableBooks.getStyleClass().add("CustomText");
                ///////////////////////////// edit ////////////////////////////////////////////////////////


                BookAuthor.setText(controller.getAuthorById(Author_id).getAuthor_name());
                BookAuthor.getStyleClass().add("CustomText");

                Label bookIdLabel = new Label(infoList[0]);
                bookIdLabel.getStyleClass().add("CustomLable");
                Label  bookNameLable= new Label(infoList[1]);
                bookNameLable.getStyleClass().add("CustomLable");
                Label  bookCopies= new Label(infoList[2]);
                bookCopies.getStyleClass().add("CustomLable");
                Label  bookAvCopies= new Label(infoList[3]);
                bookAvCopies.getStyleClass().add("CustomLable");
                Label  bookAuthorLable= new Label(infoList[4]);
                bookAuthorLable.getStyleClass().add("CustomLable");
                Label typeLable = new Label("Type:");
                typeLable.getStyleClass().add("CustomLable");
                Type = new Label(types[book.getType_int()-1]);
                Type.getStyleClass().add("CustomText");
                HBox bookTypeBox= new HBox(typeLable,Type);
                bookTypeBox.setSpacing(10);

                HBox bookIdBox= new HBox(bookIdLabel,_BookID);
                bookIdBox.setSpacing(10);
                HBox  bookNameBox= new HBox(bookNameLable,BookName);
                bookNameBox.setSpacing(10);
                HBox  bookCopiesBox= new HBox(bookCopies,BookCopies);
                bookCopiesBox.setSpacing(10);
                HBox  bookAvCopiesBox= new HBox(bookAvCopies, AviliableBooks);
                bookAvCopiesBox.setSpacing(10);
                HBox  bookAuthorBox= new HBox(bookAuthorLable,BookAuthor);
                bookAuthorBox.setSpacing(10);




                BookInfocontainer.getChildren().add(bookIdBox);
                BookInfocontainer.getChildren().add(bookNameBox);
                BookInfocontainer.getChildren().add(bookCopiesBox);
                BookInfocontainer.getChildren().add(bookAvCopiesBox);
                BookInfocontainer.getChildren().add(bookTypeBox);
                BookInfocontainer.getChildren().add(bookAuthorBox);
                if(book.getType_int()==2){
                    Label bookEditorLable  = new Label(infoList[5]);
                    bookEditorLable.getStyleClass().add("CustomLable");
                    BookEditor.setText(book.getBookEditor());
                    BookEditor.getStyleClass().add("CustomText");
                    HBox bookEditorBox= new HBox(bookEditorLable,BookEditor);
                    bookEditorBox.setSpacing(10);
                    BookInfocontainer.getChildren().add(bookEditorBox);
                }else if(book.getType_int()==1){
                    BookCat=new Label();
                    Label bookCatLable = new Label(infoList[10]);
                    bookCatLable.getStyleClass().add("CustomLable");
                    BookCat.setText(fr_categ[book.getCat()-1]);
                    BookCat.getStyleClass().add("CustomText");
                    HBox bookCatBox= new HBox(bookCatLable,BookCat);
                    bookCatBox.setSpacing(10);
                    BookInfocontainer.getChildren().add(bookCatBox);
                }



                return true;

            }
        }else{
            message.setText(messageList[2]);
            if(message.getStyleClass().get(1).equals("message_done")){
                message.getStyleClass().remove(0);
                message.getStyleClass().add("message_err");
            }
        }
        return false;
    }
    public static  boolean memberController(Controller controller,TextField MemberId){
        Member member1=null;
        if (!MemberId.getText().equals(""))
            member1=controller.MemberSearch(Integer.parseInt(MemberId.getText()));
        if(member1!=null&&member1.getId_sn()==2){
            message.setText(messageList[8]);
            if(message.getStyleClass().get(1).equals("message_done")){
                message.getStyleClass().remove(0);
                message.getStyleClass().add("message_err");
            }
            return false;
        }
        if(MemberId.getText().equals("")){
            message.setText("");
            if(MemInfocontainer.getChildren().size()>1){
            MemInfocontainer.getChildren().clear();
            pinedMemLable.setDisable(false);
            MemInfocontainer.getChildren().add(NoMem);
            MemInfocontainer.setTranslateX(0);
            }

        }else {

        boolean typeCheck= false;
        try {
            member_id=Integer.parseInt(MemberId.getText());
            typeCheck=true;

        }catch (Exception ex){
            message.setText(messageList[3]);
            if(message.getStyleClass().get(1).equals("message_done")){
                message.getStyleClass().remove(0);
                message.getStyleClass().add("message_err");
            }
        }
        if (typeCheck){
            boolean issue_check = false;
            member=controller.MemberSearch(member_id);
            if (member!=null){
                IssueBook issueBook=controller.getIssueBookByMember(member_id);
                System.out.println("type"+member.getType());
                if(member.getType()==1){
                    Dateissue.setValue(LocalDate.now());
                    Returnissue.setValue(LocalDate.now().plusDays(7));
//                    ====================================================
                    System.out.println("student -----");
                    if (issueBook==null||issueBook.getReal_return_date()!=null)
                        issue_check=true;
                }
                else{
                    System.out.println("teacher -----");
                    Dateissue.setValue(LocalDate.now());
                    Returnissue.setValue(LocalDate.now().plusDays(14));

                    List<IssueBook> issueBooks =controller.getIssueBooksByMember(member_id);
                    System.out.println("size === "+issueBooks.size());
                    int issue_number = issueBooks.size();
                    if(issue_number<3)
                        issue_check=true;
                    else {
                        message.setText(messageList[4]);
                        if(message.getStyleClass().get(1).equals("message_done")){
                            message.getStyleClass().remove(0);
                            message.getStyleClass().add("message_err");
                        }
                        return false ;
                    }
                }
                if (issue_check){
                    System.out.println("user clear");
                    pinedMemLable.setDisable(true);


                    MemInfocontainer.getChildren().clear();
                    MemInfocontainer.setTranslateX(20);
//                    Label Memberifno = new Label("Member Details");

//                    MemberInfo.getStyleClass().add("infoBox");
//                    MemberInfo.setPrefHeight(100);
                    _MemberID.setText(String.valueOf(member.getMember_id()));
                    _MemberID.getStyleClass().add("CustomText");
                    MemberFirstName.setText(member.getMember_Firstname());
                    MemberFirstName.getStyleClass().add("CustomText");
                    MemberLastName.setText(member.getMember_Lastname());
                    MemberLastName.getStyleClass().add("CustomText");
                    MemberAddress.setText(member.getAddress());
                    MemberAddress.getStyleClass().add("CustomText");

                    Label memberIdLabel = new Label(infoList[6]);
                    memberIdLabel.getStyleClass().add("CustomLable");
                    Label firstNameLabel = new Label(infoList[7]);
                    firstNameLabel.getStyleClass().add("CustomLable");
                    Label lastNameLabel = new Label(infoList[8]);
                    lastNameLabel.getStyleClass().add("CustomLable");
                    Label addressLabel = new Label(infoList[9]);
                    addressLabel.getStyleClass().add("CustomLable");

                    HBox memberIdHBox = new HBox(memberIdLabel,_MemberID);
                    memberIdHBox.setSpacing(10);
                    HBox firstNameHBox = new HBox(firstNameLabel,MemberFirstName);
                    firstNameHBox.setSpacing(10);
                    HBox lastNameHBox = new HBox(lastNameLabel,MemberLastName);
                    lastNameHBox.setSpacing(10);
                    HBox addressHBox = new HBox(addressLabel,MemberAddress);
                    addressHBox.setSpacing(10);

                    MemInfocontainer.getChildren().add(memberIdHBox);

                    MemInfocontainer.getChildren().add(firstNameHBox);

                    MemInfocontainer.getChildren().add(lastNameHBox);

                    MemInfocontainer.getChildren().add(addressHBox);


                    return true;
                }else{
                    message.setText(messageList[4]);
                    if(message.getStyleClass().get(1).equals("message_done")){
                        message.getStyleClass().remove(0);
                        message.getStyleClass().add("message_err");
                    }
                }

            }else{
                message.setText(messageList[5]);
                if(message.getStyleClass().get(1).equals("message_done")){
                    message.getStyleClass().remove(0);
                    message.getStyleClass().add("message_err");
                }
            }}}
        return false;
    }


    public BookIssue(Controller controller, double width, librarian user){
        language = controller.getLanguage();

        if (language.equals("English")) {
            types=en_types;
            infoList = new String[]{"Book ID:","Book title:","Copie's Count:","Aviliables Copies:","Book Author:","Book Editor:",
                    "Member ID:","First Name:","Last Name:", "Address:","category:"};
        } else {
            types=fr_types;
            infoList = new String[]{"ID du livre:","titre d'ouvrage:","Nombre de copies:","Copies disponibles:","Auteur du livre:","Éditeur du livre:",
                    "ID du membre:","Prénom:","Nom de famille:", "Adresse:","catégorie:"};
        }
        if (language.equals("English")) {
            detailList = new String[]{"Member Details","Book Details","No Book Selected","get pined Book","No member Selected","get pined Member",
                    "Member Id ","Enter member id ","Book Id ","Enter Book id ","Enter Issue date","Enter Return Date","submit","Order"};
        } else {
            detailList = new String[]{"Détails du Membre","Détails du Livre","Aucun livre sélectionné","obtenir le livre sélectionné","Aucun membre sélectionné","obtenir le membre sélectionné",
                    "Id du Membre ","Entrer l'ID du membre ","Id du Livre ","Entrer l'ID du livre ","Entrez la date d'émission","Entrez la date de retour","soumettre","échec"};
        }
        String[] messageEnglish = {"all the book's copies\n are already issued","this book has no copies","Book Not found","member_id must\n be a number "
                ,"must return the prev book to get a new one","Member Not found","Err: complete the form ","issue book secc","this member is blocked"};
        String[] messageFrench = {"tout les exemplaires du livre\n sont déjà empruntés","ce livre n'a pas de copies","Livre non trouvé","identifiant de membre \ndoit être un nombre"
                ,"doit retourner le livre précédent\n pour en obtenir un nouveau","Membre non trouvé","Erreur: complétez le formulaire ","livre émis avec succès","ce membre est bloqué"};

// assuming the language variable holds the selected language ("English" or "French")
        messageList = (controller.getLanguage().equals("English")) ? messageEnglish : messageFrench;

        BookInfo =new VBox(5);
        MemberInfo =new VBox(5);
        BookInfocontainer=new VBox(10);
        MemInfocontainer=new VBox(20);

        BookInfo.getStyleClass().add("infoBox");
        BookInfo.setAlignment(Pos.CENTER);
        MemberInfo.getStyleClass().add("infoBox");
        MemberInfo.setAlignment(Pos.CENTER);
        Label Memifno = new Label(detailList[0]);
        Memifno.getStyleClass().add("title");
        Label Bookifno = new Label(detailList[1]);
        Bookifno.getStyleClass().add("title");

         NOBook= new Label(detailList[2]);

        pinedBookLable= new Label(detailList[3]);
        pinedBookLable.setUnderline(true);
        pinedBookLable.setTextFill(Color.web("#4EA0EAE8"));
        if(controller.getPinedBook()==null)
            pinedBookLable.setDisable(true);
        else
            pinedBookLable.setDisable(false);
        pinedBookLable.setOnMouseClicked(e->{
            String book_id =controller.getPinedBook().getBookId();
            BookId.setText(book_id);
            bookController(controller,BookId);
        });

         NoMem = new Label(detailList[4]);
        pinedMemLable= new Label(detailList[5]);
        pinedMemLable.setUnderline(true);
        pinedMemLable.setTextFill(Color.web("#4EA0EAE8"));
        if(controller.getPinedMem()==null)
            pinedMemLable.setDisable(true);
        else
            pinedMemLable.setDisable(false);

        pinedMemLable.setOnMouseClicked(e->{

            MemberId.setText(String.valueOf(controller.getPinedMem().getMember_id()));
            memberController(controller,MemberId);
        });
        NOBook.getStyleClass().add("Empty");
        NoMem.getStyleClass().add("Empty");
        MemInfocontainer.setPrefHeight(250);
        BookInfocontainer.setPrefHeight(250);
        MemInfocontainer.getChildren().add(NoMem);
        MemInfocontainer.setAlignment(Pos.CENTER);
        BookInfocontainer.getChildren().add(NOBook);
        BookInfocontainer.setAlignment(Pos.CENTER);


        MemberInfo.getChildren().add(Memifno);
        MemberInfo.getChildren().add(pinedMemLable);
        MemberInfo.getChildren().add(MemInfocontainer);
        BookInfo.getChildren().add(Bookifno);
        BookInfo.getChildren().add(pinedBookLable);

        BookInfo.getChildren().add(BookInfocontainer);



//        BookInfo.setMaxHeight(300);
//        BookInfo.setMaxWidth(300);

//        Label label = new Label("Issue Book ");
        HBox primaryContainer  = new HBox(30);
        primaryContainer.setTranslateX(20);
        primaryContainer.getStylesheets().add(Css);
        primaryContainer.getStyleClass().add("prim_container");
        VBox container  = new VBox(20);
        container.setAlignment(Pos.CENTER);

        container.setAlignment(Pos.CENTER_LEFT);
        container.getStyleClass().add("container");

        HBox infocontainer  = new HBox(20);
        infocontainer.setAlignment(Pos.CENTER);
//        infocontainer.setMaxHeight(infocontainer.prefHeight(-1));
//        infocontainer.setH

         message =new Label("");
        message.getStyleClass().add("message_done");

        VBox MemberContainer = new VBox(5);
        Label MemberIdLabel = new Label(detailList[6]);
        MemberId = new TextField();
        MemberId.setPromptText(detailList[7]);
        MemberId.setOnMouseClicked(e->{
//            btnContainer.getChildren().remove(Order);
            if(MemberId.getText().equals("")){
                message.setText("");
            }
        });

        MemberId.setOnKeyReleased(e->{
            memberController(controller,MemberId);
            controller.setPinedMem(false);
            btnContainer.getChildren().remove(Order);
            if(MemberId.getText().equals("")){
                message.setText("");
            }
        });

        
//        Label Member = new Label();




        MemberContainer.getChildren().add(MemberIdLabel);
        MemberContainer.getChildren().add(MemberId);

        VBox BookContainer = new VBox(5);
        Label BookIdLabel = new Label(detailList[8]);
        BookId = new TextField();
        BookId.setPromptText(detailList[9]);
         first_time =true;
        BookId.setOnAction(e->{
            if(BookId.getText().equals("")){
               message.setText("");
            }
        });
        BookId.setOnKeyReleased(e->{
            btnContainer.getChildren().remove(Order);

            if (message.getText().equals("")){
                message.setText("");
                controller.setPinedBook(false);
            }
            if(BookId.getText().equals("")&&BookInfo.getChildren().size()>1){
                BookInfocontainer.getChildren().clear();
                pinedBookLable.setDisable(false);
                BookInfocontainer.getChildren().add(NOBook);
                BookInfocontainer.setTranslateX(0);
            }else
                bookController(controller,BookId);
        });
        BookContainer.getChildren().add(BookIdLabel);
        BookContainer.getChildren().add(BookId);
        VBox IssdateBox= new VBox();
        IssdateBox.getStyleClass().add("IssdateBox");

        Label DateIssueLable = new Label(detailList[10]);
        Dateissue = new DatePicker();
        Dateissue.setPrefSize(260,30);
        Dateissue.setDisable(true);

        Dateissue.setOnAction(e->{
            if(BookId.getText().equals("")||MemberId.getText().equals("")){
                message.setText("");
            }
            issue_date=Dateissue.getValue();
        });

        VBox RetdateBox= new VBox();
        Label ReturnLable = new Label(detailList[11]);
        Returnissue = new DatePicker();
        Returnissue.setPrefSize(260,30);
/////////////////////////////////////////////////////////////////////////
        Returnissue.setDisable(false);
////////////////////////////////////////////////////////////////////////////
        Returnissue.setOnAction(e->{
            if(BookId.getText().equals("")||MemberId.getText().equals("")){
                message.setText("");
            }
//            System.out.println();
                    return_date=Returnissue.getValue();
        });
        Order =new Button(detailList[13]);
        Order.setPrefSize(150,30);
        Order.getStyleClass().add("CustomBtn");
        Order.setAlignment(Pos.CENTER);

        Order.setOnAction(e->{
            if(book!=null){
                controller.orderHandlle(book.getBookId());
            }
        });

        btnContainer =new HBox(20);
        btnContainer.setAlignment(Pos.CENTER);
        Button submit =new Button(detailList[12]);
        submit.setPrefSize(150,30);
        submit.getStyleClass().add("CustomBtn");
        submit.setAlignment(Pos.CENTER);
        submit.setOnAction(e->{
            if (MemberId.getText().equals("")||BookId.getText().equals("")||return_date==null||issue_date==null){
                message.setText(messageList[6]);

                if(message.getStyleClass().get(1).equals("message_done")){
                    message.getStyleClass().remove(0);
                    message.getStyleClass().add("message_err");
                }
                return;
            }
            if (submit_count>0){
                boolean m_check=memberController(controller,MemberId);
                boolean b_check=bookController(controller,BookId);
                if(!(m_check&&b_check))
                    return;
            }
            boolean res =false;
            if (!(book_id.isEmpty()||member_id==0)){
                System.out.println(issue_date+" "+return_date+" "+member_id+" "+book_id+" "+copy_id );
                try {
                    res= controller.addIssueBook(issue_date,return_date,member_id,book_id,copy_id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (res){
                message.setText(messageList[7]);
                System.out.println(message.getStyleClass().get(1));
                if(message.getStyleClass().get(1).equals("message_err")){
                    message.getStyleClass().remove(1);
                    message.getStyleClass().add("message_done");
                }

            }
            submit_count++;

        });



        container.getChildren().add(MemberContainer);
        container.getChildren().add(BookContainer);

        IssdateBox.getChildren().add(DateIssueLable);
        IssdateBox.getChildren().add(Dateissue);

        RetdateBox.getChildren().add(ReturnLable);
        RetdateBox.getChildren().add(Returnissue);

        infocontainer.getChildren().add(MemberInfo);
        infocontainer.getChildren().add(BookInfo);

        container.getChildren().add(IssdateBox);
        container.getChildren().add(RetdateBox);
        container.getChildren().add(message);

        btnContainer.getChildren().add(submit);
        container.getChildren().add(btnContainer);

        primaryContainer.getChildren().add(container);
        primaryContainer.getChildren().add(infocontainer);
        getChildren().add(primaryContainer);
    }
}
