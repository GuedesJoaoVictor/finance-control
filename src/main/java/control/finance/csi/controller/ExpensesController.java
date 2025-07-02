package control.finance.csi.controller;

import control.finance.csi.service.ExpensesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;

//@WebServlet("/expenses")
@Controller
@RequestMapping("/expenses")
public class ExpensesController extends HttpServlet {

    private final ExpensesService expensesService = new ExpensesService();

    @GetMapping("/{userBankId}")
    public String expenses(@PathVariable("userBankId") int userBankId, HttpSession session, Model model) {
        return expensesService.redirectToExpenses(userBankId, session, model);
    }

    @GetMapping("/change-expenses/{userBankId}/{expenseId}")
    public String changeExpenses(@PathVariable("userBankId") int userBankId, @PathVariable("expenseId") int expenseId, HttpSession session, Model model) {
        return expensesService.redirectEditExpense(userBankId, expenseId, session, model);
    }

    @PostMapping
    public String createExpense(@RequestParam("value") String valueStr, @RequestParam("category_id") int categoryId,
            @RequestParam("date") String dateString, @RequestParam("description") String description, @RequestParam("bank_id") int bankId,
            @RequestParam("userBankId") int userBankId, HttpSession session) throws ParseException {
        return expensesService.createExpense(valueStr, categoryId, dateString, description, bankId, userBankId, session);
    }

    @PutMapping("/{expenseId}")
    public String updateExpense(@PathVariable("expenseId") int expenseId, @RequestParam("value") String valueStr,
           @RequestParam("category_id") int category_id, @RequestParam("date") String dateString, @RequestParam("description") String description,
           @RequestParam("userBankId") int userBankId, HttpSession session) throws ParseException {
        return expensesService.updateExpense(expenseId, valueStr, category_id, dateString, description, userBankId, session);
    }

    @DeleteMapping("/{expenseId}")
    public void deleteExpense(@PathVariable("expenseId") int expenseId) {
        expensesService.deleteExpense(expenseId);
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        expensesService.redirectToExpenses(req, resp);
//    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        expensesService.createExpense(req, resp);
//    }

//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        expensesService.deleteExpense(req, resp);
//    }

//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        expensesService.updateExpense(req, resp);
//    }
}
