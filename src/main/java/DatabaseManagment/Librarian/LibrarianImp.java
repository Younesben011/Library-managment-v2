package DatabaseManagment.Librarian;

import DatabaseManagment.Acount.Acount;
import DatabaseManagment.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibrarianImp implements librarianDAO{
    @Override
    public librarian getLibrarian(String libname) throws SQLException {
        librarian lib =null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM bibiothécaire where Login =?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1,libname);
        ResultSet res =st.executeQuery();
        while (res.next()){
            int idL =res.getInt("id_biblio") ;
            String name = res.getString("nom_biblio");
            String familyName = res.getString("prenom_biblio");
            String login = res.getString("Login");
            int libraryNum= res.getInt("no_bib") ;
            int is_admin= res.getInt("est_admin") ;
            lib = new librarian(idL,name,familyName,login,libraryNum,is_admin);
        }
        return lib;
    }
    @Override
    public librarian getLibrarianBYID(int lib_id) throws SQLException {
        librarian lib =null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM bibiothécaire where id_biblio =?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,lib_id);
        ResultSet res =st.executeQuery();
        while (res.next()){
            int idL =res.getInt("id_biblio") ;
            String name = res.getString("nom_biblio");
            String familyName = res.getString("prenom_biblio");
            String login = res.getString("Login");
            int libraryNum= res.getInt("no_bib") ;
            int is_admin= res.getInt("est_admin") ;
            lib = new librarian(idL,name,familyName,login,libraryNum,is_admin);
        }
        return lib;
    }

    @Override
    public List<librarian> getAllLibrarian() throws SQLException {
        List<librarian> librarians=new ArrayList<>();
        librarian lib =null;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM bibiothécaire ";
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet res =st.executeQuery();
        while (res.next()){
            int idL =res.getInt("id_biblio") ;
            String name = res.getString("nom_biblio");
            String familyName = res.getString("prenom_biblio");
            String login = res.getString("Login");
            int libraryNum= res.getInt("no_bib") ;
            int is_admin= res.getInt("est_admin") ;
            lib = new librarian(idL,name,familyName,login,libraryNum,is_admin);
            librarians.add(lib);
        }
        return librarians;

    }

    @Override
    public int updateLibrarian(librarian vol, Acount acount) throws SQLException {
        librarian prevLib = getLibrarianBYID(vol.getId());
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE compte SET Login = ?,mot_de_passe = ? WHERE Login = ?";
        PreparedStatement st2 = connection.prepareStatement(sql);
        st2.setString(1,acount.getLogin());
        st2.setString(2,acount.getPassword());
        st2.setString(3,prevLib.getLogin());
        int res2=st2.executeUpdate();
        sql = "UPDATE bibiothécaire SET id_biblio = ?,nom_biblio = ?,prenom_biblio = ?,Login = ?,no_bib =?,est_admin =?  WHERE id_biblio = ?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,vol.getId());
        st.setString(2,vol.getName());
        st.setString(3,vol.getFamilyName());
        st.setString(4,vol.getLogin());
        st.setInt(5,vol.getLibraryNum());
        st.setInt(6,vol.isIs_admin());
        st.setInt(7,vol.getId());
//        st.setInt(6,vol.g);
        int res1= st.executeUpdate();

        if(res1==1&&res2==1)
            return 1;

        DatabaseConnection.close(connection,st);
        return -1;
    }

    @Override
    public int addLibrarian(librarian user) throws SQLException {
        List<librarian> libs =getAllLibrarian();
        int lastLib=0;
        if(libs.size()>0)
            lastLib =libs.get(libs.size()-1).getId();
        Connection connection = DatabaseConnection.getConnection();
        String sql = "insert into bibiothécaire values (?,?,?,?,?,?)";
        PreparedStatement st = connection.prepareStatement(sql);

        st.setInt(1,lastLib+1);
        st.setString(2,user.getName());
        st.setString(3,user.getFamilyName());
        st.setString(4, user.getLogin());
        st.setInt(5,user.getLibraryNum());
        st.setInt(6,0);
//        System.out.println();
        int res = st.executeUpdate();
        DatabaseConnection.close(connection,st);
        return res;

    }

    @Override
    public void deletLibrarian(int id) throws SQLException {

    }
}
