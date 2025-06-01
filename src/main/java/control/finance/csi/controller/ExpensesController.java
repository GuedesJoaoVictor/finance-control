package control.finance.csi.controller;

import control.finance.csi.service.ExpensesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/expenses")
public class ExpensesController extends HttpServlet {

    private final ExpensesService expensesService = new ExpensesService();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        expensesService.deleteExpense(req, resp);
    }
}
