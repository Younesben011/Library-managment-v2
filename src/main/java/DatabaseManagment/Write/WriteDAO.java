package DatabaseManagment.Write;

import DatabaseManagment.Members.Member;

import java.sql.SQLException;
import java.util.List;

public interface WriteDAO {
    public Write getWrite(int author_id,String book_id) throws SQLException;
    public Write getWriteBybookID(String book_id) throws SQLException;

    public List<Write> getAllWrites() throws SQLException;
    public List<Write> getWritesByAuthorId(int author_id) throws SQLException;

    public void updateWrite(Write write) throws SQLException;
    public void addWrite(Write write) throws SQLException;
    public int deletWrite(int author_id,String book_id) throws SQLException;
}
