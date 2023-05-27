package DatabaseManagment.Copy;

import DatabaseManagment.Author.Author;
import DatabaseManagment.Books.Book;

import java.sql.SQLException;
import java.util.List;

public interface CopyDAO {
    public Copy getCopy(int CopyId) throws SQLException;
    public List<Copy> getCopiesByBookId(String book_id) throws SQLException;

    public List<Copy> getAllCopies() throws SQLException;
    public Copy getLastCopy() throws SQLException;

    public void updateCopy(Copy copy,int prev_id) throws SQLException;
    public void addCopy(Copy copy) throws SQLException;
    public int deletCopy(Copy copy) throws SQLException;
    public int disableCopy(int id) throws SQLException;
    public int enableCopy(int id) throws SQLException;
    public int deletAllCoyies(String book_id) throws SQLException;
}
