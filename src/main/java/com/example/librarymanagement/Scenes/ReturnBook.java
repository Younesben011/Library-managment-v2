package com.example.librarymanagement.Scenes;

import com.example.librarymanagement.Controller;
import com.example.librarymanagement.LoginPage;
import components.YTable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Filter;

public class ReturnBook extends VBox {
    HBox prevFilterContainer;
    Label message;
    String book_id="";
    String[] english_header=new String[]{"issue id", "copy id ","member id","librarian id","issue date ","return date ","real return date","defaulter"};
    String[] french_header = new String[]{"emprunt id", "copie id", "membre id", "bibliothécaire id", "date d'emprunt", "date de retour", "date de retour réelle", "en retard"};

    int member_id=-1;
    VBox container;
    YTable table;
    VBox FilterContainer;
    Pane prevselected;
    String Css= LoginPage.class.getResource("style.css").toExternalForm();
    List<String> filterList;
    Pane selected;
    String[] langList;

    public void setSelectedFilter(HBox filterContainer,Controller controller){
        Label filtertitlee =(Label) filterContainer.getChildren().get(1);
        Pane selectedd =(Pane) filterContainer.getChildren().get(0);
        if(prevselected!=null){
            System.out.println("LLLLL"+prevselected.getStyleClass().get(0));
//                    if(prevselected.getStyleClass().size()>1){
//                    prevselected.getStyleClass().remove(1);
//
//                    }else
            prevselected.getStyleClass().remove(0);
            prevselected.getStyleClass().add("checkBox");
//            System.out.println("SSSSSS"+prevselected.getStyleClass().get(0));

        }
        prevselected=selectedd;
        selectedd.getStyleClass().remove(0);
        selectedd.getStyleClass().add("checked");

        int indx=filterList.indexOf(filtertitlee.getText());
        List<String[]> Issueds=controller.IssueFilter(indx) ;
        table.renderTableByContent(Issueds);
    }
    public ReturnBook(Controller controller,double width){

        if (controller.getLanguage().equals("English")) {
            langList = new String[]{"Member Id ","Enter member id ","Book Id ","Enter Book id ","ReIssue","Return","Returned","pleas complete the form"};
        } else {
            langList = new String[]{"Identifiant du membre ","Entrer l'identifiant du membre ","Identifiant du livre ","Entrer l'identifiant du livre ","Réémettre","Retour","Retourné","Erreur : veuillez remplir le formulaire"};
        }
        getStylesheets().add(Css);
        FilterContainer = new VBox(10);
        Label filterLable = new Label("Filter");
        List<String>  englishFilter = new ArrayList<String>(List.of(new String[]{"show All","defaulter","returned","issued"}));
        List<String>  frenchFilter = new ArrayList<String> (List.of(new String[]{"Afficher tout", "en retard", "Retourné", "emprunter"}));

        FilterContainer.getChildren().add(filterLable);
        if(controller.getLanguage().equals("English"))
            filterList = englishFilter;
        else
            filterList = frenchFilter;
        for (String filter : filterList){
            HBox filterContainer = new HBox(10);
            Label filtertitle = new Label(filter);
            filtertitle.setMaxWidth(100);
            filtertitle.setPrefWidth(100);
            selected = new Pane();

            selected.setMaxWidth(10);
            selected.setPrefWidth(10);
            selected.setMaxHeight(10);
            selected.setPrefHeight(10);
            filterContainer.setAlignment(Pos.CENTER);
            filterContainer.getChildren().add(selected);
            filterContainer.getChildren().add(filtertitle);
            FilterContainer.getChildren().add(filterContainer);
            if(filterList.indexOf(filter)==3){
                prevselected=selected;
                selected.getStyleClass().add("checked");
                prevFilterContainer=filterContainer;

            }else{
                selected.getStyleClass().add("checkBox");
            }
            filterContainer.setOnMouseClicked(e->{
                setSelectedFilter(filterContainer,controller);

            });


        }

        Pane MainContainer = new Pane();
        Label label = new Label("Return Book");




//        Chec

        container  = new VBox(10);
        container.setTranslateX(20);
        container.setTranslateY(5);
        container.setAlignment(Pos.CENTER);
        message =new Label("");

        VBox MemberContainer = new VBox(5);
        MemberContainer.setPrefWidth(200);
        Label MemberIdLabel = new Label(langList[0]);
        TextField MemberId = new TextField();
        MemberId.setPromptText(langList[1]);


        MemberId.setOnKeyReleased(e->{
            if(!MemberId.getText().equals(""))
                member_id= Integer.parseInt(MemberId.getText());
            else {
                member_id=-1;
                table.renderTable();
            }
            if(member_id!=-1&&!book_id.isEmpty()){
//                List<String[]> Issueds=controller.IssueFilter(3) ;
                List<String[]> table_content =controller.searchIssueBook(member_id,book_id);
                table.renderTableByContent(table_content);


            }
        });


//        Label Member = new Label();




        MemberContainer.getChildren().add(MemberIdLabel);
        MemberContainer.getChildren().add(MemberId);

        VBox BookContainer = new VBox(5);
        BookContainer.setPrefWidth(200);

        Label BookIdLabel = new Label(langList[2]);
        TextField BookId = new TextField();
        BookId.setPromptText(langList[3]);

        BookId.setOnKeyReleased(e->{
            book_id=BookId.getText();
            if (BookId.getText().isEmpty()){
                    table.renderTable();
            }
            if(member_id!=-1&&!book_id.isEmpty()){
                controller.searchIssueBook(member_id,book_id);
                List<String[]> table_content =controller.searchIssueBook(member_id,book_id);
                table.renderTableByContent(table_content);

            }
        });
        HBox ButtonsContainer =new HBox(10);
        Button ReIssue = new Button(langList[4]);
        ReIssue.setDisable(true);
        ReIssue.getStyleClass().add("CustomBtn");



        Button submit =new Button(langList[5]);
        submit.getStyleClass().add("CustomBtn");
        submit.setOnAction(e->{
            if(MemberId.getText().equals("")||BookId.getText().equals("")){
                message.setText(langList[7]);
                message.setTextFill(Color.RED);
                return;

            }
            member_id= Integer.parseInt(MemberId.getText());
            book_id = BookId.getText();
            boolean res = controller.returnBook(member_id,book_id);
            if (res){
                message.setText(langList[6]);
                message.setTextFill(Color.GREEN);

            }
            List<String[]> Issueds=controller.IssueFilter(3) ;
            table.renderTableByContent(Issueds);
            setSelectedFilter(prevFilterContainer,controller);

//                table.renderTable();
        });
        ButtonsContainer.getChildren().add(submit);
        ButtonsContainer.getChildren().add(ReIssue);
        List<String[]> s = new ArrayList<>();
        s.add(new String[]{"1","2","3"});
        s.add(new String[]{"4","5","6"});


        BookContainer.getChildren().add(BookIdLabel);
        BookContainer.getChildren().add(BookId);

        container.getChildren().add(MemberContainer);
        container.getChildren().add(BookContainer);
        container.getChildren().add(message);
        container.getChildren().add(ButtonsContainer);


        MainContainer.getChildren().add(container);
        Consumer<String> filterC  =l->{setSelectedFilter(prevFilterContainer,controller);};
        if(controller.getLanguage().equals("English")){

        table = new YTable(controller,filterC,english_header,container);
        }
        else{
        table = new YTable(controller,filterC,french_header,container);
        }
        List<String[]> Issueds=controller.IssueFilter(3) ;
        table.renderTableByContent(Issueds);
        table.setTranslateY(200);
        FilterContainer.setTranslateX(800);
        FilterContainer.setTranslateY(10);

        MainContainer.getChildren().add(FilterContainer);
        MainContainer.getChildren().add(table);
        getChildren().add(MainContainer);


    }
}
