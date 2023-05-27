package DatabaseManagment.Acount;

import DatabaseManagment.DatabaseConnection;
import DatabaseManagment.Librarian.librarian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AcountImp implements AcountDAO{
    @Override
    public Acount getAcount(String Login) throws SQLException {
        Acount acount =null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM compte where Login =?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1,Login);
        ResultSet res =st.executeQuery();
        while (res.next()){
            String Loginn = res.getString("Login");
            String Password= res.getString("mot_de_passe");
            acount = new Acount(Loginn,Password);
    }
        return acount;
    }

    @Override
    public List<Acount> getAllAcount() throws SQLException{
        return null;
    }

    @Override
    public void updateAcount(Acount acount) throws SQLException {

    }

    @Override
    public int addAcount(Acount acount) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "insert into compte values (?,?)";
        PreparedStatement st = connection.prepareStatement(sql);

        st.setString(1,acount.getLogin());
        st.setString(2,acount.getPassword());


//        System.out.println();
        int res = st.executeUpdate();
        DatabaseConnection.close(connection,st);
        return res;
    }

    @Override
    public void deletAcount(String Login) throws SQLException {

    }
}
