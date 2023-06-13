package DatabaseManagment.BookOrder;

public class BookOrder {
    int order_id;
    String isbn;
    int order_num;

    public BookOrder(int order_id, String isbn, int order_num) {
        this.order_id = order_id;
        this.isbn = isbn;
        this.order_num = order_num;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }
}
