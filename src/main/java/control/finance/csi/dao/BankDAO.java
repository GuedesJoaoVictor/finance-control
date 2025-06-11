package control.finance.csi.dao;

import control.finance.csi.model.Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BankDAO {
    public static ArrayList<Bank> findAll() {
        ArrayList<Bank> banks = new ArrayList<>();
        try (Connection connection = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = connection.prepareStatement("select * from bank")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Bank bank = new Bank(rs.getString("name"));
                bank.setId(rs.getInt("id"));
                banks.add(bank);
            }

            return banks;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static Bank findById(int id) {
        try {
            Connection connection = ConectarBD.getConnectionPostgres();
            PreparedStatement stmt = connection.prepareStatement("select * from bank where id = ?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Bank bank = new Bank(rs.getString("name"));
                bank.setId(rs.getInt("id"));
                return bank;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static Bank findByName(String name) {
        try (Connection connection = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = connection.prepareStatement("select * from bank where name = ?");) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Bank bank = new Bank(rs.getString("name"));
                bank.setId(rs.getInt("id"));
                return bank;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
