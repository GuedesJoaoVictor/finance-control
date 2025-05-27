package control.finance.csi.util;

import control.finance.csi.dao.UserBankDAO;
import control.finance.csi.model.User;
import control.finance.csi.model.UserBank;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;

public class GetSessionAtributtes {
    public static HttpServletRequest setAttributtes(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        ArrayList<UserBank> userBanks = UserBankDAO.findAllByCpf(user.getCpf());
        req.setAttribute("user", user);
        req.setAttribute("userBanks", userBanks);
        return req;
    }
}
