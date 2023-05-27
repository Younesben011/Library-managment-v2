package DatabaseManagment.Author;

import DatabaseManagment.Books.Book;

import java.sql.SQLException;
import java.util.List;

public interface AuthorDAO {
    public Author getAuthor(int Author_id) throws SQLException;
    public Author getAuthorByName(String Author_name) throws SQLException;
    public Author getAuthorByPName(String Author_name) throws SQLException;

    public List<Author> getAuthors() throws SQLException;

    public void updateAuthor(Author author) throws SQLException;
    public boolean addAuthor(Author author) throws SQLException;
    public void deletAuthor(int id) throws SQLException;
}
