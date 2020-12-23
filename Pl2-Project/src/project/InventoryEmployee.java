/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import database.ProductDB;
import database.RProductDB;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        ProductDB.update_products_states();
        RProductDB.update_RProducts_states();

        Scanner input = new Scanner(System.in);
        int c;
        System.out.println("\nHello ," + this.getfName() + "!");
        do {
            System.out.printf("\nInventory Menu:"
                    + "\nAdd a new product.                          (Enter 1)"
                    + "\nAdd a product to the Sales Return List.     (Enter 2)"
                    + "\nDelete a product.                           (Enter 3)"
                    + "\nUpdate a product information                (Enter 4)"
                    + "\nList all products.                          (Enter 5)"
                    + "\nSearch for a product.                       (Enter 6)"
                    + "\nManage the Damages items.                   (Enter 7)"
                    + "\nManage the Sales Return.                    (Enter 8)"
                    + "\nAlter your information.                     (Enter 9)"
                    + "\nLogOut                                      (Enter 10)\n");
            System.out.printf("?: ");
            c = input.nextInt();
            input.nextLine();

            if (c != 1 && c != 2 && c != 3 && c != 4 && c != 5 && c != 6 && c != 7 && c != 8 && c != 9 && c != 10) {
                System.out.println("Invaild Input!");
            }

            switch (c) {
                case 1:
                    newProduct();
                    break;
                case 2:
                    newRProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    updateProduct();
                    break;
                case 5:
                    listProduct();
                    break;
                case 6:
                    break;
                case 7:
                    ManageDamagesItems();
                    break;
                case 8:
                    ManageSalesReturn();
                    break;
                case 9:

                    break;
            }

        } while (c != 10);
        System.out.printf("bey bey ,%s!", this.getfName());
        return 0;
    }

    public void newProduct() {
        System.out.printf("Enter Product Name: ");
        String name = input.nextLine();
        System.out.printf("Enter Original Price: ");
        int OP = input.nextInt();
        System.out.printf("Enter Discount: ");
        int diss = input.nextInt();
        System.out.printf("Enter Amount: ");
        int amount = input.nextInt();
        input.nextLine();
        System.out.printf("Enter Expierd Date: ");
        String EPD = input.nextLine();
        System.out.printf("Enter the Minmum Range: ");
        int minRange = input.nextInt();
        Product d = new Product(name, OP, diss, amount, EPD, minRange);
        ProductDB.add_product(d);
        System.out.println("\nAdded!");
    }

    public void newRProduct() {
        System.out.printf("Enter Product Name: ");
        String name = input.nextLine();
        System.out.printf("Enter Original Price: ");
        int OP = input.nextInt();
        System.out.printf("Enter Discount: ");
        int diss = input.nextInt();
        System.out.printf("Enter Amount: ");
        int amount = input.nextInt();
        input.nextLine();
        System.out.printf("Enter Expierd Date: ");
        String EPD = input.nextLine();
        System.out.printf("Enter the Minmum Range: ");
        int minRange = input.nextInt();
        Product d = new Product(name, OP, diss, amount, EPD, minRange);
        RProductDB.add_RProduct(d);
        System.out.println("\nAdded!");
    }

    public void deleteProduct() {
        int sn;
        System.out.printf("Enter Product Serial Number: ");
        sn = input.nextInt();
        ProductDB.delete_product(sn);
        System.out.println("\nDeleted!\n");
    }

    public void deleteEProduct() {
        int sn;
        System.out.printf("Enter Product Serial Number: ");
        sn = input.nextInt();
        ProductDB.delete_product(sn);
        System.out.println("\nDeleted!\n");
    }

    public void deleteAllEProduct() {
        ArrayList<Product> list = ProductDB.get_Eproducts();
        for (int i = 0; i < list.size(); i++) {
            ProductDB.delete_product(list.get(i).getSN());
        }
        System.out.println("\nAll Deleted!\n");
    }

    public void updateProduct() {
        System.out.printf("if you want to update discount only. (Enter 1)\n"
                + "for update all product info.         (Enter 2)\n?: ");
        int choice = input.nextInt();
        System.out.printf("Enter Product Serial Number: ");
        int sn = input.nextInt();

        if (ProductDB.isExsist(sn)) {
            if (choice == 1) {

                System.out.printf("Enter  Discount: ");
                int diss = input.nextInt();

                ProductDB.update_product(sn, diss);
                System.out.println("\nUpdated!\n");
            } else if (choice == 2) {
                input.nextLine();
                System.out.printf("Enter new Name: ");
                String name = input.nextLine();
                System.out.printf("Enter  Original Price: ");
                int OP = input.nextInt();
                System.out.printf("Enter  Discount: ");
                int diss = input.nextInt();
                System.out.printf("Enter Amount: ");
                int amount = input.nextInt();
                input.nextLine();
                System.out.printf("Enter Expier Date: ");
                String EPD = input.nextLine();
                System.out.printf("Enter the Minmum Range: ");
                int minRange = input.nextInt();
                ProductDB.update_product(sn, name, OP, diss, amount, EPD, minRange);
                System.out.println("\nUpdated!\n");
            }
        } else {
            System.out.println("\nProduct not found!\n");
        }
    }

    public static void add_offer(int sn, int discount) {
        ProductDB.update_product(sn, discount);
    }

    public void listProduct() {
        ArrayList<Product> list = new ArrayList<>();
        list = ProductDB.get_products();
        System.out.printf("%-6s%-10s%-10s%-15s%-10s%-10s%-15s&-10s%-10s\n", "SN", "Name", "Price", "Orignal price", "Disscount", "Amount", "Expier data", "Min Range", "State");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%-6s%-10s%-10d", list.get(i).getSN(), list.get(i).getName(), list.get(i).getPrice());
            System.out.printf("%-15d%-10d%-10d", list.get(i).getOrignalPrice(), list.get(i).getDiscount(), list.get(i).getAmount());
            System.out.printf("%-15s%-10s%-10s\n", list.get(i).getEPD(), list.get(i).getMinRange(), list.get(i).getpState());
        }
    }

    public void listRProduct() {
        ArrayList<Product> list = new ArrayList<>();
        list = RProductDB.get_RProducts();

        System.out.printf("%-6s%-10s%-10s%-15s%-10s%-10s%-15s&-10s%-10s\n", "SN", "Name", "Price", "Orignal price", "Disscount", "Amount", "Expier data", "Min Range", "State");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%-6s%-10s%-10d", list.get(i).getSN(), list.get(i).getName(), list.get(i).getPrice());
            System.out.printf("%-15d%-10d%-10d", list.get(i).getOrignalPrice(), list.get(i).getDiscount(), list.get(i).getAmount());
            System.out.printf("%-15s%-10s%-10s\n", list.get(i).getEPD(), list.get(i).getMinRange(), list.get(i).getpState());
        }
    }

    public void listEProduct() {
        ArrayList<Product> list = new ArrayList<>();
        list = ProductDB.get_Eproducts();

        System.out.printf("%-6s%-10s%-10s%-15s%-10s%-10s%-15s&-10s%-10s\n", "SN", "Name", "Price", "Orignal price", "Disscount", "Amount", "Expier data", "Min Range", "State");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getpState().equals("E")) {
                System.out.printf("%-6s%-10s%-10d", list.get(i).getSN(), list.get(i).getName(), list.get(i).getPrice());
                System.out.printf("%-15d%-10d%-10d", list.get(i).getOrignalPrice(), list.get(i).getDiscount(), list.get(i).getAmount());
            }
        }
    }

    public static String updateProductState(String EPDate) {

        SimpleDateFormat EPFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date productDate;
        long Pmillis = 0;//assign to 0 only to avoid not initialized var excption

        try {
            productDate = EPFormat.parse(EPDate);
            Pmillis = productDate.getTime();

        } catch (ParseException ex) {
            Logger.getLogger(InventoryEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (System.currentTimeMillis() > Pmillis) {
            return "E";
        } else {
            return "S";
        }
    }

    public void ManageDamagesItems() {
        System.out.println("\nExpired Items :");
        listEProduct();
        ArrayList<Product> list = ProductDB.get_Eproducts();
        if (!list.isEmpty()) {
            int c;
            do {
                System.out.printf("\nExpired Items Operations:"
                        + "\nRemove all from the inventory.       (Enter 1)"
                        + "\nRemove selected item from inventory. (Enter 2)"
                        + "\nExit                                 (Enter 3)\n"
                );
                System.out.printf("?: ");
                c = input.nextInt();
                input.nextLine();

                if (c != 1 && c != 2 && c != 3) {
                    System.out.println("\nInvaild Input!\n");
                }

                switch (c) {
                    case 1:
                        deleteAllEProduct();
                        break;
                    case 2:
                        deleteEProduct();
                        break;
                }
            } while (c != 3);
        }
    }

    public void returnAllToInventory() {
        ArrayList<Product> list = RProductDB.get_RProducts();
        for (int i = 0; i < list.size(); i++) {
            int sn = list.get(i).getSN();
            ProductDB.add_product(RProductDB.get_RProduct(sn));
            RProductDB.delete_RProduct(sn);
        }
        System.out.println("\nAll Returned!");
    }

    public void returnSlectedItemToInventory() {
        System.out.printf("Enter the serial number: ");
        int sn = input.nextInt();
        ProductDB.add_product(RProductDB.get_RProduct(sn));
        RProductDB.delete_RProduct(sn);
        System.out.println("\nReturned!\n");
    }

    public void ManageSalesReturn() {
        System.out.println("\nSales Return :");
        listRProduct();
        if (!RProductDB.isEmpty()) {
            int c;
            do {
                System.out.printf("\nExpired Items Operations:"
                        + "\nReturn all to the inventory.           (Enter 1)"
                        + "\nReturn selected item to the inventory. (Enter 2)"
                        + "\nExit                                   (Enter 3)\n"
                );
                System.out.printf("?: ");
                c = input.nextInt();
                input.nextLine();

                if (c != 1 && c != 2 && c != 3) {
                    System.out.println("\nInvaild Input!\n");
                }

                switch (c) {
                    case 1:
                        returnAllToInventory();
                        break;
                    case 2:
                        returnSlectedItemToInventory();
                        break;
                }
            } while (c != 3);
        }
    }

}
