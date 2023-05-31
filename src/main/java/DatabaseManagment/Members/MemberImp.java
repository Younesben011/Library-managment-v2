package DatabaseManagment.Members;

import DatabaseManagment.Author.Author;
import DatabaseManagment.Books.Book;
import DatabaseManagment.Books.BookDAO;
import DatabaseManagment.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberImp implements MemberDAO {
    List<Member> members_list =new ArrayList<>();


    @Override
    public Member getMember(int member_id) throws SQLException {
        Member member =null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM abonne where No_Ab =?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,member_id);
        for (Member member1:
                members_list) {
            if (member1.getMember_id()==member_id)
                return member1;
        }
        ResultSet res =st.executeQuery();
        while (res.next()){

            int idM =res.getInt("No_Ab") ;
            String first_name = res.getString("nom");
            String last_name = res.getString("prenom");
            String adress = res.getString("Adress");
            int library_num= res.getInt("no_bib") ;
            int type= res.getInt("type") ;
            String email= res.getString("email") ;
            member = new Member(idM,first_name,last_name,adress,library_num,type,email);
            members_list.add(member);
        }
        DatabaseConnection.closeConnection(connection);
        return member;
    }

    @Override
    public Member getMember(String member_name) throws SQLException {
        Member member =null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM abonne where No_Ab =?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1,member_name);
        for (Member member1:
                members_list) {
            if (member1.getMember_Firstname().equals(member_name))
                return member1;
        }
        ResultSet res =st.executeQuery();
        while (res.next()){

            int idM =res.getInt("No_Ab") ;
            String first_name = res.getString("nom");
            String last_name = res.getString("prenom");
            String adress = res.getString("Adress");
            int library_num= res.getInt("no_bib") ;
            int type= res.getInt("type") ;
            String email= res.getString("email") ;
            member = new Member(idM,first_name,last_name,adress,library_num,type,email);
            members_list.add(member);
        }
        DatabaseConnection.closeConnection(connection);
        return member;
    }

    @Override
    public List<Member> getAllMembers() throws SQLException {
        Member member =null;
        List<Member> members_list2 =new ArrayList<>();

        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM abonne ";
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet res =st.executeQuery();
        while (res.next()){
            int idM =res.getInt("No_Ab") ;
            String first_name = res.getString("nom");
            String last_name = res.getString("prenom");
            String adress = res.getString("Adress");
            int library_num= res.getInt("no_bib") ;
            int type= res.getInt("type") ;
            String email= res.getString("email") ;
            member = new Member(idM,first_name,last_name,adress,library_num,type,email);
            members_list.add(member);
            members_list2.add(member);
        }
        DatabaseConnection.closeConnection(connection);
        return members_list2;
    }

    @Override
    public void updateMember(Member member) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE abonne SET No_Ab = ?,nom = ?,prenom = ?,Adress = ?,no_bib =?,email=?,type=? WHERE No_Ab = ?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,member.getMember_id());
        st.setString(2,member.getMember_Firstname());
        st.setString(3,member.getMember_Lastname());
        st.setString(4,member.getAddress());
        st.setInt(5,member.getLibrary_num());
        st.setString(6,member.getEmail());
        st.setInt(7,member.getType());
        st.setInt(8,member.getMember_id());
        st.executeUpdate();

        DatabaseConnection.close(connection,st);



    }

    @Override
    public void addMember(Member member) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "insert into abonne values (?,?,?,?,?,?,?)";
        PreparedStatement st = connection.prepareStatement(sql);

        st.setInt(1,member.getMember_id());
        st.setString(2,member.getMember_Firstname());
        st.setString(3,member.getMember_Lastname());
        st.setString(4,member.getAddress());
        st.setInt(5,member.getLibrary_num());
        st.setString(6,member.getEmail());
        st.setInt(7,member.getType());

        System.out.println(member.getType());
        int res = st.executeUpdate();
        DatabaseConnection.close(connection,st);

    }

    @Override
    public int deletMember(int member_id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "delete from abonne where No_Ab =?";

        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,member_id);
        int res = st.executeUpdate();

        DatabaseConnection.close(connection,st);
        return res;
    }
}
