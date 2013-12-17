/*
 * Notas de tu yo del pasado:  * Tal vez quitar el atributo reporte de aqui
 *                              
 *                             
 * 
 */
package com.dao.manager;

import com.clinica.modelo.Reporte;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ReporteMgrImpl implements ReporteMgr {

    public ReporteMgrImpl(Reporte reporte) {
        this.reporte = reporte;
    }

    @Override
    public void modificarRutaDeGuardado(String ruta) {
        reporte.setFicheroPDF(ruta);
    }

    @Override
    public void generarReporte() { //o mejor generarEsqueletoDelReporte
        try {
        
        PdfWriter.getInstance(
                                documento,
                                reporte.getFicheroPDF()
                             ).setInitialLeading(20);
        
        documento.open();
            this.agregarEncabezado();
            this.agregarFechas();
            this.agregarSeccionIngresos();
            this.agregarSeccionEgresos();
        documento.close();
        
        
        } catch (DocumentException ex) {
            System.out.println(ex.toString());
        }
        
        
        
        
    }

    @Override
    public void agregarFechas() {
        try {
            this.documento.add(this.reporte.getFechaInicial());
            this.documento.add(this.reporte.getFechaFinal());
        } catch (DocumentException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public void agregarEncabezado() {
        try {
            this.documento.add(this.reporte.getEncabezado());
            this.documento.add(this.reporte.getTituloDelInforme());
        } catch (DocumentException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public void agregarSeccionIngresos() {
        try {
            this.documento.add(new Paragraph(" "));//Salto de linea
                Paragraph tituloIngreso = new Paragraph("Ingresos");
                tituloIngreso.setAlignment(1);//el 1 es para centrar
            this.documento.add(tituloIngreso);
            this.documento.add(new Paragraph(" "));//Salto de linea
            this.documento.add(this.reporte.getIngresosTabla());
        } catch (DocumentException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public void agregarSeccionEgresos() {
        try {
            this.documento.add(new Paragraph(" "));//Salto de linea
                Paragraph tituloEgreso = new Paragraph("Egreso");
                tituloEgreso.setAlignment(1);//el 1 es para centrar
            this.documento.add(tituloEgreso);
            this.documento.add(new Paragraph(" "));//Salto de linea
            this.documento.add(this.reporte.getEgresosTabla());
        } catch (DocumentException ex) {
            System.out.println(ex.toString());
        }
    }

    /*
     Atributos privados
     */
    private Reporte reporte;
    private Document documento = new Document();
}
