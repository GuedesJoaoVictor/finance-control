package control.finance.csi;

import control.finance.csi.dao.ConectarBD;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = ConectarBD.getConnectionPostgres();
            System.out.println("Conectado com sucesso!");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
