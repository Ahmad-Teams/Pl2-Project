/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import database.EmployeeDB;
import database.PreviousActionsDB;
import database.ProductDB;
import database.RProductDB;
import database.SentOffersDB;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InventoryEmployee extends Employee {

    final static int numOfDaysToGetCloseToExpire = 3; //to use it later in notifications

    Scanner input = new Scanner(System.in);

    public InventoryEmployee(int id, String fName, String lName, String userName, String password) {
        super(id, fName, lName, userName, password, "I");
    }

    public InventoryEmployee(String fName, String lName, String userName, String password) {
        super(fName, lName, userName, password, "I");
    }

    @Override
    public String getTitle() {
        return "IE." + super.getTitle();
    }

    public int openList() {
        Scanner input = new Scanner(System.in);
        String c;
        System.out.println("\nHello ," + this.getfName() + "!\n");
        do {
            numOfNotifications();
            System.out.printf("\nInventory Menu:"
                    + "\nAdd a new product.                          (Enter 1)"
                    + "\nAdd a product to the Sales Return List.     (Enter 2)"
                    + "\nDelete a product.                           (Enter 3)"
                    + "\nUpdate a product information                (Enter 4)"
                    + "\nList all products.                          (Enter 5)"
                    + "\nSearch for a product.                       (Enter 6)"
                    + "\nManage the Damages items.                   (Enter 7)"
                    + "\nManage the Sales Return.                    (Enter 8)"
                    + "\nShow All Sent Offers by Markting Dep.       (Enter 9)"
                    + "\nAlter your information.                     (Enter 10)"
                    + "\nAlter your password.                        (Enter 11)"
                    + "\nDisplay all your previous actions.          (Enter 12)"
                    + "\nLogOut.                                     (Enter 13)\n");
            System.out.printf("?: ");
            ProductDB.update_products_states();
            RProductDB.update_RProducts_states();
            c = input.nextLine();
            if (!"0".equals(c) && !"1".equals(c) && !"2".equals(c) && !"3".equals(c) && !"4".equals(c) && !"5".equals(c) && !"6".equals(c) && !"7".equals(c) && !"8".equals(c) && !"9".equals(c) && !"10".equals(c) && !"11".equals(c) && !"12".equals(c) && !"13".equals(c)) {
                System.out.println("Invaild Input!");
            }

            switch (c) {
                case "0":
                    displayNotifications();
                    break;
                case "1":
                    newProduct();
                    break;
                case "2":
                    newRProduct();
                    break;
                case "3":
                    deleteProduct();
                    break;
                case "4":
                    updateProduct();
                    break;
                case "5":
                    listProduct();
                    break;
                case "6":
                    searchProduct();
                    break;
                case "7":
                    manageDamagesItems();
                    break;
                case "8":
                    manageSalesReturn();
                    break;
                case "9":
                    manageSentOffers();
                    break;
                case "10":
                    alterInformation();
                    break;
                case "11":
                    alterPassword();
                    break;
                case "12":
                    displayPreviousActions();
                    break;
            }

        } while (!"13".equals(c));
        System.out.printf("bey bey ,%s!\n", this.getfName());
        return 0;
    }

    public void newProduct() {
        System.out.printf("Enter Product Name: ");
        String name = input.nextLine();
        System.out.printf("Enter Original Price: ");
        int OP = input.nextInt();
        input.nextLine();
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
        System.out.println("\nAdded!\n");
        Util.registerAction(this.getId(), "Add-New_Product:(" + name + ").");
    }

    public void newRProduct() {
        System.out.printf("Enter Product Name: ");
        String name = input.nextLine();
        System.out.printf("Enter Original Price: ");
        int OP = input.nextInt();
        input.nextLine();
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
        System.out.println("\nAdded!\n");
        Util.registerAction(this.getId(), "Add-New_Product To Sales Return List:(" + name + ").");
    }

    public void deleteProduct() {
        int sn;
        System.out.printf("Enter Product Serial Number: ");
        sn = input.nextInt();
        ProductDB.delete_product(sn);
        System.out.println("\nDeleted!\n");
        Util.registerAction(this.getId(), "Delete-Product SN :(" + sn + ").");
    }

    public void deleteEProduct() {
        int sn;
        System.out.printf("Enter Product Serial Number: ");
        sn = input.nextInt();
        ProductDB.delete_product(sn);
        System.out.println("\nDeleted!\n");
        Util.registerAction(this.getId(), "Delete-Expired Product SN :(" + sn + ").");
    }

    public void deleteAllEProduct() {
        ArrayList<Product> list = ProductDB.get_Eproducts();
        for (int i = 0; i < list.size(); i++) {
            int sn = list.get(i).getSN();
            ProductDB.delete_product(sn);
            Util.registerAction(this.getId(), "Delete-Expired Product SN :(" + sn + ").");
        }
        System.out.println("\nAll Deleted!\n");
    }

    public void updateProduct() {
        System.out.printf("If you want to update discount only. (Enter 1)\n"
                + "For update all product info.         (Enter 2)\n?: ");
        int choice = input.nextInt();
        System.out.printf("Enter Product Serial Number: ");
        int sn = input.nextInt();

        if (ProductDB.isExsist(sn)) {
            if (choice == 1) {

                System.out.printf("Enter  Discount: ");
                double diss = input.nextDouble();

                ProductDB.update_product(sn, diss);
                System.out.println("\nUpdated!\n");
                Util.registerAction(this.getId(), "Update-Product SN :(" + sn + ").");
            } else if (choice == 2) {
                input.nextLine();
                System.out.printf("Enter new Name: ");
                String name = input.nextLine();
                System.out.printf("Enter  Original Price: ");
                double OP = input.nextDouble();
                System.out.printf("Enter  Discount: ");
                double diss = input.nextDouble();
                System.out.printf("Enter Amount: ");
                int amount = input.nextInt();
                input.nextLine();
                System.out.printf("Enter Expier Date: ");
                String EPD = input.nextLine();
                System.out.printf("Enter the Minmum Range: ");
                int minRange = input.nextInt();
                ProductDB.update_product(sn, name, OP, diss, amount, EPD, minRange);
                System.out.println("\nUpdated!\n");
                Util.registerAction(this.getId(), "Update-Product SN :(" + sn + ").");
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
        Util.PrintProductHeader();
        for (int i = 0; i < list.size(); i++) {
            Util.PrintProduct(list.get(i));
        }
    }

    public void listRProduct() {
        ArrayList<Product> list = new ArrayList<>();
        list = RProductDB.get_RProducts();

        Util.PrintProductHeader();
        for (int i = 0; i < list.size(); i++) {
            Util.PrintProduct(list.get(i));

        }
    }

    public void listEProduct() {
        ArrayList<Product> list = new ArrayList<>();
        list = ProductDB.get_Eproducts();

        Util.PrintProductHeader();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getpState().equals("E")) {
                Util.PrintProduct(list.get(i));

            }
        }
    }

    public void searchProduct() {
        ArrayList<Product> list = new ArrayList<>();
        list = ProductDB.get_products();

        System.out.println("Search a product by its Name:          (Enter 1)");
        System.out.println("Search a product by its Serial number: (Enter 2)\n?:");
        int choice = input.nextInt();
        if (choice == 1) {
            int i, serial = -1;
            input.nextLine();
            System.out.print("Enter Product Name:");
            String name = input.nextLine();
            for (i = 0; i < list.size(); i++) {
                if (name.equals(list.get(i).getName())) {
                    serial = list.get(i).getSN();
                }
            }
            if (ProductDB.isExsist(serial)) {
                for (i = 0; i < list.size(); i++) {
                    if (name.equals(list.get(i).getName())) {
                        Util.PrintProductHeader();
                        Util.PrintProduct(list.get(i));

                    }
                }
            } else {
                System.out.println("Product didn't Found!!");
            }
        } else if (choice == 2) {
            int i;
            System.out.print("Enter Product Serial Number:");
            int Serial = input.nextInt();
            if (ProductDB.isExsist(Serial)) {
                for (i = 0; i < list.size(); i++) {
                    if (Serial == list.get(i).getSN()) {
                        Util.PrintProductHeader();
                        Util.PrintProduct(list.get(i));

                    }
                }
            } else {
                System.out.println("Product didn't Found!!");
            }
        } else {
            System.out.println("Invaild Input!");
            searchProduct();
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

    public static boolean compareEPD(String EPDate1, String EPDate2) {

        SimpleDateFormat EPFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date productDate1;
        Date productDate2;
        long Pmillis1 = 0;//assign to 0 only to avoid not initialized var excption
        long Pmillis2 = 0;//assign to 0 only to avoid not initialized var excption

        try {
            productDate1 = EPFormat.parse(EPDate1);
            Pmillis1 = productDate1.getTime();

            productDate2 = EPFormat.parse(EPDate2);
            Pmillis2 = productDate2.getTime();

        } catch (ParseException ex) {
            Logger.getLogger(InventoryEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (Pmillis1 > Pmillis2) {
            return true;
        }
        return false;
    }

    public static ArrayList<Product> closeToExpProducts() {
        ArrayList<Product> allProducts = ProductDB.get_products();
        ArrayList<Product> closeToExpProducts = new ArrayList<Product>();

        SimpleDateFormat EPFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date productDate;
        long Pmillis = 0;//assign to 0 only to avoid not initialized var excption

        try {
            for (int i = 0; i < allProducts.size(); i++) {
                Product p = allProducts.get(i);
                productDate = EPFormat.parse(p.getEPD());
                Pmillis = productDate.getTime();

                if (Pmillis <= TimeUnit.DAYS.toMillis(InventoryEmployee.numOfDaysToGetCloseToExpire) + System.currentTimeMillis() && !(System.currentTimeMillis() > Pmillis)) {
                    closeToExpProducts.add(p);
                }
            }

        } catch (ParseException ex) {
            Logger.getLogger(InventoryEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return closeToExpProducts;
    }

    public static ArrayList<Product> ReducedProducts() {
        ArrayList<Product> allProducts = ProductDB.get_products();
        ArrayList<Product> ReducedProducts = new ArrayList<Product>();

        for (int i = 0; i < allProducts.size(); i++) {
            Product p = allProducts.get(i);
            if (p.getAmount() <= p.getMinRange()) {
                ReducedProducts.add(p);
            }
        }
        return ReducedProducts;
    }

    public static boolean comparePrice(double price1, double price2) {
        if (price1 > price2) {
            return true;
        }
        return false;
    }

    public void manageDamagesItems() {
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

    public void ReturnAllToInventory() {
        ArrayList<Product> list = RProductDB.get_RProducts();
        for (int i = 0; i < list.size(); i++) {
            int sn = list.get(i).getSN();
            ProductDB.add_product(RProductDB.get_RProduct(sn));
            RProductDB.delete_RProduct(sn);
            System.out.println("\nAll Returend!\n");
            Util.registerAction(this.getId(), "Return-Product_to_Inventory SN :(" + sn + ").");
        }
    }

    public void ReturnSlectedItemToInventory() {
        System.out.printf("Enter the serial number: ");
        int sn = input.nextInt();
        ProductDB.add_product(RProductDB.get_RProduct(sn));
        RProductDB.delete_RProduct(sn);
        System.out.println("\nReturend!\n");
        Util.registerAction(this.getId(), "Return-Product_to_Inventory SN :(" + sn + ").");
    }

    public void manageSalesReturn() {
        System.out.println("\nSales Return :");
        listRProduct();
        if (!RProductDB.isEmpty()) {
            int c;
            do {
                System.out.printf("\nReturned Items Operations:"
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
                        ReturnAllToInventory();
                        break;
                    case 2:
                        ReturnSlectedItemToInventory();
                        break;
                }
            } while (c != 3);
        }
    }

    public void alterInformation() {
        System.out.print("Enter the new frist name: ");
        String fname = input.next();
        System.out.print("Enter the new last name: ");
        String lname = input.next();
        String password = this.getPassword();
        EmployeeDB.update_employee(this.getId(), fname, lname, this.getUserName(), this.getPassword(), this.getEType());
        System.out.println("\nUpdated!\n");
        Util.registerAction(this.getId(), "Update-Your First-Name & Last-Name.");
    }

    public void alterPassword() {
        System.out.print("Enter the new password: ");
        String password = input.next();
        EmployeeDB.update_employee(this.getId(), this.getfName(), this.getlName(), this.getUserName(), password, this.getEType());
        System.out.println("\nUpdated!\n");
        Util.registerAction(this.getId(), "Update-Your Password.");
    }

    private void numOfNotifications() {
        int numOfNotifications = 0;
        ArrayList closeToExpProducts = closeToExpProducts();
        ArrayList ReducedProducts = ReducedProducts();

        numOfNotifications += closeToExpProducts.size();
        numOfNotifications += ReducedProducts.size();

        if (numOfNotifications > 0) {
            System.out.printf("\nYou Have (%d) Notifications!    (Enter (0) To Display)\n", numOfNotifications);
        }
        if (numOfNotifications == 0) {
            System.out.printf("\nYou Don`t Have Any Notifications!.\n", numOfNotifications);
        }
    }

    private void displayNotifications() {
        ArrayList<Product> closeToExpProducts = closeToExpProducts();
        ArrayList<Product> ReducedProducts = ReducedProducts();

        if (closeToExpProducts.size() + ReducedProducts.size() == 0) {
            return;
        }

        if (!closeToExpProducts.isEmpty()) {
            System.out.printf("\nGet close to Expire Products (Will Expire in %d Day/s!) : \n", InventoryEmployee.numOfDaysToGetCloseToExpire);
            Util.PrintProductHeader();
            for (int i = 0; i < closeToExpProducts.size(); i++) {
                Product P = closeToExpProducts.get(i);
                Util.PrintProduct(P);
            }
        }

        if (!ReducedProducts.isEmpty()) {
            System.out.println("\nReduced To Minmim Range Products : ");
            Util.PrintProductHeader();
            for (int i = 0; i < ReducedProducts.size(); i++) {
                Product P = ReducedProducts.get(i);
                Util.PrintProduct(P);
            }
        }

    }

    private void displayPreviousActions() {
        ArrayList<Action> list = PreviousActionsDB.get_actions(this.getId());
        Util.PrintActionHeader();
        for (int i = 0; i < list.size(); i++) {
            Util.PrintAction(list.get(i));
        }
    }

    private void manageSentOffers() {
        System.out.println("\nSent Offers :");
        displayAllSentOffers();
        if (!SentOffersDB.isEmpty()) {
            int c;
            do {
                System.out.printf("\nSent Offers Operations:"
                        + "\nAccept All.                            (Enter 1)"
                        + "\nReject All.                            (Enter 2)"
                        + "\nAccept Selected One.                   (Enter 3)"
                        + "\nReject Selected One.                   (Enter 4)"
                        + "\nExit                                   (Enter 5)\n"
                );
                System.out.printf("?: ");
                c = input.nextInt();
                if (c != 1 && c != 2 && c != 3 && c != 4 && c != 5) {
                    System.out.println("\nInvaild Input!\n");
                }

                switch (c) {
                    case 1:
                        AcceptAllSentOffers();
                        break;
                    case 2:
                        RejectAllSentOffers();
                        break;
                    case 3:
                        AcceptSelectedSentOffer();
                        break;
                    case 4:
                        RejectSelectedSentOffer();
                        break;
                }
            } while (c != 5);
        }
    }

    private void displayAllSentOffers() {
        ArrayList<Offer> list = SentOffersDB.get_sent_offers();
        Util.PrintOfferHeader();
        for (int i = 0; i < list.size(); i++) {
            Util.PrintOffer(list.get(i));
        }
    }

    private void AcceptAllSentOffers() {
        ArrayList<Offer> list = SentOffersDB.get_sent_offers();
        for (int i = 0; i < list.size(); i++) {
            Offer o = list.get(i);
            Product p = ProductDB.get_Product(o.getPSN());
            ProductDB.update_product(o.getPSN(), p.getDiscount() + o.getDiscount());
            SentOffersDB.delete_sent_offer(o.getId());
            Util.registerAction(this.getId(), "Accept-Offer SN :(" + o.getPSN() + ") Discount :(" + o.getDiscount() + ").");
        }
        System.out.println("\nAll Accepted!\n");
    }

    private void RejectAllSentOffers() {
        ArrayList<Offer> list = SentOffersDB.get_sent_offers();
        for (int i = 0; i < list.size(); i++) {
            Offer o = list.get(i);
            SentOffersDB.delete_sent_offer(o.getId());
            Util.registerAction(this.getId(), "Reject-Offer SN :(" + o.getPSN() + ") Discount :(" + o.getDiscount() + ").");
        }
        System.out.println("\nAll Rejected!\n");
    }

    private void AcceptSelectedSentOffer() {
        System.out.printf("Enter The Offer ID: ");
        int id = input.nextInt();
        Offer o = SentOffersDB.get_sent_offer(id);
        Product p = ProductDB.get_Product(o.getPSN());
        ProductDB.update_product(o.getPSN(), p.getDiscount() + o.getDiscount());
        SentOffersDB.delete_sent_offer(o.getId());
        System.out.println("\nAccepted!\n");
        Util.registerAction(this.getId(), "Accept-Offer SN :(" + o.getPSN() + ") Discount :(" + o.getDiscount() + ").");
    }

    private void RejectSelectedSentOffer() {
        int id;
        System.out.printf("Enter The Offer ID: ");
        id = input.nextInt();
        Offer o = SentOffersDB.get_sent_offer(id);
        SentOffersDB.delete_sent_offer(o.getId());
        System.out.println("\nRejected!\n");
        Util.registerAction(this.getId(), "Reject-Offer SN :(" + o.getPSN() + ") Discount :(" + o.getDiscount() + ").");
    }

}
