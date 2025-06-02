package control.finance.csi.service;

import control.finance.csi.dao.BankDAO;
import control.finance.csi.dao.CategoryDAO;
import control.finance.csi.dao.RevenuesDAO;
import control.finance.csi.dao.UserBankDAO;
import control.finance.csi.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class RevenuesService {

    public void redirectToRevenues(HttpServletRequest req, HttpServletResponse resp) {
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
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/create-revenue.jsp");
        try {
            rd.forward(req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createRevenue(HttpServletRequest req, HttpServletResponse resp) {
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

        Revenues revenues = new Revenues(user.getCpf(), description, value, date, categoryId, bankId);
        RevenuesDAO.create(revenues);

        try {
            resp.sendRedirect(req.getContextPath() + "/bank-info?userBankId=" + userBankId);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteRevenue(HttpServletRequest req, HttpServletResponse resp) {
        int revenueId = Integer.parseInt(req.getParameter("id"));

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
}
