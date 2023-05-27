package DatabaseManagment.Author;

public class Author {
    int Author_id;
    String Author_name;

    public Author(int author_id, String author_name) {
        Author_id = author_id;
        Author_name = author_name;
    }

    public int getAuthor_id() {
        return Author_id;
    }

    public void setAuthor_id(int author_id) {
        Author_id = author_id;
    }

    public String getAuthor_name() {
        return Author_name;
    }

    public void setAuthor_name(String author_name) {
        Author_name = author_name;
    }
}
