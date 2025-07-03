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
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;

//@WebServlet("/revenues")
@Controller
@RequestMapping("/revenues")
public class RevenuesController extends HttpServlet {

    private final RevenuesService revenuesService = new RevenuesService();

    @PostMapping
    public String createExpense(@RequestParam("value") String valueStr, @RequestParam("category_id") int categoryId,
                                @RequestParam("receipt_date") String dateString, @RequestParam("description") String description, @RequestParam("bank_id") int bankId,
                                @RequestParam("userBankId") int userBankId, HttpSession session) throws ParseException {
        return revenuesService.createRevenue(valueStr, categoryId, dateString, description, bankId, userBankId, session);
    }

    @GetMapping("/{userBankId}")
    public String editRevenue(@PathVariable("userBankId") int userBankId, HttpSession session, Model model) {
        return revenuesService.redirectToRevenues(model, userBankId, session);
    }

    @GetMapping("/change-revenues/{userBankId}/{revenueId}")
    public String changeRevenues(@PathVariable("userBankId") int userBankId, @PathVariable("revenueId") int revenueId, HttpSession session, Model model) {
        return revenuesService.redirectEditRevenue(userBankId, revenueId, session, model);
    }

    @PutMapping("/{revenueId}")
    public String updateExpense(@PathVariable("revenueId") int revenueId, @RequestParam("value") String valueStr,
                                @RequestParam("category_id") int category_id, @RequestParam("date") String dateString, @RequestParam("description") String description,
                                @RequestParam("userBankId") int userBankId, HttpSession session) throws ParseException {
        return revenuesService.updateRevenue(revenueId, valueStr, category_id, dateString, description, userBankId, session);
    }

    @DeleteMapping("/{revenueId}")
    public void deleteExpense(@PathVariable("revenueId") int revenueId) {
        revenuesService.deleteRevenue(revenueId);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        revenuesService.createRevenue(req, resp);
//    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        revenuesService.redirectToRevenues(req, resp);
//    }

//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        revenuesService.deleteRevenue(req, resp);
//    }

//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        revenuesService.updateRevenue(req, resp);
//    }
}
