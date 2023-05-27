package DatabaseManagment.Issue;

import DatabaseManagment.Acount.Acount;

import java.sql.SQLException;
import java.util.List;

public interface IssueBookDAO {
    public IssueBook getIssueBook(int id) throws SQLException;
    public IssueBook getLastIssueBook() throws SQLException;
    public IssueBook getIssueBookByMemberID(int member_id) throws SQLException;
    public void deletIssueBookBymember(int id) throws SQLException;
    public List<IssueBook> getAllIssues()throws SQLException;
    public List<IssueBook> Search(int member_id)throws SQLException;


    public void updateIssueBook(IssueBook issueBook) throws SQLException;
    public int  addIssueBook(IssueBook issueBook) throws SQLException;
    public void deletIssueBook(int id) throws SQLException;
    public boolean ReturnIssueBook(IssueBook issueBook) throws SQLException;
}
