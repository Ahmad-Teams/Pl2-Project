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
     Scanner input=new Scanner(System.in);
    public InventoryEmployee(int id, String fName, String lName, String userName, String password, String eType) {
        super(id, fName, lName, userName, password, eType);
    }

    public int openList() {
        Scanner input = new Scanner(System.in);
        char c;
        System.out.println("\nHello ," + this.getfName() + "!");
        do {
            System.out.printf("\nInventory Menu:"
                    + "\nAdd a new product.                          (Enter 1)"
                    + "\nDelete a product.                           (Enter 2)"
                    + "\nUpdate a product information                (Enter 3)"
                    + "\nlist all products.                          (Enter 4)"
                    + "\nSearch for a product.                       (Enter 5)"
                    + "\nManage the Damages items and sales Return.  (Enter 6)"//delete order
                    + "\nAlter your information.                     (Enter 7)"
                    + "\nLogOut                                      (Enter 8)\n");
            System.out.printf("?: ");
            c = input.nextLine().charAt(0);

            if (c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8') {
                System.out.println("Invaild Input!");
            }

            switch (c) {
                case '1':newProduct();
                    break;
                case '2':deleteProduct();
                    break;
                case '3':updateProduct();
                    break;
                case '4':listProduct();
                    break;
                case '5':
                    break;
                case '6':
                    break;
                case '7':
                    break;

            }

        } while (c != '8');
        System.out.println("bey bey ," + this.getfName() + "!");
        return 0;
    }
    public  void newProduct(){
        System.out.printf("Enter Product Name:");
        String name=input.nextLine();
        System.out.printf("Enter Original Price:");
        int OP=input.nextInt();
        System.out.printf("Enter Discount:");
        int diss=input.nextInt();
        System.out.printf("Enter Amount:");
        int amount=input.nextInt();
        input.nextLine();
        System.out.printf("Enter Expierd Date:");
        String EPD=input.nextLine();
        System.out.printf("Enter Product State:");
        String State=input.nextLine();
        Product d=new Product(name,OP,diss,amount,EPD,State);
       ProductDB.add_product(d);
    }
    public void deleteProduct(){
        int sn;
        System.out.printf("Enter Product Serial Number:");
        sn=input.nextInt();
        ProductDB.delete_product(sn);
    }
    public void updateProduct(){
        System.out.printf("Enter 1 if you want to update discount"
                + " only and 2 for update all product info: ");
        int choice=input.nextInt();
        if(choice==1){
        System.out.printf("Enter Product Serial Number:");
        int sn=input.nextInt();
            System.out.printf("Enter  Discount:");
        int diss=input.nextInt();
        ProductDB.update_product(sn, diss);
        }
        else if(choice==2){
        int sn;
        System.out.printf("Enter Product Serial Number:");
        sn=input.nextInt();
        input.nextLine();
        System.out.printf("Enter new Name:");
        String name=input.nextLine();
        System.out.printf("Enter  Original Price:");
        int OP=input.nextInt();
        System.out.printf("Enter  Discount:");
        int diss=input.nextInt();
        System.out.printf("Enter Amount:");
        int amount=input.nextInt();
         input.nextLine();
        System.out.printf("Enter Expiera Date:");
        String EPD=input.nextLine();
        System.out.printf("Enter Product Sate:");
        String State=input.nextLine();
        ProductDB.update_product(sn, name, OP, diss, amount, EPD, State);
    }
        else System.out.printf("Invaild Input");
    }
    public void listProduct(){
       ArrayList<Product> list = new ArrayList<>();
     list= ProductDB.get_products();
     System.out.printf("%5s  %5s %5s  %6s %12s  %8s\n","Name","price","disscount","amount","expier data" ,"state");
     for(int i=0;i<list.size();i++){
        System.out.printf("%5s  %4d",list.get(i).getName(),list.get(i).getOrignalPrice());
        System.out.printf("%7d  %8d",list.get(i).getDiscount(),list.get(i).getAmount());
        System.out.printf("%14s  %7s\n",list.get(i).getEPD(),list.get(i).getpState());
    }
    }}