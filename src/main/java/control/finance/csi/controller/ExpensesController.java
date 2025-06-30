package control.finance.csi.controller;

import control.finance.csi.dao.ExpensesDAO;
import control.finance.csi.model.Expenses;
import control.finance.csi.model.User;
import control.finance.csi.service.ExpensesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//@WebServlet("/expenses")
@Controller
@RequestMapping("/expenses")
public class ExpensesController extends HttpServlet {

    private final ExpensesService expensesService = new ExpensesService();

    @GetMapping("/{userBankId}")
    public String expenses(@PathVariable("userBankId") int userBankId, HttpSession session, Model model) {
        return expensesService.redirectToExpenses(userBankId, session, model);
    }


//    @PostMapping
//    public String createExpense(int userBankId, HttpSession session, Model model, Expenses expense) {
//        return expensesService.createExpense(session, model, expense, userBankId);
//    }

    @PostMapping
    public String createExpense(@RequestParam("value") String valueStr, @RequestParam("category_id") int categoryId,
            @RequestParam("date") String dateString, @RequestParam("description") String description, @RequestParam("bank_id") int bankId,
            @RequestParam("userBankId") int userBankId, HttpSession session) throws ParseException {
        return expensesService.createExpense(valueStr, categoryId, dateString, description, bankId, userBankId, session);
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        expensesService.redirectToExpenses(req, resp);
//    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        expensesService.createExpense(req, resp);
//    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        expensesService.deleteExpense(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        expensesService.updateExpense(req, resp);
    }
}
