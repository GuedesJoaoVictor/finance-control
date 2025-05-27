package control.finance.csi.controller;

import control.finance.csi.dao.UserBankDAO;
import control.finance.csi.model.User;
import control.finance.csi.model.UserBank;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/home")
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("user") == null) {
             req.getRequestDispatcher("/login.jsp").forward(req, resp);
             return;
        }
        User user = (User) req.getSession().getAttribute("user");
        ArrayList<UserBank> userBanks = UserBankDAO.findAllByCpf(user.getCpf());
        req.setAttribute("user", user);
        req.setAttribute("userBanks", userBanks);
        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }
}
