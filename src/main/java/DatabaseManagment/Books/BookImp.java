package DatabaseManagment.Books;

import DatabaseManagment.Author.Author;
import DatabaseManagment.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookImp implements BookDAO {
    List<Book> books_list =new ArrayList<>();

    @Override
    public Book getBook(String bookId) throws SQLException {
        Book book =null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM ouvrage where ISBN =?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1,bookId);
        ResultSet res =st.executeQuery();
        while (res.next()){
            String idB =res.getString("ISBN") ;
            String Bookname = res.getString("titre");
            String BookEditor = res.getString("editeur");
            int BookQuantity= res.getInt("quantité") ;
            int BookType= res.getInt("ouvrage_type") ;
            String disc_long = res.getString("discript_long");
            String disc_short = res.getString("discript_short");
            int cat = res.getInt("catégorie");
            book = new Book(idB,Bookname,BookEditor,BookQuantity,BookType,disc_short,disc_long,cat);
        }
        DatabaseConnection.closeConnection(connection);
        return book;
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        Book book =null;
        List<Book> books = new ArrayList<>();

        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM ouvrage ";
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet res =st.executeQuery();
        while (res.next()){
            String idB =res.getString("ISBN") ;
            String Bookname = res.getString("titre");
            String BookEditor = res.getString("editeur");
            int BookQuantity= res.getInt("quantité") ;
            int BookType= res.getInt("ouvrage_type") ;
            String disc_long = res.getString("discript_long");
            String disc_short = res.getString("discript_short");
            int cat = res.getInt("catégorie");
            book = new Book(idB,Bookname,BookEditor,BookQuantity,BookType,disc_short,disc_long,cat);
            books.add(book);
        }
        DatabaseConnection.closeConnection(connection);

        return books;
    }

    @Override
    public boolean updateBook(Book book,String prev_id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE ouvrage SET ISBN =? ,titre =? ,editeur =? ,quantité =?,ouvrage_type=?,discript_short=?," +
                "discript_long=?,catégorie=?  WHERE ISBN =? ";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1,book.getBookId());
        st.setString(2,book.getBookName());
        st.setString(3,book.getBookEditor());
        st.setInt(4,book.getBookQuantity());
        st.setInt(5,book.getType_int());
        st.setString(6,book.getShort_disc());
        st.setString(7,book.getLong_disc());
        st.setInt(8,book.getCat());
        st.setString(9,prev_id);
        if (st.executeUpdate()!=0){
            st.execute("commit ");
            DatabaseConnection.close(connection,st);
            return true;

        }
        DatabaseConnection.close(connection,st);
        return false;

    }

    @Override
    public void addBook(Book book,Author author) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "insert into ouvrage values (?,?,?,?,?,?,?,?)";
        String sql2 = "insert into ecrire values (?,?)";
        PreparedStatement st = connection.prepareStatement(sql);
        PreparedStatement st2 = connection.prepareStatement(sql2);

        st.setString(1,book.getBookId());
        st.setString(2,book.getBookName());
        st.setInt(3,book.getBookQuantity());
        st.setInt(4,book.getType_int());
        st.setString(5,book.getShort_disc());
        st.setString(6,book.getLong_disc());
        st.setString(7,book.getBookEditor());
        st.setInt(8,book.getCat());

        st2.setString(1,book.getBookId());
        st2.setInt(2,author.getAuthor_id());
//        System.out.println();
        int res = st.executeUpdate();
        int res2 = st2.executeUpdate();
        DatabaseConnection.close(connection,st);
    }

    @Override
    public int deletBook(String id) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        String sql = "delete from ouvrage where ISBN =?";

        PreparedStatement st = connection.prepareStatement(sql);

        st.setString(1,id);

         int res = st.executeUpdate();
        DatabaseConnection.close(connection,st);
        return  res;

    }
}
