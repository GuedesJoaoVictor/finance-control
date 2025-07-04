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

public class RevenuesService {

    public String redirectToRevenues(Model model, int userBankId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        ArrayList<Category> categories = getAllCategoriesPerUser(user);

        UserBank userBank = UserBankDAO.findById(userBankId);
        Bank bank = BankDAO.findById(userBank.getBank_id());

        model.addAttribute("userBankId", userBankId);
        model.addAttribute("user", user);
        model.addAttribute("categories", categories);
        model.addAttribute("bank", bank);
        // A principio apenas redirecionar para a pagina com as categorias
        return "views/create-revenue";
    }

    public String createRevenue(String valueStr, int categoryId, String dateString,
        String description, int bankId, int userBankId, HttpSession session) throws ParseException {

        BigDecimal value = new BigDecimal(valueStr);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        User user = (User) session.getAttribute("user");

        Revenues revenue = new Revenues(
                user.getCpf(),
                description,
                value,
                date,
                categoryId,
                bankId
        );

        RevenuesDAO.create(revenue);
        return "redirect:/bank-info/" + userBankId;
    }


    public void deleteRevenue(int revenueId) {
        RevenuesDAO.deleteById(revenueId);
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

    public String redirectEditRevenue(int userBankId, int revenueId, HttpSession session, Model model) {
        Revenues revenue = RevenuesDAO.findById(revenueId);
        User user = (User) session.getAttribute("user");
        UserBank userBank = UserBankDAO.findById(userBankId);
        Bank bank = BankDAO.findById(userBank.getBank_id());
        ArrayList<Category> categories = getAllCategoriesPerUser(user);

        model.addAttribute("revenue", revenue);
        model.addAttribute("revenueId", revenueId);
        model.addAttribute("userBankId", userBankId);
        model.addAttribute("bank", bank);
        model.addAttribute("categories", categories);

        return "views/edit-revenue";
    }

    public String updateRevenue(int revenueId, String valueStr, int category_id, String dateString,
        String description, int userBankId, HttpSession session) throws ParseException {

        BigDecimal value = new BigDecimal(valueStr);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        UserBank userBank = UserBankDAO.findById(userBankId);
        Bank bank = BankDAO.findById(userBank.getBank_id());
        User user = (User) session.getAttribute("user");
        Revenues revenue = new Revenues(user.getCpf(), description, value, date, category_id, bank.getId());
        revenue.setId(revenueId);

        RevenuesDAO.update(revenue);

        return "redirect:/bank-info/" + userBankId;
    }
}
