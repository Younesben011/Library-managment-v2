package com.example.librarymanagement;

import DatabaseManagment.Acount.Acount;
import DatabaseManagment.Acount.AcountDAO;
import DatabaseManagment.Acount.AcountImp;
import DatabaseManagment.Author.Author;
import DatabaseManagment.Author.AuthorDAO;
import DatabaseManagment.Author.AuthorImp;
import DatabaseManagment.BookOrder.BokkOrderImmp;
import DatabaseManagment.BookOrder.BookOrder;
import DatabaseManagment.BookOrder.BookOrderDAO;
import DatabaseManagment.Books.Book;
import DatabaseManagment.Books.BookDAO;
import DatabaseManagment.Books.BookImp;
import DatabaseManagment.Copy.Copy;
import DatabaseManagment.Copy.CopyDAO;
import DatabaseManagment.Copy.CopyImp;
import DatabaseManagment.Issue.IssueBook;
import DatabaseManagment.Issue.IssueBookDAO;
import DatabaseManagment.Issue.IssueBookImp;
import DatabaseManagment.Librarian.LibrarianImp;
import DatabaseManagment.Librarian.librarian;
import DatabaseManagment.Librarian.librarianDAO;
import DatabaseManagment.Members.Member;
import DatabaseManagment.Members.MemberDAO;
import DatabaseManagment.Members.MemberImp;
import DatabaseManagment.Write.Write;
import DatabaseManagment.Write.WriteDAO;
import DatabaseManagment.Write.WriteDAOImp;
import com.example.librarymanagement.Scenes.*;
import components.CustomTable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller {
//    private  final  String MANAGER_EMAIL = "gestbibio20@gmail.com";
    private  final  String MANAGER_EMAIL = "younesbenzaama011@gmail.com";
    private  final  int MAX_ORDERS = 5;
//    private  final  int MAX_Defaulter = 5;
    Routes routes;
    public Book deletedBook;
    Stage deaStage;
    HBox root;
    librarianDAO libDAO = new LibrarianImp();
    AcountDAO acountDAO = new AcountImp();
    BookDAO bookDAO = new BookImp();
    AuthorDAO authorDAO =new AuthorImp();
    MemberDAO memberDAO =new MemberImp();
    WriteDAO writeDAO =new WriteDAOImp();
    CopyDAO copyDAO = new CopyImp();
    IssueBookDAO issueBookDAO=new IssueBookImp();
    List<Author> author_list = new ArrayList<>();
    EmailController emailController;

    public HashMap<String,VBox> windows;
    public Member deletedMember;
    public Member PinedMem;
    private Book PinedBook;
    LocalDate current_date=LocalDate.now();
    private IssueBook selected_Ibook;
    private  String language="English" ;
    private int  library_number;
    private String Password;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getLibrary_number() {
        return library_number;
    }

    public void setLibrary_number(int library_number) {
        this.library_number = library_number;
    }

    private int  librarian_number;

    public int getLibrarian_number() {
        return librarian_number;
    }

    public void setLibrarian_number(int librarian_number) {
        this.librarian_number = librarian_number;
    }

    Controller(Routes routes){
        windows = new HashMap<>();
        this.routes =routes;
    }
    public void setSelected_Ibook(IssueBook issueBook){
        selected_Ibook=issueBook;
    }
    public  IssueBook getSelected_Ibook(){
        return selected_Ibook;
    }
    public void setRoot(HBox root) {
        this.root = root;
    }
    public int getIssuedBooksNumber(){
        int num=0;
        List<IssueBook> issueBooks;
        try {
             issueBooks =issueBookDAO.getAllIssues();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(IssueBook issueBook :issueBooks){
            if(issueBook.getReal_return_date()==null)
                num++;
        }
        return num;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDefaulterNumber(){
        try {
             emailController=new EmailController();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        LocalDate current_date = LocalDate.now();

        int num=0;
        List<IssueBook> issueBooks;
        try {
             issueBooks =issueBookDAO.getAllIssues();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(IssueBook issueBook :issueBooks){
            int is_defaulter =current_date.compareTo(issueBook.getReturn_date());
            if(is_defaulter>=0&&issueBook.getReal_return_date()==null){
                num++;
                if(!issueBook.isNotify()){
                    Member member=MemberSearch(issueBook.getMember_id());
                    member.setId_sn(1);
                    try {
                        memberDAO.updateMember(member);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        Book bookInfo =bookDAO.getBook(copyDAO.getCopy(issueBook.getCopy_id()).getBook_id());
                        if(language.equals("English"))
                            emailController.sendEmail(MemberSearch(issueBook.getMember_id()).getEmail(),"Dear "+member.getMember_Firstname()+" You have exceeded the deadline for returning the book.\n"+
                                    "book id :"+bookInfo.getBookId()+"\n"+
                                    "book title :"+bookInfo.getBookName()+"\n"+
                                    " You must return it as soon as possible","deadline exceeded");
                        else
                            emailController.sendEmail(MemberSearch(issueBook.getMember_id()).getEmail(),member.getMember_Firstname()+
                                    " Vous avez dépassé le délai de retour d'ouvrage," +
                                    "ouvrage id :"+bookInfo.getBookId()+"\n"+
                                    "ouvrage titre :"+bookInfo.getBookName()+"\n"+
                                    " vous devez le retourner au plus vite","dépassé le délai");
                        issueBook.setNotify(true);
                        issueBookDAO.updateIssueBook(issueBook);
                    } catch (GeneralSecurityException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return num;
    }
    public List<String[]>  IssueFilter(int filter_number){
        List<String[]>resList =new ArrayList<>();
        List<IssueBook> issueBooks=null;
        try {
            issueBooks = issueBookDAO.getAllIssues();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(IssueBook issueBook :issueBooks){
            int is_defaulter =current_date.compareTo(issueBook.getReturn_date());
            if(is_defaulter>=0&&issueBook.getReal_return_date()==null){
                if(!issueBook.isNotify()){
                    Member member=MemberSearch(issueBook.getMember_id());
                    member.setId_sn(1);
                    try {
                        memberDAO.updateMember(member);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        Book bookInfo =bookDAO.getBook(copyDAO.getCopy(issueBook.getCopy_id()).getBook_id());

                        if(language.equals("English"))
                            emailController.sendEmail(MemberSearch(issueBook.getMember_id()).getEmail(),"Dear "+member.getMember_Firstname()+
                                    " You have exceeded the deadline for returning the book.\n"+
                                    "book id :"+bookInfo.getBookId()+"\n"+
                                    "book title :"+bookInfo.getBookName()+"\n"+
                                    " You must return it as soon as possible","deadline exceeded");
                        else
                            emailController.sendEmail(MemberSearch(issueBook.getMember_id()).getEmail(),member.getMember_Firstname()+
                                    " Vous avez dépassé le délai de retour d'ouvrage,\n" +
                                    "ouvrage id :"+bookInfo.getBookId()+"\n"+
                                    "ouvrage titre :"+bookInfo.getBookName()+"\n"+
                                    " vous devez le retourner au plus vite","dépassé le délai");
                        issueBook.setNotify(true);
                        issueBookDAO.updateIssueBook(issueBook);
                    } catch (GeneralSecurityException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }


        if(filter_number==0){
            resList= getIssueBooks();
        }else if(filter_number==1){
            if(issueBooks!=null){
                for (IssueBook issueBook:
                        issueBooks) {
                    String id = String.valueOf(issueBook.getIssue_id());
                    String idc= String.valueOf(issueBook.getCopy_id());
                    String idm= String.valueOf(issueBook.getMember_id());
                    String idL= String.valueOf(issueBook.getLibrary_num());
                    String dateI = String.valueOf(issueBook.getIssue_date());
                    LocalDate return_date =issueBook.getReturn_date();
                    String dateR = String.valueOf(return_date);
                    String dateRR = String.valueOf(issueBook.getReal_return_date());
                    String defaulter ;
                    int is_defaulter =current_date.compareTo(return_date);
                    System.out.println("defaulterrr"+String.valueOf(is_defaulter>=0));
                    if (!dateRR.equals("null")){
                        defaulter="false";
                    }else
                    if(is_defaulter>=0){
                        defaulter="true";
                        resList.add(new String[]{id,idc,idm,idL,dateI,dateR,dateRR,defaulter});
                    }else{
                        defaulter="false";
                    }
                }
            }
        }else if(filter_number==2) {
            if(issueBooks!=null){
                for (IssueBook issueBook:
                        issueBooks) {
                    String id = String.valueOf(issueBook.getIssue_id());
                    String idc= String.valueOf(issueBook.getCopy_id());
                    String idm= String.valueOf(issueBook.getMember_id());
                    String idL= String.valueOf(issueBook.getLibrary_num());
                    String dateI = String.valueOf(issueBook.getIssue_date());
                    LocalDate return_date =issueBook.getReturn_date();
                    String dateR = String.valueOf(return_date);
                    String dateRR = String.valueOf(issueBook.getReal_return_date());
                    String defaulter ;
                    int is_defaulter =current_date.compareTo(return_date);
                    System.out.println("defaulterrr"+String.valueOf(is_defaulter>=0));
//                    if (!dateRR.equals("null")){
//                        defaulter="false";
//                    }else
//                    if(is_defaulter>=0){
//                        defaulter="true";
//                    }else{
//                        defaulter="false";
//                    }
                    if (!dateRR.equals("null")){
                        resList.add(new String[]{id,idc,idm,idL,dateI,dateR,dateRR,"false"});
                    }
                }
            }

        }else{
            if(issueBooks!=null){
                for (IssueBook issueBook:
                        issueBooks) {
                    String id = String.valueOf(issueBook.getIssue_id());
                    String idc= String.valueOf(issueBook.getCopy_id());
                    String idm= String.valueOf(issueBook.getMember_id());
                    String idL= String.valueOf(issueBook.getLibrary_num());
                    String dateI = String.valueOf(issueBook.getIssue_date());
                    LocalDate return_date =issueBook.getReturn_date();
                    String dateR = String.valueOf(return_date);
                    String dateRR = String.valueOf(issueBook.getReal_return_date());
                    String defaulter ;
                    int is_defaulter =current_date.compareTo(return_date);
                    System.out.println("defaulterrr"+String.valueOf(is_defaulter>=0));
//                    if (!dateRR.equals("null")){
//                        defaulter="false";
//                    }else
                    if(is_defaulter>=0){
                        defaulter="true";
                    }else{
                        defaulter="false";
                    }
                    if (dateRR.equals("null")){
                        resList.add(new String[]{id,idc,idm,idL,dateI,dateR,dateRR,defaulter});
                    }
                }
            }
        }

        return resList;
    }



    public List<String[]>  searchIssueBook(int member_id,String book_id){
        current_date = LocalDate.now();

        List<String[]>resList =new ArrayList<>();
        List<IssueBook> issueBooks =null;
        try {
            issueBooks = issueBookDAO.Search(member_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(issueBooks!=null){
            for (IssueBook issueBook:
                    issueBooks) {
                if(getBookByCopyId(issueBook.getCopy_id()).getBookId().equals(book_id)){
                String id = String.valueOf(issueBook.getIssue_id());
                String idc= String.valueOf(issueBook.getCopy_id());
                String idm= String.valueOf(issueBook.getMember_id());
                String idL= String.valueOf(issueBook.getLibrary_num());
                String dateI = String.valueOf(issueBook.getIssue_date());
                LocalDate return_date =issueBook.getReturn_date();
                String dateR = String.valueOf(return_date);
                String dateRR = String.valueOf(issueBook.getReal_return_date());
                String defaulter ;
                int is_defaulter =current_date.compareTo(return_date);
                System.out.println("defaulterrr"+String.valueOf(is_defaulter>=0));
                if (!dateRR.equals("null")){
                    defaulter="false";
                }else
                if(is_defaulter>=0){
                    defaulter="true";
                }else{
                    defaulter="false";
                }
                if(dateRR.equals("null"))
                    resList.add(new String[]{id,idc,idm,idL,dateI,dateR,dateRR,defaulter});}
            }
        }
        return resList;
    }
    public List<String[]> getIssueBooks(){
        current_date = LocalDate.now();

        List<String[]>resList =new ArrayList<>();
        List<IssueBook> issueBooks =null;
        try {
            issueBooks = issueBookDAO.getAllIssues();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(issueBooks!=null){
            for (IssueBook issueBook:
                 issueBooks) {
                String id = String.valueOf(issueBook.getIssue_id());
                String idc= String.valueOf(issueBook.getCopy_id());
                String idm= String.valueOf(issueBook.getMember_id());
                String idL= String.valueOf(issueBook.getLibrary_num());
                String dateI = String.valueOf(issueBook.getIssue_date());
                LocalDate return_date =issueBook.getReturn_date();
                String dateR = String.valueOf(return_date);
                String dateRR = String.valueOf(issueBook.getReal_return_date());
                String defaulter ;
                int is_defaulter =current_date.compareTo(return_date);
                System.out.println("defaulterrr"+String.valueOf(is_defaulter>=0));
                if (!dateRR.equals("null")){
                    defaulter="false";
                }else
                if(is_defaulter>=0){
                    defaulter="true";
                }else{
                    defaulter="false";
                }
                resList.add(new String[]{id,idc,idm,idL,dateI,dateR,dateRR,defaulter});
            }
        }
        return resList;
    }
    public boolean CheckEmail(librarian user){
        List<librarian> librarians = null;
        try {
            librarians = libDAO.getAllLibrarian();
            if(librarians==null){
                System.out.println("Empty");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (librarian lib:
                librarians) {
                if (lib.getLogin().equals(user.getLogin()))
                    return false;
        }
        return true;
    }
    public boolean AddLibrarian(librarian librarian,String password){
        Acount acount =new Acount(librarian.getLogin(),password);
        try {
           int res= acountDAO.addAcount(acount);
            int res1= libDAO.addLibrarian(librarian);
            if(res!=0&&res1!=0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    public boolean Edituser(librarian prevUser,librarian user,String password){
        Acount acount =null;
        try {
            acount=acountDAO.getAcount(prevUser.getLogin());
            acount.setPassword(password);
            return libDAO.updateLibrarian(user,acount)!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void ShowStage(String StageName){
        if (deaStage!=null)
            deaStage.close();
        deaStage= routes.getRoute(StageName);
        deaStage.show();
    }
    public int getBooksNumber() throws SQLException {
        palceOrder();
        List<Book> books=bookDAO.getAllBooks();
        for (Book book:
             books) {
            System.out.println(book.getBookId());
        }
        return books.size();
    }
    public int getMembersNum() throws SQLException {
        int members_num=0;
        try {
         members_num = memberDAO.getAllMembers().size();
        }catch ( NullPointerException e){
            System.out.println(e);
        }
        return members_num;
    }


    public void ShowStage(String StageName, librarian data){
        if (deaStage!=null)
            deaStage.close();
        deaStage= routes.getRoute(StageName);
        deaStage.show();
    }
    public boolean Login(String userName ,String Password) throws SQLException {
//        librarianDAO lib = new LibrarianImp();
//        librarian librarian = lib.getLibrarian(userName);
        AcountDAO acountDAO = new AcountImp();
        Acount loginAcount = acountDAO.getAcount(userName);
        if (loginAcount!=null){
        if (loginAcount.getPassword().equals(Password)){
            setPassword(loginAcount.getPassword());
            return true;}
        }
       return false;
    }

    public librarian getLib(String lib) throws SQLException {
        librarian libr = null;
        librarianDAO libDAO =new LibrarianImp();
        libr= libDAO.getLibrarian(lib);
        return libr ;
    }
//    String[] english = {"ADD Member","Search","Add member","Delete","Pin","Edit","EDIT BOOK","Member Id","Enter member Id",
//            "First Name","Enter member first name","Last Name","Enter member last name","Address","Enter member address",
//            "Library Number","Submit","Reset"
//    } ;
        String[] english = {"Member Id ","Enter member id ","Book Id ","Enter Book id ","ReIssue","Return","Returned"};

    public VBox windowHandller(String item,int indx,Controller controller,double width,librarian user){
        System.out.println(indx);
        VBox window =null;
        switch (indx){
            case 0:{
                window=new Dashboard(controller,width);
                windows.put(item,window);
                break;
            }
            case 1:{
                try {
                    window=new BookManager(controller,width,user);
                    windows.put(item,window);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case 2 :{
                window=new MembersManagmer(controller,width);
                windows.put(item,window);

                break;
            }
            case 3 :{
                window=new BookIssue(controller,width,user);
                windows.put(item,window);

                break;
            }
            case 4:{
                window=new ReturnBook(controller,width);
                windows.put(item,window);
                break;
            }
            case 5:{
                window=new UserMnager(controller,user);
                windows.put(item,window);
                break;
            }

        }
        return window;
    }
    public VBox setWindow(String item,int indx,Controller controller,double width,librarian user)  {
        System.out.println(width);
        VBox window =null;
        if (item.equals("Logout")||item.equals("Déconnexion")){
            controller.ShowStage("Login");
            return null;
        }
        window =windowHandller(item,indx,controller,width,user);
//        root.getChildren().removeAll();

        int lastNode =root.getChildren().size()-1;

        root.getChildren().remove(lastNode);
        root.getChildren().add(window);
        return window;
    }
    public boolean AddBook(Book book,Author author){
        try {

        bookDAO.addBook(book,author);
        int copy_id =1;
        for (int i=0;i<book.getBookQuantity();i++){
            Copy last_copy =copyDAO.getLastCopy();
            if (last_copy!=null){
             copy_id =last_copy.getCopy_id()+1;
                System.out.println(copy_id);
            }
            int copy_state=1;
            int library_num=1;
            String book_id=book.getBookId();
            Copy copy = new Copy(copy_id,copy_state,library_num,book_id);
            copyDAO.addCopy(copy);
        }
        }catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    public Write getWrite(String book_id, int author_id){
        Write write=null ;
        try {
            write = writeDAO.getWrite(author_id,book_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return write;
    }
    public void setPinedBook(boolean state){
        if(state){
            if(deletedBook!=null){

            PinedBook =deletedBook;
            }
        }else {
            PinedBook=null;
        }
    }
    public Book getPinedBook(){
        return PinedBook;
    }
    public Member getPinedMem(){
        return PinedMem;
    }
    public void setPinedMem(boolean state){
        if(state){
            if(deletedMember!=null){
            PinedMem =deletedMember;
            }
        }else {
            PinedMem=null;
        }
    }
    public Write getWrite(String book_id){
        Write write=null ;
        try {
            write= writeDAO.getWriteBybookID(book_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return write;
    }
    public boolean deleteBook(Controller controller,VBox book_form){
        List<Copy> copies;
        if (deletedBook==null)
            return false;
        int res=0;
        try {
            copies=copyDAO.getCopiesByBookId(deletedBook.getBookId());
            for (Copy copy:
                 copies) {
                if (copy.getCopy_state()!=1)
                    return false;
            }
            int author_id =writeDAO.getWriteBybookID(deletedBook.getBookId()).getAuthor_id();
            int write_res= writeDAO.deletWrite(author_id,deletedBook.getBookId());
            for (Copy copy:copies){
                issueBookDAO.deletIssueBook(copy.getCopy_id());
            }
            if (write_res==1) {
                int copy_delete_res= copyDAO.deletAllCoyies(deletedBook.getBookId());
                if(copy_delete_res==deletedBook.getBookQuantity()){
                    res =bookDAO.deletBook(deletedBook.getBookId());
                    VBox book_manager;
                    if(controller.getLanguage().equals("English"))
                        book_manager =windows.get("Book Manager");
                    else
                        book_manager =windows.get("Gestionnaire de livres");
                    Pane container = (Pane) book_manager.getChildren().get(book_manager.getChildren().size()-1);
                    ScrollPane scrollPane = (ScrollPane) container.getChildren().get(0);
                    CustomTable table = (CustomTable) scrollPane.getContent();
                    table.UpdateTable(controller,bookDAO.getAllBooks(),book_form);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res==1;
    }
    public  boolean AddMember (Member member){
        try {
            memberDAO.addMember(member);
        }catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    public int  NewMemberId(){
        List<Member> members = null;
        try {
            members = getAllMembers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (members.size()==0){
            return 1;
        }else
            return members.get(members.size()-1).getMember_id()+1;
    }
    public boolean  updateMember(Member member){
        try {
            memberDAO.updateMember(member);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public boolean  updateBook(Book book,String prev_id){
        try {
            Book prev_book = bookDAO.getBook(prev_id);
            System.out.println("q"+prev_book.getBookQuantity());
            int quantity_diff = book.getBookQuantity()-prev_book.getBookQuantity();
            System.out.println("diff"+quantity_diff);

            if (quantity_diff<0){
                System.out.println("<"+quantity_diff);
                Copy last_copy =copyDAO.getLastCopy();
                copyDAO.deletCopy(last_copy);
            }else if(quantity_diff>0){
                System.out.println(">"+quantity_diff);
                int copy_id =1;
                for (int i=0;i<quantity_diff;i++){
                    Copy last_copy =copyDAO.getLastCopy();
                    if (last_copy!=null){
                        copy_id =last_copy.getCopy_id()+1;
                        System.out.println("newId"+copy_id);
                    }
                    int copy_state=1;
                    int library_num=1;
                    String book_id=book.getBookId();
                    Copy copy = new Copy(copy_id,copy_state,library_num,book_id);
                    copyDAO.addCopy(copy);}
            }

            boolean update_state = bookDAO.updateBook(book,prev_id);
            if (update_state){
                System.out.println("up done");
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public  List<Book>getAllBooks() throws SQLException {
        return bookDAO.getAllBooks();
    }
    public  List<Member>getAllMembers() throws SQLException {
        return memberDAO.getAllMembers();
    }
    public Book getBookByCopyId(int copy_id){
        List<Copy> copies;
        try {
             copies= copyDAO.getAllCopies();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Copy copy:copies){
            if (copy.getCopy_id()==copy_id){
                return getBookByID(copy.getBook_id());
            }
        }
        return null;
    }
    public int getAviliableBooks(String book_id){
        int count=0;
        List<Copy> copies= null;
        try {
            copies = copyDAO.getCopiesByBookId(book_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Copy copy:
                copies) {
            if (copy.getCopy_state()==1)
                count++;
        }
        return count;
    }
    public boolean addAuthor(String name){
        try {
            List<Author>authors =authorDAO.getAuthors();
            int new_id=1;
            if (authors.size()!=0)
            {
                new_id = authors.get(authors.size()-1).getAuthor_id()+1;
            }
            return authorDAO.addAuthor(new Author(new_id,name));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public Author getAuthor(String name) throws SQLException {
        Author author1=null;
        for (Author author:
             author_list) {
            if (author.getAuthor_name().equals(name))
                author1= author;
        }
        author1 =authorDAO.getAuthorByName(name);
        return author1;
    }
    public Author getAuthorById(int author_id) {
        Author author1=null;
        for (Author author:
             author_list) {
            if (author.getAuthor_id()==author_id)
                author1= author;
        }
        try {
            author1 =authorDAO.getAuthor(author_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        author_list.add(author1);
        return author1;
    }
    public Book getBookByID(String book_id){
        Book book=null;
        try {
            book=bookDAO.getBook(book_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }
    public Member MemberSearch(int member_id ){
        Member member=null;
        try {
            member =memberDAO.getMember(member_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return member;
    }
    public IssueBook getIssueBookByMember(int member_id){
        IssueBook issueBook=null;
        try {
            issueBook=issueBookDAO.getIssueBookByMemberID(member_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return issueBook;
    }
    public List<IssueBook> getIssueBooksByMember(int member_id){
        List<IssueBook> issueBooks=null;
        try {
            issueBooks=issueBookDAO.getIssueBooksByMemberID(member_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return issueBooks;
    }
//    getLastIssueBook
public  boolean ReIssueBook(IssueBook issueBook){
    IssueBook last_issueBook;
    int res=0;
    int new_id=1;
    try {
        last_issueBook =issueBookDAO.getLastIssueBook();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    if (last_issueBook!=null){
        new_id=last_issueBook.getIssue_id()+1;

    }
    issueBook.setIssue_id(new_id);
    try {
        res= issueBookDAO.addIssueBook(issueBook);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    if (res>0){
        int update_res = 0;
        try {
            update_res = copyDAO.disableCopy(issueBook.getCopy_id());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (update_res==1){
            System.out.println("updated");
            return true;
        }
    }
    return false;
}
    public  boolean addIssueBook(LocalDate issue_date,LocalDate return_date,int member_id,String book_id,int copy_id) throws SQLException {
        IssueBook last_issueBook;
        int res=0;
        int new_id=1;
        try {
                 last_issueBook =issueBookDAO.getLastIssueBook();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        if (last_issueBook!=null){
            new_id=last_issueBook.getIssue_id()+1;

        }
        IssueBook issueBook = new IssueBook(new_id,getLibrarian_number(),member_id,copy_id,issue_date,return_date);
        try {
            res= issueBookDAO.addIssueBook(issueBook);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (res>0){
            int update_res =copyDAO.disableCopy(copy_id);
            if (update_res==1){
                return true;
            }
        }
        return false;
    }
    public int bookAvailableCheck(String book_id){
        List<Copy> copies=null;
        Copy available_copy=null;
        try {
           copies= copyDAO.getCopiesByBookId(book_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (copies==null)
            return 0;
        else{
            int count=0;
            for (Copy copy:
                    copies) {
                if (copy.getCopy_state()==1){

                    available_copy=copy;
                    count++;
                }
            }
            if (count!=0)
                return available_copy.getCopy_id();
        }

        return -1;
    }
    public boolean deleteMember (Controller controller,VBox member_form){
        int res = 0;
        try {
            IssueBook issueBook=controller.getIssueBookByMember(this.deletedMember.getMember_id());
            if (issueBook!=null)
                return false;
            issueBookDAO.deletIssueBookBymember(this.deletedMember.getMember_id());
            res =memberDAO.deletMember(this.deletedMember.getMember_id());
            if (res==1){
                VBox members_manager;
                if(controller.getLanguage().equals("English"))
                 members_manager =windows.get("Members Manager");
                else
                 members_manager =windows.get("Gestionnaire de membres");
                Pane container = (Pane) members_manager.getChildren().get(members_manager.getChildren().size()-1);
                ScrollPane scrollPane = (ScrollPane) container.getChildren().get(0);
                CustomTable table = (CustomTable) scrollPane.getContent();
                table.UpdateTable(controller,null,memberDAO.getAllMembers(),member_form);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res==1;
    }
    public void  setDeletedBook(Book book){
        this.deletedBook=book;
    }
    public void  setDeletedMember(Member member){
        this.deletedMember=member;
    }


    public boolean returnBook(int member_Id,String book_id){
        boolean res= false;
        DateTimeFormatter df= DateTimeFormatter.ofPattern("mm/dd/yyyy HH:mm:ss");
        LocalDate todays_date=LocalDate.now();
        System.out.println(todays_date);
        List<Copy> copies=new ArrayList<>();
        IssueBook issueBook;
        boolean return_status=false;
        int copy_id ;
        try {
            issueBook=issueBookDAO.getIssueBookByMemberID(member_Id);
            issueBook.setReal_return_date(todays_date);
//            copies =copyDAO.getCopiesByBookId(book_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            return_status =issueBookDAO.ReturnIssueBook(issueBook);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (return_status){
            try {
                System.out.println(issueBook.getCopy_id());
                int enable_copy=copyDAO.enableCopy(issueBook.getCopy_id());
                if (enable_copy>0)
                    res =true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
//        if(copies.size()>0){
//            for (Copy copy :copies) {
//                if (copy.)
//            }
//        }

        return res;
    }


    public IssueBook getIssue(int issue_id){
        IssueBook issueBook=null;
        try {
            issueBook =issueBookDAO.getIssueBook(issue_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return issueBook;
    }

    public int getNewOrderId(){
        BookOrderDAO bookOrderDAO = new BokkOrderImmp();
        List<BookOrder> orders1=new ArrayList<>();
        try {
            orders1=bookOrderDAO.getAllBookOrders();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(orders1.size()==0)
            return 1;
        else {
            int last_id=orders1.get(orders1.size()-1).getOrder_id();
            return last_id+1;
        }
    }
    public int order_num(String isbn){
        BookOrderDAO bookOrderDAO = new BokkOrderImmp();
        BookOrder order;
        try {
            order =bookOrderDAO.getBookOrderByBook(isbn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(order!=null)
            return order.getOrder_num();
        return 0;
    }
    public List<BookOrder> getPlacedOrders(){
        BookOrderDAO bookOrderDAO = new BokkOrderImmp();
        List<BookOrder> orders;
        List<BookOrder> placed_orders = null;
        try {
            orders=bookOrderDAO.getAllBookOrders();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(orders!=null){
            placed_orders=new ArrayList<>();
            for (BookOrder order:orders){
                if(order.getOrder_num()==MAX_ORDERS)
                    placed_orders.add(order);
            }
        }
        return placed_orders;
    }
    public boolean palceOrder(){
        String englishOrder ="[book inventory notice]\n" +
                "refill your inventory with this missing books :\n" +
                "";
        String frenchOrder ="[book inventory notice]\n" +
                "refill your inventory with this missing books :\n" +
                "";
        String englishSub="book inventory notice";
        String frenchSub="book inventory notice";
        String order ;
        String subject ;
       order= language.equals("English")?englishOrder:frenchOrder;
        subject= language.equals("English")?englishSub:frenchSub;
        List<BookOrder> placed_orders = getPlacedOrders();
        if (placed_orders.size()>0){
            order+=getOrderList(placed_orders);
            try {
                EmailController emailController= new EmailController();
                emailController.sendEmail(MANAGER_EMAIL,order,subject);
                for(BookOrder order1: placed_orders){
                    ResetOrder(order1.getIsbn());
                }
            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }
    public String getOrderList(List<BookOrder> placed_orders){
        String orders_list="";
        int i=0;
        for (BookOrder bookOrder:placed_orders){
            i++;
            orders_list+=i+"-"+bookOrder.getIsbn()+"\n";
        }
        return orders_list;
    }
    public void ResetOrder(String isbn){
        BookOrderDAO bookOrderDAO = new BokkOrderImmp();
        try {
            bookOrderDAO.ResetOrder(isbn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





    public void orderHandlle(String isbn){
        if(isbn==null)
            isbn=deletedBook.getBookId();
        BookOrderDAO bookOrderDAO = new BokkOrderImmp();
        BookOrder order = null;
        try {
            order =bookOrderDAO.getBookOrderByBook(isbn);
        if(order==null){
            order= new BookOrder(getNewOrderId(),isbn,1);
            bookOrderDAO.addBook(order);
            System.out.println("we got a new order == new order num "+order.getOrder_num());

        }else{
            System.out.println("we got an old order == prev order num "+order.getOrder_num());
            order.setOrder_num(order.getOrder_num()+1);
            System.out.println("we got a new order == current order num "+order.getOrder_num());
            bookOrderDAO.updateBook(order);
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void blockMember(Member member){
        try {
            memberDAO.updateMember(member);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendUnblockMsg(Member member){
        String message;
        String subject="["+member.getMember_Firstname()+"]";
        if(language.equals("English")){
            message="Your account has been unblocked, you can now resume borrowing books";

        }else{
            message="Votre compte a été Débloquer, vous pouvez maintenant reprendre l'emprunt de livres";
        }
        try {
            emailController.sendEmail(member.getEmail(),message,subject);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendblockMsg(Member member){
        String message;
        String subject="["+member.getMember_Firstname()+"]";
        if(language.equals("English")){
            message="You have been banned for damaging library property." +
                    " To lift the ban, please contact the administration to take the necessary action";

        }else{
            message="Vous avez été banni pour avoir endommagé la propriété de la bibliothèque. Pour lever l'interdiction, veuillez contacter l'administration pour prendre les mesures nécessaires";
        }
        try {
            emailController.sendEmail(member.getEmail(),message,subject);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}