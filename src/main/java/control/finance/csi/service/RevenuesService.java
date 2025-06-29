package control.finance.csi.service;

import control.finance.csi.dao.BankDAO;
import control.finance.csi.dao.CategoryDAO;
import control.finance.csi.dao.RevenuesDAO;
import control.finance.csi.dao.UserBankDAO;
import control.finance.csi.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

import java.math.BigDecimal;
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

    public String createRevenue(Revenues revenue, HttpSession session, int userBankId) {

        User user = (User) session.getAttribute("user");

        Revenues revenues = new Revenues(user.getCpf(),
                revenue.getDescription(),
                revenue.getValue(),
                revenue.getReceipt_date(),
                revenue.getCategory_id(),
                revenue.getBank_id());

        RevenuesDAO.create(revenues);

        return "redirect:/bank-info/" + userBankId;
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

    public void redirectEditRevenue(HttpServletRequest req, HttpServletResponse resp) {
        int userBankId = Integer.parseInt(req.getParameter("userBankId"));
        int revenueId = Integer.parseInt(req.getParameter("id"));

        Revenues revenue = RevenuesDAO.findById(revenueId);
        User user = (User) req.getSession().getAttribute("user");
        UserBank userBank = UserBankDAO.findById(userBankId);
        Bank bank = BankDAO.findById(userBank.getBank_id());
        ArrayList<Category> categories = getAllCategoriesPerUser(user);

        req.setAttribute("revenue", revenue);
        req.setAttribute("revenueId", revenueId);
        req.setAttribute("userBankId", userBankId);
        req.setAttribute("bank", bank);
        req.setAttribute("categories", categories);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/edit-revenue.jsp");
        try {
            rd.forward(req, resp);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateRevenue(HttpServletRequest req, HttpServletResponse resp) {
        int revenueId = Integer.parseInt(req.getParameter("revenueId"));
        int userBankId = Integer.parseInt(req.getParameter("userBankId"));
        BigDecimal value = BigDecimal.valueOf(Double.parseDouble(req.getParameter("value")));
        String dateString = req.getParameter("date");
        String description = req.getParameter("description");
        int categoryId = Integer.parseInt(req.getParameter("category"));

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
        Revenues revenue = new Revenues(user.getCpf(), description, value, date, categoryId, bank.getId());
        revenue.setId(revenueId);

        RevenuesDAO.update(revenue);

        try {
            resp.sendRedirect(req.getContextPath() + "/bank-info?userBankId=" + userBankId);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
