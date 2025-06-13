package control.finance.csi.dao;

import control.finance.csi.model.Bank;
import control.finance.csi.model.User;
import control.finance.csi.model.UserBank;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserBankDAO {

    public static ArrayList<UserBank> findAllByCpf(String user_cpf) {
        ArrayList<UserBank> userBanks = new ArrayList<>();
        try (Connection connection = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = connection.prepareStatement("select * from user_bank where user_cpf = ?")) {

            stmt.setString(1, user_cpf);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UserBank userBank = new UserBank(
                        rs.getString("name"), rs.getString("user_cpf"),
                        rs.getInt("bank_id"), rs.getBigDecimal("total_amount")
                );
                userBank.setId(rs.getInt("id"));
                userBanks.add(userBank);
            }
            return userBanks;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static UserBank create(String cpf, String bankName, BigDecimal totalAmount) {
        try (Connection connection = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = connection.prepareStatement("insert into user_bank (user_cpf, bank_id, name, total_amount) values (?, ?, ?, ?)")) {

            Bank bank = BankDAO.findByName(bankName);

            stmt.setString(1, cpf);
            stmt.setInt(2, bank.getId());
            stmt.setString(3, bankName);
            stmt.setBigDecimal(4, totalAmount);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new UserBank(
                        rs.getString("name"), rs.getString("user_cpf"),
                        rs.getInt("bank_id"), rs.getBigDecimal("total_amount")
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static boolean delete(int userBankId) {
        String sqlExpenses = "delete from expenses where user_cpf = ? and bank_id = ?";
        String sqlRevenues = "delete from revenues where user_cpf = ? and bank_id = ?";
        String sqlUserBank = "delete from user_bank where id = ?";

        try (Connection connection = ConectarBD.getConnectionPostgres()) {
            connection.setAutoCommit(false);

            UserBank userBank = findById(userBankId);

            try (PreparedStatement stmt1 = connection.prepareStatement(sqlExpenses);
                 PreparedStatement stmt2 = connection.prepareStatement(sqlRevenues);
                 PreparedStatement stmt3 = connection.prepareStatement(sqlUserBank)) {

                stmt1.setString(1, userBank.getUser_cpf());
                stmt1.setInt(2, userBank.getBank_id());
                stmt1.executeUpdate();

                stmt2.setString(1, userBank.getUser_cpf());
                stmt2.setInt(2, userBank.getBank_id());
                stmt2.executeUpdate();

                stmt3.setInt(1, userBankId);
                int rows = stmt3.executeUpdate();

                connection.commit();
                return rows > 0;
            } catch (Exception e) {
                connection.rollback();
                throw e;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public static UserBank findById(int id) {
        try (Connection connection = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = connection.prepareStatement("select * from user_bank where id = ?");) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                UserBank userBank = new UserBank(rs.getString("name"), rs.getString("user_cpf"), rs.getInt("bank_id"), rs.getBigDecimal("total_amount"));
                userBank.setId(rs.getInt("id"));
                return userBank;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void updateTotalAmount(int userBankId, BigDecimal totalAmount) {
        try (Connection connection = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = connection.prepareStatement("update user_bank set total_amount = ? where id = ?");) {

            stmt.setBigDecimal(1, totalAmount);
            stmt.setInt(2, userBankId);
            stmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
