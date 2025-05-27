package control.finance.csi.dao;

import control.finance.csi.model.Bank;
import control.finance.csi.model.UserBank;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserBankDAO {

    public static ArrayList<UserBank> findAllByCpf(String user_cpf) {
        ArrayList<UserBank> userBanks = new ArrayList<>();
        try {
            Connection connection = ConectarBD.getConnectionPostgres();
            PreparedStatement stmt = connection.prepareStatement("select * from user_bank where user_cpf = ?");
            stmt.setString(1, user_cpf);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UserBank userBank = new UserBank(
                        rs.getString("name"), rs.getString("user_cpf"),
                        rs.getInt("bank_id"), rs.getBigDecimal("initial_balance")
                );
                userBank.setId(rs.getInt("id"));
                userBanks.add(userBank);
            }
            return userBanks;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static UserBank create(String cpf, String bankName, String nameAccount, BigDecimal initialBalance) {
        try {
            Connection connection = ConectarBD.getConnectionPostgres();
            Bank bank = BankDAO.findByName(bankName);
            PreparedStatement stmt = connection.prepareStatement("insert into user_bank (user_cpf, bank_id, name, initial_balance) values (?, ?, ?, ?)");
            stmt.setString(1, cpf);
            stmt.setInt(2, bank.getId());
            stmt.setString(3, nameAccount);
            stmt.setBigDecimal(4, initialBalance);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new UserBank(
                        rs.getString("name"), rs.getString("user_cpf"),
                        rs.getInt("bank_id"), rs.getBigDecimal("initial_balance")
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
