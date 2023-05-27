package DatabaseManagment.Library;

import DatabaseManagment.Author.Author;
import DatabaseManagment.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibDAO implements LibDAOI{
    @Override
    public Lib getLib() throws SQLException {
        List<Lib> libs =new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM library";
        PreparedStatement st = connection.prepareStatement(sql);
//        st.setString(2,"%");
        ResultSet res =st.executeQuery();
        while (res.next()){
            String id =res.getString("id") ;
            boolean state= res.getBoolean("state");
            libs.add( new Lib(id,state));
        }
        DatabaseConnection.closeConnection(connection);
        return libs.get(0);
    }
}
