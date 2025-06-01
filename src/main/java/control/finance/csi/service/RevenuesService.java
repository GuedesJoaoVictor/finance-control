package control.finance.csi.service;

import control.finance.csi.dao.RevenuesDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RevenuesService {

    public void createRevenue(HttpServletRequest req, HttpServletResponse resp) {

    }

    public void deleteRevenue(HttpServletRequest req, HttpServletResponse resp) {
        int revenueId = Integer.parseInt(req.getParameter("id"));

        RevenuesDAO.deleteById(revenueId);
    }
}
