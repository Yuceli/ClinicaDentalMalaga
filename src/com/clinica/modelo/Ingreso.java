package com.clinica.modelo;
/**
 *
 * @author Arian Castillo
 */
import java.util.Date;

public class Ingreso  implements java.io.Serializable {

    public Ingreso() {
    }

    public Ingreso(String concepto, String tipoDeIngreso, Double monto, Date fechaDeVenta) {
       this.concepto = concepto;
       this.tipoDeIngreso = tipoDeIngreso;
       this.monto = monto;
       this.fechaDeVenta = fechaDeVenta;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getConcepto() {
        return this.concepto;
    }
    
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
    public String getTipoDeIngreso() {
        return this.tipoDeIngreso;
    }
    
    public void setTipoDeIngreso(String tipoDeIngreso) {
        this.tipoDeIngreso = tipoDeIngreso;
    }
    public Double getMonto() {
        return this.monto;
    }
    
    public void setMonto(Double monto) {
        this.monto = monto;
    }
    
    public Date getFechaDeVenta(){
        return this.fechaDeVenta;
    }
    
    public void setFechaDeVenta(Date fechaDeVenta){
        this.fechaDeVenta = fechaDeVenta;
    }
    
    private Integer id;
    private String concepto;
    private String tipoDeIngreso;
    private Double monto;
    private Date fechaDeVenta;
}


