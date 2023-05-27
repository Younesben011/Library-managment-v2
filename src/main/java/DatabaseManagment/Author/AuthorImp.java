package DatabaseManagment.Author;

import DatabaseManagment.Books.Book;
import DatabaseManagment.Books.BookDAO;
import DatabaseManagment.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorImp implements AuthorDAO {
    List<Author> authors_list =new ArrayList<>();
    @Override
    public Author getAuthor(int Author_id) throws SQLException {
        Author author =null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM auteur where no_Aut =?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,Author_id);
        ResultSet res =st.executeQuery();
        while (res.next()){
            int idAut =res.getInt("no_Aut") ;
            for (Author author1:
                 authors_list) {
                if (author1.getAuthor_id()==idAut)
                    return author1;
            }
            String Autname = res.getString("nom_Aut");
            author = new Author(idAut,Autname);
            authors_list.add(author);

        }
        DatabaseConnection.closeConnection(connection);
        return author;
    }
    public Author getAuthorByName(String Author_name) throws SQLException {
        Author author =null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM auteur where nom_Aut = ?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1,Author_name);
//        st.setString(2,"%");
        ResultSet res =st.executeQuery();
        while (res.next()){
            int idAut =res.getInt("no_Aut") ;
            String Autname = res.getString("nom_Aut");
            author = new Author(idAut,Autname);
        }
        DatabaseConnection.closeConnection(connection);
        return author;
    }
    public Author getAuthorByPName(String Author_name) throws SQLException {
        Author author =null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM auteur where nom_Aut like ?"+"'%'";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1,Author_name);
        System.out.println(sql);
//        st.setString(2,"%");
        ResultSet res =st.executeQuery();
        while (res.next()){
            int idAut =res.getInt("no_Aut") ;
            String Autname = res.getString("nom_Aut");
            author = new Author(idAut,Autname);
        }
        DatabaseConnection.closeConnection(connection);
        return author;
    }

    @Override
    public List<Author> getAuthors() throws SQLException {
        List<Author> Authors =new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM auteur";
        PreparedStatement st = connection.prepareStatement(sql);
//        st.setString(2,"%");
        ResultSet res =st.executeQuery();
        while (res.next()){
            int idAut =res.getInt("no_Aut") ;
            String Autname = res.getString("nom_Aut");
            Authors.add( new Author(idAut,Autname));
        }
        DatabaseConnection.closeConnection(connection);
        return Authors;
    }

    @Override
    public void updateAuthor(Author author) throws SQLException {

    }

    @Override
    public boolean addAuthor(Author author) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "insert into auteur values (?,?)";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,author.getAuthor_id());
        st.setString(2,author.getAuthor_name());
        int res = st.executeUpdate();
        if (res!=0)
            return true;
        return false;
    }

    @Override
    public void deletAuthor(int id) throws SQLException {

    }


//    @Override
//    public Book getBook(String bookId) throws SQLException {
//        Book book =null;
//        Connection connection = DatabaseConnection.getConnection();
//        String sql = "SELECT * FROM livre where ISBN =?";
//        PreparedStatement st = connection.prepareStatement(sql);
//        st.setString(1,bookId);
//        ResultSet res =st.executeQuery();
//        while (res.next()){
//            String idB =res.getString("id_biblio") ;
//            String Bookname = res.getString("nom_biblio");
//            String BookAuth = res.getString("prenom_biblio");
//            int BookQuantity= res.getInt("no_bib") ;
//            book = new Book(idB,Bookname,BookAuth,BookQuantity);
//        }
//        DatabaseConnection.closeConnection(connection);
//        return book;
//    }
//
//    @Override
//    public List<Book> getAllBooks() throws SQLException {
//        Book book =null;
//        List<Book> books = new ArrayList<>();
//
//        Connection connection = DatabaseConnection.getConnection();
//        String sql = "SELECT * FROM livre ";
//        PreparedStatement st = connection.prepareStatement(sql);
//        ResultSet res =st.executeQuery();
//        while (res.next()){
//            String idB =res.getString("ISBN") ;
//            String Bookname = res.getString("titre");
//            String BookAuth = res.getString("editeur");
//            int BookQuantity= res.getInt("quantité") ;
//            book = new Book(idB,Bookname,BookAuth,BookQuantity);
//            books.add(book);
//        }
//        DatabaseConnection.closeConnection(connection);
//
//        return books;
//    }
//
//    @Override
//    public void updateBook(Book book) throws SQLException {
//
//    }
//
//    @Override
//    public void addBook(Book book) throws SQLException {
//        Connection connection = DatabaseConnection.getConnection();
//        String sql = "insert into livre values (?,?,?,?)";
//        PreparedStatement st = connection.prepareStatement(sql);
//
//        st.setString(1,book.getBookId());
//        st.setString(2,book.getBookName());
//        st.setString(3,book.getBookAuth());
//        st.setInt(4,book.getBookQuantity());
////        System.out.println();
//        int res = st.executeUpdate();
//        DatabaseConnection.close(connection,st);
//    }
//
//    @Override
//    public void deletBook(int id) throws SQLException {
//
//    }
}
