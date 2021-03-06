/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;
import java.awt.Color;
import static java.lang.Thread.sleep;
/**
 *
 * @author Edson García
 */
public class Main extends javax.swing.JFrame {

    // Referencia estatica al GUI
    static Main gui = new Main();
    
    // Procesos individuales
    Thread1 thread1 = new Thread1();
    Thread2 thread2 = new Thread2();
    
    // Variable booleana que indica si uno de los algoritmos se ejecuta o se detiene
    public static boolean eventFlag;
    
//    // Variable que indica cuanto tiempo debe procesarse la barra
//    int tiempoEspera = 0;
    
    public static boolean getEventFlag() {
        return eventFlag;
    }

    public static void setEventFlag(boolean _eventFlag) {
        eventFlag = _eventFlag;
    }
    
    // Variable int que indica a cuál numero de proceso corresponde ejecutarse
    public static int turn;
    public static boolean[] interested=new boolean[2];

    public static int getTurn() {
        return turn;
    }
    
    public static void setTurn(int _turn) {
        turn = _turn;
    }

    public static boolean[] getInterested() {
        return interested;
    }

    public static void setInterested(int position,boolean value) {
        Main.interested[position] = value;
    }

    public static void calcTurn() {
        int val = (int)(Math.random() * 2) + 1;
        setTurn(val);
    }
    
    // Variables booleanas de turn de procesos
    public static boolean p1qe;

    public static boolean getP1qe() {
        return p1qe;
    }

    public static void setP1qe(boolean p1qe) {
        Main.p1qe = p1qe;
    }
    
    public static boolean p2qe;

    public static boolean getP2qe() {
        return p2qe;
    }

    public static void setP2qe(boolean p2qe) {
        Main.p2qe = p2qe;
    }
  
    public void updateStatusP1(String s){
        lblStatusP1.setText(s);
    }
    
    public void updateStatusP2(String s){
        lblStatusP2.setText(s);
    }
    
    public void setVisibleProgressBarP1(String s){
        switch(s){
            case "show":
                pbProceso1.setVisible(true);
                break;
            case "hide":
                pbProceso1.setVisible(false);
                break;
        }
    }
    
    public void setVisibleProgressBarP2(String s){
        switch(s){
            case "show":
                pbProceso2.setVisible(true);
                break;
            case "hide":
                pbProceso2.setVisible(false);
                break;
        }
    }
    
    public void setOperationProgressBarP1(String s){
        switch(s){
            case "bussy":
                pbProceso1.setIndeterminate(true);
                pbProceso1.setBackground(Color.decode("#73C9E3"));
                pbProceso1.setForeground(Color.decode("#5BC0DE"));
                break;
            case "wait":
                pbProceso1.setIndeterminate(true);
                pbProceso1.setBackground(Color.decode("#DE6C69"));
                pbProceso1.setForeground(Color.decode("#D9534F"));
                break;
            case "working":
                pbProceso1.setIndeterminate(true);
                pbProceso1.setBackground(Color.decode("#74C274"));
                pbProceso1.setForeground(Color.decode("#3E9A3E"));
//                pbProceso1.setMinimum(1);
//                pbProceso1.setMaximum(tiempoEspera);
//                for(int i = 1; i <= tiempoEspera; i++){
//                    pbProceso1.setValue(i);
//                    try{
//                        sleep(i * 1000);
//                    }catch(InterruptedException e){}
//                }
        }
    }
    
    public void setOperationProgressBarP2(String s){
        switch(s){
            case "bussy":
                pbProceso2.setIndeterminate(true);
                pbProceso2.setBackground(Color.decode("#73C9E3"));
                pbProceso2.setForeground(Color.decode("#5BC0DE"));
                break;
            case "wait":
                pbProceso2.setIndeterminate(true);
                pbProceso2.setBackground(Color.decode("#DE6C69"));
                pbProceso2.setForeground(Color.decode("#D9534F"));
                break;
            case "working":
                pbProceso2.setIndeterminate(true);
                pbProceso2.setBackground(Color.decode("#74C274"));
                pbProceso2.setForeground(Color.decode("#3E9A3E"));
//                pbProceso2.setMinimum(0);
//                pbProceso2.setMaximum(tiempoEspera);
//                for(int i = 1; i <= tiempoEspera; i++){
//                    pbProceso2.setValue(i);
//                    try{
//                        sleep(time * 1000);
//                    }catch(InterruptedException e){}
//                }
        }
    }
    
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        
        eventFlag = false;
        
        pbProceso1.setBorderPainted(false);
        pbProceso2.setBorderPainted(false);
        
        inicializarObjetos();
    }
    
    private void inicializarObjetos() {
        // Inicializa estilos por defecto de objetos
        pbProceso1.setVisible(false);
        pbProceso2.setVisible(false);
        
        lblStatusP1.setText("-");
        lblStatusP2.setText("-");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        btnDekker1 = new javax.swing.JButton();
        btnDekker2 = new javax.swing.JButton();
        btnDekker3 = new javax.swing.JButton();
        btnDekker4 = new javax.swing.JButton();
        btnDekker5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pbProceso1 = new javax.swing.JProgressBar();
        pbProceso2 = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblStatusP1 = new javax.swing.JLabel();
        lblStatusP2 = new javax.swing.JLabel();
        peterson = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Finish");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnDekker1.setText("Dekker1");
        btnDekker1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDekker1ActionPerformed(evt);
            }
        });

        btnDekker2.setText("Dekker2");
        btnDekker2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDekker2ActionPerformed(evt);
            }
        });

        btnDekker3.setText("Dekker3");
        btnDekker3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDekker3ActionPerformed(evt);
            }
        });

        btnDekker4.setText("Dekker4");
        btnDekker4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDekker4ActionPerformed(evt);
            }
        });

        btnDekker5.setText("Dekker5");
        btnDekker5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDekker5ActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PROCESS 1");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("PROCESS 2");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Status");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Status");

        lblStatusP1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatusP1.setText("-");

        lblStatusP2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatusP2.setText("-");

        peterson.setText("Peterson");
        peterson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                petersonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnDekker1)
                        .addGap(18, 18, 18)
                        .addComponent(btnDekker2)
                        .addGap(18, 18, 18)
                        .addComponent(btnDekker3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDekker4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pbProceso1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblStatusP1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnDekker5)
                                        .addGap(26, 26, 26)
                                        .addComponent(peterson)))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addContainerGap(45, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(pbProceso2, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(137, 137, 137))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblStatusP2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDekker1)
                    .addComponent(btnDekker2)
                    .addComponent(btnDekker3)
                    .addComponent(btnDekker4)
                    .addComponent(btnDekker5)
                    .addComponent(jButton1)
                    .addComponent(peterson))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pbProceso1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pbProceso2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStatusP1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStatusP2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        eventFlag = false;
        inicializarObjetos();
        thread1.interrupt();
        thread2.interrupt();
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnDekker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDekker1ActionPerformed
        // TODO add your handling code here:
        calcTurn();
        if(!thread1.getState().equals("RUNNABLE"))
            thread1.start();
        System.out.println(thread1.getState());
        thread1.Dekker(1);
        if(!thread2.getState().equals("RUNNABLE"))
            thread2.start();
        thread2.Dekker(1);
    }//GEN-LAST:event_btnDekker1ActionPerformed

    private void btnDekker2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDekker2ActionPerformed
        // TODO add your handling code here:
        setP1qe(false);
        setP2qe(false);
        if(thread1.getState().equals("RUNNABLE"))
            thread1.interrupt();
        thread1.start();
        thread1.Dekker(2);
        if(thread2.getState().equals("RUNNABLE"))
            thread2.interrupt();
        thread2.start();
        thread2.Dekker(2);
    }//GEN-LAST:event_btnDekker2ActionPerformed

    private void btnDekker3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDekker3ActionPerformed
        // TODO add your handling code here:
        setP1qe(false);
        setP2qe(false);
        if(!thread1.getState().equals("RUNNABLE"))
            thread1.start();
        thread1.Dekker(3);
        if(!thread2.getState().equals("RUNNABLE"))
            thread2.start();
        thread2.Dekker(3);
    }//GEN-LAST:event_btnDekker3ActionPerformed

    private void btnDekker4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDekker4ActionPerformed
        // TODO add your handling code here:
        setP1qe(false);
        setP2qe(false);
        if(!thread1.getState().equals("RUNNABLE"))
            thread1.start();
        thread1.Dekker(4);
        if(!thread2.getState().equals("RUNNABLE"))
            thread2.start();
        thread2.Dekker(4);
    }//GEN-LAST:event_btnDekker4ActionPerformed

    private void btnDekker5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDekker5ActionPerformed
        // TODO add your handling code here:
        calcTurn();
        setP1qe(false);
        setP2qe(false);
        if(!thread1.getState().equals("RUNNABLE"))
            thread1.start();
        thread1.Dekker(5);
        if(!thread2.getState().equals("RUNNABLE"))
            thread2.start();
        thread2.Dekker(5);
    }//GEN-LAST:event_btnDekker5ActionPerformed

    private void petersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_petersonActionPerformed
        setInterested(0,false);
        setInterested(1,true);
        if(!thread1.getState().equals("RUNNABLE"))
            thread1.start();
        thread1.Dekker(6);
        if(!thread2.getState().equals("RUNNABLE"))
            thread2.start();
        thread2.Dekker(6);
    }//GEN-LAST:event_petersonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDekker1;
    private javax.swing.JButton btnDekker2;
    private javax.swing.JButton btnDekker3;
    private javax.swing.JButton btnDekker4;
    private javax.swing.JButton btnDekker5;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblStatusP1;
    private javax.swing.JLabel lblStatusP2;
    private javax.swing.JProgressBar pbProceso1;
    private javax.swing.JProgressBar pbProceso2;
    private javax.swing.JButton peterson;
    // End of variables declaration//GEN-END:variables
}
