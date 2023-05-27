package DatabaseManagment;

import java.sql.*;

public class DatabaseConnection {
    public static Connection getConnection()throws SQLException {
        Connection connection=null;
        String url = "jdbc:mysql://localhost:3306/gestbibio";
        String username="root";
        String password="054090";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    public static  void  close(Connection connection , PreparedStatement stP) throws SQLException{
        closePreSt(stP);
        closeConnection(connection);
    }
    public  static void  closeConnection(Connection connection) throws SQLException{
        connection.close();
    }
    public static  void  closeSt(Statement st)throws SQLException{
        st.close();
    }
    public static void  closePreSt(PreparedStatement st) throws SQLException{
        st.close();
    }

}
