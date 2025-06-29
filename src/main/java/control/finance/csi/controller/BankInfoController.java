package control.finance.csi.controller;

import control.finance.csi.service.BankInfoService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@WebServlet("/bank-info")
@Controller
@RequestMapping("/bank-info")
public class BankInfoController /* extends HttpServlet */ {

    private final BankInfoService bankInfoService = new BankInfoService();

    @GetMapping("/{id}")
    public String bankInfo(Model model, @PathVariable("id") int id, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return bankInfoService.getBankInfo(model, id, session);
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (req.getSession().getAttribute("user") == null) {
//            req.getRequestDispatcher("/login.jsp").forward(req, resp);
//            return;
//        }
//        bankInfoService.getBankInfo(req, resp);
//
//    }
}
