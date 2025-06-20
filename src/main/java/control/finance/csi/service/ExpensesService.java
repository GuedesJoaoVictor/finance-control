package control.finance.csi.service;

import control.finance.csi.dao.*;
import control.finance.csi.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class ExpensesService {

    public void redirectToExpenses(HttpServletRequest req, HttpServletResponse resp) {
        int userBankId = Integer.parseInt(req.getParameter("userBankId"));
        User user = (User) req.getSession().getAttribute("user");
        ArrayList<Category> categories = getAllCategoriesPerUser(user);

        UserBank userBank = UserBankDAO.findById(userBankId);
        Bank bank = BankDAO.findById(userBank.getBank_id());

        req.setAttribute("userBankId", userBankId);
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.setAttribute("categories", categories);
        req.setAttribute("bank", bank);
        // A principio apenas redirecionar para a pagina com as categorias
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/create-expense.jsp");
        try {
            rd.forward(req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createExpense(HttpServletRequest req, HttpServletResponse resp) {
        BigDecimal value = BigDecimal.valueOf(Double.parseDouble(req.getParameter("value")));
        int categoryId = Integer.parseInt(req.getParameter("category"));
        String dateString = req.getParameter("date");
        String description = req.getParameter("description");
        int bankId = Integer.parseInt(req.getParameter("bankId"));
        int userBankId = Integer.parseInt(req.getParameter("userBankId"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        User user = (User) req.getSession().getAttribute("user");

        Expenses expenses = new Expenses(user.getCpf(), description, value, date, categoryId, bankId);
        ExpensesDAO.create(expenses);

        try {
            resp.sendRedirect(req.getContextPath() + "/bank-info?userBankId=" + userBankId);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteExpense(HttpServletRequest req, HttpServletResponse resp) {
        int expenseId = Integer.parseInt(req.getParameter("id"));

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

    public void redirectEditExpense(HttpServletRequest req, HttpServletResponse resp) {
        int userBankId = Integer.parseInt(req.getParameter("userBankId"));
        int expenseId = Integer.parseInt(req.getParameter("id"));

        Expenses expense = ExpensesDAO.findById(expenseId);
        User user = (User) req.getSession().getAttribute("user");
        UserBank userBank = UserBankDAO.findById(userBankId);
        Bank bank = BankDAO.findById(userBank.getBank_id());
        ArrayList<Category> categories = getAllCategoriesPerUser(user);

        req.setAttribute("expense", expense);
        req.setAttribute("userBankId", userBankId);
        req.setAttribute("expenseId", expenseId);
        req.setAttribute("bank", bank);
        req.setAttribute("categories", categories);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/edit-expense.jsp");
        try {
            rd.forward(req, resp);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
