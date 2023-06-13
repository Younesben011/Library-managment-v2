package DatabaseManagment.Books;

public class Book {
    String bookId ;
    String bookName;
    String bookEditor;
    String short_disc;
    String long_disc;
    int bookQuantity;
    int type_int;
    int cat;
    String type;

    public Book(String bookId , String bookName,String bookEditor,
                int bookQuantity,int type_int,String short_disc,String long_disc,int cat) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookEditor = bookEditor;
        this.bookQuantity = bookQuantity;
        this.type_int = type_int;
        this.short_disc=short_disc;
        this.long_disc=long_disc;
        this.cat=cat;

    }

    public int getType_int() {
        return type_int;
    }

    public String getShort_disc() {
        return short_disc;
    }

    public void setShort_disc(String short_disc) {
        this.short_disc = short_disc;
    }

    public String getLong_disc() {
        return long_disc;
    }

    public void setLong_disc(String long_disc) {
        this.long_disc = long_disc;
    }

    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }

    public void setType_int(int type_int) {
        this.type_int = type_int;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookEditor='" + bookEditor + '\'' +
                ", short_disc='" + short_disc + '\'' +
                ", long_disc='" + long_disc + '\'' +
                ", bookQuantity=" + bookQuantity +
                ", type_int=" + type_int +
                ", cat=" + cat +
                ", type='" + type + '\'' +
                '}';
    }
}
