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
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;

public class VinculateBankService {

    public String vinculateBank(Model model, String cpf, String bank, HttpSession session) {
        if (cpf == null || bank == null) {
            String message = "Todos os campos devem ser preenchidos!";
            model.addAttribute("message", message);
            return "redirect:/vinculate-bank";
        }

        if (userIsAlreadyLinked(cpf, bank)) {
            model.addAttribute("message", "Conta já vinculada!");
            return "views/vinculate-bank";
        }

        // Vinculando o usuario ao banco no banco de dados
        UserBankDAO.create(cpf, bank, BigDecimal.ZERO);

        String message = "Conta vinculada com sucesso!";

        GetSessionAtributtes.setAttributtes(model, session);
        model.addAttribute("message", message);
        return "redirect:/home";
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

    public String unvinculateBank(Model model, int userBankId, HttpSession session) {
        try {
            UserBankDAO.delete(userBankId);
            String message = "Conta deletada com sucesso!";
            model.addAttribute("message", message);
            GetSessionAtributtes.setAttributtes(model, session);
            return "redirect:/home";
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/home";
        }
    }
}
