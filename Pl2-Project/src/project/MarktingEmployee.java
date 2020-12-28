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
                    + "\nList products ordered by the EPD.    (Enter 5)"
                    + "\nList products ordered by the Price.  (Enter 6)"
                    + "\nExit.                                (Enter 7)\n");
            System.out.printf("?: ");
            c = input.nextInt();

            if (c != 1 && c != 2 && c != 3 && c != 4 && c != 5 && c != 6 && c != 7) {
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
                case 5:
                    makeReportOrderedByEPD();
                    break;
                case 6:
                    makeReportOrderedByPrice();
                    break;
            }

        } while (c != 7);
    }

    private void makeReportByOffers() {
        ArrayList<Product> list = ProductDB.get_products();

        Util.PrintProductHeader();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDiscount() > 0) {
                Util.PrintProduct(list.get(i));

            }
        }

    }

    private void makeReportByHasPriceGreaterThan() {
        System.out.printf("Enter the price : ");
        int price = input.nextInt();
        ArrayList<Product> list = ProductDB.get_products();

        Util.PrintProductHeader();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPrice() > price) {
                Util.PrintProduct(list.get(i));

            }
        }

    }

    private void makeReportByHasPriceLessThan() {
        System.out.printf("Enter the price : ");
        int price = input.nextInt();
        ArrayList<Product> list = ProductDB.get_products();
        Util.PrintProductHeader();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPrice() < price) {
                Util.PrintProduct(list.get(i));

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

    private void sortByPrice(ArrayList<Product> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                if (InventoryEmployee.comparePrice(list.get(i).getPrice(), list.get(j).getPrice())) {
                    Product temp = list.get(j);
                    list.set(j, list.get(i));
                    list.set(i, temp);
                }
            }
        }
    }

    private void sortByEPD(ArrayList<Product> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                if (InventoryEmployee.compareEPD(list.get(i).getEPD(), list.get(j).getEPD())) {
                    Product temp = list.get(j);
                    list.set(j, list.get(i));
                    list.set(i, temp);
                }
            }
        }
    }

    private void makeReportOrderedByEPD() {
        ArrayList<Product> list = ProductDB.get_products();
        this.sortByEPD(list);
        Util.PrintProductHeader();

        for (int i = 0; i < list.size(); i++) {
            Util.PrintProduct(list.get(i));

        }
    }

    private void makeReportOrderedByPrice() {
        ArrayList<Product> list = ProductDB.get_products();
        this.sortByPrice(list);
        Util.PrintProductHeader();

        for (int i = 0; i < list.size(); i++) {
            Util.PrintProduct(list.get(i));

        }
    }
}
