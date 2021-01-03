package project;

import java.util.ArrayList;

public class Util {

    public static void PrintProductHeader() {
        System.out.printf("\n%-6s%-10s%-8s%-15s%-11s%-8s%-14s%-10s%-10s\n", "SN", "Name", "Price", "Orignal_price", "Disscount", "Amount", "Expier data", "Min_Range", "State");
    }

    public static void PrintProduct(Product p) {

        System.out.printf("%-6d%-10s%-8.2f", p.getSN(), p.getName(), p.getPrice());
        System.out.printf("%-15.2f%-11.2f%-8d", p.getOrignalPrice(), p.getDiscount(), p.getAmount());
        System.out.printf("%-14s%-10d%-10s\n", p.getEPD(), p.getMinRange(), p.getpState());

    }
}
