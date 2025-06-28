package control.finance.csi.util;

import control.finance.csi.dao.ExpensesDAO;
import control.finance.csi.dao.RevenuesDAO;
import control.finance.csi.dao.UserBankDAO;
import control.finance.csi.model.Expenses;
import control.finance.csi.model.Revenues;
import control.finance.csi.model.User;
import control.finance.csi.model.UserBank;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public class GetSessionAtributtes {
    public static void setAttributtes(Model model) {
        User user = (User) model.getAttribute("user");
        ArrayList<UserBank> userBanks = UserBankDAO.findAllByCpf(user.getCpf());
        userBanks = updateUserBankTotalAmount(userBanks);
        model.addAttribute("userBanks", userBanks);
    }

    private static ArrayList<UserBank> updateUserBankTotalAmount(ArrayList<UserBank> userBanks) {
        if (Objects.isNull(userBanks) || userBanks.isEmpty()) {
            return userBanks;
        }

        for (UserBank userBank : userBanks) {
            BigDecimal totalRevenue = BigDecimal.ZERO;
            BigDecimal totalExpenses = BigDecimal.ZERO;

            // Somar todas as receitas daquele banco
            ArrayList<Revenues> allRevenues = RevenuesDAO.findAllByCpf(userBank.getUser_cpf());
            for (Revenues revenue : allRevenues) {
                if (revenue.getBank_id() == userBank.getBank_id()) {
                    totalRevenue = totalRevenue.add(revenue.getValue());
                }
            }

            // Somar todas as despesas daquele banco
            ArrayList<Expenses> allExpenses = ExpensesDAO.findAllByCpf(userBank.getUser_cpf());
            for (Expenses expense : allExpenses) {
                if (expense.getBank_id() == userBank.getBank_id()) {
                    totalExpenses = totalExpenses.add(expense.getValue());
                }
            }

            BigDecimal newTotalAmount = totalRevenue.subtract(totalExpenses);

            if (!Objects.equals(newTotalAmount, UserBankDAO.findById(userBank.getBank_id()).getTotalAmount())) {
                UserBankDAO.updateTotalAmount(userBank.getBank_id(), newTotalAmount);
            }

            userBank.setTotalAmount(newTotalAmount);
        }

        return userBanks;
    }
}
