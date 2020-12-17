package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.Order;
import java.util.ArrayList;

public class OrderDB {

    public static Connection connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection("jdbc:sqlite:system.db");
    }

    public static void add_order(Order ord) {
        try (
                Connection con = connect();
                PreparedStatement p = con.prepareStatement("insert into orders(PSN,amount) values(?,?)");
                PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {

            p1.execute();
            p.setInt(1, ord.getPSN());
            p.setInt(2, ord.getAmount());
            
            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }
    }

    public static void delete_order(int PSN) {
        try (
                Connection con = connect();
                PreparedStatement p = con.prepareStatement("delete from orders where PSN = ?");
                PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();

            p.setInt(1, PSN);

            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }
    }

    public static void update_order(int PSN,int amount) {
        try (
                Connection con = connect();
                PreparedStatement p = con.prepareStatement("UPDATE orders SET amount = ? WHERE PSN = ?");
                PreparedStatement p1 = con.prepareStatement("PRAGMA foreign_keys = ON;");) {
            p1.execute();
            p.setInt(1, amount);
            p.setInt(2, PSN);

            
            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }
    }

    public static ArrayList<Order> get_orders() {
        ArrayList<Order> list = new ArrayList<>();
        try (
                Connection con = connect();
                PreparedStatement p = con.prepareStatement("select * from orders");) {
            {
                ResultSet r = p.executeQuery();
                while (r.next()) {
                    list.add(new Order(r.getInt("PSN"), r.getInt("amount")));
                }
            }
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());// we will put out custimize exption massages here
        }
        return list;
    }
}
