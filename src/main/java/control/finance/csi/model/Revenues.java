package control.finance.csi.model;

import java.math.BigDecimal;
import java.util.Date;

public class Revenues {
    private int id;
    private String user_cpf;
    private String description;
    private BigDecimal value;
    private Date receipt_date;
    private int category_id;

    public Revenues(String user_cpf, String description, BigDecimal value, Date receipt_date, int category_id) {
        this.user_cpf = user_cpf;
        this.description = description;
        this.value = value;
        this.receipt_date = receipt_date;
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_cpf() {
        return user_cpf;
    }

    public void setUser_cpf(String user_cpf) {
        this.user_cpf = user_cpf;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getReceipt_date() {
        return receipt_date;
    }

    public void setReceipt_date(Date receipt_date) {
        this.receipt_date = receipt_date;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
