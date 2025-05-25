package control.finance.csi.dao;

import control.finance.csi.model.UserBank;

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
}
