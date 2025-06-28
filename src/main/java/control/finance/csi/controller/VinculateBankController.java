package control.finance.csi.controller;

import control.finance.csi.service.VinculateBankService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@WebServlet("/vinculate-bank")
public class VinculateBankController extends HttpServlet {

    private final VinculateBankService vinculateBankService = new VinculateBankService();

    @PostMapping
    public String vinculateBank(Model model, String cpf, String bank) {
        return vinculateBankService.vinculateBank(model, cpf, bank);
    }

    @DeleteMapping("/${userBankId}")
    public String unvinculateBank(Model model, @PathVariable("userBankId") int userBankId) {
        return vinculateBankService.unvinculateBank(model, userBankId);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        vinculateBankService.vinculateBank(req, resp);
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/vinculate-bank.jsp");
        rd.forward(req, resp);
    }

//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        vinculateBankService.unvinculateBank(req, resp);
//    }
}
