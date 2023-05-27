package DatabaseManagment.Members;

import DatabaseManagment.Author.Author;
import DatabaseManagment.Books.Book;

import java.sql.SQLException;
import java.util.List;

public interface MemberDAO {
    public Member getMember(int member_id) throws SQLException;
    public Member getMember(String member_name) throws SQLException;

    public List<Member> getAllMembers() throws SQLException;

    public void updateMember(Member member) throws SQLException;
    public void addMember(Member member) throws SQLException;
    public int deletMember(int member_id) throws SQLException;
}
