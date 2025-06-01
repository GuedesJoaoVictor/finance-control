package control.finance.csi.controller;

import control.finance.csi.service.RevenuesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/revenues")
public class RevenuesController extends HttpServlet {

    private final RevenuesService revenuesService = new RevenuesService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        revenuesService.createRevenue(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userBankId = Integer.parseInt(req.getParameter("userBankId"));

        req.setAttribute("userBankId", userBankId);
        req.setAttribute("user", req.getSession().getAttribute("user"));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        revenuesService.deleteRevenue(req, resp);
    }
}
