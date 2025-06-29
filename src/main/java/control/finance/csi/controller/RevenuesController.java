package control.finance.csi.controller;

import control.finance.csi.model.Revenues;
import control.finance.csi.service.RevenuesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

//@WebServlet("/revenues")
@Controller
@RequestMapping("/revenues")
public class RevenuesController extends HttpServlet {

    private final RevenuesService revenuesService = new RevenuesService();

    @PostMapping
    public String create(Revenues revenue, HttpSession session, int userBankId) {
        return revenuesService.createRevenue(revenue, session, userBankId);
    }

    @GetMapping("/{userBankId}")
    public String editRevenue(@PathVariable("userBankId") int userBankId, HttpSession session, Model model) {
        return revenuesService.redirectToRevenues(model, userBankId, session);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        revenuesService.createRevenue(req, resp);
//    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        revenuesService.redirectToRevenues(req, resp);
//    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        revenuesService.deleteRevenue(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        revenuesService.updateRevenue(req, resp);
    }
}
