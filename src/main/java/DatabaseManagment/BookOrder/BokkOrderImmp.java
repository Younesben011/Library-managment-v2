package DatabaseManagment.BookOrder;

import DatabaseManagment.Books.Book;
import DatabaseManagment.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BokkOrderImmp implements BookOrderDAO {
    @Override
    public BookOrder getBookOrder(int order_id) throws SQLException {
        BookOrder order =null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM historique where id_histo =?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,order_id);
        ResultSet res =st.executeQuery();
        while (res.next()){
            int idO =res.getInt("id_histo") ;
            String idB =res.getString("ISBN") ;
            int order_num= res.getInt("num_echec") ;
            order = new BookOrder(idO,idB,order_num);
        }
        DatabaseConnection.closeConnection(connection);
        return order;
//        return null;
    }
    public BookOrder getBookOrderByBook(String  isbn) throws SQLException {
        BookOrder order =null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM historique where ISBN =?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1,isbn);
        ResultSet res =st.executeQuery();
        while (res.next()){
            int idO =res.getInt("id_histo") ;
            String idB =res.getString("ISBN") ;
            int order_num= res.getInt("num_echec") ;
            order = new BookOrder(idO,idB,order_num);
        }
        DatabaseConnection.closeConnection(connection);
        return order;
//        return null;
    }

    @Override
    public List<BookOrder> getAllBookOrders() throws SQLException {
        List<BookOrder> orders =new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM historique ";
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet res =st.executeQuery();
        while (res.next()){
            int idO =res.getInt("id_histo") ;
            String idB =res.getString("ISBN") ;
            int order_num= res.getInt("num_echec") ;
            orders.add( new BookOrder(idO,idB,order_num));
        }
        DatabaseConnection.closeConnection(connection);
        return orders;
    }

    @Override
    public boolean updateBook(BookOrder book) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE historique SET id_histo =? ,ISBN =? ,num_echec =? WHERE id_histo =? ";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,book.getOrder_id());
        st.setString(2,book.getIsbn());
        st.setInt(3,book.getOrder_num());
        st.setInt(4,book.getOrder_id());
        if (st.executeUpdate()!=0){
            st.execute("commit ");
            DatabaseConnection.close(connection,st);
            return true;
        }
        DatabaseConnection.close(connection,st);
        return false;
    }

    @Override
    public void addBook(BookOrder book) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "insert into historique values (?,?,?)";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,book.getOrder_id());
        st.setString(2,book.getIsbn());
        st.setInt(3,book.getOrder_num());
        int res = st.executeUpdate();
        DatabaseConnection.close(connection,st);
    }

    @Override
    public boolean ResetOrder(String isbn) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE historique SET num_echec =? WHERE ISBN =? ";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,0);
        st.setString(2,isbn);
        if (st.executeUpdate()!=0){
            st.execute("commit ");
            DatabaseConnection.close(connection,st);
            return true;
        }
        DatabaseConnection.close(connection,st);
        return false;
    }

    @Override
    public int deletBook(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "delete from historique where id_histo =?";

        PreparedStatement st = connection.prepareStatement(sql);

        st.setInt(1,id);

        int res = st.executeUpdate();
        DatabaseConnection.close(connection,st);
        return  res;
    }
}
