/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import database.EmployeeDB;
import database.ProductDB;
import database.OrderDB;
import java.util.ArrayList;
import java.util.Scanner;
import project.Order;
import project.Product;

public class SalesEmployee extends Employee {

    public SalesEmployee(int id, String fName, String lName, String userName, String password, String eType) {
        super(id, fName, lName, userName, password, eType);
    }

    public int openList() {
        Scanner input = new Scanner(System.in);
        char c;
        System.out.println("\nHello ," + this.getfName() + "!");
        do {
            System.out.printf("\nSales Menu:"
                    + "\nSearch for a product.              (Enter 1)"
                    + "\nList all products.                 (Enter 2)"
                    + "\nList all orders.                   (Enter 3)"
                    + "\nMake an order.                     (Enter 4)"
                    + "\nDelete an order.                   (Enter 5)"//in return
                    + "\nAlter your information.            (Enter 6)"//fname lname
                    + "\nLogOut                             (Enter 7)\n");
            System.out.printf("?: ");
            c = input.nextLine().charAt(0);

            if (c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6'&& c != '7') {
                System.out.println("Invaild Input!");
            }

            switch (c) {
                case '1':search();
                    break;
                case '2':print_list_Product();
                    break;
                case '3':print_list_Order();
                    break;
                case '4':
                    make_an_order();
                    print_list_Order();
                    break;
                case '5':
                    break;
                case '6':update_info();
                    break;
            }

        } while (c != '7');
        System.out.println("bey bey ," + this.getfName() + "!");
        return 0;
    }
void search(){
     ArrayList<Product> list=new ArrayList<>();
     list = ProductDB.get_products();
     Scanner input=new Scanner(System.in);
     System.out.print("Enter the serial number");
     int sn=input.nextInt();
     int c=0;
     for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSN()==sn) {
                    System.out.printf("\n \t%s \t %s \t %s \t %s \t %s \t\t %s  \t\t %s \n ","SN","NAME","ORIGNAL_price","discount","amount","epd","state");
                System.out.printf("\n\t%d \t %s \t %d \t\t %d \t\t %d \t\t %s  \t\t %s\n" , list.get(i).getSN(),list.get(i).getName(),list.get(i).getOrignalPrice(),list.get(i).getDiscount(),
                list.get(i).getAmount(),list.get(i).getEPD(),list.get(i).getpState());
                c++;
                }
         
     }
     if(c==0)
       System.out.printf("not found");  
     
}
void print_list_Product(){
    ArrayList<Product> list=new ArrayList<>();
     list = ProductDB.get_products();
     for (int i = 0; i < list.size(); i++) {
     System.out.printf("\n \t%s \t %s \t %s \t %s \t %s \t\t %s  \t\t %s \n ","SN","NAME","ORIGNAL_price","discount","amount","epd","state");
    System.out.printf("\n\t%d \t %s \t %d \t\t %d \t\t %d \t\t %s  \t\t %s\n" , list.get(i).getSN(),list.get(i).getName(),list.get(i).getOrignalPrice(),list.get(i).getDiscount(),
            list.get(i).getAmount(),list.get(i).getEPD(),list.get(i).getpState());
    System.out.println();
}
}
void print_list_Order(){
    ArrayList<Order> list=new ArrayList<>();
     list = OrderDB.get_orders();
     for (int i = 0; i < list.size(); i++) {
    System.out.printf("\n  \t\t %s  \t\t %s \n","PSN","AMOUNT");
    System.out.printf("\n \t\t %d \t\t %d " , list.get(i).getPSN(),list.get(i).getAmount());
    System.out.println();
     }
}
void make_an_order(){
    Scanner input=new Scanner(System.in);
    System.out.print("Enter the amount: ");
    int amount=input.nextInt();
    System.out.print("Enter the product serial number: ");
    int psn=input.nextInt();
    Order O=new Order(psn,amount);
    OrderDB.add_order(O);
}
void update_info(){
     Scanner input=new Scanner(System.in);
     System.out.print("Enter the ID: ");
     int id=input.nextInt();
     System.out.print("Enter the frist name: ");
     String fname=input.next();
     System.out.print("Enter the last name: ");
     String lname=input.next();
     EmployeeDB.update_employee_info(id,fname,lname);
}
}
