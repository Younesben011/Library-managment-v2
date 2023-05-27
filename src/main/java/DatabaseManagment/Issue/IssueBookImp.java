package DatabaseManagment.Issue;

import DatabaseManagment.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueBookImp implements IssueBookDAO{
    @Override
    public IssueBook getIssueBook(int id) throws SQLException {
        IssueBook issueBook=null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM empreinte where no_emp =?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,id);
        ResultSet res =st.executeQuery();
        while (res.next()){
            int issue_id=res.getInt(1);
            int library_num =res.getInt(4);
            int member_id1=res.getInt(5);
            int copy_id1=res.getInt(6);
            LocalDate issue_date= res.getDate(2).toLocalDate();
            LocalDate return_date=res.getDate(3).toLocalDate();
            LocalDate real_return_date;
            try{
                real_return_date=res.getDate(7).toLocalDate();
            }catch (Exception ex){
                real_return_date=null;
            }
            boolean notify=res.getBoolean(8);
            issueBook=new IssueBook(issue_id,library_num,member_id1,copy_id1,issue_date,return_date,real_return_date,notify);

        }
        return issueBook;
    }

    @Override
    public IssueBook getLastIssueBook() throws SQLException {
        List<IssueBook> issueBooks=new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM empreinte ";
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet res =st.executeQuery();
        while (res.next()){
            int issue_id=res.getInt(1);
            int library_num =res.getInt(4);
            int member_id1=res.getInt(5);
            int copy_id=res.getInt(6);
            LocalDate issue_date= res.getDate(2).toLocalDate();
            LocalDate return_date=res.getDate(3).toLocalDate();
            LocalDate real_return_date;
            try{
                 real_return_date=res.getDate(7).toLocalDate();
            }catch (Exception ex){
                 real_return_date=null;
            }
            boolean notify=res.getBoolean(  8);
            issueBooks.add(
            new IssueBook(issue_id,library_num,member_id1,copy_id,issue_date,return_date,real_return_date,notify));
        }
        int issuesCount=issueBooks.size();
        if (issuesCount==0){
            return null;
        }
        return issueBooks.get(issuesCount-1);
    }

    @Override
    public IssueBook getIssueBookByMemberID(int member_id) throws SQLException {
        IssueBook issueBook=null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM empreinte where No_Ab =? and DateRest_reel IS NULL";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,member_id);
        ResultSet res =st.executeQuery();
        while (res.next()){
            int issue_id=res.getInt(1);
            int library_num =res.getInt(4);
            int member_id1=res.getInt(5);
            int copy_id=res.getInt(6);
            LocalDate issue_date= res.getDate(2).toLocalDate();
            LocalDate return_date=res.getDate(3).toLocalDate();
            LocalDate real_return_date;
            try{
                real_return_date=res.getDate(7).toLocalDate();
            }catch (Exception ex){
                real_return_date=null;
            }
            boolean notify=res.getBoolean(8);
            issueBook=new IssueBook(issue_id,library_num,member_id1,copy_id,issue_date,return_date,real_return_date,notify);

        }
        return issueBook;
    }
    public IssueBook getIssueBookByBookID(int  copy_id) throws SQLException {
        IssueBook issueBook=null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM empreinte where no_Ex =?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,copy_id);
        ResultSet res =st.executeQuery();
        while (res.next()){
            int issue_id=res.getInt(1);
            int library_num =res.getInt(4);
            int member_id1=res.getInt(5);
            int copy_id1=res.getInt(6);
            LocalDate issue_date= res.getDate(2).toLocalDate();
            LocalDate return_date=res.getDate(3).toLocalDate();
            LocalDate real_return_date;
            try{
                real_return_date=res.getDate(7).toLocalDate();
            }catch (Exception ex){
                real_return_date=null;
            }
            boolean notify=res.getBoolean(8);
            issueBook=new IssueBook(issue_id,library_num,member_id1,copy_id1,issue_date,return_date,real_return_date,notify);


        }
        return issueBook;
    }
    public IssueBook getIssue(int member_id,int  copy_id) throws SQLException {
        IssueBook issueBook=null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM empreinte where No_Ab =? and no_Ex=? ";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,member_id);
        st.setInt(2,copy_id);
        ResultSet res =st.executeQuery();
        while (res.next()){
            int issue_id=res.getInt(1);
            int library_num =res.getInt(4);
            int member_id1=res.getInt(5);
            int copy_id1=res.getInt(6);
            LocalDate issue_date= res.getDate(2).toLocalDate();
            LocalDate return_date=res.getDate(3).toLocalDate();
            LocalDate real_return_date;
            try{
                real_return_date=res.getDate(7).toLocalDate();
            }catch (Exception ex){
                real_return_date=null;
            }
            boolean notify=res.getBoolean(8);
            issueBook=new IssueBook(issue_id,library_num,member_id1,copy_id1,issue_date,return_date,real_return_date,notify);

        }
        return issueBook;
    }

    @Override
    public List<IssueBook> getAllIssues() throws SQLException {
        List<IssueBook> issueBooks= new ArrayList<>();

        IssueBook issueBook=null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM empreinte";
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet res =st.executeQuery();
        while (res.next()){
            int issue_id=res.getInt(1);
            int library_num =res.getInt(4);
            int member_id1=res.getInt(5);
            int copy_id1=res.getInt(6);
            LocalDate issue_date= res.getDate(2).toLocalDate();
            LocalDate return_date=res.getDate(3).toLocalDate();
            LocalDate real_return_date;
            try{
                real_return_date=res.getDate(7).toLocalDate();
            }catch (Exception ex){
                real_return_date=null;
            }
            boolean notify=res.getBoolean(8);
            issueBook=new IssueBook(issue_id,library_num,member_id1,copy_id1,issue_date,return_date,real_return_date,notify);
            issueBooks.add(issueBook);
        }
        return issueBooks;
    }

    @Override
    public List<IssueBook> Search(int member_id) throws SQLException {
        List<IssueBook> issueBooks =new ArrayList<>();
        IssueBook issueBook=null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM empreinte where No_Ab =?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,member_id);
        ResultSet res =st.executeQuery();
        while (res.next()){
            int issue_id=res.getInt(1);
            int library_num =res.getInt(4);
            int member_id1=res.getInt(5);
            int copy_id=res.getInt(6);
            LocalDate issue_date= res.getDate(2).toLocalDate();
            LocalDate return_date=res.getDate(3).toLocalDate();
            LocalDate real_return_date;
            try{
                real_return_date=res.getDate(7).toLocalDate();
            }catch (Exception ex){
                real_return_date=null;
            }
            boolean notify=res.getBoolean(8);
            issueBook=new IssueBook(issue_id,library_num,member_id1,copy_id,issue_date,return_date,real_return_date,notify);
            issueBooks.add(issueBook);
        }
        return issueBooks;
    }

    @Override
    public void updateIssueBook(IssueBook issueBook) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE empreinte SET notifie=? where no_emp=? ";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setBoolean(1,issueBook.isNotify());
        st.setInt(2,issueBook.getIssue_id());
        int res =st.executeUpdate();



    }

    @Override
    public int  addIssueBook(IssueBook issueBook) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "insert into empreinte (no_emp,Date_Emp,DateRest_prevu,id_biblio,No_Ab,no_Ex,notifie) values (?,?,?,?,?,?,false)";
        PreparedStatement st = connection.prepareStatement(sql);

        st.setInt(1,issueBook.getIssue_id());
        st.setDate(2, Date.valueOf(issueBook.getIssue_date()));
        st.setDate(3, Date.valueOf(issueBook.getReturn_date()));
        st.setInt(4,issueBook.getLibrary_num());
        st.setInt(5,issueBook.getMember_id());
        st.setInt(6,issueBook.getCopy_id());
//        st.setBoolean(7,issueBook.isNotify());
        int res = st.executeUpdate();
        DatabaseConnection.close(connection,st);
        return res;
    }

    @Override
    public void deletIssueBook(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "DELETE FROM  empreinte where no_Ex=? ";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,id);
        int res =st.executeUpdate();

    }
    @Override
    public void deletIssueBookBymember(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "DELETE FROM  empreinte where No_Ab=? ";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,id);
        int res =st.executeUpdate();

    }

    @Override
    public boolean ReturnIssueBook(IssueBook issueBook) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE empreinte SET DateRest_reel =? where no_emp=? ";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setDate(1, Date.valueOf(issueBook.getReal_return_date()));
        st.setInt(2,issueBook.getIssue_id());
        int res =st.executeUpdate();
        if (res==1)
            return true;
        return false;
    }
}
