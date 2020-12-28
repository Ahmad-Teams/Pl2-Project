/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import database.ProductDB;
import database.RProductDB;
import java.util.ArrayList;
import java.util.Scanner;

public class MarktingEmployee extends Employee {

    public MarktingEmployee(int id, String fName, String lName, String userName, String password) {
        super(id, fName, lName, userName, password, "M");
    }

    public MarktingEmployee(String fName, String lName, String userName, String password) {
        super(fName, lName, userName, password, "M");
    }

    @Override
    public String getFullName() {
        return "ME." + super.getFullName();
    }

    Scanner input = new Scanner(System.in);

    public int openList() {
        ProductDB.update_products_states();
        RProductDB.update_RProducts_states();

        int c;
        System.out.println("\nHello ," + this.getfName() + "!\n");
        do {
            System.out.printf("\nMarkting Menu:"
                    + "\nMake products reports.              (Enter 1)"
                    + "\nMake offers and send them.          (Enter 2)"
                    + "\nAlter your information.             (Enter 3)"
                    + "\nLogOut                              (Enter 4)\n");
            System.out.printf("?: ");
            c = input.nextInt();
            input.nextLine();

            if (c != 1 && c != 2 && c != 3 && c != 4) {
                System.out.println("Invaild Input!");
            }

            switch (c) {
                case 1:
                    makeReports();
                    break;
                case 2:
                    Make_offers_and_send_them();
                    break;
                case 3:
                    break;
            }

        } while (c != 4);
        System.out.println("bey bey ," + this.getfName() + "!\n");
        return 0;
    }

    public void makeReports() {
        int c;
        do {
            System.out.printf("\nMaking Report about products by:"
                    + "\nOfferd products.                     (Enter 1)"
                    + "\nHas price greater than.              (Enter 2)"
                    + "\nHas price less than.                 (Enter 3)"
                    + "\nNumber of products.                  (Enter 4)"
                    + "\nExit.                                (Enter 5)\n");
            System.out.printf("?: ");
            c = input.nextInt();

            if (c != 1 && c != 2 && c != 3 && c != 4 && c != 5) {
                System.out.println("Invaild Input!");
            }

            switch (c) {
                case 1:
                    makeReportByOffers();
                    break;
                case 2:
                    makeReportByHasPriceGreaterThan();
                    break;
                case 3:
                    makeReportByHasPriceLessThan();
                    break;
                case 4:
                    makeReportByNumberOfProducts();
                    break;
            }

        } while (c != 5);
    }

    private void makeReportByOffers() {
        ArrayList<Product> list = ProductDB.get_products();

        System.out.printf("\n%-6s%-10s%-10s%-15s%-10s%-8s%-14s%-10s%-10s\n", "SN", "Name", "Price", "Orignal price", "Disscount", "Amount", "Expier data", "Min Range", "State");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDiscount() > 0) {
                System.out.printf("%-6d%-10s%-10d", list.get(i).getSN(), list.get(i).getName(), list.get(i).getPrice());
                System.out.printf("%-15d%-10d%-8d", list.get(i).getOrignalPrice(), list.get(i).getDiscount(), list.get(i).getAmount());
                System.out.printf("%-14s%-10s%-10s\n", list.get(i).getEPD(), list.get(i).getMinRange(), list.get(i).getpState());
            }
        }

    }

    private void makeReportByHasPriceGreaterThan() {
        System.out.printf("Enter the price : ");
        int price = input.nextInt();
        ArrayList<Product> list = ProductDB.get_products();

        System.out.printf("\n%-6s%-10s%-10s%-15s%-10s%-8s%-14s%-10s%-10s\n", "SN", "Name", "Price", "Orignal price", "Disscount", "Amount", "Expier data", "Min Range", "State");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPrice() > price) {
                System.out.printf("%-6d%-10s%-10d", list.get(i).getSN(), list.get(i).getName(), list.get(i).getPrice());
                System.out.printf("%-15d%-10d%-8d", list.get(i).getOrignalPrice(), list.get(i).getDiscount(), list.get(i).getAmount());
                System.out.printf("%-14s%-10s%-10s\n", list.get(i).getEPD(), list.get(i).getMinRange(), list.get(i).getpState());
            }
        }

    }

    private void makeReportByHasPriceLessThan() {
        System.out.printf("Enter the price : ");
        int price = input.nextInt();
        ArrayList<Product> list = ProductDB.get_products();
        System.out.printf("\n%-6s%-10s%-10s%-15s%-10s%-8s%-14s%-10s%-10s\n", "SN", "Name", "Price", "Orignal price", "Disscount", "Amount", "Expier data", "Min Range", "State");

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPrice() < price) {
                System.out.printf("%-6d%-10s%-10d", list.get(i).getSN(), list.get(i).getName(), list.get(i).getPrice());
                System.out.printf("%-15d%-10d%-8d", list.get(i).getOrignalPrice(), list.get(i).getDiscount(), list.get(i).getAmount());
                System.out.printf("%-14s%-10s%-10s\n", list.get(i).getEPD(), list.get(i).getMinRange(), list.get(i).getpState());
            }
        }

    }

    private void makeReportByNumberOfProducts() {
        ArrayList<Product> list = ProductDB.get_products();
        System.out.println("\nNumber of products = " + list.size());
    }

    private void Make_offers_and_send_them() {
        System.out.printf("Enter the SN of the product : ");
        int sn = input.nextInt();
        System.out.printf("Enter the discount : ");
        int discount = input.nextInt();
        InventoryEmployee.add_offer(sn, discount);
        System.out.println("\nOffer Sent and Accepted!");
    }
}
