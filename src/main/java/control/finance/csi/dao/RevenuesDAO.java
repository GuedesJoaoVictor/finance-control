package control.finance.csi.dao;

import control.finance.csi.model.Revenues;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RevenuesDAO {

    public static ArrayList<Revenues> findAllByCpf(String cpf) {
        ArrayList<Revenues> revenues = new ArrayList<>();
        try (Connection connection = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = connection.prepareStatement("select * from revenues where user_cpf = ?")) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Revenues revenue = new Revenues(rs.getString("user_cpf"), rs.getString("description"), rs.getBigDecimal("value"), rs.getDate("receipt_date"), rs.getInt("category_id"), rs.getInt("bank_id"));
                revenue.setId(rs.getInt("id"));
                revenues.add(revenue);
            }
            return revenues;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return revenues;
    }

    public static boolean deleteById(int id) {
        try (Connection connection = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = connection.prepareStatement("delete from revenues where id = ?");) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static Revenues create(Revenues revenue) {
        try (Connection connection = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = connection.prepareStatement(
             "insert into revenues (user_cpf, description, value, receipt_date, category_id, bank_id) values (?, ?, ?, ?, ?, ?)")) {

            stmt.setString(1, revenue.getUser_cpf());
            stmt.setString(2, revenue.getDescription());
            stmt.setBigDecimal(3, revenue.getValue());
            stmt.setDate(4, new Date(revenue.getReceipt_date().getTime()));
            stmt.setInt(5, revenue.getCategory_id());
            stmt.setInt(6, revenue.getBank_id());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                revenue.setId(rs.getInt("id"));
                return revenue;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static Revenues findById(int id) {
        try {
            Connection connection = ConectarBD.getConnectionPostgres();
            PreparedStatement stmt = connection.prepareStatement("select * from revenues where id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Revenues revenue = new Revenues(rs.getString("user_cpf"), rs.getString("description"), rs.getBigDecimal("value"), rs.getDate("receipt_date"), rs.getInt("category_id"), rs.getInt("bank_id"));
                revenue.setId(rs.getInt("id"));
                return revenue;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Revenues update(Revenues revenue) {
        try (Connection connection = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = connection.prepareStatement(
             "update revenues set user_cpf = ?, description = ?, value = ?, receipt_date = ?, category_id = ?, bank_id = ? where id = ?")) {

            stmt.setString(1, revenue.getUser_cpf());
            stmt.setString(2, revenue.getDescription());
            stmt.setBigDecimal(3, revenue.getValue());
            stmt.setDate(4, new Date(revenue.getReceipt_date().getTime()));
            stmt.setInt(5, revenue.getCategory_id());
            stmt.setInt(6, revenue.getBank_id());
            stmt.setInt(7, revenue.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return revenue;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
