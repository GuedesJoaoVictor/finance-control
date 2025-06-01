package control.finance.csi.model;

public class Category {
    private Integer id;
    private String type; // Fazer um enum de tipos de categoria
    private String name;
    private String user_cpf;

    public Category(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public Category(String type, String name, String user_cpf) {
        this.type = type;
        this.name = name;
        this.user_cpf = user_cpf;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_cpf() {
        return user_cpf;
    }

    public void setUser_cpf(String user_cpf) {
        this.user_cpf = user_cpf;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
