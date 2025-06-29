package control.finance.csi.controller;

import control.finance.csi.service.ExpensesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

//@WebServlet("/expenses")
@Controller
@RequestMapping("/expenses")
public class ExpensesController extends HttpServlet {

    private final ExpensesService expensesService = new ExpensesService();

    @GetMapping("/{userBankId}")
    public String

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        expensesService.redirectToExpenses(req, resp);
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        expensesService.createExpense(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        expensesService.deleteExpense(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        expensesService.updateExpense(req, resp);
    }
}
