/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ControladorMesa;
import controlador.VistaMesa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import modelo.Jugador;
import modelo.JugadorRuleta;
import modelo.Mesa;
import modelo.Numero;

/**
 *
 * @author Euge
 */
public class VistaMesaV1 extends javax.swing.JDialog implements VistaMesa, ActionListener{
    
    private ControladorMesa controlador;
    private JSplitPane split = new JSplitPane();
    private PanelTablero bottom;
    private PanelDatos top;

    public VistaMesaV1(Mesa m, Jugador j) {
        initComponents();
        JugadorRuleta jr= m.buscarJugador(j);
        controlador = new ControladorMesa(this,m,jr);
        top = new PanelDatos(controlador);
        split.setTopComponent(top);
        split.setOrientation(JSplitPane.VERTICAL_SPLIT);
        split.setDividerLocation(180);
        setContentPane(split);
        controlador.cargarJugadoresActivos();
        controlador.buscarNumeroActual();
        controlador.mostrarSaldo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        lbl.setText("jLabel1");
        getContentPane().add(lbl);
        lbl.setBounds(114, 38, 178, 14);

        setBounds(0, 0, 716, 493);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        salirDeMesa();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbl;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mostrar(ArrayList<Numero> numeros) {
        bottom = new PanelTablero(numeros,this);
        split.setBottomComponent(bottom);
        split.setDividerLocation(180);
        validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BotonRuleta origen = (BotonRuleta) e.getSource();
        Numero n = origen.getNumero();
        int monto = top.obtenerApuesta();
        controlador.apostar(n,monto);
    }

    @Override
    public void mostrarJugadores(ArrayList<JugadorRuleta> j) {
        top.mostrarJugadores(j);
    }

    // se llama cuando todos han hecho click sobre finalizar apuesta, asi que
    // es avisado
    @Override
    public void mostrarNumeroSorteado(int num) {
        top.mostrarNumeroSorteado(num);
        validate(); // para refrescar en el momento
        
    }

    @Override
    public void salirDeMesa() {

    }

    @Override
    public void exitoApuesta() {
        top.exito();
    }

    @Override
    public void errorApuesta(String msj) {
        JOptionPane.showMessageDialog(this, msj);
    }

    @Override
    public void mostrarSaldo(long saldoJugador) {
        top.mostrarSaldo(saldoJugador);
    }
    
    
   


}
