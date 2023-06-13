package DatabaseManagment.BookOrder;


import DatabaseManagment.Books.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookOrderDAO {


    public BookOrder getBookOrder(int order_id) throws SQLException;
    public BookOrder getBookOrderByBook(String  isbn) throws SQLException;
    public List<BookOrder> getAllBookOrders() throws SQLException;

    public boolean updateBook(BookOrder book) throws SQLException;
    public void addBook(BookOrder book) throws SQLException;
    public boolean ResetOrder(String isbn) throws SQLException;
    public int deletBook(int id) throws SQLException;

}
