package chat;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
 
public class Frm1 extends javax.swing.JFrame implements Observer {
 
    public Frm1() {
        initComponents();
        this.getRootPane().setDefaultButton(this.btnEnviar);
        Servidor s = new Servidor(5000);
        s.addObserver(this);
        Thread t = new Thread(s);
        t.start();
    }
 
    @SuppressWarnings("unchecked")
    // &amp;lt;editor-fold defaultstate="collapsed" desc="Generated Code"&amp;gt;                          
    private void initComponents() {
    	
    	


 
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTexto = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();
        txtTextoEnviar = new javax.swing.JTextField();
 
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FRM1");
 
        txtTexto.setColumns(20);
        txtTexto.setRows(5);
        jScrollPane1.setViewportView(txtTexto);
 
        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });
 
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTextoEnviar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(txtTextoEnviar))
                .addContainerGap())
        );
 
        pack();
    }// &amp;lt;/editor-fold&amp;gt;                        
 
    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {
    	try {
            final String claveEncriptacion = "secreto";
            String datosOriginales = "Alejandro Ospina: " + this.txtTextoEnviar.getText() + "\n";

            Encriptador encriptador = new Encriptador();

            String encriptado = encriptador.encriptar(datosOriginales, claveEncriptacion);
            String desencriptado = encriptador.desencriptar(encriptado, claveEncriptacion);
            
            this.txtTexto.append(datosOriginales);
            
            Cliente c = new Cliente(6000, encriptado);
            Thread t = new Thread(c);
            t.start();

            System.out.println("Cadena Original: " + datosOriginales);
            System.out.println("Encriptado     : " + encriptado);
            System.out.println("Desencriptado  : " + desencriptado);

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Frm1.class.getName()).log(Level.SEVERE, null, ex);
        }
 
 
    }                                         
 
    @Override
    public void update(Observable o, Object arg) {
    	try {
    		final String claveEncriptacion = "secreto";
            Encriptador encriptador = new Encriptador();
            
       
            System.out.println("Recibido Encriptado     : " + (String) arg);
        	String desencriptado = encriptador.desencriptar((String) arg, claveEncriptacion);
        	
            this.txtTexto.append("\n" + desencriptado + "\n");
            
    	} catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Frm1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm1().setVisible(true);
            }
        });
    }
 
    // Variables declaration - do not modify                     
    private javax.swing.JButton btnEnviar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtTexto;
    private javax.swing.JTextField txtTextoEnviar;
    // End of variables declaration                   
 
}