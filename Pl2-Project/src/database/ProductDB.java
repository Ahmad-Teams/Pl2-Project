package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.Product;
import java.util.ArrayList;

public class ProductDB {

    public static Connection connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection("jdbc:sqlite:system.db");
    }

    public static void add_product(Product prod) {
        try (
                Connection con = connect();
                PreparedStatement p = con.prepareStatement("insert into product(name,orignal_price,discount,amount,EPD,state) values(?,?,?,?,?,?)");
                PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setString(1, prod.getName());
            p.setInt(2, prod.getOrignalPrice());
            p.setInt(3, prod.getDiscount());
            p.setInt(4, prod.getAmount());
            p.setString(5, prod.getEPD());
            p.setString(6, prod.getpState());
            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }
    }

    public static void delete_product(int SN) {
        try (
                Connection con = connect();
                PreparedStatement p = con.prepareStatement("delete from product where SN = ?");
                PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setInt(1, SN);

            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }
    }

    public static void update_product(int SN, String name, int orignalPrice, int discount, int amount, String EPD, String state) {
        try (
                Connection con = connect();
                PreparedStatement p = con.prepareStatement("UPDATE product SET name = ?, orignal_price = ?, discount = ?, amount = ?,EPD = ?,state = ? WHERE SN = ?");
                PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setString(1, name);
            p.setInt(2, orignalPrice);
            p.setInt(3, discount);
            p.setInt(4, amount);
            p.setString(5, EPD);
            p.setString(6, state);
            p.setInt(7, SN);

            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }
    }

    public static void update_product(int SN, int discount) {
        try (
                Connection con = connect();
                PreparedStatement p = con.prepareStatement("UPDATE product SET discount = ? WHERE SN = ?");
                PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setInt(1, discount);
            p.setInt(2, SN);

            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }
    }

    public static ArrayList<Product> get_products() {
        ArrayList<Product> list = new ArrayList<>();
        try (
                Connection con = connect();
                PreparedStatement p = con.prepareStatement("select * from product");) {
            {
                ResultSet r = p.executeQuery();
                while (r.next()) {
                    list.add(new Product(r.getInt("SN"), r.getString("name"), r.getInt("orignal_price"), r.getInt("discount"), r.getInt("amount"), r.getString("EPD"), r.getString("state")));
                }
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }
        return list;
    }
}
