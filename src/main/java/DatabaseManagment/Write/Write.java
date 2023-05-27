package DatabaseManagment.Write;

public class Write {
//    int copy_id;
    String book_id;
    int author_id;

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public Write(String book_id, int author_id) {
        this.book_id = book_id;
        this.author_id = author_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }
}
