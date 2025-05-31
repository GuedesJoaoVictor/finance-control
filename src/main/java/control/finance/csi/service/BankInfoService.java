package control.finance.csi.service;

import control.finance.csi.dao.BankDAO;
import control.finance.csi.dao.UserBankDAO;
import control.finance.csi.model.Bank;
import control.finance.csi.model.UserBank;
import control.finance.csi.util.GetSessionAtributtes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BankInfoService {

    public void getBankInfo(HttpServletRequest req, HttpServletResponse resp) {
        int userBankId = Integer.parseInt(req.getParameter("userBankId"));

        UserBank current = UserBankDAO.findById(userBankId);
        Bank bank = BankDAO.findById(current.getBank_id());

        req.setAttribute("bank", bank);
        req.setAttribute("userBank", current);


        GetSessionAtributtes.setAttributtes(req);
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/home.jsp");
        try {
            rd.forward(req, resp);
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
