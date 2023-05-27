package components;

import DatabaseManagment.Issue.IssueBook;
import com.example.librarymanagement.Controller;
import com.example.librarymanagement.LoginPage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class YTable extends ScrollPane {
    String[] header ;
    Consumer filterC;
    List<String[]> table_content;
    Controller controller;
    VBox table;
    int i;
    HBox PrevRow=null;
    int Prev_row_num=-1;
    TextField member_label;
    TextField book_label;

    Button ReIssue;
    String Css= LoginPage.class.getResource("style.css").toExternalForm();
    public YTable(Controller controller,Consumer<String>filterC, String[] header,VBox container){
        this.filterC=filterC;
        this.table = new VBox();
        this.header=header;
        table_content=new ArrayList<>();
        this.controller=controller;
        VBox  MemberContainer =  (VBox) container.getChildren().get(0);
        VBox  BookContainer =  (VBox) container.getChildren().get(1);
        HBox BtnContainer = (HBox) container.getChildren().get(3);


        ReIssue = (Button) BtnContainer.getChildren().get(1);
        member_label = (TextField) MemberContainer.getChildren().get(1);
        book_label = (TextField) BookContainer.getChildren().get(1);


    }
public YTable(Controller controller, Consumer<String>filterC, String[] header, VBox container, List<String[]> table_content){
    this.header=header;
    this.filterC=filterC;

    this.table_content=table_content;
    this.controller=controller;
    this.table = new VBox();
    table.setMaxWidth(1000);
    table.setMaxHeight(330);
    table.setPrefSize(1000,330);
    renderTable();
    VBox  MemberContainer =  (VBox) container.getChildren().get(0);
    VBox  BookContainer =  (VBox) container.getChildren().get(1);


    member_label = (TextField) MemberContainer.getChildren().get(1);
    book_label = (TextField) BookContainer.getChildren().get(1);
}

public void renderTableByContent(List<String[]> table_content){
        if(table_content.size()==0){
            table.getChildren().clear();
            Empty();
        }else {
            this.table_content =table_content;
            table.getChildren().clear();
            render();
        }
}
public void renderTable(){

    this.table_content =controller.getIssueBooks();
    if(table_content.size()==0){
        table.getChildren().clear();
        Empty();
    }else {
    table.getChildren().clear();
    render();}
    }
    public void Empty(){
        Label empty =new Label("No Record");
        empty.getStyleClass().add("Empty");
        table.getChildren().add(empty);
        setStyle("-fx-focus-color:transparent;-fx-border-width: 0");
        setPrefSize(1000, 330);
        setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        setHbarPolicy(ScrollBarPolicy.NEVER);
        setContent(table);
    }
private void render(){

    i=0;
        HBox HeaderContainer =new HBox();

        HeaderContainer.getStyleClass().add("TableheaderContainer");
    for (String h:
            header) {
//        HBox column =new HBox();
        Label headerLable = new Label(" "+h);
        headerLable.setMaxSize(130,30);
        headerLable.setPrefWidth(130);
        headerLable.setPrefHeight(30);
        headerLable.getStyleClass().add("Tableheader");

        HeaderContainer.getChildren().add(headerLable);
    }
    table.getChildren().add(HeaderContainer);
    int row_num=0;
        for (String[] row:
             table_content) {
            HBox rowContainer =new HBox();
            rowContainer.setOnMouseClicked(e->{
                ReIssue.setDisable(false);
                Label id1 = (Label) rowContainer.getChildren().get(0);
                int issue_id1= Integer.parseInt(id1.getText().trim());
                IssueBook issueBook1 = controller.getIssue(issue_id1);
                if (issueBook1.getReal_return_date()!=null){
                        ReIssue.setDisable(true);

                }else{

                ReIssue.setOnMouseClicked(ee->{
                    Label id = (Label) rowContainer.getChildren().get(0);
                    int issue_id= Integer.parseInt(id.getText().trim());
                    IssueBook issueBook = controller.getIssue(issue_id);
                    boolean res = controller.returnBook(issueBook.getMember_id(),controller.getBookByCopyId(issueBook.getCopy_id()).getBookId());
                    issueBook.setIssue_date(LocalDate.now());
                    issueBook.setReturn_date(LocalDate.now().plusDays(7));
                    issueBook.setReal_return_date(null);
                    controller.ReIssueBook(issueBook);
                    List<String[]> Issueds=controller.IssueFilter(3) ;
                    renderTableByContent(Issueds);
                    filterC.accept("");
//                    renderTable();
                });
                }
                if (PrevRow!=null){
                    PrevRow.getStyleClass().remove(1);
                    if(Prev_row_num%2==0){
                        PrevRow.getStyleClass().add("evenRow");
                }else {
                    PrevRow.getStyleClass().add("OddRow");

                }}

                PrevRow=rowContainer;
                Prev_row_num=table.getChildren().indexOf(rowContainer);
                rowContainer.getStyleClass().remove(1);
                rowContainer.getStyleClass().add("rowContainerSelected");
                Label id = (Label) rowContainer.getChildren().get(0);
                int issue_id= Integer.parseInt(id.getText().trim());
                IssueBook issueBook = controller.getIssue(issue_id);
                System.out.println(issueBook);
                controller.setSelected_Ibook(issueBook);
                member_label.setText(String.valueOf(issueBook.getMember_id()));
                book_label.setText(controller.getBookByCopyId(issueBook.getCopy_id()).getBookId());

            });
            rowContainer.getStyleClass().add("rowContainer");
            if(row_num%2==0){
                rowContainer.getStyleClass().add("evenRow");
            }else {
                rowContainer.getStyleClass().add("OddRow");

            }
            for (String item :row){
            Label itemLable =new Label("  "+item);
            rowContainer.getChildren().add(itemLable);
            itemLable.getStyleClass().add("row");
            }
            row_num++;
            table.getChildren().add(rowContainer);
            }
    setStyle("-fx-focus-color:transparent;-fx-border-width: 0");
    setPrefSize(1000, 330);
    setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    setHbarPolicy(ScrollBarPolicy.NEVER);
    setContent(table);

} 
}
