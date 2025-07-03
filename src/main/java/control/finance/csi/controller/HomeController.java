package control.finance.csi.controller;

import control.finance.csi.util.GetSessionAtributtes;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//@WebServlet("/home")
@Controller
@RequestMapping("/home")
public class HomeController extends HttpServlet {

    @GetMapping
    public String home(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        GetSessionAtributtes.setAttributtes(model, session);
        return "views/home";
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if(req.getSession().getAttribute("user") == null) {
//             req.getRequestDispatcher("/login.jsp").forward(req, resp);
//             return;
//        }
//        GetSessionAtributtes.setAttributtes(req);
//        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
//    }
}
