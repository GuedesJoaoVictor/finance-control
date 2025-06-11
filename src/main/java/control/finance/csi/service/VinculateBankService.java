package control.finance.csi.service;

import control.finance.csi.dao.BankDAO;
import control.finance.csi.dao.UserBankDAO;
import control.finance.csi.dao.UserDAO;
import control.finance.csi.model.Bank;
import control.finance.csi.model.User;
import control.finance.csi.model.UserBank;
import control.finance.csi.util.GetSessionAtributtes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;

public class VinculateBankService {

    public void vinculateBank(HttpServletRequest req, HttpServletResponse resp) {
        String cpf = req.getParameter("cpf");
        String bank = req.getParameter("bank");
        String nameAccount = req.getParameter("name");

        if (cpf == null || bank == null || nameAccount == null) {
            String message = "Todos os campos devem ser preenchidos!";
            req.setAttribute("message", message);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/vinculate-bank.jsp");
            try {
                rd.forward(req, resp);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return;
        }

        if (userIsAlreadyLinked(cpf, bank)) {
           req.setAttribute("message", "Conta já vinculada!");
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/vinculate-bank.jsp");
            try {
                rd.forward(req, resp);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return;
        }

        // Vinculando o usuario ao banco no banco de dados
        UserBankDAO.create(cpf, bank, nameAccount, BigDecimal.ZERO);

        String message = "Conta vinculada com sucesso!";

        GetSessionAtributtes.setAttributtes(req);
        req.setAttribute("message", message);
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/home.jsp");
        try {
            rd.forward(req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean userIsAlreadyLinked(String cpf, String bankName) {
        ArrayList<UserBank> userBanks = UserBankDAO.findAllByCpf(cpf);
        ArrayList<Bank> banks = BankDAO.findAll();

        if(!userBanks.isEmpty()) {
            for(UserBank userBank : userBanks) {
                for(Bank bank : banks) {
                    if(userBank.getBank_id() == bank.getId() && bank.getName().equals(bankName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void unvinculateBank(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));
            UserBankDAO.delete(id);
            String message = "Conta deletada com sucesso!";
            GetSessionAtributtes.setAttributtes(req);
            req.setAttribute("message", message);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/home.jsp");
            rd.forward(req, resp);
            return;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
