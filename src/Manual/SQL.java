/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Manual;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author FUCK_HIM
 */
public class SQL {

    Connection con;
    Statement stmt;
    ResultSet rs;

    public int bnyTransaksi() {
        int jml = 0;

        try {
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=MBA_Final;integratedSecurity=true;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery("select count(NoBukti) from tbTransaksi ");
            while (rs.next()) {
                jml = rs.getInt(1);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return jml;
    }

    public int c2(String a, String b) {
        int jml = 0;
        try {
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=MBA_Final;integratedSecurity=true;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery("select COUNT(DISTINCT NoBukti)as ddd from tbTransaksi "
                    + "where NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeKategori='" + a + "') "
                    + "and NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeKategori='" + b + "')");
            while (rs.next()) {
                jml = rs.getInt(1);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error di SQL:c2()\n" + ex);
        }

        return jml;
    }

    public int c3(String a, String b, String c) {
        int jml = 0;
        try {
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=MBA_Final;integratedSecurity=true;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery("select count(DISTINCT NoBukti) from tbTransaksi "
                    + "where NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeKategori='" + a + "') "
                    + "and NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeKategori='" + b + "') "
                    + "and NoBukti in (select NoBukti from tbTransaksi a JOIN tbBarang b ON a.KodeBarang = b.KodeBarang where KodeKategori='" + c + "')");
            while (rs.next()) {
                jml = rs.getInt(1);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error di SQL:c3()\n" + ex);
        }

        return jml;
    }
}
