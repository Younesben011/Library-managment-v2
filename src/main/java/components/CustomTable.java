package components;

import DatabaseManagment.Books.Book;
import DatabaseManagment.Issue.IssueBook;
import DatabaseManagment.Members.Member;
import DatabaseManagment.Write.Write;
import com.example.librarymanagement.Controller;
import com.example.librarymanagement.LoginPage;
import com.example.librarymanagement.Scenes.BookManager;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomTable extends VBox {
    String[] memberList;

    Double width;
    VBox table  ;
    int i=0;
    int member_i=0;
    String Css= LoginPage.class.getResource("Components.css").toExternalForm();
    boolean deleted = false;
    HBox options;
    Pane selected;
    Pane select;
    String language;
    String[] bookList;
    String[] types;
    boolean toggle=false;
    int count =0;
    HBox PrevRow=null;
    String prevStyle= "";
    HBox PrevRowM=null;
    String prevStyleM= "";

    List<HBox> rows_list = new ArrayList<>();
    String selectedStyle="-fx-pref-width: 10;" +
            "-fx-pref-height: 10;" +
            "-fx-background-radius: 5;" +
            "-fx-border-width: 1px;" +
            "-fx-border-color: rgba(74,156,246,0.72);" +
            "-fx-border-radius: 5px;";
    String UnselectedStyle="-fx-pref-width: 10;" +
            "-fx-pref-height: 10;" +
            "-fx-background-radius: 5;" +
            "-fx-border-width: 1px;" +
            "-fx-border-color: rgba(74,156,246,0.72);" +
            "-fx-border-radius: 5px;" +
            "-fx-background-color:rgba(74,156,246,0.72); ";

    public void addMemberRow(Member member,Controller controller,VBox member_form){
        HBox row = new HBox(10);
        row.getStylesheets().add(Css);
        row.setAlignment(Pos.BASELINE_CENTER);
        if (i%2==0)
            row.setBackground(Background.fill(Color.web("#FFFFFFFF")));
        else
            row.setBackground(Background.fill(Color.web("#A5C8FD89")));
        row.getChildren().add(new Label(String.valueOf(member.getMember_id())));
        row.getChildren().add(new Label(member.getMember_Firstname()));
        row.getChildren().add(new Label(member.getMember_Lastname()));
        row.getChildren().add(new Label(member.getAddress()));
        row.getChildren().add(new Label(String.valueOf(member.getLibrary_num())));
        Label delete =new Label("delete");
        delete.setOnMouseClicked(e->{
            deleted=true;

            System.out.println("delete");
            controller.deleteMember(controller,member_form);
            for (int i=0;i<member_form.getChildren().size()-1;i++){

                TextField textField= (TextField)member_form.getChildren().get(i);
                textField.setText("");

            }
            deleted=false;

//            controller.deleteBook(book.getBookId(), book.getBookAuth(),controller);
        });
        row.getChildren().add(delete);
        table.getChildren().add(row);
        member_i++;
    }
    public void addBookRow(Book book,Controller controller,VBox book_form){
        HBox row = new HBox(10);
        row.getStylesheets().add(Css);
        row.setAlignment(Pos.BASELINE_CENTER);
        if (i%2==0)
            row.setBackground(Background.fill(Color.web("#FFFFFFFF")));
        else
            row.setBackground(Background.fill(Color.web("#5D5C5C8E")));
        row.getChildren().add(new Label(book.getBookId()));
        row.getChildren().add(new Label(book.getBookName()));
        row.getChildren().add(new Label(book.getBookEditor()));
        row.getChildren().add(new Label(String.valueOf(book.getBookQuantity())));
        Label delete =new Label("delete");
        delete.setOnMouseClicked(e->{
            deleted=true;
            controller.deleteBook(controller,book_form);
            for (int i=0;i<book_form.getChildren().size()-1;i++){

                TextField textField= (TextField)book_form.getChildren().get(i);
                textField.setText("");
            }
        });
        row.getChildren().add(delete);
        table.getChildren().add(row);
        i++;


    }
    public void  UpdateTable(Controller controller,List<Book> books_list,VBox book_form){

        if (language.equals("English")) {
            bookList = new String[]{"Book Id","Book Name","Book Editor","Book Quantity","Rest"};
        } else {
            bookList = new String[]{"Identifiant", "Nom du livre", "Éditeur du livre", "Quantité de livres", "Reste"};
        }
        if (table.getChildren().size()>0)
            table.getChildren().clear();
        HBox header = new HBox();
        header.getStylesheets().add(Css);
//        header.getStyleClass().add("header");
        Label space=new Label("");
        space.setPrefWidth(30);
        Label BookID=new Label(bookList[0]);
        BookID.getStyleClass().add("_column");
        BookID.setPrefWidth(200);
        Label Bookname=new Label(bookList[1]);
        Bookname.getStyleClass().add("_column");
        Bookname.setPrefWidth(200);
        Label BookEditor=new Label(bookList[2]);
        BookEditor.getStyleClass().add("_column");
        BookEditor.setPrefWidth(200);
        Label Quantity=new Label(bookList[3]);
        Quantity.getStyleClass().add("_column");
        Quantity.setPrefWidth(200);
        Label rest=new Label(bookList[4]);
        rest.getStyleClass().add("_column");
        rest.setPrefWidth(200);
        header.getChildren().add(space);
        header.getChildren().add(BookID);
        header.getChildren().add(Bookname);
        header.getChildren().add(BookEditor);
        header.getChildren().add(Quantity);
        header.getChildren().add(rest);
        header.prefWidth(width);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle("-fx-pref-height: 25px;" +
                "-fx-border-width: 1px 0 1px 0;" +
                "-fx-border-color: rgba(0,0,0,0.18);" +
                "-fx-background-color: white" );
        VBox updated_table = new VBox();
        updated_table.getChildren().add(header);
//            table.
        for (Book book:
                books_list) {
            HBox row = new HBox();
            row.getStylesheets().add(Css);
            Label spacer=new Label("");
            spacer.setPrefWidth(20);
            row.getStyleClass().add("row");
            row.setPrefWidth(width);
            row.setStyle("");
            row.setAlignment(Pos.CENTER_LEFT);

            selected= new Pane();
            selected.setPrefWidth(20);
            selected.setPrefHeight(20);

            select=new Pane();
            select.setLayoutX(5);
            select.setLayoutY(5);
            select.setStyle(selectedStyle);
            selected.getChildren().add(select);
            row.getStyleClass().remove("rowUnSelected");

            row.setOnMouseClicked(e->{
                controller.setDeletedBook(book);
                HBox editContainer = (HBox) options.getChildren().get(1);
                Label editbtn = (Label) editContainer.getChildren().get(1);
                HBox delContainer = (HBox) options.getChildren().get(2);
                HBox pinContainer = (HBox) options.getChildren().get(3);
                Label messageOp = (Label) options.getChildren().get(4);
                messageOp.setText("");
                Label del = (Label) delContainer.getChildren().get(1);
                if(PrevRow==null){
                    System.out.println(PrevRow);
                    PrevRow=row;
                }else
                if(PrevRow!=row){
                    editContainer.setDisable(true);
                    delContainer.setDisable(true);
                    pinContainer.setDisable(true);

                    PrevRow.getStyleClass().remove(1);
                    System.out.println(prevStyle);
                    PrevRow.getStyleClass().add(prevStyle);
                    toggle=false;
                    PrevRow=row;
                }

                count=0;
                Pane selectedCon = (Pane)row.getChildren().get(0);

                if (!toggle){
                    editContainer.setDisable(false);
                    delContainer.setDisable(false);
                    pinContainer.setDisable(false);
                    toggle=!toggle;
                    prevStyle = row.getStyleClass().get(1);
                    row.getStyleClass().remove(1);
                    row.getStyleClass().add("rowSelected");
//                    selectedCon.getChildren().get(0).setStyle(UnselectedStyle);
                 }else{
                    editContainer.setDisable(true);
                    delContainer.setDisable(true);
                    pinContainer.setDisable(true);
                    row.getStyleClass().remove(1);
                    System.out.println(prevStyle);
                    row.getStyleClass().add(prevStyle);

                    toggle=!toggle;
                }

//                    row.getStyleClass().remove("rowSelected");

//                }else {
//                    if(rows_list.indexOf(row)%2==0){
//                        row.getStyleClass().clear();
//                        row.getStyleClass().add("rowOdd");
//                    }
//
////                row.setBackground(Background.fill(Color.web("#FFFFFFFF")));
//                    else{row.getStyleClass().clear();
//                        row.getStyleClass().add("roweven");}

//                    toggle=!toggle;
//                    editContainer.setDisable(true);
//                    delContainer.setDisable(true);
//                    selectedCon.getChildren().get(0).setStyle(selectedStyle);
//                }
//                    int count =0;
//                    for (HBox item:
//                            rows_list) {
//                            Pane selectedCon2 = (Pane)item.getChildren().get(0);
//                        if (!item.equals(row)){
//                                toggle=!toggle;
//                                selectedCon.getChildren().get(0).setStyle(selectedStyle);
//                                if (count%2==0)
//                                row.setStyle("-fx-pref-height: 30;-fx-background-color:#FFFFFFFF ");
////                row.setBackground(Background.fill(Color.web("#FFFFFFFF")));
//                            else
//                                row.setStyle("-fx-pref-height: 30;-fx-background-color:#A6A6A615 ");
//                            }
//
//                        count++;
//
//                    }



                    int  author_id = controller.getWrite(book.getBookId()).getAuthor_id();
                    String author_name=controller.getAuthorById(author_id).getAuthor_name();
                    TextField boo_id = (TextField) book_form.getChildren().get(2);
                    boo_id.setText(book.getBookId());
                    TextField name = (TextField) book_form.getChildren().get(4);
                    name.setText(book.getBookName());
//                HBox authorSection = (HBox)  book_form.getChildren().get(2);
                    TextField Authorname = (TextField) book_form.getChildren().get(6);
                    Authorname.setText(author_name);
//                controller.ge
                    TextField edit = (TextField) book_form.getChildren().get(8);
                    edit.setText(book.getBookEditor());
                    TextField qu = (TextField) book_form.getChildren().get(10);
                    qu.setText(String.valueOf(book.getBookQuantity()));
                    HBox buttons = (HBox) book_form.getChildren().get(11);
                    Button submit = (Button)  buttons.getChildren().get(0);
                    submit.setText("Update");
                    Button reset = (Button)  buttons.getChildren().get(1);
                    reset.setVisible(true);

            });
            if (i%2==0)
                row.getStyleClass().add("rowOdd");
            else
                row.getStyleClass().add("roweven");
            Label bid=new Label(book.getBookId());
            bid.setPrefWidth(200);
            row.getChildren().add(selected);
            row.getChildren().add(spacer);
            row.getChildren().add(bid);
            Label bn=new Label(book.getBookName());
            bn.setPrefWidth(200);
            row.getChildren().add(bn);
            Label bed=new Label(book.getBookEditor());
            bed.setPrefWidth(200);
            row.getChildren().add(bed);
            Label bq=new Label(String.valueOf(book.getBookQuantity()));
            bq.setPrefWidth(200);
            int available_books = controller.getAviliableBooks(book.getBookId());
            Label reestLable = new Label(String.valueOf(available_books));
            if (available_books<=1){
                reestLable.setTextFill(Paint.valueOf("#fc3232"));
            }
            reestLable.setPrefWidth(200);
            row.getChildren().add(bq);
            row.getChildren().add(reestLable);
            Label delete =new Label("delete");
            delete.setOnMouseClicked(e->{
                deleted=true;
                boolean res= controller.deleteBook(controller,book_form);
                if (!res){
                    Label message =(Label) options.getChildren().get(4);
                    message.setText("cannot delete this book before All the \n"+"copies returns  to the library ");
                }

                   for (int i=1;i<book_form.getChildren().size()-2;i++){
                       if(i%2==0){
                       TextField textField= (TextField)book_form.getChildren().get(i);
                       textField.setText("");}
                   }
                   HBox buttons = (HBox) book_form.getChildren().get(5);
                   Button submit = (Button)  buttons.getChildren().get(0);
                   submit.setText("Submit");
                   Button reset = (Button)  buttons.getChildren().get(1);
                   reset.setVisible(false);
                deleted=false;

            });
//            row.getChildren().add(delete);
            updated_table.getChildren().add(row);
            rows_list.add(row);
            i++;
        }
        table=updated_table;
        getChildren().add(table);

    }
    public void   UpdateTable(Controller controller,List<Book> books_list,List<Member> members_list,VBox member_form){
        if (language.equals("English")) {
            memberList = new String[]{"Id","First Name","Last Name", "Address","Issue","email","type"};
            types=new String[]{"student","teacher"};

        } else {
            memberList = new String[]{"Identifiant","Prénom","Nom", "Adresse","Emprunt","email","type"};
            types=new String[]{"enseignant","etudiant"};
        }

        if (table.getChildren().size()>0)
            table.getChildren().clear();
        HBox header = new HBox();
        header.getStylesheets().add(Css);

        Label space=new Label("");
//        space.setPrefWidth(18);
        Label BookID=new Label(memberList[0]);
        BookID.getStyleClass().add("_column");
        BookID.setPrefWidth(150);
        Label Bookname=new Label(memberList[1]);
        Bookname.getStyleClass().add("_column");
        Bookname.setPrefWidth(150);
        Label BookEditor=new Label(memberList[2]);
        BookEditor.getStyleClass().add("_column");
        BookEditor.setPrefWidth(150);
        Label Quantity=new Label(memberList[3]);
        Quantity.getStyleClass().add("_column");
        Quantity.setPrefWidth(150);
        Label email=new Label(memberList[5]);
        email.getStyleClass().add("_column");
        email.setPrefWidth(150);
        Label type=new Label(memberList[6]);
        type.getStyleClass().add("_column");
        type.setPrefWidth(150);
        Label Issue=new Label(memberList[4]);
        Issue.getStyleClass().add("_column");
        Issue.setPrefWidth(150);
        header.getChildren().add(BookID);
        header.getChildren().add(Bookname);
        header.getChildren().add(BookEditor);
        header.getChildren().add(email);
        header.getChildren().add(type);
        header.getChildren().add(Quantity);
        header.getChildren().add(Issue);
        header.prefWidth(width);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle("-fx-pref-height: 25px;" +
                "-fx-border-width: 1px 0 1px 0;" +
                "-fx-border-color: rgba(0,0,0,0.18);" +
                "-fx-background-color: white" );
        VBox updated_table = new VBox();
        updated_table.getChildren().add(header);
        updated_table.getStylesheets().add(Css);












//            table.
        for (Member member:
                members_list) {
            HBox row = new HBox();
            row.getStylesheets().add(Css);
            row.getStyleClass().add("row");
            row.setPrefWidth(width);
//            selected= new Pane();
//            selected.setPrefWidth(20);
//            selected.setPrefHeight(20);
//            row.getStyleClass().remove("rowUnSelected");
//            select=new Pane();
//            select.setLayoutX(5);
//            select.setLayoutY(5);
//            select.setStyle(selectedStyle);
//            selected.getChildren().add(select);
//            row.getChildren().add(selected);
            row.setOnMouseClicked(e->{

                controller.setDeletedMember(member);
                HBox editContainer = (HBox) options.getChildren().get(1);
                Label editbtn = (Label) editContainer.getChildren().get(1);
                HBox delContainer = (HBox) options.getChildren().get(2);
                HBox pinContainer = (HBox) options.getChildren().get(3);
                Label messageOp = (Label) options.getChildren().get(4);
                messageOp.setText("");
                Label del = (Label) delContainer.getChildren().get(1);
                count=0;
//                Pane selectedCon = (Pane)row.getChildren().get(0);

                if(PrevRowM==null){
                    System.out.println(PrevRowM);
                    PrevRowM=row;
                }else
                if(PrevRowM!=row){
                    editContainer.setDisable(true);
                    delContainer.setDisable(true);
                    pinContainer.setDisable(true);

                    PrevRowM.getStyleClass().remove(1);
                    System.out.println(prevStyleM);
                    PrevRowM.getStyleClass().add(prevStyleM);
                    toggle=false;
                    PrevRowM=row;
                }

                if (!toggle){
                    editContainer.setDisable(false);
                    delContainer.setDisable(false);
                    pinContainer.setDisable(false);

                    prevStyleM = row.getStyleClass().get(1);
                    System.out.println(row.getStyleClass().get(1));
                    row.getStyleClass().remove(1);
                    row.getStyleClass().add("rowSelected");
//                    selectedCon.getChildren().get(0).setStyle(UnselectedStyle);
                    toggle=!toggle;
                }else{
                    editContainer.setDisable(true);
                    delContainer.setDisable(true);
                    pinContainer.setDisable(true);

                    row.getStyleClass().remove(1);
                    System.out.println(prevStyleM);
                    row.getStyleClass().add(prevStyleM);

                toggle=!toggle;
                }














//                if (!toggle){
//                    editContainer.setDisable(false);
//                    delContainer.setDisable(false);
//                    toggle=!toggle;
//                    row.setStyle("-fx-pref-height: 30;-fx-background-color:rgba(93,92,92,0.54) ");
//                    selectedCon.getChildren().get(0).setStyle(UnselectedStyle);
//                }else {
//                    if(rows_list.indexOf(row)%2==0)
//                        row.setStyle("-fx-pref-height: 30;-fx-background-color:#FFFFFFFF ");
////                row.setBackground(Background.fill(Color.web("#FFFFFFFF")));
//                    else
//                        row.setStyle("-fx-pref-height: 30;-fx-background-color:#A6A6A615 ");
//                    toggle=!toggle;
//                    editContainer.setDisable(true);
//                    delContainer.setDisable(true);
//                    selectedCon.getChildren().get(0).setStyle(selectedStyle);
//                }
                    TextField meb_id =(TextField)  member_form.getChildren().get(2);
                    meb_id.setText(String.valueOf(member.getMember_id()));
                    TextField first_name =(TextField)  member_form.getChildren().get(4);
                    first_name.setText(member.getMember_Firstname());
                    TextField last_name = (TextField) member_form.getChildren().get(6);
                    last_name.setText(member.getMember_Lastname());
                    TextField Temail = (TextField) member_form.getChildren().get(8);
                    Temail.setText(member.getEmail());
                    TextField address = (TextField) member_form.getChildren().get(10);
                    address.setText(member.getAddress());
                    TextField lib = (TextField) member_form.getChildren().get(12);
                    lib.setText(String.valueOf(member.getLibrary_num()));
                ComboBox Ttype =(ComboBox) member_form.getChildren().get(14);
                Ttype.getSelectionModel().select(member.getType()-1);
                        HBox buttons = (HBox) member_form.getChildren().get(15);
                Button submit =(Button)buttons.getChildren().get(0);
                Button reset =(Button)buttons.getChildren().get(1);
                submit.setText("Update");
                reset.setVisible(true);


            });
//            row.setAlignment(Pos.BASELINE_CENTER);

            if (member_i%2==0)
                row.getStyleClass().add("rowOdd");
            else
                row.getStyleClass().add("roweven");
            Label MID =new Label(String.valueOf(member.getMember_id()));
            MID.setPrefWidth(150);
            MID.setAlignment(Pos.CENTER_LEFT);
            Label T_email = new Label(member.getEmail());
            T_email.setPrefWidth(150);
            T_email.setAlignment(Pos.CENTER_LEFT);
            Label T_type = new Label(types[member.getType()-1]);
            T_type.setPrefWidth(150);
            T_type.setAlignment(Pos.CENTER_LEFT);
//            MID.setStyle("-fx-pref-width: 100;-fx-background-color: red");
            row.getChildren().add(MID);
            Label MFS =new Label(member.getMember_Firstname());
//            MFS.getStyleClass().add("item");
            MFS.setPrefWidth(150);
            MFS.setAlignment(Pos.CENTER_LEFT);
            row.getChildren().add(MFS);
            Label MFl =new Label(member.getMember_Lastname());
            MFl.setPrefWidth(150);
            MFl.setAlignment(Pos.CENTER_LEFT);
            row.getChildren().add(MFl);
            row.getChildren().add(T_email);
            row.getChildren().add(T_type);
            Label MA =new Label(member.getAddress());
            MA.setPrefWidth(150);
            MA.setAlignment(Pos.CENTER_LEFT);
            row.getChildren().add(MA);
            String issue = "";
            IssueBook issue1 = controller.getIssueBookByMember(member.getMember_id());
            if(issue1!=null){
                issue="true";
            }else
                issue="false";
            Label MI =new Label(issue);
            MI.setPrefWidth(150);
            MI.setAlignment(Pos.CENTER_LEFT);
            row.getChildren().add(MI);
            Label delete =new Label("delete");
            delete.setOnMouseClicked(e->{
                deleted=true;
                boolean res =controller.deleteMember(controller,member_form);
                if (!res){

                    Label messageOp =(Label) options.getChildren().get(4);
                    messageOp.setText("cannot delete this member before he returns the book ");
                    messageOp.setTextFill(Paint.valueOf("#fc3232"));
                }
                for (int i=0;i<member_form.getChildren().size()-2;i++){
                    TextField textField= (TextField)member_form.getChildren().get(i);
                    textField.setText("");
                }
//                controller.d(M.getBookId(), book.getBookAuth(),controller);
                deleted=false;

            });
            updated_table.getChildren().add(row);
            member_i++;
        }
        table =updated_table;

        getChildren().add(table);

    }
    public CustomTable(Controller controller,Double width,HBox options,List<Book> books_lsit,VBox book_form) throws SQLException {
        language = controller.getLanguage();
        table= new VBox();
        this.width=width;
        this.options=options;
        table.setPrefWidth(width-500);
        table.setBackground(Background.fill(Color.BLACK));
        table.getStylesheets().add(Css);
        table.getStyleClass().add("table");
        UpdateTable( controller, books_lsit,book_form);

        }
        public CustomTable(Controller controller,Double width,HBox options,List<Book> books_lsit, List<Member> member_list,VBox member_form){
            language = controller.getLanguage();
            table= new VBox();
            this.width=width;
            this.options=options;
            System.out.println(width);
            table.setPrefWidth(width-500);
//            table.setVgrow();
            table.setBackground(Background.fill(Color.BLACK));
            table.getStylesheets().add(Css);
            table.getStyleClass().add("table");
            UpdateTable( controller, books_lsit,member_list,member_form);


    }

    public boolean isToggle() {
        return toggle;
    }

    public void setToggle(boolean toggle) {
        this.toggle = toggle;
    }
}



