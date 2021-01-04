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

    Scanner input = new Scanner(System.in);

    public SalesEmployee(int id, String fName, String lName, String userName, String password) {
        super(id, fName, lName, userName, password, "S");
    }

    public SalesEmployee(String fName, String lName, String userName, String password) {
        super(fName, lName, userName, password, "S");
    }

    @Override
    public String getTitle() {
        return "SE." + super.getTitle();
    }

    public int openList() {
        int c;
        System.out.println("\nHello ," + this.getfName() + "!\n");
        do {

            System.out.printf("\nSales Menu:"
                    + "\nSearch for a Product.               (Enter 1)"
                    + "\nList all Products.                  (Enter 2)"
                    + "\nList all orders.                    (Enter 3)"
                    + "\nMake an order.                      (Enter 4)"
                    + "\nDelete an order.                    (Enter 5)"
                    + "\nAlter your information.             (Enter 6)"
                    + "\nAlter your password.                (Enter 7)"
                    + "\nLogOut.                             (Enter 8)\n");
            System.out.printf("?: ");
            ProductDB.update_products_states();
            RProductDB.update_RProducts_states();

            c = input.nextInt();
            input.nextLine();

            if (c != 1 && c != 2 && c != 3 && c != 4 && c != 5 && c != 6 && c != 7 && c != 8) {
                System.out.println("Invaild Input!");
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
                    AlterInformation();
                    break;
                case 7:
                    AlterPassword();
                    break;
            }

        } while (c != 8);
        System.out.println("bey bey ," + this.getfName() + "!\n");
        return 0;
    }

    void search() {
        ArrayList<Product> list = new ArrayList<>();
        list = ProductDB.get_products();
        System.out.print("Enter the serial number : ");
        int sn = input.nextInt();
        int c = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSN() == sn) {
                Util.PrintProductHeader();
                Util.PrintProduct(list.get(i));
            }

        }
        if (c == 0) {
            System.out.printf("\nnot found\n");
        }

    }

    void print_list_Product() {
        ArrayList<Product> list = new ArrayList<>();
        list = ProductDB.get_products();
        Util.PrintProductHeader();
        for (int i = 0; i < list.size(); i++) {
            Util.PrintProduct(list.get(i));
        }
    }

    void print_list_Order() {
        ArrayList<Order> list = new ArrayList<>();
        list = OrderDB.get_orders();
        System.out.printf("\n%-5s %-15s\n", "PSN", "AMOUNT");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%-5s %-15s\n", list.get(i).getPSN(), list.get(i).getAmount());
            System.out.println();
        }
    }

    void make_an_order() {
        System.out.print("Enter the amount: ");
        int amount = input.nextInt();
        System.out.print("Enter the RProduct serial number: ");
        int psn = input.nextInt();
        OrderDB.add_order(new Order(psn, amount));
        System.out.println("\nAdded!\n");
    }

    void delete_an_order() {
        ArrayList<Order> list = new ArrayList<>();
        list = OrderDB.get_orders();
        System.out.printf("Enter the order id: ");
        int id = input.nextInt();
        if (OrderDB.isExisit(id)) {
            OrderDB.delete_order(id);
            System.out.println("\nDeleted!\n");
        } else {
            System.out.println("\nOrder not found!");
        }
    }

    public void AlterInformation() {
        System.out.print("Enter the new frist name: ");
        String fname = input.next();
        System.out.print("Enter the new last name: ");
        String lname = input.next();
        String password = this.getPassword();
        EmployeeDB.update_employee(this.getId(), fname, lname, this.getUserName(), this.getPassword(), this.getEType());
        System.out.println("\nUpdated!\n");
    }

    public void AlterPassword() {
        System.out.print("Enter the new password: ");
        String password = input.next();
        EmployeeDB.update_employee(this.getId(), this.getfName(), this.getlName(), this.getUserName(), password, this.getEType());
        System.out.println("\nUpdated!\n");
    }
}
