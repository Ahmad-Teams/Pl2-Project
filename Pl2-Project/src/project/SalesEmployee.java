/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.Scanner;

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
                    + "\nAlter your information.            (Enter 6)"
                    + "\nLogOut                             (Enter 7)\n");
            System.out.printf("?: ");
            c = input.nextLine().charAt(0);

            if (c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6'&& c != '7') {
                System.out.println("Invaild Input!");
            }

            switch (c) {
                case '1':
                    break;
                case '2':
                    break;
                case '3':
                    break;
                case '4':
                    break;
                case '5':
                    break;
                case '6':
                    break;
            }

        } while (c != '7');
        System.out.println("bey bey ," + this.getfName() + "!");
        return 0;
    }

}
