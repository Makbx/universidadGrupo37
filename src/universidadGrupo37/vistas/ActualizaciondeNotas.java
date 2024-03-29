/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package universidadGrupo37.vistas;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import universidadGrupo37.entidades.Alumno;
import universidadGrupo37.entidades.Inscripcion;

/**
 *
 * @author crist
 */
public class ActualizaciondeNotas extends javax.swing.JInternalFrame {

    /**
     * Creates new form ActualizaciondeNotas
     */
    private DefaultTableModel modelo=new DefaultTableModel(){
        
    public boolean isCellEditable(int c, int f){
            return false;
        }
    };
    public ActualizaciondeNotas() {
        initComponents();
        armarCabecera();
        cargarCombo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        CBalumnos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTnotas = new javax.swing.JTable();
        JBguardar = new javax.swing.JButton();
        JBsalir = new javax.swing.JButton();

        jLabel1.setText("Carga de Notas");

        jLabel2.setText("Seleccione un alumno:");

        CBalumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBalumnosActionPerformed(evt);
            }
        });

        JTnotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        JTnotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTnotasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTnotas);

        JBguardar.setText("Guardar");
        JBguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBguardarActionPerformed(evt);
            }
        });

        JBsalir.setText("Salir");
        JBsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBsalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JBguardar)
                        .addGap(177, 177, 177)
                        .addComponent(JBsalir))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(41, 41, 41)
                            .addComponent(jLabel2)
                            .addGap(36, 36, 36)
                            .addComponent(CBalumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(247, 247, 247)
                            .addComponent(jLabel1))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(CBalumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBguardar)
                    .addComponent(JBsalir))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CBalumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBalumnosActionPerformed
        // TODO add your handling code here:
        Alumno aluSelec = (Alumno) CBalumnos.getSelectedItem();
        limpiarTabla();
        cargarTabla(aluSelec);
    }//GEN-LAST:event_CBalumnosActionPerformed

    private void JBguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBguardarActionPerformed
        // TODO add your handling code here:
        int fila = JTnotas.getSelectedRow(); //devuelve el numero de la fila seleccionada
        int idMateria = (int) modelo.getValueAt(fila, 0);//devuelve el valor de la fila seleccionada y columna (codigo 0 , nombre 1 y nota 2)
        double nota = (double) modelo.getValueAt(fila, 2);
        Alumno aluSelec = (Alumno) CBalumnos.getSelectedItem();
        Menu.inscripciondata.actualizarNota(aluSelec.getIdAlumno(), idMateria, nota);
        limpiarTabla();
        cargarTabla(aluSelec);
        
        
    }//GEN-LAST:event_JBguardarActionPerformed

    private void JBsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBsalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_JBsalirActionPerformed

    private void JTnotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTnotasMouseClicked
        // TODO add your handling code here:
        try {
            String notaSelec = JOptionPane.showInputDialog("Ingrese la nota a editar");
            double  nota = Double.parseDouble(notaSelec);
            if(nota <=10 && nota >=0){
                JTnotas.setValueAt(nota, JTnotas.getSelectedRow(), 2);
                JOptionPane.showMessageDialog(null, "Click en el boton Guardar para guardar los cambios");
            }else{
                JOptionPane.showMessageDialog(null, "La nota esta entre 0 y 10");
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_JTnotasMouseClicked
    private void armarCabecera(){
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Nota");
        
        JTnotas.setModel(modelo);
        
    }
    private void cargarCombo() {
        for (Alumno alu : Menu.alumnodata.listarAlumnos()) {
            CBalumnos.addItem(alu);
        }
    }
    private void limpiarTabla(){
        int f = JTnotas.getRowCount() - 1;
        for (; f >= 0; f--) {
            modelo.removeRow(f);
        }
    }
    private void cargarTabla(Alumno aluSelec){
        for (Inscripcion ins : Menu.inscripciondata.obtenerInscripcionesPorAlumno(aluSelec.getIdAlumno())){
            modelo.addRow(new Object[]{
                ins.getMateria().getIdMateria(),
                ins.getMateria().getNombre(),
                ins.getNota()
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Alumno> CBalumnos;
    private javax.swing.JButton JBguardar;
    private javax.swing.JButton JBsalir;
    private javax.swing.JTable JTnotas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
