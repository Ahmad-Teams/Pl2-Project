/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import database.EmployeeDB;
import database.ProductDB;
import database.OrderDB;
import database.RProductDB;
import java.util.ArrayList;
import java.util.Scanner;
import project.Order;
import project.Product;

public class SalesEmployee extends Employee {

    //Scanner input = new Scanner(System.in);
    public SalesEmployee(int id, String fName, String lName, String userName, String password) {
        super(id, fName, lName, userName, password, "S");
    }

    public SalesEmployee(String fName, String lName, String userName, String password) {
        super(fName, lName, userName, password, "S");
    }

    @Override
    public String getFullName() {
        return "SE." + super.getFullName();
    }

    public int openList() {
        ProductDB.update_products_states();
        RProductDB.update_RProducts_states();

        int c;
        System.out.println("\nHello ," + this.getfName() + "!");
        do {
            System.out.printf("\nSales Menu:"
                    + "\nSearch for a product.              (Enter 1)"
                    + "\nList all products.                 (Enter 2)"
                    + "\nList all orders.                   (Enter 3)"
                    + "\nMake an order.                     (Enter 4)"
                    + "\nDelete an order.                   (Enter 5)"
                    + "\nAlter your information.            (Enter 6)"//fname lname
                    + "\nLogOut                             (Enter 7)\n");
            System.out.printf("?: ");
            c = Check.CheckNumber();

            if (c != 1 && c != 2 && c != 3 && c != 4 && c != 5 && c != 6 && c != 7) {
                System.out.println("Invaild Input!");
                continue;
            }

            switch (c) {
                case 1:
                    search();
                    break;
                case 2:
                    print_list_Product();
                    break;
                case 3:
                    print_list_Order();
                    break;
                case 4:
                    make_an_order();
                    break;
                case 5:
                    delete_an_order();
                    break;
                case 6:
                    update_info();
                    break;
            }

        } while (c != 7);
        System.out.println("bey bey ," + this.getfName() + "!");
        return 0;
    }

    private void search() {
        ArrayList<Product> list = new ArrayList<>();
        list = ProductDB.get_products();
        System.out.print("Enter the serial number : ");
        int sn = Check.CheckSerialNumber();
        if (sn == -1) {
            System.out.println("No update happened");
        } else {
            int c = 0;                  //??????
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSN() == sn) {
                    System.out.printf("%-6s%-10s%-10s%-15s%-10s%-10s%-15s&-10s%-10s\n", "SN", "Name", "Price", "Orignal price", "Disscount", "Amount", "Expier data", "Min Range", "State");
                    System.out.printf("%-6s%-10s%-10d", list.get(i).getSN(), list.get(i).getName(), list.get(i).getPrice());
                    System.out.printf("%-15d%-10d%-10d", list.get(i).getOrignalPrice(), list.get(i).getDiscount(), list.get(i).getAmount());
                    System.out.printf("%-15s%-10s%-10s\n", list.get(i).getEPD(), list.get(i).getMinRange(), list.get(i).getpState());
                }

            }
            if (c == 0) {                  //??????
                System.out.printf("\nNot found\n");
            }
        }

    }

    private void print_list_Product() {
        ArrayList<Product> list = new ArrayList<>();
        list = ProductDB.get_products();
        System.out.printf("%-6s%-10s%-10s%-15s%-10s%-10s%-15s&-10s%-10s\n", "SN", "Name", "Price", "Orignal price", "Disscount", "Amount", "Expier data", "Min Range", "State");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%-6s%-10s%-10d", list.get(i).getSN(), list.get(i).getName(), list.get(i).getPrice());
            System.out.printf("%-15d%-10d%-10d", list.get(i).getOrignalPrice(), list.get(i).getDiscount(), list.get(i).getAmount());
            System.out.printf("%-15s%-10s%-10s\n", list.get(i).getEPD(), list.get(i).getMinRange(), list.get(i).getpState());
        }
    }

    private void print_list_Order() {
        ArrayList<Order> list = new ArrayList<>();
        list = OrderDB.get_orders();
        System.out.printf("\n%-5s %-15s\n", "PSN", "AMOUNT");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%-5s %-15s\n", list.get(i).getPSN(), list.get(i).getAmount());
            System.out.println();
        }
    }

    private void make_an_order() {
        System.out.print("Enter the amount: ");
        int amount = Check.CheckNumber();
        System.out.print("Enter the product serial number: ");
        int psn = Check.CheckSerialNumber();
        if (psn == -1) {
            System.out.println("No update happened");
        } else {
            Order O = new Order(psn, amount);
            OrderDB.add_order(O);
        }
    }

    private void update_info() {
        System.out.print("Enter the ID: ");         //why is this here ??? he should change his informations only ???
        int id = Check.CheckID();
        if (id == -1) {
            System.out.println("No update happened");
        } else {
            System.out.print("Enter the frist name: ");
            String fname = Check.CheckFname();
            System.out.print("Enter the last name: ");
            String lname = Check.CheckFname();
            EmployeeDB.update_employee_info(id, fname, lname);
        }
    }

    private void delete_an_order() {
//        ArrayList<Order> list = new ArrayList<>();                //why is this here ??? it's not used
//        list = OrderDB.get_orders();
        System.out.printf("Enter the order id: ");
        int id = Check.CheckOrderID();
        if (id == -1) {
            System.out.println("No deletion happened");
        } else {
            if (OrderDB.isExist(id)) {
                OrderDB.delete_order(id);
                System.out.println("\nDeleted!");
            } else {
                System.out.println("\nOrder not found!");
            }
        }
    }

}
