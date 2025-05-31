package control.finance.csi.dao;

import control.finance.csi.model.Expenses;

import java.sql.Connection;
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
                Expenses current = new Expenses(rs.getString("user_cpf"), rs.getString("description"), rs.getBigDecimal("value"), rs.getDate("expense_date"), rs.getInt("category_id"));
                current.setId(rs.getInt("id"));
                expenses.add(current);
            }
            return expenses;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return expenses;
    }
}
