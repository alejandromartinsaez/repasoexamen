package classes;

public class Cuenta {
    //Attributes
    private int id, codCliente;
    private double saldo;
    private String tipoCuenta;

    //Constructor
    public Cuenta(){};

    public Cuenta(int id, int codCliente, double saldo, String tipoCuenta) {
        this.id = id;
        this.codCliente = codCliente;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
    }

    //Methods

    // getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
}
