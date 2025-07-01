//package control.finance.csi.controller;
//
//import control.finance.csi.dao.RevenuesDAO;
//import control.finance.csi.model.Revenues;
//import control.finance.csi.service.RevenuesService;
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@WebServlet("/change-revenues")
//public class ChangeRevenuesController extends HttpServlet {
//
//    private final RevenuesService revenuesService = new RevenuesService();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        revenuesService.redirectEditRevenue(req, resp);
//    }
//}
