package DatabaseManagment.Members;

public class Member {
        int member_id;
        String member_Firstname;
        String member_Lastname;
        String address;
        int library_num;
        int type;
        int id_sn;
        String email;

    public Member(int member_id, String member_Firstname, String member_Lastname, String address, int library_num,int type,String email,int id_sn) {
        this.member_id = member_id;
        this.member_Firstname = member_Firstname;
        this.member_Lastname = member_Lastname;
        this.address = address;
        this.library_num = library_num;
        this.type = type;
        this.email = email;
        this.id_sn = id_sn;
    }

    public int getId_sn() {
        return id_sn;
    }

    public void setId_sn(int id_sn) {
        this.id_sn = id_sn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMember_Firstname() {
        return member_Firstname;
    }

    public void setMember_Firstname(String member_Firstname) {
        this.member_Firstname = member_Firstname;
    }

    public String getMember_Lastname() {
        return member_Lastname;
    }

    public void setMember_Lastname(String member_Lastname) {
        this.member_Lastname = member_Lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLibrary_num() {
        return library_num;
    }

    public void setLibrary_num(int library_num) {
        this.library_num = library_num;
    }
}
