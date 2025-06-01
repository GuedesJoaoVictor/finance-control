package control.finance.csi.service;

import control.finance.csi.dao.CategoryDAO;
import control.finance.csi.dao.RevenuesDAO;
import control.finance.csi.model.Category;
import control.finance.csi.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Objects;

public class RevenuesService {

    public void redirectToRevenues(HttpServletRequest req, HttpServletResponse resp) {
        int userBankId = Integer.parseInt(req.getParameter("userBankId"));
        User user = (User) req.getSession().getAttribute("user");
        ArrayList<Category> categories = getAllCategoriesPerUser(user);

        req.setAttribute("userBankId", userBankId);
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.setAttribute("categories", categories);
        // A principio apenas redirecionar para a pagina com as categorias
    }

    public void createRevenue(HttpServletRequest req, HttpServletResponse resp) {

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
