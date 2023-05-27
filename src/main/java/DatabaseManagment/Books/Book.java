package DatabaseManagment.Books;

public class Book {
    String bookId ;
    String bookName;
    String bookEditor;

    int bookQuantity;

    public Book(String bookId , String bookName,String bookEditor, int bookQuantity) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookEditor = bookEditor;
        this.bookQuantity = bookQuantity;
    }

    public String getBookEditor() {
        return bookEditor;
    }

    public void setBookEditor(String bookEditor) {
        this.bookEditor = bookEditor;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }



    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }
}
