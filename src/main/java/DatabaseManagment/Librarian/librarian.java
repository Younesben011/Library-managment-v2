package DatabaseManagment.Librarian;

public class librarian {
    int id ;
    String name;
    String familyName;
    String login;
    int libraryNum;
    int is_admin;

    public librarian(int id, String name, String familyName, String login, int libraryNum,int is_admin) {
        this.id = id;
        this.name = name;
        this.familyName = familyName;
        this.login = login;
        this.libraryNum = libraryNum;
        this.is_admin=is_admin;
    }

    public int isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getLibraryNum() {
        return libraryNum;
    }

    public void setLibraryNum(int libraryNum) {
        this.libraryNum = libraryNum;
    }
}
