package control.finance.csi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Revenues {
    private int id;
    private String user_cpf;
    private String description;
    private BigDecimal value;
    private LocalDate receipt_date;
    private int category_id;
}
