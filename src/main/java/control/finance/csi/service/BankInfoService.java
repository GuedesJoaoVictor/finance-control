package control.finance.csi.service;

import control.finance.csi.dao.BankDAO;
import control.finance.csi.dao.ExpensesDAO;
import control.finance.csi.dao.RevenuesDAO;
import control.finance.csi.dao.UserBankDAO;
import control.finance.csi.model.Bank;
import control.finance.csi.model.Expenses;
import control.finance.csi.model.Revenues;
import control.finance.csi.model.UserBank;
import control.finance.csi.util.GetSessionAtributtes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;

public class BankInfoService {

    public void getBankInfo(HttpServletRequest req, HttpServletResponse resp) {
        int userBankId = Integer.parseInt(req.getParameter("userBankId"));
        UserBank current = UserBankDAO.findById(userBankId);
        Bank bank = BankDAO.findById(current.getBank_id());
        ArrayList<Expenses> expenses = getAllBankExpenses(current, bank);
        ArrayList<Revenues> revenues = getAllBankRevenues(current, bank);

        if (expenses.isEmpty()) {
            req.setAttribute("expensesEmpty", "Nenhuma despesa foi registrada nessa conta!");
        }
        if (revenues.isEmpty()) {
            req.setAttribute("revenuesEmpty", "Nenhuma receita foi registrada nessa conta!");
        }

        req.setAttribute("bank", bank);
        req.setAttribute("userBank", current);
        req.setAttribute("expenses", expenses);
        req.setAttribute("revenues", revenues);

        GetSessionAtributtes.setAttributtes(req);
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/bank-info.jsp");
        try {
            rd.forward(req, resp);
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private ArrayList<Expenses> getAllBankExpenses(UserBank current, Bank bank) {
        ArrayList<Expenses> expenses = ExpensesDAO.findAllByCpf(current.getUser_cpf());
        ArrayList<Expenses> allBankExpenses = new ArrayList<>();
        for(Expenses expense : expenses) {
            if(expense.getBank_id() == bank.getId()) {
                allBankExpenses.add(expense);
            }
        }

        return allBankExpenses;
    }

    private ArrayList<Revenues> getAllBankRevenues(UserBank current, Bank bank) {
        ArrayList<Revenues> revenues = RevenuesDAO.findAllByCpf(current.getUser_cpf());
        ArrayList<Revenues> allBankRevenues = new ArrayList<>();
        for(Revenues revenue : revenues) {
            if(revenue.getBank_id() == bank.getId()) {
                allBankRevenues.add(revenue);
            }
        }

        return allBankRevenues;
    }
}
