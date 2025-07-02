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
import java.util.List;
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

    public String updateExpense(int expenseId, String valueStr, int category_id, String dateString,
                              String description, int userBankId, HttpSession session) throws ParseException {

        System.out.println(List.of(valueStr, category_id, dateString, description, userBankId, valueStr));
        BigDecimal value = new BigDecimal(valueStr);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        User user = (User) session.getAttribute("user");
        UserBank userBank = UserBankDAO.findById(userBankId);
        Bank bank = BankDAO.findById(userBank.getBank_id());
        Expenses expense = new Expenses(user.getCpf(), description, value, date, category_id, bank.getId());
        expense.setId(expenseId);

        ExpensesDAO.update(expense);

        return "redirect:/bank-info/" + userBankId;
    }
}
