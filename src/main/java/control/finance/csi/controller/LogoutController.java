package control.finance.csi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@WebServlet("/logout")
@Controller
@RequestMapping("/logout")
public class LogoutController extends HttpServlet {

    @GetMapping
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getSession().invalidate();
//        try {
//            resp.sendRedirect("login.jsp");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }

}
