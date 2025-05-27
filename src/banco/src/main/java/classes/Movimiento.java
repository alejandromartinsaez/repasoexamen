package classes;

import java.sql.Date;

public class Movimiento {
    //Attributes
    private int id, codCuenta, cantidad;
    private String tipo;
    private Date fecha;

    //Constructor
    public Movimiento(){};

    public Movimiento(int id, int codCuenta, int cantidad, String tipo, Date fecha) {
        this.id = id;
        this.codCuenta = codCuenta;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.fecha = fecha;
    }

    //Methods

    // getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodCuenta() {
        return codCuenta;
    }

    public void setCodCuenta(int codCuenta) {
        this.codCuenta = codCuenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
