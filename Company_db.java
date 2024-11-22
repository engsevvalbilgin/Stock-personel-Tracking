//Şevval Bilğin 2221221013
package com.mycompany.sevval_bilgin_stock_personel_tracking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class Company_db {

    public static final String connectionString = "jdbc:mysql://127.0.0.1:3306/stock_company_personels?user=root&password=sevval";

    public static Connection conn;
    public static ArrayList<Personel> recorded_personels = new ArrayList<>();
    public static ArrayList<String> recorded_stock_infos = new ArrayList<>();
    public static ArrayList<ArrayList> stocks = new ArrayList<>();
    public static ArrayList personelInfos = new ArrayList<>();
//Database'e personel yükleme metodu
    public static boolean AddPersonel(Personel np1) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        try {
            conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO stock_company_personels.personel (name, surname, password, position, email)"
                    + " VALUES ('" + np1.name + "','" + np1.surname
                    + "','" + np1.password + "','" + np1.position + "','" + np1.email + "');";

            stmt.executeUpdate(query);
            conn.close();

            return true;
        } catch (SQLException ex) {
           
            return false;
        }
    }
//Database'e stock ekleme metodu
    public static boolean AddStock(String name, String num) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        try {
            conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO stock_company_personels.material (material_name,material_number)"
                    + " VALUES ('" + name + "','" + num
                    + "');";

            stmt.executeUpdate(query);
            conn.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Company_db.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
//Database'e bağanıp girşi yapma metodu
    public static Personel Login(int id, String password) {
        Personel p1 = new Personel();
        try {
            conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM stock_company_personels.personel WHERE password = '" + password + "' AND  id =  '" + id + "' ;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                p1.surname = rs.getString("surname");
                p1.name = rs.getString("name");
                p1.email = rs.getString("email");
                p1.password = rs.getString("password");

                if (rs.getString("position").compareTo("Leader") == 0) {
                    p1.position = Personel.Position.Leader;
                } else {
                    p1.position = Personel.Position.Member;
                }
            }

            conn.close();
        } catch (SQLException ex) {

            System.out.println("bağlantı kurulamadı in login");
            return p1;
        }
        return p1;
    }
//Database'e bağlanıp arama yapma metodu
    public static ArrayList<Personel> SearchByNameOrSurnameOrEmailOrPosition(String s) {
        try {
            conn = DriverManager.getConnection(connectionString);
            java.sql.Statement stmt = conn.createStatement();
            String query = "SELECT * FROM stock_company_personels.personel WHERE name LIKE"
                    + " '%" + s + "%' OR surname LIKE  '%" + s + "%' OR email LIKE  '%" + s + "%' OR position LIKE  '%" + s + "%' ;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                Personel p1 = new Personel();

                p1.name = rs.getString("name");
                p1.surname = rs.getString("surname");
                p1.email = rs.getString("email");
                p1.password = rs.getString("password");
                if (rs.getString("position").compareToIgnoreCase("Leader") == 0) {
                    p1.position = Personel.Position.Leader;
                } else {
                    p1.position = Personel.Position.Member;
                }

                recorded_personels.add(p1);
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("bağlantı kurulamadı in search");
        }

        return recorded_personels;
    }
//Database'e bağlanıp email ile arama yaparak id bulma metodu
    public static int SearchByEmailFindId(String s) {
        int id = -1;
        try {
            conn = DriverManager.getConnection(connectionString);
            java.sql.Statement stmt = conn.createStatement();
            String query = "SELECT * FROM stock_company_personels.personel WHERE email = '" + s + "';";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = Integer.parseInt(rs.getString("id"));
            }

            conn.close();
        } catch (SQLException ex) {
            System.out.println("bağlantı kurulamadı in search");
        }

        return id;
    }
    public static PreparedStatement ps;
//Database'e bağlanıp id ile personeli bulma ve güncelleme metodu
    public static boolean SearchbyIdFindPersonelAndUpdate(int id, String nemail, String npassword, String position) {
        try {

            conn = DriverManager.getConnection(connectionString);
            java.sql.Statement stmt = conn.createStatement();

            String query = " UPDATE stock_company_personels.personel SET email='" + nemail + "',"
                    + "position = '" + position + "', password='" + npassword + "' WHERE id='" + id + "';";

            ps = conn.prepareStatement(query);
            ps.execute();

            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Company_db.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
//stktaki eşyaları ismi ile bulup stokları güncelleme
    public static boolean SearchbyMaterialNameAndUpdate(String name, String num) {
        try {

            conn = DriverManager.getConnection(connectionString);
            java.sql.Statement stmt = conn.createStatement();

            String query = " UPDATE stock_company_personels.material SET material_number = " + num + " WHERE material_name='" + name + "';";

            ps = conn.prepareStatement(query);
            ps.execute();

            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Company_db.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
////Database'ten kullanıcyı silme
    public static boolean DeletePersonel(int id) {
        try {
            conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            String query = "DELETE FROM stock_company_personels.personel WHERE id='" + id + " ';";

            stmt.executeUpdate(query);
            conn.close();

            return true;
        } catch (SQLException ex) {
            System.out.println("bağlantı kurulamadı in delete personel");
            return false;
        }

    }
////Database'e bağlanıp ismi verilen stoğu silme
    public static boolean DeleteStock(String name) {
        try {
            conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            String query = "DELETE FROM stock_company_personels.material WHERE material_name='" + name + " ';";

            stmt.executeUpdate(query);
            conn.close();

            return true;
        } catch (SQLException ex) {
            System.out.println("bağlantı kurulamadı in delete stock");
            return false;
        }

    }
//Database'te stok arama
    public static ArrayList StocksSearchByName(String s) {
        try {

            recorded_stock_infos.clear();
            conn = DriverManager.getConnection(connectionString);
            java.sql.Statement stmt = conn.createStatement();
            String query = "SELECT * FROM stock_company_personels.material WHERE material_name LIKE  '%" + s + "%' ;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                recorded_stock_infos.add(rs.getString("material_name"));
                recorded_stock_infos.add(rs.getString("material_number"));
                stocks.add(recorded_stock_infos);

            }

            conn.close();
        } catch (SQLException ex) {
            System.out.println("bağlantı kurulamadı in search");
        }

        return stocks;
    }


}
