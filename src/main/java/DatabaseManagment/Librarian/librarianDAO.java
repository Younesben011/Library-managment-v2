package DatabaseManagment.Librarian;

import DatabaseManagment.Acount.Acount;

import java.sql.SQLException;
import java.util.List;

public interface librarianDAO {
    public librarian getLibrarian(String libName) throws SQLException;

    public List<librarian> getAllLibrarian() throws SQLException;

    public int addLibrarian(librarian user) throws SQLException;
    public void deletLibrarian(int id) throws SQLException;
    public int updateLibrarian(librarian vol, Acount acount) throws SQLException;
    public librarian getLibrarianBYID(int lib_id) throws SQLException;
}
