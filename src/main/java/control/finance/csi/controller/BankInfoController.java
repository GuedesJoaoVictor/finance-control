package control.finance.csi.controller;

import control.finance.csi.service.BankInfoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/bank-info")
public class BankInfoController extends HttpServlet {

    private final BankInfoService bankInfoService = new BankInfoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        bankInfoService.getBankInfo(req, resp);

    }
}
