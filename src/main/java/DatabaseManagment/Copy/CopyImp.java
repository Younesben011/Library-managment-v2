package DatabaseManagment.Copy;

import DatabaseManagment.Author.Author;
import DatabaseManagment.Books.Book;
import DatabaseManagment.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CopyImp implements CopyDAO{
    List<Copy> copy_list=new ArrayList<>();
    @Override
    public Copy getCopy(int CopyId) throws SQLException {
        Copy copy =null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM exemplaire where no_Ex =?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,CopyId);
        ResultSet res =st.executeQuery();
        while (res.next()){

            int copy_id = res.getInt(1);
            for (Copy copy1:
                 copy_list) {
                if (copy1.getCopy_id()==copy_id){
                    return  copy1;
                }
            }
            int copy_state= res.getInt(2);
            int library_num= res.getInt(3);
            String book_id= res.getString(4);
            copy=new Copy(copy_id,copy_state,library_num,book_id);
            copy_list.add(copy);
        }
        return copy;
    }

    @Override
    public List<Copy> getCopiesByBookId(String book_id) throws SQLException {
        List<Copy> copies =new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM exemplaire where ISBN =?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1,book_id);
        ResultSet res =st.executeQuery();
        while (res.next()){
            int copy_id = res.getInt(1);
            int copy_state= res.getInt(2);
            int library_num= res.getInt(3);
            String book_id1= res.getString(4);
            copies.add(new Copy(copy_id,copy_state,library_num,book_id1));
        }
        return copies;
    }

    @Override
    public List<Copy> getAllCopies() throws SQLException {
        Copy copy =null;
        List<Copy> copies = new ArrayList<>();

        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM exemplaire ";
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet res =st.executeQuery();
        while (res.next()){
            int copy_id = res.getInt(1);
            int copy_state= res.getInt(2);
            int library_num= res.getInt(3);
            String book_id1= res.getString(4);
            copy = new Copy(copy_id,copy_state,library_num,book_id1);
            copies.add(copy);
        }
        DatabaseConnection.closeConnection(connection);

        return copies;
    }
    @Override
    public Copy getLastCopy() throws SQLException {
        Copy copy =null;
        List<Copy> copies = new ArrayList<>();

        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM exemplaire ";
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet res =st.executeQuery();
        while (res.next()){
            int copy_id = res.getInt(1);
            int copy_state= res.getInt(2);
            int library_num= res.getInt(3);
            String book_id1= res.getString(4);
            copy = new Copy(copy_id,copy_state,library_num,book_id1);
            copies.add(copy);
        }
        DatabaseConnection.closeConnection(connection);
        if (copies.size()>=1)
            return copies.get(copies.size() - 1);
        return null;
    }

    @Override
    public void updateCopy(Copy copy, int prev_id) throws SQLException {

    }


    @Override
    public void addCopy(Copy copy) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "insert into exemplaire values (?,?,?,?)";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,copy.getCopy_id());
        st.setInt(2,copy.getCopy_state());
        st.setInt(3,copy.getLibrary_num());
        st.setString(4,copy.getBook_id());
        int res = st.executeUpdate();
        DatabaseConnection.close(connection,st);
    }

    @Override
    public int deletCopy(Copy copy) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "delete from exemplaire where no_Ex=? and etat_ex=?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,copy.getCopy_id());
        st.setInt(2,copy.getCopy_state());
        int res = st.executeUpdate();
        DatabaseConnection.close(connection,st);
        return res;
    }

    @Override
    public int disableCopy(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE exemplaire SET etat_ex =? where no_Ex=? ";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,0);
        st.setInt(2,id);
        int res =st.executeUpdate();
        return res;
    }

    @Override
    public int enableCopy(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE exemplaire SET etat_ex =? where no_Ex=? ";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,1);
        st.setInt(2,id);
        int res =st.executeUpdate();
        return res;
    }

    @Override
    public int deletAllCoyies(String book_id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "delete from exemplaire where ISBN=?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1,book_id);
        int res = st.executeUpdate();
        DatabaseConnection.close(connection,st);
        return res;
    }
}
