package control.finance.csi.dao;

import control.finance.csi.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO {

    public static ArrayList<User> findAll() {
        ArrayList<User> users = new ArrayList<>();

        try {
            Connection connection = ConectarBD.getConnectionPostgres();

            PreparedStatement stmt = connection.prepareStatement("select * from users");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getString("cpf"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
                users.add(user);
            }

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    public static User create(User user) {
        try {
            Connection connection = ConectarBD.getConnectionPostgres();
            PreparedStatement stmt = connection.prepareStatement("insert into users (cpf, name, email, password) values (?, ?, ?, ?)");
            stmt.setString(1, user.getCpf());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());

            stmt.executeUpdate();

            return user;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static User findById(String cpf) {
        try {
            Connection connection = ConectarBD.getConnectionPostgres();
            PreparedStatement stmt = connection.prepareStatement("select * from users where cpf = ?");
            stmt.setString(1, cpf);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getString("cpf"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
            }

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static User update(User user) {
        try {
            Connection connection = ConectarBD.getConnectionPostgres();
            PreparedStatement stmt = connection.prepareStatement("update users set name = ?, email = ?, password = ? where cpf = ?");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getCpf());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return user;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static boolean delete(String cpf) {
        try {
            Connection connection = ConectarBD.getConnectionPostgres();
            PreparedStatement stmt = connection.prepareStatement("delete from users where cpf = ?");
            stmt.setString(1, cpf);

            return stmt.executeUpdate() > 0;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
