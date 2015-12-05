/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ichsan Hariadi
 */
public class FormAnalisisManual extends javax.swing.JFrame {

    /**
     * Creates new form FormAnalisis
     */
    Connection con;
    Statement stmt;
    String[] hasilFinal;

    DefaultTableModel tblModel1, tblModel2;

    public FormAnalisisManual() {
        initComponents();
        LimitSetting();
        tblModel1 = (DefaultTableModel) tblHasil1.getModel();
        tblModel2 = (DefaultTableModel) tblHasil2.getModel();
        btnAnalisis1.setEnabled(false);
        btnAnalisis2.setEnabled(false);
        cmbProduk.setEnabled(false);
        cmbProduk1.setEnabled(false);
        cmbProduk2.setEnabled(false);
        jProgressBar1.setVisible(false);
    }

    private void LimitSetting() {
        txtSupp.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9')
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        txtConf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9')
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
    }

    private void FillComboBoxKategori() {
        cmbProduk.removeAllItems();
        cmbProduk1.removeAllItems();
        cmbProduk2.removeAllItems();

        try {
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=MBA_Final;integratedSecurity=true;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery("SELECT KodeKategori, NamaKategori FROM tbKategori");

            while (rs.next()) {
                ComboValue temp = new ComboValue();
                temp.setKey(rs.getString(1));
                temp.setValue(rs.getString(2));
                cmbProduk.addItem(temp);
                cmbProduk1.addItem(temp);
                cmbProduk2.addItem(temp);
            }

            cmbProduk.setEnabled(true);
            cmbProduk1.setEnabled(true);
            cmbProduk2.setEnabled(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void FillComboBoxMerk() {
        cmbProduk.removeAllItems();
        cmbProduk1.removeAllItems();
        cmbProduk2.removeAllItems();

        try {
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=MBA_Final;integratedSecurity=true;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery("SELECT KodeMerk, NamaMerk FROM tbMerk");

            while (rs.next()) {
                ComboValue temp = new ComboValue();
                temp.setKey(rs.getString(1));
                temp.setValue(rs.getString(2));
                cmbProduk.addItem(temp);
                cmbProduk1.addItem(temp);
                cmbProduk2.addItem(temp);
            }

            cmbProduk.setEnabled(true);
            cmbProduk1.setEnabled(true);
            cmbProduk2.setEnabled(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }

    }

    private void FillComboBoxSupplier() {
        cmbProduk.removeAllItems();
        cmbProduk1.removeAllItems();
        cmbProduk2.removeAllItems();

        try {
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=MBA_Final;integratedSecurity=true;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery("SELECT KodeSupplier, NamaSupplier FROM tbSupplier");

            while (rs.next()) {
                ComboValue temp = new ComboValue();
                temp.setKey(rs.getString(1));
                temp.setValue(rs.getString(2));
                cmbProduk.addItem(temp);
                cmbProduk1.addItem(temp);
                cmbProduk2.addItem(temp);
            }

            cmbProduk.setEnabled(true);
            cmbProduk1.setEnabled(true);
            cmbProduk2.setEnabled(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbProduk = new javax.swing.JComboBox();
        btnAnalisis1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHasil1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cmbProduk1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cmbProduk2 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHasil2 = new javax.swing.JTable();
        btnAnalisis2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        rbtKategori = new javax.swing.JRadioButton();
        rbtMerk = new javax.swing.JRadioButton();
        rbtSupplier = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSupp = new javax.swing.JTextField();
        txtConf = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        txtStatus = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuSwitch = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Market Basket Analysis (Manual)");

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Item 1 :");

        btnAnalisis1.setText("Analisis!");
        btnAnalisis1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalisis1ActionPerformed(evt);
            }
        });

        tblHasil1.setAutoCreateRowSorter(true);
        tblHasil1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kemungkinan Cross-selling", "Conf. (%)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblHasil1);
        if (tblHasil1.getColumnModel().getColumnCount() > 0) {
            tblHasil1.getColumnModel().getColumn(0).setResizable(false);
            tblHasil1.getColumnModel().getColumn(0).setPreferredWidth(300);
            tblHasil1.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAnalisis1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 200, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAnalisis1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("2 Item", jPanel1);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Item 1 :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Item 2 :");

        tblHasil2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kemungkinan Cross-selling", "Conf. (%)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblHasil2);
        if (tblHasil2.getColumnModel().getColumnCount() > 0) {
            tblHasil2.getColumnModel().getColumn(0).setResizable(false);
            tblHasil2.getColumnModel().getColumn(0).setPreferredWidth(300);
            tblHasil2.getColumnModel().getColumn(1).setResizable(false);
        }

        btnAnalisis2.setText("Analisis!");
        btnAnalisis2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalisis2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbProduk1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbProduk2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAnalisis2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbProduk1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cmbProduk2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAnalisis2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("3 Item", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dimensi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        jPanel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        buttonGroup1.add(rbtKategori);
        rbtKategori.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbtKategori.setText("Kategori");
        rbtKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtKategoriActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtMerk);
        rbtMerk.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbtMerk.setText("Merk");
        rbtMerk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtMerkActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtSupplier);
        rbtSupplier.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbtSupplier.setText("Supplier");
        rbtSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtSupplierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtKategori)
                    .addComponent(rbtMerk)
                    .addComponent(rbtSupplier))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtKategori)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtMerk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtSupplier)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Setting", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Min. Support");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Min. Confidence");

        jLabel6.setText("%");

        jLabel7.setText("%");

        txtSupp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSupp.setText("1");
        txtSupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSuppActionPerformed(evt);
            }
        });

        txtConf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtConf.setText("10");
        txtConf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConfActionPerformed(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("(Nilai minimum dari jumlah transaksi");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("yang membeli barang yang sama)");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("(Nilai minimum tingkat kepercayaan");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("yang harus dipenuhi oleh aturan");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("yang dihasilkan)");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtConf, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(txtSupp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtConf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addContainerGap())
        );

        jMenu1.setText("File");

        menuSwitch.setText("Switch to WEKA Calculation...");
        menuSwitch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSwitchActionPerformed(evt);
            }
        });
        jMenu1.add(menuSwitch);
        jMenu1.add(jSeparator1);

        jMenuItem2.setText("Quit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rbtKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtKategoriActionPerformed
        // TODO add your handling code here:     
        FillComboBoxKategori();
        btnAnalisis1.setEnabled(true);
        btnAnalisis2.setEnabled(true);

    }//GEN-LAST:event_rbtKategoriActionPerformed

    private void rbtMerkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtMerkActionPerformed
        // TODO add your handling code here:
        FillComboBoxMerk();
        btnAnalisis1.setEnabled(true);
        btnAnalisis2.setEnabled(true);
    }//GEN-LAST:event_rbtMerkActionPerformed

    private void rbtSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtSupplierActionPerformed
        // TODO add your handling code here:
        FillComboBoxSupplier();
        btnAnalisis1.setEnabled(true);
        btnAnalisis2.setEnabled(true);
    }//GEN-LAST:event_rbtSupplierActionPerformed

    private void txtSuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSuppActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSuppActionPerformed

    private void txtConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConfActionPerformed

    private void btnAnalisis1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalisis1ActionPerformed
        // TODO add your handling code here:
        while (tblModel1.getRowCount() > 0) {
            tblModel1.removeRow(0);
        }
        analisis1();

    }//GEN-LAST:event_btnAnalisis1ActionPerformed

    private void btnAnalisis2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalisis2ActionPerformed
        // TODO add your handling code here:
        ComboValue pilihan1 = (ComboValue) cmbProduk1.getSelectedItem();
        ComboValue pilihan2 = (ComboValue) cmbProduk2.getSelectedItem();

        if (pilihan1.getKey().equals(pilihan2.getKey())) {
            JOptionPane.showMessageDialog(this, "Pilihan item 1 dan item 2 tidak boleh sama!");
        } else {
            while (tblModel1.getRowCount() > 0) {
                tblModel1.removeRow(0);
            }
            analisis2();
        }
    }//GEN-LAST:event_btnAnalisis2ActionPerformed

    private void menuSwitchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSwitchActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        FormAnalisisWEKA fwek = new FormAnalisisWEKA();
        fwek.setVisible(true);
    }//GEN-LAST:event_menuSwitchActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        System.exit(1);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormAnalisisManual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormAnalisisManual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormAnalisisManual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormAnalisisManual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormAnalisisManual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalisis1;
    private javax.swing.JButton btnAnalisis2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbProduk;
    private javax.swing.JComboBox cmbProduk1;
    private javax.swing.JComboBox cmbProduk2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem menuSwitch;
    private javax.swing.JRadioButton rbtKategori;
    private javax.swing.JRadioButton rbtMerk;
    private javax.swing.JRadioButton rbtSupplier;
    private javax.swing.JTable tblHasil1;
    private javax.swing.JTable tblHasil2;
    private javax.swing.JTextField txtConf;
    private javax.swing.JLabel txtStatus;
    private javax.swing.JTextField txtSupp;
    // End of variables declaration//GEN-END:variables

    double totTransaksi = 20419;

    ArrayList<String> suppxy = new ArrayList<String>();
    ArrayList<Double> suppXY = new ArrayList<Double>();
    ArrayList<Double> totXY = new ArrayList<Double>();
    ArrayList<Double> confXY = new ArrayList<Double>();
    ArrayList<String> item2 = new ArrayList<String>();

    DecimalFormat df = new DecimalFormat("0.00");

    private void analisis1() {

        ComboValue pilihan = (ComboValue) cmbProduk.getSelectedItem();

        double minS = Double.parseDouble(txtSupp.getText());
        double minC = Double.parseDouble(txtConf.getText());

        totXY.clear();
        suppxy.clear();
        suppXY.clear();
        confXY.clear();
        item2.clear();

        try {
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=MBA_Final;integratedSecurity=true;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            if (rbtKategori.isSelected()) {
                double totX = 0;

                //Hitung total transaksi X
                Statement sta1 = con.createStatement();
                ResultSet rs1 = sta1.executeQuery("select COUNT(DISTINCT NoBukti) from tbTransaksi "
                        + "where NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeKategori='" + pilihan.getKey() + "')");

                while (rs1.next()) {
                    totX = rs1.getDouble(1);
                }

                //List item selain pilihan combobox
                Statement sta2 = con.createStatement();
                ResultSet rs2 = sta2.executeQuery("select KodeKategori from tbKategori where KodeKategori !='" + pilihan.getKey() + "'");

                while (rs2.next()) {
                    item2.add(rs2.getString(1));
                }

                //hitung support
                for (int i = 0; i < item2.size(); i++) {
                    Statement sta3 = con.createStatement();
                    ResultSet rs3 = sta3.executeQuery("select COUNT(DISTINCT NoBukti) from tbTransaksi "
                            + "where NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeKategori='" + pilihan.getKey() + "') "
                            + "and NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeKategori='" + item2.get(i) + "')");

                    while (rs3.next()) {
                        double supp = rs3.getDouble(1) / totTransaksi;
                        suppXY.add(supp * 100);
                        totXY.add(rs3.getDouble(1));
                    }

                    //suppXY.add(Double.parseDouble(suppxy.get(i)) * 100);
                    confXY.add(totXY.get(i) / totX * 100);
                    
                    System.out.println(pilihan.getKey() + "=>" + item2.get(i) + "  s:" + suppXY.get(i) + "  c:" + Math.round(confXY.get(i)));               

                    if (suppXY.get(i) >= minS && confXY.get(i) >= minC) {
                        Statement sta4 = con.createStatement();
                        ResultSet rs4 = sta4.executeQuery("select NamaKategori from tbKategori where KodeKategori='" + item2.get(i) + "'");

                        while (rs4.next()) {
                            Object[] row = new Object[]{rs4.getString(1), Math.round(confXY.get(i))};
                            tblModel1.insertRow(tblModel1.getRowCount(), row);
                        }
                    }
                }

            } else if (rbtMerk.isSelected()) {
                double totX = 0;

                //Hitung total transaksi X
                Statement sta1 = con.createStatement();
                ResultSet rs1 = sta1.executeQuery("select COUNT(DISTINCT NoBukti) from tbTransaksi "
                        + "where NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeMerk='" + pilihan.getKey() + "')");

                while (rs1.next()) {
                    totX = rs1.getDouble(1);
                }

                //List item selain pilihan combobox
                Statement sta2 = con.createStatement();
                ResultSet rs2 = sta2.executeQuery("select KodeMerk from tbMerk where KodeMerk !='" + pilihan.getKey() + "'");

                while (rs2.next()) {
                    item2.add(rs2.getString(1));
                }

                //hitung support
                for (int i = 0; i < item2.size(); i++) {
                    Statement sta3 = con.createStatement();
                    ResultSet rs3 = sta3.executeQuery("select COUNT(DISTINCT NoBukti) from tbTransaksi "
                            + "where NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeMerk='" + pilihan.getKey() + "') "
                            + "and NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeMerk='" + item2.get(i) + "')");

                    while (rs3.next()) {
                        double supp = rs3.getDouble(1) / totTransaksi;
                        suppXY.add(supp * 100);
                        totXY.add(rs3.getDouble(1));
                    }

                    //suppXY.add(Double.parseDouble(suppxy.get(i)) * 100);
                    confXY.add(totXY.get(i) / totX * 100);

                    System.out.println(pilihan.getKey() + "=>" + item2.get(i) + "  s:" + suppXY.get(i) + "  c:" + Math.round(confXY.get(i)));               

                    if (suppXY.get(i) >= minS && confXY.get(i) >= minC) {
                        Statement sta4 = con.createStatement();
                        ResultSet rs4 = sta4.executeQuery("select NamaMerk from tbMerk where KodeMerk='" + item2.get(i) + "'");

                        while (rs4.next()) {
                            Object[] row = new Object[]{rs4.getString(1), Math.round(confXY.get(i))};
                            tblModel1.insertRow(tblModel1.getRowCount(), row);
                        }
                    }
                }

            } else if (rbtSupplier.isSelected()) {
                double totX = 0;

                //Hitung total transaksi X
                Statement sta1 = con.createStatement();
                ResultSet rs1 = sta1.executeQuery("select COUNT(DISTINCT NoBukti) from tbTransaksi "
                        + "where NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeSupplier='" + pilihan.getKey() + "')");

                while (rs1.next()) {
                    totX = rs1.getDouble(1);
                }

                //List item selain pilihan combobox
                Statement sta2 = con.createStatement();
                ResultSet rs2 = sta2.executeQuery("select KodeSupplier from tbSupplier where KodeSupplier !='" + pilihan.getKey() + "'");

                while (rs2.next()) {
                    item2.add(rs2.getString(1));
                }

                //hitung support
                for (int i = 0; i < item2.size(); i++) {
                    Statement sta3 = con.createStatement();
                    ResultSet rs3 = sta3.executeQuery("select COUNT(DISTINCT NoBukti) from tbTransaksi "
                            + "where NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeSupplier='" + pilihan.getKey() + "') "
                            + "and NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeSupplier='" + item2.get(i) + "')");

                    while (rs3.next()) {
                        double supp = rs3.getDouble(1) / totTransaksi;
                        suppXY.add(supp * 100);
                        totXY.add(rs3.getDouble(1));
                    }

                    //suppXY.add(Double.parseDouble(suppxy.get(i)) * 100);
                    confXY.add(totXY.get(i) / totX * 100);
                    
                    System.out.println(pilihan.getKey() + "=>" + item2.get(i) + "  s:" + suppXY.get(i) + "  c:" + Math.round(confXY.get(i)));               

                    if (suppXY.get(i) >= minS && confXY.get(i) >= minC) {
                        Statement sta4 = con.createStatement();
                        ResultSet rs4 = sta4.executeQuery("select NamaSupplier from tbSupplier where KodeSupplier='" + item2.get(i) + "'");

                        while (rs4.next()) {
                            Object[] row = new Object[]{rs4.getString(1), Math.round(confXY.get(i))};
                            tblModel1.insertRow(tblModel1.getRowCount(), row);
                        }
                    }
                }

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error di analisis()\n" + ex);
            ex.printStackTrace();
        }
        txtStatus.setText("");
    }

    private void analisis2() {
        ComboValue pilihan1 = (ComboValue) cmbProduk1.getSelectedItem();
        ComboValue pilihan2 = (ComboValue) cmbProduk2.getSelectedItem();

        double minS = Double.parseDouble(txtSupp.getText());
        double minC = Double.parseDouble(txtConf.getText());
        
        totXY.clear();
        suppxy.clear();
        suppXY.clear();
        confXY.clear();
        item2.clear();

        try {
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=MBA_Final;integratedSecurity=true;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            if (rbtKategori.isSelected()) {
                double totX = 0;
                
                //Hitung total transaksi X
                Statement sta1 = con.createStatement();
                ResultSet rs1 = sta1.executeQuery("select COUNT(DISTINCT NoBukti) from tbTransaksi "
                        + "where NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeKategori='" + pilihan1.getKey() + "')"
                        + "and NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeKategori='" + pilihan2.getKey() + "')");

                while (rs1.next()) {
                    totX = rs1.getDouble(1);
                }

                //List item selain pilihan combobox
                Statement sta2 = con.createStatement();
                ResultSet rs2 = sta2.executeQuery("select KodeKategori from tbKategori where KodeKategori !='" + pilihan1.getKey() + "'"
                        + "AND KodeKategori !='" + pilihan2.getKey() + "'");

                while (rs2.next()) {
                    item2.add(rs2.getString(1));
                }

                //hitung support
                for (int i = 0; i < item2.size(); i++) {
                    Statement sta3 = con.createStatement();
                    ResultSet rs3 = sta3.executeQuery("select COUNT(DISTINCT NoBukti) from tbTransaksi "
                            + "where NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeKategori='" + pilihan1.getKey() + "') "
                            + "and NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeKategori='" + pilihan2.getKey() + "') "
                            + "and NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeKategori='" + item2.get(i) + "')");

                    while (rs3.next()) {
                        double supp = rs3.getDouble(1) / totTransaksi;
                        suppXY.add(supp * 100);
                        totXY.add(rs3.getDouble(1));
                    }
                    
                    confXY.add(totXY.get(i) / totX * 100);
                    
                    System.out.println("(" + pilihan1.getKey() + "," + pilihan2.getKey() + ")=>" + item2.get(i) + "  s:" + suppXY.get(i) + "  c:" + Math.round(confXY.get(i)));               

                    if (suppXY.get(i) >= minS && confXY.get(i) >= minC) {
                        Statement sta4 = con.createStatement();
                        ResultSet rs4 = sta4.executeQuery("select NamaKategori from tbKategori where KodeKategori='" + item2.get(i) + "'");

                        while (rs4.next()) {
                            Object[] row = new Object[]{rs4.getString(1), Math.round(confXY.get(i))};
                            tblModel2.insertRow(tblModel2.getRowCount(), row);
                        }
                    }
                }

            } else if (rbtMerk.isSelected()) {
                double totX = 0;
                
                //Hitung total transaksi X
                Statement sta1 = con.createStatement();
                ResultSet rs1 = sta1.executeQuery("select COUNT(DISTINCT NoBukti) from tbTransaksi "
                        + "where NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeMerk='" + pilihan1.getKey() + "')"
                        + "and NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeMerk='" + pilihan2.getKey() + "')");

                while (rs1.next()) {
                    totX = rs1.getDouble(1);
                }

                //List item selain pilihan combobox
                Statement sta2 = con.createStatement();
                ResultSet rs2 = sta2.executeQuery("select KodeMerk from tbMerk where KodeMerk !='" + pilihan1.getKey() + "'"
                        + "AND KodeMerk !='" + pilihan2.getKey() + "'");

                while (rs2.next()) {
                    item2.add(rs2.getString(1));
                }

                //hitung support
                for (int i = 0; i < item2.size(); i++) {
                    Statement sta3 = con.createStatement();
                    ResultSet rs3 = sta3.executeQuery("select COUNT(DISTINCT NoBukti) from tbTransaksi "
                            + "where NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeMerk='" + pilihan1.getKey() + "') "
                            + "and NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeMerk='" + pilihan2.getKey() + "') "
                            + "and NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeMerk='" + item2.get(i) + "')");

                    while (rs3.next()) {
                        double supp = rs3.getDouble(1) / totTransaksi;
                        suppXY.add(supp * 100);
                        totXY.add(rs3.getDouble(1));
                    }

                    //suppXY.add(Double.parseDouble(suppxy.get(i)) * 100);
                    confXY.add(totXY.get(i) / totX * 100);
                    
                    System.out.println("(" + pilihan1.getKey() + "," + pilihan2.getKey() + ")=>" + item2.get(i) + "  s:" + suppXY.get(i) + "  c:" + Math.round(confXY.get(i)));               

                    if (suppXY.get(i) >= minS && confXY.get(i) >= minC) {
                        Statement sta4 = con.createStatement();
                        ResultSet rs4 = sta4.executeQuery("select NamaMerk from tbMerk where KodeMerk='" + item2.get(i) + "'");

                        while (rs4.next()) {
                            Object[] row = new Object[]{rs4.getString(1), Math.round(confXY.get(i))};
                            tblModel2.insertRow(tblModel2.getRowCount(), row);
                        }
                    }
                }

            } else if (rbtSupplier.isSelected()) {
                double totX = 0;
                
                //Hitung total transaksi X
                Statement sta1 = con.createStatement();
                ResultSet rs1 = sta1.executeQuery("select COUNT(DISTINCT NoBukti) from tbTransaksi "
                        + "where NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeSupplier='" + pilihan1.getKey() + "')"
                        + "and NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeSupplier='" + pilihan2.getKey() + "')");

                while (rs1.next()) {
                    totX = rs1.getDouble(1);
                }

                //List item selain pilihan combobox
                Statement sta2 = con.createStatement();
                ResultSet rs2 = sta2.executeQuery("select KodeSupplier from tbSupplier where KodeSupplier !='" + pilihan1.getKey() + "'"
                        + "AND KodeSupplier !='" + pilihan2.getKey() + "'");

                while (rs2.next()) {
                    item2.add(rs2.getString(1));
                }

                //hitung support
                for (int i = 0; i < item2.size(); i++) {
                    Statement sta3 = con.createStatement();
                    ResultSet rs3 = sta3.executeQuery("select COUNT(DISTINCT NoBukti) from tbTransaksi "
                            + "where NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeSupplier='" + pilihan1.getKey() + "') "
                            + "and NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeSupplier='" + pilihan2.getKey() + "') "
                            + "and NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeSupplier='" + item2.get(i) + "')");

                    while (rs3.next()) {
                        double supp = rs3.getDouble(1) / totTransaksi;
                        suppXY.add(supp * 100);
                        totXY.add(rs3.getDouble(1));
                    }

                    //suppXY.add(Double.parseDouble(suppxy.get(i)) * 100);
                    confXY.add(totXY.get(i) / totX * 100);
                    
                    System.out.println("(" + pilihan1.getKey() + "," + pilihan2.getKey() + ")=>" + item2.get(i) + "  s:" + suppXY.get(i) + "  c:" + Math.round(confXY.get(i)));               

                    if (suppXY.get(i) >= minS && confXY.get(i) >= minC) {
                        Statement sta4 = con.createStatement();
                        ResultSet rs4 = sta4.executeQuery("select NamaSupplier from tbSupplier where KodeSupplier='" + item2.get(i) + "'");

                        while (rs4.next()) {
                            Object[] row = new Object[]{rs4.getString(1), Math.round(confXY.get(i))};
                            tblModel2.insertRow(tblModel2.getRowCount(), row);
                        }
                    }
                }


            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error di analisis()\n" + ex);
            ex.printStackTrace();
        }

    }
}
