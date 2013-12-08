/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vistas;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

/**
 *
 * @author Yuceli
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();

    }

    public void mostrar() {

        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelClinica = new javax.swing.JPanel();
        BotonProveedores = new javax.swing.JButton();
        BotonEgresos = new javax.swing.JButton();
        BotonIngresos = new javax.swing.JButton();
        LabelClinica = new javax.swing.JLabel();
        LabelBienvenido = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelClinica.setBackground(new java.awt.Color(0, 102, 102));
        PanelClinica.setForeground(new java.awt.Color(0, 153, 255));

        BotonProveedores.setForeground(new java.awt.Color(51, 51, 51));
        BotonProveedores.setText("Proveedores");
        BotonProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonProveedoresActionPerformed(evt);
            }
        });

        BotonEgresos.setText("Egresos ");
        BotonEgresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEgresosActionPerformed(evt);
            }
        });

        BotonIngresos.setText("Ingresos");
        BotonIngresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonIngresosActionPerformed(evt);
            }
        });

        LabelClinica.setBackground(new java.awt.Color(0, 153, 255));
        LabelClinica.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 36)); // NOI18N
        LabelClinica.setText("Clinica Dental Málaga ");

        LabelBienvenido.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 48)); // NOI18N
        LabelBienvenido.setText("Bienvenido");

        jProgressBar1.setStringPainted(true);

        javax.swing.GroupLayout PanelClinicaLayout = new javax.swing.GroupLayout(PanelClinica);
        PanelClinica.setLayout(PanelClinicaLayout);
        PanelClinicaLayout.setHorizontalGroup(
            PanelClinicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelClinicaLayout.createSequentialGroup()
                .addGroup(PanelClinicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelClinicaLayout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(LabelClinica))
                    .addGroup(PanelClinicaLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(BotonProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(BotonEgresos, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(BotonIngresos, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
            .addGroup(PanelClinicaLayout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(LabelBienvenido)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelClinicaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(233, 233, 233))
        );

        PanelClinicaLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BotonEgresos, BotonIngresos, BotonProveedores});

        PanelClinicaLayout.setVerticalGroup(
            PanelClinicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelClinicaLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(LabelBienvenido)
                .addGap(38, 38, 38)
                .addComponent(LabelClinica, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(PanelClinicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonEgresos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonIngresos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(93, 93, 93)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        PanelClinicaLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {BotonEgresos, BotonIngresos, BotonProveedores});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelClinica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelClinica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonProveedoresActionPerformed
        // TODO add your handling code here:
        VistaProveedores vistaProveedores = new VistaProveedores();
        vistaProveedores.abrirVentana();
        VentanaPrincipal screen = new VentanaPrincipal();
        

    }//GEN-LAST:event_BotonProveedoresActionPerformed

    private void BotonEgresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEgresosActionPerformed
        // TODO add your handling code here:
        VistaEgresos vistaEgresos = new VistaEgresos();
        vistaEgresos.abrirVentana();
    }//GEN-LAST:event_BotonEgresosActionPerformed

    private void BotonIngresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonIngresosActionPerformed
        // TODO add your handling code here:
        VistaIngresos vistaIngresos = new VistaIngresos();
        vistaIngresos.mostrarVistaIngresos();
    }//GEN-LAST:event_BotonIngresosActionPerformed

    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonEgresos;
    private javax.swing.JButton BotonIngresos;
    private javax.swing.JButton BotonProveedores;
    private javax.swing.JLabel LabelBienvenido;
    private javax.swing.JLabel LabelClinica;
    private javax.swing.JPanel PanelClinica;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
