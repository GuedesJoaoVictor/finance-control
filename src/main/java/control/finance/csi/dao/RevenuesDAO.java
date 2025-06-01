package control.finance.csi.dao;

import control.finance.csi.model.Revenues;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RevenuesDAO {
    public static ArrayList<Revenues> findAllByCpf(String cpf) {
        ArrayList<Revenues> revenues = new ArrayList<>();
        try {
            Connection connection = ConectarBD.getConnectionPostgres();
            PreparedStatement stmt = connection.prepareStatement("select * from revenues where user_cpf = ?");
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
}
