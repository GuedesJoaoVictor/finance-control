package control.finance.csi.dao;

import control.finance.csi.model.Expenses;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ExpensesDAO {

    public static ArrayList<Expenses> findAllByCpf(String cpf) {
        ArrayList<Expenses> expenses = new ArrayList<>();
        try {
            Connection connection = ConectarBD.getConnectionPostgres();
            PreparedStatement stmt = connection.prepareStatement("select * from expenses where user_cpf = ?");
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Expenses current = new Expenses(rs.getString("user_cpf"), rs.getString("description"), rs.getBigDecimal("value"), rs.getDate("expense_date"), rs.getInt("category_id"), rs.getInt("bank_id"));
                current.setId(rs.getInt("id"));
                expenses.add(current);
            }
            return expenses;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return expenses;
    }

    public static boolean deleteById(int id) {
        try {
            Connection connection = ConectarBD.getConnectionPostgres();
            PreparedStatement stmt = connection.prepareStatement("delete from expenses where id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static Expenses create(Expenses expenses) {
        try {
            Connection connection = ConectarBD.getConnectionPostgres();
            PreparedStatement stmt = connection.prepareStatement("insert into expenses (user_cpf, description, value, expense_date, category_id, bank_id) values (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, expenses.getUser_cpf());
            stmt.setString(2, expenses.getDescription());
            stmt.setBigDecimal(3, expenses.getValue());
            stmt.setDate(4, new Date(expenses.getExpense_date().getTime()));
            stmt.setInt(5, expenses.getCategory_id());
            stmt.setInt(6, expenses.getBank_id());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                expenses.setId(rs.getInt("id"));
                return expenses;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Expenses findById(int id) {
        try {
            Connection connection = ConectarBD.getConnectionPostgres();
            PreparedStatement stmt = connection.prepareStatement("select * from expenses where id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Expenses expense = new Expenses(rs.getString("user_cpf"), rs.getString("description"), rs.getBigDecimal("value"), rs.getDate("expense_date"), rs.getInt("category_id"), rs.getInt("bank_id"));
                expense.setId(rs.getInt("id"));
                return expense;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
