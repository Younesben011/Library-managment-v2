package DatabaseManagment.Copy;

public class Copy {
    int copy_id;
    int copy_state;
    int library_num;
    String book_id;

    public Copy(int copy_id, int copy_state, int library_num, String book_id) {
        this.copy_id = copy_id;
        this.copy_state = copy_state;
        this.library_num = library_num;
        this.book_id = book_id;
    }

    public int getCopy_id() {
        return copy_id;
    }

    public void setCopy_id(int copy_id) {
        this.copy_id = copy_id;
    }

    public int getCopy_state() {
        return copy_state;
    }

    public void setCopy_state(int copy_state) {
        this.copy_state = copy_state;
    }

    public int getLibrary_num() {
        return library_num;
    }

    public void setLibrary_num(int library_num) {
        this.library_num = library_num;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }
}
