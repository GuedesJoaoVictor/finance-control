package control.finance.csi.controller;

import control.finance.csi.service.LoginService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

//@WebServlet("/login")
@Controller
public class LoginController /* extends HttpServlet */{

    private final LoginService loginService = new LoginService();

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, String email, String password, HttpSession session) {

        if (email == null || password == null) {
            String message = "Todos os campos devem ser preenchidos!";
            model.addAttribute("message", message);
            return "login";
        }
        return loginService.login(email, password, model, session);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        loginService.login(req, resp);
//    }
}
