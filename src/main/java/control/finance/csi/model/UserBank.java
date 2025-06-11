package control.finance.csi.model;

import java.math.BigDecimal;

public class UserBank {
    private int id;
    private String name; // Nome da conta
    private String user_cpf;
    private int bank_id;
    // Fazer o initialBalance virar totalAmount, no caso
    // o tanto que ele tem de dinheiro sobrando, ou devendo pro banco
    private BigDecimal totalAmount;

    public UserBank(String name, String user_cpf, int bank_id, BigDecimal totalAmount) {
        this.name = name;
        this.user_cpf = user_cpf;
        this.bank_id = bank_id;
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_cpf() {
        return user_cpf;
    }

    public void setUser_cpf(String user_cpf) {
        this.user_cpf = user_cpf;
    }

    public int getBank_id() {
        return bank_id;
    }

    public void setBank_id(int bank_id) {
        this.bank_id = bank_id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    @Override
    public String toString() {
        return "UserBank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user_cpf='" + user_cpf + '\'' +
                ", bank_id=" + bank_id +
                ", initial_balance=" + totalAmount +
                '}';
    }

}
