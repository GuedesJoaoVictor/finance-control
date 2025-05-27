package control.finance.csi.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/vinculate-bank")
public class VinculateBankController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cpf = req.getParameter("cpf");
        String bank = req.getParameter("bank");
        String nameAccount = req.getParameter("name");
        String initialBalance = req.getParameter("initialBalance");

        System.out.println("Cpf: " + cpf);
        System.out.println("Bank: " + bank);
        System.out.println("Name Account: " + nameAccount);
        System.out.println("Initial Balance: " + initialBalance);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/vinculate-bank.jsp");
        rd.forward(req, resp);
    }
}
