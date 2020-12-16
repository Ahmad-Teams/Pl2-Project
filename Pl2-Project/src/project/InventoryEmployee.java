/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.Scanner;

public class InventoryEmployee extends Employee {

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
                case '7':
                    break;

            }

        } while (c != '8');
        System.out.println("bey bey ," + this.getfName() + "!");
        return 0;
    }

}
