package control.finance.csi.service;

import control.finance.csi.dao.UserDAO;
import control.finance.csi.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

import java.io.IOException;

public class UserService {

    public String create(Model model, User user) {
        if (user.getCpf() == null || user.getName() == null || user.getEmail() == null || user.getPassword() == null) {
            String message = "Todos os campos devem ser preenchidos!";
            model.addAttribute("message", message);
            return "index";
        }

        User userDB = UserDAO.create(new User(user.getCpf(), user.getName(), user.getEmail(), user.getPassword()));

        model.addAttribute("user", userDB);
        return "login";
    }

    public void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cpf = req.getParameter("cpf");

        if (cpf == null) {
            String message = "CPF não informado!";
            System.out.println(UserDAO.findAll());
            req.setAttribute("message", message);
            RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
            rd.forward(req, resp);
            return;
        }

        User user = UserDAO.findByCpf(cpf);

        req.setAttribute("user", user);
        RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
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
            RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
            try {
                rd.forward(req, resp);
            } catch (ServletException | IOException e) {
                System.out.println(e.getMessage());
            }
        }

        UserDAO.delete(cpf);

        RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
        try {
            rd.forward(req, resp);
        } catch (ServletException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
