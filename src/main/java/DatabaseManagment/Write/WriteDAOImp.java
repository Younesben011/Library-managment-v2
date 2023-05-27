package DatabaseManagment.Write;

import DatabaseManagment.Books.Book;
import DatabaseManagment.DatabaseConnection;
import DatabaseManagment.Members.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WriteDAOImp implements WriteDAO{
    List<Write> write_list =new ArrayList<>();
    @Override
    public Write getWrite(int author_id, String book_id) throws SQLException {
        Write write =null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "select * from ecrire where ISBN = ? and no_Aut =?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1,book_id);
        st.setInt(2,author_id);
        ResultSet res =st.executeQuery();
        while (res.next()){
            String book_id1 =res.getString("ISBN") ;
            for (Write write1:
                    write_list) {
                if (write1.getBook_id().equals(book_id1))
                    return write1;
            }
            int author_id1 = res.getInt("no_Aut");
            write = new Write(book_id1,author_id1) ;
            write_list.add(write);
        }
        DatabaseConnection.closeConnection(connection);
        return write;
    }
    @Override
    public Write getWriteBybookID(String book_id) throws SQLException {
        Write write =null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "select * from ecrire where ISBN = ? ";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1,book_id);
        ResultSet res =st.executeQuery();
        while (res.next()){
            String book_id1 =res.getString("ISBN") ;
            for (Write write1:
                    write_list) {
                if (write1.getBook_id().equals(book_id1))
                    return write1;
            }
            int author_id1 = res.getInt("no_Aut");
            write = new Write(book_id1,author_id1) ;
            write_list.add(write);
        }
        DatabaseConnection.closeConnection(connection);
        return write;
    }

    @Override
    public List<Write> getAllWrites() throws SQLException {
        return null;
    }

    @Override
    public List<Write> getWritesByAuthorId(int author_id) throws SQLException {
        return null;
    }

    @Override
    public void updateWrite(Write write) throws SQLException {

    }

    @Override
    public void addWrite(Write write) throws SQLException {

    }

    @Override
    public int deletWrite(int author_id, String book_id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "delete from ecrire where ISBN =? and no_Aut=? ";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1,book_id);
        st.setInt(2,author_id);
        int res = st.executeUpdate();
        return res;
    }
}
