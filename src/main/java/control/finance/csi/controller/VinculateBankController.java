package control.finance.csi.controller;

import control.finance.csi.service.VinculateBankService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//@WebServlet("/vinculate-bank")
@Controller
@RequestMapping("/vinculate-bank")
public class VinculateBankController extends HttpServlet {

    private final VinculateBankService vinculateBankService = new VinculateBankService();

    @GetMapping
    public String vinculateBank(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "views/vinculate-bank";
    }

    @PostMapping
    public String vinculateBank(Model model, String cpf, String bank, HttpSession session) {
        return vinculateBankService.vinculateBank(model, cpf, bank, session);
    }

    @DeleteMapping("/{userBankId}")
    public String unvinculateBank(Model model, @PathVariable("userBankId") int userBankId, HttpSession session) {
        return vinculateBankService.unvinculateBank(model, userBankId, session);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        vinculateBankService.vinculateBank(req, resp);
//    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/vinculate-bank.jsp");
//        rd.forward(req, resp);
//    }

//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        vinculateBankService.unvinculateBank(req, resp);
//    }
}
