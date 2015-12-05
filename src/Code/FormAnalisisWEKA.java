/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import weka.associations.*;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.experiment.*;
import weka.filters.*;
import weka.filters.unsupervised.attribute.*;
import weka.filters.unsupervised.instance.*;

/**
 *
 * @author Ichsan Hariadi
 */
public class FormAnalisisWEKA extends javax.swing.JFrame {

    /**
     * Creates new form FormAnalisis
     */
    Connection con;
    Statement stmt;
    ResultSet rs;

    String[] hasilFinal;

    DefaultTableModel tblModel1, tblModel2;

    public FormAnalisisWEKA() {
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

    SwingWorker swSupplier = new SwingWorker() {
        @Override
        protected Object doInBackground() throws Exception {
            jProgressBar1.setVisible(true);
            jProgressBar1.setIndeterminate(true);
            txtStatus.setText("Working...");
            LoadInstanceSupplier();
            return null;
        }

        @Override
        public void done(){
            jProgressBar1.setVisible(false);
            jProgressBar1.setIndeterminate(false);
            FillComboBoxSupplier();
            txtStatus.setText("");
            btnAnalisis1.setEnabled(true);
            btnAnalisis2.setEnabled(true);
            cmbProduk.setEnabled(true);
            cmbProduk1.setEnabled(true);
            cmbProduk2.setEnabled(true);
        }
    };

    SwingWorker swKategori = new SwingWorker() {
        @Override
        protected Object doInBackground() throws Exception {
            jProgressBar1.setVisible(true);
            jProgressBar1.setIndeterminate(true);
            txtStatus.setText("Working...");
            LoadInstanceKategori();
            return null;
        }

        @Override
        public void done(){
            jProgressBar1.setVisible(false);
            jProgressBar1.setIndeterminate(false);
            txtStatus.setText("");
            FillComboBoxKategori();
            btnAnalisis1.setEnabled(true);
            btnAnalisis2.setEnabled(true);
            cmbProduk.setEnabled(true);
            cmbProduk1.setEnabled(true);
            cmbProduk2.setEnabled(true);
        }
    };
    
    SwingWorker swMerk = new SwingWorker() {
        @Override
        protected Object doInBackground() throws Exception {
            jProgressBar1.setVisible(true);
            jProgressBar1.setIndeterminate(true);
            txtStatus.setText("Working...");
            LoadInstanceMerk();
            return null;
        }

        @Override
        public void done(){
            jProgressBar1.setVisible(false);
            jProgressBar1.setIndeterminate(false);
            FillComboBoxMerk();
            txtStatus.setText("");
            btnAnalisis1.setEnabled(true);
            btnAnalisis2.setEnabled(true);
            cmbProduk.setEnabled(true);
            cmbProduk1.setEnabled(true);
            cmbProduk2.setEnabled(true);
        }
    };
    
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

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void FillResult() {
        try {
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=MBA_Final;integratedSecurity=true;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery("TRUNCATE TABLE tbCobaAnalisis BULK INSERT tbCobaAnalisis FROM 'C:\\Users\\Ichsan Hariadi\\Documents\\NetBeansProjects\\MBA_USTA\\Analisis.txt' WITH (FIELDTERMINATOR = '>', ROWTERMINATOR = '\\n')");
        } catch (Exception ex) {

        }
    }

    private void WriteTxt() {
        try {
            PrintWriter pr = new PrintWriter("Analisis.txt");
            for (int i = 0; i < hasilFinal.length; i++) {
                pr.println(hasilFinal[i]);
                System.out.println(hasilFinal[i]);
            }
            pr.close();

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void ReadResult1() {
        try {
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=MBA_Final;integratedSecurity=true;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            Statement sta = con.createStatement();
            Statement stax = con.createStatement();
            Statement stay = con.createStatement();

            ComboValue pilihan = (ComboValue) cmbProduk.getSelectedItem();

            ResultSet rs = sta.executeQuery("select kemungkinan, conf from tbCobaAnalisis where variabel='" + pilihan.getKey() + "'");

            if (!rs.isBeforeFirst()) {
                System.out.println("Tidak ditemukan kemungkinan Cross-selling!");
                JOptionPane.showMessageDialog(this, "Tidak ditemukan kemungkinan Cross-selling!");
            } else {
                String n1 = null;
                String n2 = null;

                while (rs.next()) {
                    if (rbtKategori.isSelected()) {
                        if (rs.getString(1).length() == 4) {
                            String kat1 = rs.getString(1).substring(0, 2);
                            ResultSet rsk1 = stax.executeQuery("SELECT * FROM tbKategori where KodeKategori='" + kat1 + "'");
                            while (rsk1.next()) {
                                n1 = rsk1.getString(2);
                            }

                            String kat2 = rs.getString(1).substring(2, 4);
                            ResultSet rsk2 = stay.executeQuery("SELECT * FROM tbKategori where KodeKategori='" + kat2 + "'");
                            while (rsk2.next()) {
                                n2 = rsk2.getString(2);
                            }

                            Double cfd = (Double.parseDouble(rs.getString(2)) * 100);

                            Object[] row = new Object[]{n1 + " dan " + n2, cfd};
                            tblModel1.insertRow(tblModel1.getRowCount(), row);
                        } else if (rs.getString(1).length() == 2) {
                            String kat1 = rs.getString(1).substring(0, 2);
                            ResultSet rsk1 = stax.executeQuery("SELECT * FROM tbKategori where KodeKategori='" + kat1 + "'");
                            while (rsk1.next()) {
                                n1 = rsk1.getString(2);
                            }

                            Double cfd = (Double.parseDouble(rs.getString(2)) * 100);

                            Object[] row = new Object[]{n1, cfd};
                            tblModel1.insertRow(tblModel1.getRowCount(), row);
                        }
                    } else if (rbtMerk.isSelected()) {
                        if (rs.getString(1).length() == 4) {
                            String mrk1 = rs.getString(1).substring(0, 2);
                            ResultSet rsm1 = stax.executeQuery("SELECT * FROM tbMerk where KodeMerk='" + mrk1 + "'");
                            while (rsm1.next()) {
                                n1 = rsm1.getString(2);
                            }

                            String mrk2 = rs.getString(1).substring(2, 4);
                            ResultSet rsm2 = stay.executeQuery("SELECT * FROM tbMerk where KodeMerk='" + mrk2 + "'");
                            while (rsm2.next()) {
                                n2 = rsm2.getString(2);
                            }

                            Double cfd = (Double.parseDouble(rs.getString(2)) * 100);

                            Object[] row = new Object[]{n1 + " dan " + n2, cfd};
                            tblModel1.insertRow(tblModel1.getRowCount(), row);
                        } else if (rs.getString(1).length() == 2) {
                            String kat1 = rs.getString(1).substring(0, 2);
                            ResultSet rsk1 = stax.executeQuery("SELECT * FROM tbMerk where KodeMerk='" + kat1 + "'");
                            while (rsk1.next()) {
                                n1 = rsk1.getString(2);
                            }

                            Double cfd = (Double.parseDouble(rs.getString(2)) * 100);

                            Object[] row = new Object[]{n1, cfd};
                            tblModel1.insertRow(tblModel1.getRowCount(), row);
                        }
                    }
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void ReadResult2() {
        try {
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=MBA_Final;integratedSecurity=true;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            Statement sta = con.createStatement();
            Statement stax = con.createStatement();
            Statement stay = con.createStatement();

            ComboValue pilihan1 = (ComboValue) cmbProduk1.getSelectedItem();
            ComboValue pilihan2 = (ComboValue) cmbProduk2.getSelectedItem();

            ResultSet rs = sta.executeQuery("select kemungkinan, conf from tbCobaAnalisis where variabel='" + pilihan1.getKey() + pilihan2.getKey() + "'");

            if (!rs.isBeforeFirst()) {
                System.out.println("Tidak ditemukan kemungkinan Cross-selling!");
                JOptionPane.showMessageDialog(this, "Tidak ditemukan kemungkinan Cross-selling!");
            } else {
                String n1 = null;
                String n2 = null;

                while (rs.next()) {
                    if (rbtKategori.isSelected()) {
                        if (rs.getString(1).length() == 4) {
                            String kat1 = rs.getString(1).substring(0, 2);
                            ResultSet rsk1 = stax.executeQuery("SELECT * FROM tbKategori where KodeKategori='" + kat1 + "'");
                            while (rsk1.next()) {
                                n1 = rsk1.getString(2);
                            }

                            String kat2 = rs.getString(1).substring(2, 4);
                            ResultSet rsk2 = stay.executeQuery("SELECT * FROM tbKategori where KodeKategori='" + kat2 + "'");
                            while (rsk2.next()) {
                                n2 = rsk2.getString(2);
                            }

                            Double cfd = (Double.parseDouble(rs.getString(2)) * 100);

                            Object[] row = new Object[]{n1 + " dan " + n2, cfd};
                            tblModel2.insertRow(tblModel2.getRowCount(), row);
                        } else if (rs.getString(1).length() == 2) {
                            String kat1 = rs.getString(1).substring(0, 2);
                            ResultSet rsk1 = stax.executeQuery("SELECT * FROM tbKategori where KodeKategori='" + kat1 + "'");
                            while (rsk1.next()) {
                                n1 = rsk1.getString(2);
                            }

                            Double cfd = (Double.parseDouble(rs.getString(2)) * 100);

                            Object[] row = new Object[]{n1, cfd};
                            tblModel2.insertRow(tblModel2.getRowCount(), row);
                        }
                    } else if (rbtMerk.isSelected()) {
                        if (rs.getString(1).length() == 4) {
                            String mrk1 = rs.getString(1).substring(0, 2);
                            ResultSet rsm1 = stax.executeQuery("SELECT * FROM tbMerk where KodeMerk='" + mrk1 + "'");
                            while (rsm1.next()) {
                                n1 = rsm1.getString(2);
                            }

                            String mrk2 = rs.getString(1).substring(2, 4);
                            ResultSet rsm2 = stay.executeQuery("SELECT * FROM tbMerk where KodeMerk='" + mrk2 + "'");
                            while (rsm2.next()) {
                                n2 = rsm2.getString(2);
                            }

                            Double cfd = (Double.parseDouble(rs.getString(2)) * 100);

                            Object[] row = new Object[]{n1 + " dan " + n2, cfd};
                            tblModel2.insertRow(tblModel2.getRowCount(), row);
                        } else if (rs.getString(1).length() == 2) {
                            String kat1 = rs.getString(1).substring(0, 2);
                            ResultSet rsk1 = stax.executeQuery("SELECT * FROM tbMerk where KodeMerk='" + kat1 + "'");
                            while (rsk1.next()) {
                                n1 = rsk1.getString(2);
                            }

                            Double cfd = (Double.parseDouble(rs.getString(2)) * 100);

                            Object[] row = new Object[]{n1, cfd};
                            tblModel2.insertRow(tblModel2.getRowCount(), row);
                        }
                    }
                }
            }

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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jMenuItem1 = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbProduk = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHasil1 = new javax.swing.JTable();
        btnAnalisis1 = new javax.swing.JButton();
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
        menuQuit = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Market Basket Analysis (WEKA)");

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Item 1 :");

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

        btnAnalisis1.setText("Analisis!");
        btnAnalisis1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalisis1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnAnalisis1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        menuSwitch.setText("Switch to Manual Calculation...");
        menuSwitch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSwitchActionPerformed(evt);
            }
        });
        jMenu1.add(menuSwitch);
        jMenu1.add(jSeparator1);

        menuQuit.setText("Quit");
        menuQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQuitActionPerformed(evt);
            }
        });
        jMenu1.add(menuQuit);

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
        swKategori.execute();
    }//GEN-LAST:event_rbtKategoriActionPerformed

    private void rbtMerkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtMerkActionPerformed
        // TODO add your handling code here:
        swMerk.execute();
    }//GEN-LAST:event_rbtMerkActionPerformed

    private void rbtSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtSupplierActionPerformed
        // TODO add your handling code here:
        swSupplier.execute();
    }//GEN-LAST:event_rbtSupplierActionPerformed

    private void txtSuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSuppActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSuppActionPerformed

    private void txtConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConfActionPerformed

    private void btnAnalisis1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalisis1ActionPerformed
        // TODO add your handling code here:
        DoMining1();
        while (tblModel1.getRowCount() > 0) {
            tblModel1.removeRow(0);
        }
        ReadResult1();
    }//GEN-LAST:event_btnAnalisis1ActionPerformed

    private void btnAnalisis2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalisis2ActionPerformed
        // TODO add your handling code here:
        DoMining2();
        while (tblModel2.getRowCount() > 0) {
            tblModel2.removeRow(0);
        }
        ReadResult2();
    }//GEN-LAST:event_btnAnalisis2ActionPerformed

    private void menuSwitchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSwitchActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        FormAnalisisManual fman = new FormAnalisisManual();
        fman.setVisible(true);
    }//GEN-LAST:event_menuSwitchActionPerformed

    private void menuQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuQuitActionPerformed
        // TODO add your handling code here:
        System.exit(1);
    }//GEN-LAST:event_menuQuitActionPerformed

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
            java.util.logging.Logger.getLogger(FormAnalisisWEKA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormAnalisisWEKA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormAnalisisWEKA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormAnalisisWEKA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormAnalisisWEKA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalisis1;
    private javax.swing.JButton btnAnalisis2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
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
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem menuQuit;
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

    private void LoadInstanceKategori() {
        try {

            InstanceQuery query = new InstanceQuery();
            query.setDatabaseURL("jdbc:odbc:MBA_Final");
            query.setUsername("");
            query.setPassword("");
            query.setQuery("SELECT distinct a.NoBukti, b.KodeKategori FROM tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang ORDER BY a.NoBukti ASC");
            Instances data = query.retrieveInstances();

            String[] options1 = weka.core.Utils.splitOptions("-G first -A Sum -S");
            Denormalize denormal = new Denormalize();
            denormal.setOptions(options1);
            denormal.setInputFormat(data);
            Instances data2 = Filter.useFilter(data, denormal);

            String[] options2 = weka.core.Utils.splitOptions("-R first");
            Remove rmv = new Remove();
            rmv.setOptions(options2);
            rmv.setInputFormat(data2);
            Instances resultData = Filter.useFilter(data2, rmv);

            BufferedWriter writer = new BufferedWriter(new FileWriter("Data.arff"));
            writer.write(resultData.toString());
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void LoadInstanceMerk() {
        try {

            InstanceQuery query = new InstanceQuery();
            query.setDatabaseURL("jdbc:odbc:MBA_Final");
            query.setUsername("");
            query.setPassword("");
            query.setQuery("SELECT distinct a.NoBukti, b.KodeMerk FROM tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang ORDER BY a.NoBukti ASC");
            Instances data = query.retrieveInstances();

            String[] options1 = weka.core.Utils.splitOptions("-G first -A Sum -S");
            Denormalize denormal = new Denormalize();
            denormal.setOptions(options1);
            denormal.setInputFormat(data);
            Instances data2 = Filter.useFilter(data, denormal);

            String[] options2 = weka.core.Utils.splitOptions("-R first");
            Remove rmv = new Remove();
            rmv.setOptions(options2);
            rmv.setInputFormat(data2);
            Instances resultData = Filter.useFilter(data2, rmv);

            BufferedWriter writer = new BufferedWriter(new FileWriter("Data.arff"));
            writer.write(resultData.toString());
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void LoadInstanceSupplier() {
        try {
            InstanceQuery query = new InstanceQuery();
            query.setDatabaseURL("jdbc:odbc:MBA_Final");
            query.setUsername("");
            query.setPassword("");
            query.setQuery("SELECT distinct a.NoBukti, b.KodeSupplier FROM tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang ORDER BY a.NoBukti ASC");
            Instances data = query.retrieveInstances();

            String[] options1 = weka.core.Utils.splitOptions("-G first -A Sum -S");
            Denormalize denormal = new Denormalize();
            denormal.setOptions(options1);
            denormal.setInputFormat(data);
            Instances data2 = Filter.useFilter(data, denormal);

            String[] options2 = weka.core.Utils.splitOptions("-R first");
            Remove rmv = new Remove();
            rmv.setOptions(options2);
            rmv.setInputFormat(data2);
            Instances resultData = Filter.useFilter(data2, rmv);

            BufferedWriter writer = new BufferedWriter(new FileWriter("Data.arff"));
            writer.write(resultData.toString());
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void DoMining2() {
        double minS = Double.parseDouble(txtSupp.getText()) / 100;
        double minC = Double.parseDouble(txtConf.getText()) / 100;

        ComboValue cmb1 = (ComboValue) cmbProduk1.getSelectedItem();
        ComboValue cmb2 = (ComboValue) cmbProduk2.getSelectedItem();

        if (rbtKategori.isSelected()) {
            try {
                DataSource source = new DataSource("Data.arff");
                Instances data = source.getDataSet();

                FPGrowth model = new FPGrowth();
                model.setNumRulesToFind(100);
                model.setMinMetric(minC);
                model.setLowerBoundMinSupport(minS);
                model.setRulesMustContain("KodeKategori_" + cmb1.getKey() + ",KodeKategori_" + cmb2.getKey());
                model.buildAssociations(data);

                if (model.toString().contains("!")) {
                    JOptionPane.showMessageDialog(this, "Tidak ditemukan kemungkinan Cross-Selling!");
                    System.out.print("Tidak ditemukan kemungkinan Cross-Selling!");
                } else {
                    String hasil1 = "";
                    String hasil2 = model.toString();
                    String[] out = hasil2.split("Showing only rules that contain: KodeKategori_" + cmb1.getKey() + ",KodeKategori_" + cmb2.getKey());
                    String[] barang = out[1].trim().split("\n");

                    for (int a = 0; a < barang.length; a++) {
                        if (!barang[a].trim().equalsIgnoreCase(" ")) {
                            String[] temp = barang[a].trim().split("==>");
                            //System.out.println("\n" + temp[0]);
                            String[] tempA = temp[0].split("==");
                            //System.out.println("\n" + tempA[0]);
                            String[] tempB = temp[1].split(">");
                            //String tempC = temp[2].trim().replace("<conf:(", "");
                            hasil1 = hasil1.concat((tempA[0].substring(0, (tempA[0].length() - 5))) + " >") + (tempB[0].substring(0, (tempB[0].length() - 1)).replace("<conf:(", "conf:")) + "\n";
                        }
                    }

                    String HasilFinalTemp = hasil1.replaceAll("=t", "").replaceAll("\\d+?[.] ", "").replaceAll("\\[", "").replaceAll("\\]", "").replace(": > ", ">").replace(":  > ", ">").replaceAll(": \\d\\d\\d   conf:", ">").replaceAll(": \\d\\d\\d\\d   conf:", ">");
                    hasilFinal = HasilFinalTemp.replace("KodeKategori_", "").replace(", ", "").split("\n");

                    WriteTxt();
                    FillResult();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        } else if (rbtMerk.isSelected()) {
            try {
                DataSource source = new DataSource("Data.arff");
                Instances data = source.getDataSet();

                FPGrowth model = new FPGrowth();
                model.setNumRulesToFind(100);
                model.setMinMetric(minC);
                model.setLowerBoundMinSupport(minS);
                model.setRulesMustContain("KodeMerk_" + cmb1.getKey() + ",KodeMerk_" + cmb2.getKey());
                model.buildAssociations(data);

                if (model.toString().contains("!")) {
                    JOptionPane.showMessageDialog(this, "Tidak ditemukan kemungkinan Cross-Selling!");
                    System.out.print("Tidak ditemukan kemungkinan Cross-Selling!");
                } else {
                    String hasil1 = "";
                    String hasil2 = model.toString();
                    String[] out = hasil2.split("Showing only rules that contain: KodeMerk_" + cmb1.getKey() + ",KodeMerk_" + cmb2.getKey());
                    String[] barang = out[1].trim().split("\n");

                    for (int a = 0; a < barang.length; a++) {
                        if (!barang[a].trim().equalsIgnoreCase(" ")) {
                            String[] temp = barang[a].trim().split("==>");
                            //System.out.println("\n" + temp[0]);
                            String[] tempA = temp[0].split("==");
                            //System.out.println("\n" + tempA[0]);
                            String[] tempB = temp[1].split(">");
                            //String tempC = temp[2].trim().replace("<conf:(", "");
                            hasil1 = hasil1.concat((tempA[0].substring(0, (tempA[0].length() - 5))) + " >") + (tempB[0].substring(0, (tempB[0].length() - 1)).replace("<conf:(", "conf:")) + "\n";
                        }
                    }

                    String HasilFinalTemp = hasil1.replaceAll("=t", "").replaceAll("\\d+?[.] ", "").replaceAll("\\[", "").replaceAll("\\]", "").replace(": > ", ">").replace(":  > ", ">").replaceAll(": \\d\\d\\d   conf:", ">").replaceAll(": \\d\\d\\d\\d   conf:", ">");
                    hasilFinal = HasilFinalTemp.replace("KodeMerk_", "").replace(", ", "").split("\n");

                    WriteTxt();
                    FillResult();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        } else if (rbtSupplier.isSelected()) {
            try {
                DataSource source = new DataSource("Data.arff");
                Instances data = source.getDataSet();

                FPGrowth model = new FPGrowth();
                model.setNumRulesToFind(100);
                model.setMinMetric(minC);
                model.setLowerBoundMinSupport(minS);
                model.setRulesMustContain("KodeSupplier_" + cmb1.getKey() + ",KodeSupplier_" + cmb2.getKey());
                model.buildAssociations(data);

                if (model.toString().contains("!")) {
                    JOptionPane.showMessageDialog(this, "Tidak ditemukan kemungkinan Cross-Selling!");
                    System.out.print("Tidak ditemukan kemungkinan Cross-Selling!");
                } else {
                    String hasil1 = "";
                    String hasil2 = model.toString();
                    String[] out = hasil2.split("Showing only rules that contain: KodeSupplier_" + cmb1.getKey() + ",KodeSupplier_" + cmb2.getKey());
                    String[] barang = out[1].trim().split("\n");

                    for (int a = 0; a < barang.length; a++) {
                        if (!barang[a].trim().equalsIgnoreCase(" ")) {
                            String[] temp = barang[a].trim().split("==>");
                            //System.out.println("\n" + temp[0]);
                            String[] tempA = temp[0].split("==");
                            //System.out.println("\n" + tempA[0]);
                            String[] tempB = temp[1].split(">");
                            //String tempC = temp[2].trim().replace("<conf:(", "");
                            hasil1 = hasil1.concat((tempA[0].substring(0, (tempA[0].length() - 5))) + " >") + (tempB[0].substring(0, (tempB[0].length() - 1)).replace("<conf:(", "conf:")) + "\n";
                        }
                    }

                    String HasilFinalTemp = hasil1.replaceAll("=t", "").replaceAll("\\d+?[.] ", "").replaceAll("\\[", "").replaceAll("\\]", "").replace(": > ", ">").replace(":  > ", ">").replaceAll(": \\d\\d\\d   conf:", ">").replaceAll(": \\d\\d\\d\\d   conf:", ">");
                    hasilFinal = HasilFinalTemp.replace("KodeSupplier_", "").replace(", ", "").split("\n");

                    WriteTxt();
                    FillResult();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        }
    }

    private void DoMining1() {
        double minS = Double.parseDouble(txtSupp.getText()) / 100;
        double minC = Double.parseDouble(txtConf.getText()) / 100;

        ComboValue cmb = (ComboValue) cmbProduk.getSelectedItem();

        if (rbtKategori.isSelected()) {
            try {
                DataSource source = new DataSource("Data.arff");
                Instances data = source.getDataSet();

                FPGrowth model = new FPGrowth();
                model.setNumRulesToFind(100);
                model.setMinMetric(minC);
                model.setLowerBoundMinSupport(minS);
                model.setRulesMustContain("KodeKategori_" + cmb.getKey());
                model.buildAssociations(data);

                if (model.toString().contains("!")) {
                    JOptionPane.showMessageDialog(this, "Tidak ditemukan kemungkinan Cross-Selling!");
                    System.out.print("Tidak ditemukan kemungkinan Cross-Selling!");
                } else {
                    String hasil1 = "";
                    String hasil2 = model.toString();
                    String[] out = hasil2.split("Showing only rules that contain: KodeKategori_" + cmb.getKey());
                    String[] barang = out[1].trim().split("\n");

                    for (int a = 0; a < barang.length; a++) {
                        if (!barang[a].trim().equalsIgnoreCase(" ")) {
                            String[] temp = barang[a].trim().split("==>");
                            //System.out.println("\n" + temp[0]);
                            String[] tempA = temp[0].split("==");
                            //System.out.println("\n" + tempA[0]);
                            String[] tempB = temp[1].split(">");
                            //String tempC = temp[2].trim().replace("<conf:(", "");
                            hasil1 = hasil1.concat((tempA[0].substring(0, (tempA[0].length() - 5))) + " >") + (tempB[0].substring(0, (tempB[0].length() - 1)).replace("<conf:(", "conf:")) + "\n";
                        }
                    }

                    String HasilFinalTemp = hasil1.replaceAll("=t", "").replaceAll("\\d+?[.] ", "").replaceAll("\\[", "").replaceAll("\\]", "").replace(": > ", ">").replace(":  > ", ">").replaceAll(": \\d\\d\\d   conf:", ">").replaceAll(": \\d\\d\\d\\d   conf:", ">");
                    hasilFinal = HasilFinalTemp.replace("KodeKategori_", "").replace(", ", "").split("\n");
                    
                    WriteTxt();
                    FillResult();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        } else if (rbtMerk.isSelected()) {
            try {
                DataSource source = new DataSource("Data.arff");
                Instances data = source.getDataSet();

                FPGrowth model = new FPGrowth();
                model.setNumRulesToFind(100);
                model.setMinMetric(minC);
                model.setLowerBoundMinSupport(minS);
                model.setRulesMustContain("KodeMerk_" + cmb.getKey());
                model.buildAssociations(data);

                if (model.toString().contains("!")) {
                    JOptionPane.showMessageDialog(this, "Tidak ditemukan kemungkinan Cross-Selling!");
                    System.out.print("Tidak ditemukan kemungkinan Cross-Selling!");
                } else {
                    String hasil1 = "";
                    String hasil2 = model.toString();
                    String[] out = hasil2.split("Showing only rules that contain: KodeMerk_" + cmb.getKey());
                    String[] barang = out[1].trim().split("\n");

                    for (int a = 0; a < barang.length; a++) {
                        if (!barang[a].trim().equalsIgnoreCase(" ")) {
                            String[] temp = barang[a].trim().split("==>");
                            //System.out.println("\n" + temp[0]);
                            String[] tempA = temp[0].split("==");
                            //System.out.println("\n" + tempA[0]);
                            String[] tempB = temp[1].split(">");
                            //String tempC = temp[2].trim().replace("<conf:(", "");
                            hasil1 = hasil1.concat((tempA[0].substring(0, (tempA[0].length() - 5))) + " >") + (tempB[0].substring(0, (tempB[0].length() - 1)).replace("<conf:(", "conf:")) + "\n";
                        }
                    }

                    String HasilFinalTemp = hasil1.replaceAll("=t", "").replaceAll("\\d+?[.] ", "").replaceAll("\\[", "").replaceAll("\\]", "").replace(": > ", ">").replace(":  > ", ">").replaceAll(": \\d\\d\\d   conf:", ">").replaceAll(": \\d\\d\\d\\d   conf:", ">");
                    hasilFinal = HasilFinalTemp.replace("KodeMerk_", "").replace(", ", "").split("\n");
                    
                    WriteTxt();
                    FillResult();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        } else if (rbtSupplier.isSelected()) {
            try {
                DataSource source = new DataSource("Data.arff");
                Instances data = source.getDataSet();

                FPGrowth model = new FPGrowth();
                model.setNumRulesToFind(100);
                model.setMinMetric(minC);
                model.setLowerBoundMinSupport(minS);
                model.setRulesMustContain("KodeSupplier_" + cmb.getKey());
                model.buildAssociations(data);

                if (model.toString().contains("!")) {
                    JOptionPane.showMessageDialog(this, "Tidak ditemukan kemungkinan Cross-Selling!");
                    System.out.print("Tidak ditemukan kemungkinan Cross-Selling!");
                } else {
                    String hasil1 = "";
                    String hasil2 = model.toString();
                    String[] out = hasil2.split("Showing only rules that contain: KodeSupplier_" + cmb.getKey());
                    String[] barang = out[1].trim().split("\n");

                    for (int a = 0; a < barang.length; a++) {
                        if (!barang[a].trim().equalsIgnoreCase(" ")) {
                            String[] temp = barang[a].trim().split("==>");
                            //System.out.println("\n" + temp[0]);
                            String[] tempA = temp[0].split("==");
                            //System.out.println("\n" + tempA[0]);
                            String[] tempB = temp[1].split(">");
                            //String tempC = temp[2].trim().replace("<conf:(", "");
                            hasil1 = hasil1.concat((tempA[0].substring(0, (tempA[0].length() - 5))) + " >") + (tempB[0].substring(0, (tempB[0].length() - 1)).replace("<conf:(", "conf:")) + "\n";
                        }
                    }

                    String HasilFinalTemp = hasil1.replaceAll("=t", "").replaceAll("\\d+?[.] ", "").replaceAll("\\[", "").replaceAll("\\]", "").replace(": > ", ">").replace(":  > ", ">").replaceAll(": \\d\\d\\d   conf:", ">").replaceAll(": \\d\\d\\d\\d   conf:", ">");
                    hasilFinal = HasilFinalTemp.replace("KodeSupplier", "").replace(", ", "").split("\n");

                    WriteTxt();
                    FillResult();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        }
    }
}
