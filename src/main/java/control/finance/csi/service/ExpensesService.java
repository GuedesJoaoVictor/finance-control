package control.finance.csi.service;

import control.finance.csi.dao.ExpensesDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExpensesService {

    public void createExpense(HttpServletRequest req, HttpServletResponse resp) {

    }

    public void deleteExpense(HttpServletRequest req, HttpServletResponse resp) {
        int expenseId = Integer.parseInt(req.getParameter("id"));

        ExpensesDAO.deleteById(expenseId);

    }
}
