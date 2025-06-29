package control.finance.csi.service;

import control.finance.csi.dao.UserDAO;
import control.finance.csi.model.User;
import control.finance.csi.util.GetSessionAtributtes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

public class LoginService {

    public String login(String email, String password, Model model, HttpSession session) {
        User user = UserDAO.findByEmail(email);

        if(user != null && user.getEmail().equals(email) && user.getPassword().equals(password)) {
            // Usuario autenticdo com sucesso
            session.setAttribute("user", user);
            GetSessionAtributtes.setAttributtes(model, session);
            return "redirect:/home";
        }

        String message = "Senha ou Email incorretos.";
        model.addAttribute("message", message);
        return "login";
    }
}
