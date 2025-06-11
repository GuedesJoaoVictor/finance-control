package control.finance.csi.service;

import control.finance.csi.dao.UserDAO;
import control.finance.csi.model.User;
import control.finance.csi.util.GetSessionAtributtes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginService {

    public void login(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || password == null) {
            String message = "Todos os campos devem ser preenchidos!";
            req.setAttribute("message", message);
            RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
            try {
                rd.forward(req, resp);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        User user = UserDAO.findByEmail(email);

        if(user != null && user.getEmail().equals(email) && user.getPassword().equals(password)) {
            // Usuario autenticdo com sucesso
            req.getSession().setAttribute("user", user);
            GetSessionAtributtes.setAttributtes(req);
            // Mandar também além dos bancos, as transações feitas. Tanto as bonificações quanto os descontos.
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/home.jsp");
            try {
                rd.forward(req, resp);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        String message = "Senha ou Email incorretos.";
        req.setAttribute("message", message);
        RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
        try {
            rd.forward(req, resp);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
