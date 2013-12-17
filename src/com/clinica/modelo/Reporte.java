/*
 * Notas de tu yo del pasado:  *Quiero poner otra contante de tipo Paragraph solo para el titulo de ingresos y egresos,
 *                              pero no quiero llenar esto de atributos
 *                             *Hay que centrar estos Paragraph en el documento ¿Donde pongo esto?
 */
package com.clinica.modelo;

import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reporte {
    

    public Reporte() {
    }

    public Reporte(String fechaInicial, String fechaFinal, Image encabezado, PdfPTable ingresosTabla, PdfPTable egresosTabla, String rutaDeGuardado) {
        this.setFechaInicial(fechaInicial);
        this.setFechaFinal(fechaFinal);
        this.encabezado = encabezado;
        this.ingresosTabla = ingresosTabla;
        this.egresosTabla = egresosTabla;
        this.setFicheroPDF(rutaDeGuardado);
    }
    
    public Paragraph getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = new Paragraph(fechaInicial);
    }

    public Paragraph getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = new Paragraph(fechaFinal);
    }

    public Image getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(Image encabezado) {
        this.encabezado = encabezado;
    }

    public PdfPTable getIngresosTabla() {
        return ingresosTabla;
    }

    public void setIngresosTabla(PdfPTable ingresosTabla) {
        this.ingresosTabla = ingresosTabla;
    }

    public PdfPTable getEgresosTabla() {
        return egresosTabla;
    }

    public void setEgresosTabla(PdfPTable egresosTabla) {
        this.egresosTabla = egresosTabla;
    }

    public Paragraph getTituloDelInforme() {
        return tituloDelInforme;
    }
    
    public FileOutputStream getFicheroPDF() {
        return ficheroPDF;
    }

    public void setFicheroPDF(String rutaDeGuardado) {
        try {
            this.ficheroPDF = new FileOutputStream(rutaDeGuardado);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }
    
    /*
     Atributos privados
     */
    private Paragraph fechaInicial;
    private Paragraph fechaFinal;
    private Image encabezado;
    private PdfPTable ingresosTabla;
    private PdfPTable egresosTabla;
    private static final Paragraph tituloDelInforme = 
            new Paragraph("Reporte general de Ingresos y Egresos");
    private FileOutputStream ficheroPDF;
    
}
