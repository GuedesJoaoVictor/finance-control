package control.finance.csi.dao;

import control.finance.csi.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoryDAO {

    public static ArrayList<Category> findAll() {
        ArrayList<Category> categories = new ArrayList<>();
        try (Connection connection = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = connection.prepareStatement("select * from category")) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getString("type"), rs.getString("name"), rs.getString("user_cpf"));
                category.setId(rs.getInt("id"));
                categories.add(category);
            }
            return categories;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }
}
