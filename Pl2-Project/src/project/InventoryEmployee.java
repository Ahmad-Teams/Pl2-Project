/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import database.ProductDB;
import java.util.ArrayList;
import java.util.Scanner;

public class InventoryEmployee extends Employee {

    Scanner input = new Scanner(System.in);

    public InventoryEmployee(int id, String fName, String lName, String userName, String password) {
        super(id, fName, lName, userName, password, "I");
    }

    public InventoryEmployee(String fName, String lName, String userName, String password) {
        super(fName, lName, userName, password, "I");
    }

    @Override
    public String getFullName() {
        return "IE." + super.getFullName();
    }

    public int openList() {
        Scanner input = new Scanner(System.in);
        int c;
        System.out.println("\nHello ," + this.getfName() + "!");
        do {
            System.out.printf("\nInventory Menu:"
                    + "\nAdd a new product.                          (Enter 1)"
                    + "\nDelete a product.                           (Enter 2)"
                    + "\nUpdate a product information                (Enter 3)"
                    + "\nList all products.                          (Enter 4)"
                    + "\nSearch for a product.                       (Enter 5)"
                    + "\nManage the Damages items and sales Return.  (Enter 6)"
                    + "\nAlter your information.                     (Enter 7)"
                    + "\nLogOut                                      (Enter 8)\n");
            System.out.printf("?: ");
            c = input.nextInt();

            if (c != 1 && c != 2 && c != 3 && c != 4 && c != 5 && c != 6 && c != 7 && c != 8) {
                System.out.println("Invaild Input!");
            }

            switch (c) {
                case 1:
                    newProduct();
                    break;
                case 2:
                    deleteProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    listProduct();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
            }

        } while (c != 8);
        System.out.printf("bey bey ,%s!", this.getfName());
        return 0;
    }

    public void newProduct() {
        System.out.printf("Enter Product Name:");
        String name = input.nextLine();
        System.out.printf("Enter Original Price:");
        int OP = input.nextInt();
        System.out.printf("Enter Discount:");
        int diss = input.nextInt();
        System.out.printf("Enter Amount:");
        int amount = input.nextInt();
        input.nextLine();
        System.out.printf("Enter Expierd Date:");
        String EPD = input.nextLine();
        System.out.printf("Enter Product State:");
        String State = input.nextLine();
        Product d = new Product(name, OP, diss, amount, EPD, State);
        ProductDB.add_product(d);
        System.out.println("Added!");
    }

    public void deleteProduct() {
        int sn;
        System.out.printf("Enter Product Serial Number:");
        sn = input.nextInt();
        ProductDB.delete_product(sn);
        System.out.println("Deleted!");
    }

    public void updateProduct() {
        System.out.printf("if you want to update discount only (Enter 1)\n"
                + "for update all product info:        (Enter 2)\n?:");
        int choice = input.nextInt();
        System.out.printf("Enter Product Serial Number:");
        int sn = input.nextInt();

        boolean Check = foundInProducts(sn);
        if (Check) {
            if (choice == 1) {

                System.out.printf("Enter  Discount:");
                int diss = input.nextInt();

                ProductDB.update_product(sn, diss);
                System.out.println("Updated!");
            } else if (choice == 2) {
                input.nextLine();
                System.out.printf("Enter new Name:");
                String name = input.nextLine();
                System.out.printf("Enter  Original Price:");
                int OP = input.nextInt();
                System.out.printf("Enter  Discount:");
                int diss = input.nextInt();
                System.out.printf("Enter Amount:");
                int amount = input.nextInt();
                input.nextLine();
                System.out.printf("Enter Expier Date:");
                String EPD = input.nextLine();
                System.out.printf("Enter Product Sate:");
                String State = input.nextLine();
                ProductDB.update_product(sn, name, OP, diss, amount, EPD, State);
                System.out.println("Updated!");
            }
        } else {
            System.out.println("Product not found!");
        }
    }
    
    public static void add_offer(int sn , int discount){
        ProductDB.update_product(sn, discount);
    }

    public boolean foundInProducts(int sn) {
        ArrayList<Product> list = ProductDB.get_products();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSN() == sn) {
                return true;
            }
        }
        return false;
    }

    public void listProduct() {
        ArrayList<Product> list = new ArrayList<>();
        list = ProductDB.get_products();
        System.out.printf("%-10s%-10s%-15s%-10s%-10s%-15s%-10s\n", "Name", "price", "orignal price", "disscount", "amount", "expier data", "state");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%-10s%-10d", list.get(i).getName(), list.get(i).getPrice());
            System.out.printf("%-15d%-10d%-10d", list.get(i).getOrignalPrice(), list.get(i).getDiscount(), list.get(i).getAmount());
            System.out.printf("%-15s%-10s\n", list.get(i).getEPD(), list.get(i).getpState());
        }
    }

}
