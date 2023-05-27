package DatabaseManagment.Books;

import DatabaseManagment.Author.Author;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO {
    public Book getBook(String bookId) throws SQLException;

    public List<Book> getAllBooks() throws SQLException;

    public boolean updateBook(Book book,String prev_id) throws SQLException;
    public void addBook(Book book, Author author) throws SQLException;
    public int deletBook(String id) throws SQLException;
}
