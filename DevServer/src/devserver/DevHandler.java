/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devserver;
import java.sql.*;
import java.util.*;

public class DevHandler {
    
    String query = "SELECT * FROM languages";
    private final String url = "jdbc:postgresql://localhost/plang";
    private final String user = "aleksandarmilanovic";
    private final String password = "";
    
    public Vector getAllLanguages() {
        Connection conn = getSqlConnection();
        Vector<String> response = new Vector<String>();
        try {
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(query); // izvrsavanje upita i rezultat? //
                while(result.next()) {
                    String n = result.getString("naziv");
                    String k = result.getString("kompanija");
                    String i = result.getString("imgurl");
                    String o = result.getString("opis");
                    PLanguage p = new PLanguage(n,k,i,o);
                    
                    response.add(p.toString());
                }
        } catch(SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return response;
    }
    
    public Vector login(String username, String password) {
        String loginQuery = "SELECT * FROM users WHERE username = ? and lozinka = ?";
        Connection conn = getSqlConnection();
        Vector<String> response = new Vector<String>();
        try {
            PreparedStatement st = conn.prepareStatement(loginQuery);
            st.setString(1, username);
            st.setString(2, password);
            
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                response.addElement("Prijava usepala !");
                response.addElement(rs.getString("imeprezime"));
                response.addElement(rs.getString("username"));
            } else {
                response.addElement("database error: no records found.");
            }
        } catch(SQLException sqe) {
            response.addElement("database error: " + sqe.getMessage());
        }
        return response;
    }
    
    public Vector signup(String namesurname, String username, String password){
        String regQuery = "INSERT INTO users (username, imeprezime, lozinka) VALUES (?, ?, ?)";
        Connection conn = getSqlConnection();
        Vector<String> response = new Vector<String>();
        try {
            PreparedStatement st = conn.prepareStatement(regQuery);
            st.setString(1, username);
            st.setString(2, namesurname);
            st.setString(3, password);
            
            int rs = st.executeUpdate();
            
            if(rs > 0) {
                response.addElement("Registrovani ste !");
                response.addElement("Registracija uspela! Prijavite se.");
            } else {
                response.addElement("database error: error while signing up.");
            }
        } catch(SQLException sqe) {
            response.addElement("SQL State: " + sqe.getSQLState());
        }
        return response;
    }
    
    private Connection getSqlConnection() { // konekcija sa bazom podataka
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
