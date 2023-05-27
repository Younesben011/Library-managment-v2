package DatabaseManagment.Acount;

import java.sql.SQLException;
import java.util.List;

public interface AcountDAO {
    public Acount getAcount(String Login) throws SQLException;

    public List<Acount> getAllAcount() throws SQLException;

    public void updateAcount(Acount acount) throws SQLException;
    public int addAcount(Acount acount) throws SQLException;
    public void deletAcount(String Login) throws SQLException;
}
