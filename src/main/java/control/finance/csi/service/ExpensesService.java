package control.finance.csi.service;

import control.finance.csi.dao.*;
import control.finance.csi.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class ExpensesService {

    public String redirectToExpenses(int userBankId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        ArrayList<Category> categories = getAllCategoriesPerUser(user);

        UserBank userBank = UserBankDAO.findById(userBankId);
        Bank bank = BankDAO.findById(userBank.getBank_id());

        model.addAttribute("userBankId", userBankId);
        model.addAttribute("user", user);
        model.addAttribute("categories", categories);
        model.addAttribute("bank", bank);
        // A principio apenas redirecionar para a pagina com as categorias
        return "views/create-expense";
    }

    public String createExpense(String valueStr, int categoryId, String dateString,
            String description, int bankId, int userBankId, HttpSession session) throws ParseException {

            BigDecimal value = new BigDecimal(valueStr);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            User user = (User) session.getAttribute("user");

            Expenses expense = new Expenses(
                    user.getCpf(),
                    description,
                    value,
                    date,
                    categoryId,
                    bankId
            );

            ExpensesDAO.create(expense);
            return "redirect:/bank-info/" + userBankId;
    }

    public void deleteExpense(int expenseId) {
        ExpensesDAO.deleteById(expenseId);
    }

    private ArrayList<Category> getAllCategoriesPerUser(User user) {
        ArrayList<Category> categories = new ArrayList<>();
        for(Category category : CategoryDAO.findAll()) {
            if(Objects.equals(category.getUser_cpf(), user.getCpf()) || Objects.isNull(category.getUser_cpf())) {
                categories.add(category);
            }
        }
        return categories;
    }

    public String redirectEditExpense(int userBankId, int expenseId, HttpSession session, Model model) {
        Expenses expense = ExpensesDAO.findById(expenseId);
        User user = (User) session.getAttribute("user");
        UserBank userBank = UserBankDAO.findById(userBankId);
        Bank bank = BankDAO.findById(userBank.getBank_id());
        ArrayList<Category> categories = getAllCategoriesPerUser(user);

        model.addAttribute("expense", expense);
        model.addAttribute("userBankId", userBankId);
        model.addAttribute("expenseId", expenseId);
        model.addAttribute("bank", bank);
        model.addAttribute("categories", categories);

        return "views/edit-expense";
    }

    public void updateExpense(HttpServletRequest req, HttpServletResponse resp) {
        int expenseId = Integer.parseInt(req.getParameter("expenseId"));
        int userBankId = Integer.parseInt(req.getParameter("userBankId"));
        BigDecimal value = BigDecimal.valueOf(Double.parseDouble(req.getParameter("value")));
        String dateString = req.getParameter("date");
        String description = req.getParameter("description");
        int categoryId = Integer.parseInt(req.getParameter("category"));

        System.out.println(expenseId);
        System.out.println(userBankId);
        System.out.println(value);
        System.out.println(description);
        System.out.println(categoryId);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserBank userBank = UserBankDAO.findById(userBankId);
        Bank bank = BankDAO.findById(userBank.getBank_id());
        User user = (User) req.getSession().getAttribute("user");
        Expenses expense = new Expenses(user.getCpf(), description, value, date, categoryId, bank.getId());
        expense.setId(expenseId);

        ExpensesDAO.update(expense);

        try {
            resp.sendRedirect(req.getContextPath() + "/bank-info?userBankId=" + userBankId);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
