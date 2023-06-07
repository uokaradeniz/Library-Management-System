package Model.Manager;

import Helper.DbConnection;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    public static boolean loginFetch(String name,String password){
        try {
            String query = "SELECT * FROM users WHERE name = ? AND password = ?";
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful");
                return true;
            } else {
                System.out.println("Invalid login credentials");
                return false;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean register(User user){
        String query = "INSERT INTO users(name,password,email) VALUES (?,?,?)";
        try {
            PreparedStatement pr = DbConnection.getInstance().prepareStatement(query);
            pr.setString(1,user.getName());
            pr.setString(2,user.getPassword());
            pr.setString(3,user.getEmail());

            int response = pr.executeUpdate();

            if (response == -1){
                return false;
            }
            pr.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
