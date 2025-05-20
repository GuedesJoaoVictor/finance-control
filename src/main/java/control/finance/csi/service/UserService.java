package control.finance.csi.service;

import control.finance.csi.dao.UserDAO;
import control.finance.csi.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UserService {

    public void create(HttpServletRequest req, HttpServletResponse resp) {
        String cpf = req.getParameter("cpf");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (cpf == null || name == null || email == null || password == null) {
            String message = "Todos os campos devem ser preenchidos!";
            req.setAttribute("message", message);
            RequestDispatcher rd = req.getRequestDispatcher("/user.jsp");
            try {
                rd.forward(req, resp);
            } catch (ServletException | IOException e) {
                System.out.println(e.getMessage());
            }
        }

        User user = UserDAO.create(new User(cpf, name, email, password));

        req.setAttribute("user", user);
        RequestDispatcher rd = req.getRequestDispatcher("/user.jsp");
        try {
            rd.forward(req, resp);
        } catch (ServletException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cpf = req.getParameter("cpf");

        if (cpf == null) {
            String message = "CPF não informado!";
            System.out.println(UserDAO.findAll());
            req.setAttribute("message", message);
            RequestDispatcher rd = req.getRequestDispatcher("/user.jsp");
            rd.forward(req, resp);
            return;
        }

        User user = UserDAO.findByCpf(cpf);

        req.setAttribute("user", user);
        RequestDispatcher rd = req.getRequestDispatcher("/user.jsp");
        try {
            rd.forward(req, resp);
        } catch (ServletException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        String cpf = req.getParameter("cpf");

        if (cpf == null) {
            String message = "CPF não informado!";
            req.setAttribute("message", message);
            RequestDispatcher rd = req.getRequestDispatcher("/user.jsp");
            try {
                rd.forward(req, resp);
            } catch (ServletException | IOException e) {
                System.out.println(e.getMessage());
            }
        }

        UserDAO.delete(cpf);

        RequestDispatcher rd = req.getRequestDispatcher("/user.jsp");
        try {
            rd.forward(req, resp);
        } catch (ServletException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
