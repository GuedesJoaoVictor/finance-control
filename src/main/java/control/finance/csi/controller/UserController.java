package control.finance.csi.controller;

import control.finance.csi.model.User;
import control.finance.csi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@WebServlet("/user")
@Controller
@RequestMapping("/user")
public class UserController /* extends HttpServlet */ {

    private final UserService userService = new UserService();

    @PostMapping
    public String create(Model model, User user) {
        return userService.create(model, user);
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        userService.findById(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        userService.create(req, resp);
//    }
}
