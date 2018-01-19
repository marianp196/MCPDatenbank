/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MCPdatenbank_standardVerbindung.gui;

import MCPdatenbank_standardVerbindung.datenbank.CConInfo;
import MCPdatenbank_standardVerbindung.datenbank.ConnectionFactory;
import MCPdatenbank_standardVerbindung.datenbank.CXmlErsteller;
import java.io.File;
import javax.swing.*;

/**
 *
 * @author marian
 */
public class GMcpXmlErsteller extends JFrame {
    
    private File zielverezeichnis=null;
    private VerbindungsAufbau fabrik_parent;
    
    
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton BtnTesteVerbindung;
    private javax.swing.JButton BtnGo;
    private javax.swing.JComboBox<String> CbDatenbanktreiber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField passwort;
    private javax.swing.JTextField txtDatenbankUrl;
    private javax.swing.JTextField txtDatenbank;
    private javax.swing.JTextField txtBenutzer;
    
    public GMcpXmlErsteller(VerbindungsAufbau parent) 
    {
        this.fabrik_parent = parent;
        this.zielverezeichnis = parent.getVerzeichnis();
        verhaltenFensterSetzen();
        initComponents() ;
        
    }
    
    private void verhaltenFensterSetzen()
    {
        this.setResizable(false);
        this.setTitle("mcp.xml Konfiguration");
        this.setSize(300, 450);
        this.setLocation(100, 100);
        this.setVisible(true);
        
    } 
    
    private void eventTesteVebindung(){
        CConInfo info  = initCConInfo();
        
        ConnectionFactory con = new ConnectionFactory(info);
        if(con.verbindungAufbauenServer())
        {
            JOptionPane.showMessageDialog(null, "Konnte mich mit Server verbinden!", "Erfolg!", JOptionPane.OK_CANCEL_OPTION);
        }else
        {
            JOptionPane.showMessageDialog(null, "Verbindung fehlgeschlagen! Für nähere Informationen log.txt lesen", "Fehler!", JOptionPane.OK_CANCEL_OPTION);
        }    
    }
    
    private void eventErstelleXml(){
        CConInfo info = initCConInfo();
        File f;
        if(zielverezeichnis == null)
        {
            JFileChooser dateiAuswahlDialog = new JFileChooser();
            dateiAuswahlDialog.setDialogType(JFileChooser.SAVE_DIALOG);
            int ergebnis = dateiAuswahlDialog.showOpenDialog(null);
            
            if(ergebnis != JFileChooser.APPROVE_OPTION){
                return;
            }
            f = dateiAuswahlDialog.getSelectedFile();
        }else
        {
            f = this.zielverezeichnis;
        }   
        
        CXmlErsteller xmlErsteller = new CXmlErsteller(f, info);
        if(xmlErsteller.saveToXML())
        {
           JOptionPane.showMessageDialog(null, "Konnte mcp.xml erstellen!", "Erfolg!", JOptionPane.OK_CANCEL_OPTION);
           this.setVisible(false);
           
           fabrik_parent.macheVerbindung();
        }else
        {
          JOptionPane.showMessageDialog(null, "mcp.xml konnte nicht erzeugt werden! Für nähere Informationen log.txt lesen", "Fehler!", JOptionPane.OK_CANCEL_OPTION);  
        }    
        
    }
    
    private CConInfo initCConInfo(){
        
         String pw;
        try{
          pw = String.valueOf(passwort.getPassword()); 
        }
        catch(Exception e)
        {
           pw = ""; 
        }    
       
        return new CConInfo(
                txtDatenbankUrl.getText(),
                txtDatenbank.getText(),
                txtBenutzer.getText(),
                pw,
                String.valueOf(CbDatenbanktreiber.getSelectedItem())
        );
    }
    
    private void initComponents() {

        CbDatenbanktreiber = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtDatenbankUrl = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDatenbank = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 32767));
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtBenutzer = new javax.swing.JTextField();
        passwort = new javax.swing.JPasswordField();
        BtnTesteVerbindung = new javax.swing.JButton();
        BtnGo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(300, 450));

        CbDatenbanktreiber.setModel(new javax.swing.DefaultComboBoxModel<>(CConInfo.SQLTREIBER));
        CbDatenbanktreiber.setName("CBTreiber"); // NOI18N

        jLabel1.setText("Datenbanktreiber");

        txtDatenbankUrl.setText("jdbc:mysql://localhost");
        txtDatenbankUrl.setName("txtUrl"); // NOI18N

        jLabel2.setText("URL zum Datenbankserver");

        jLabel3.setText("Datenbank-Name");

        //txtDatenbank.setText("jTextField2");
        txtDatenbank.setName("txtName"); // NOI18N

        filler1.setBackground(new java.awt.Color(69, 211, 72));

        jLabel4.setText("Benutzer");

        jLabel5.setText("Passwort");

        //txtBenutzer.setText("jTextField3");
        txtBenutzer.setName("txtBenutzer"); // NOI18N

        //passwort.setText("jPasswordField1");
        passwort.setName("txtPaswort"); // NOI18N
        passwort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               
            }
        });

        BtnTesteVerbindung.setText("Teste Verbindung");
        BtnTesteVerbindung.setName("btntest"); // NOI18N
        BtnTesteVerbindung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               eventTesteVebindung();
            }
        });

        BtnGo.setText("Erstelle XML");
        BtnGo.setName("btnGO"); // NOI18N
        BtnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               eventErstelleXml();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filler1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDatenbankUrl)
                            .addComponent(CbDatenbanktreiber, 0, 279, Short.MAX_VALUE)
                            .addComponent(txtDatenbank)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBenutzer, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                            .addComponent(passwort)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnTesteVerbindung, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnGo, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CbDatenbanktreiber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDatenbankUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDatenbank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtBenutzer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(passwort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnTesteVerbindung)
                    .addComponent(BtnGo))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>  
}
