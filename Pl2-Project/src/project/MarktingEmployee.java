/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.Scanner;

public class MarktingEmployee extends Employee {

    public MarktingEmployee(int id, String fName, String lName, String userName, String password, String eType) {
        super(id, fName, lName, userName, password, eType);
    }

    public int openList() {
        Scanner input = new Scanner(System.in);
        char c;
        System.out.println("\nHello ," + this.getfName() + "!");
        do {
            System.out.printf("\nMarkting Menu:"
                    + "\nMake products reports.              (Enter 1)"
                    + "\nMake offers and send them.          (Enter 2)"
                    + "\nAlter your information.             (Enter 3)"
                    + "\nLogOut                              (Enter 4)\n");
            System.out.printf("?: ");
            c = input.nextLine().charAt(0);

            if (c != '1' && c != '2' && c != '3' && c != '4') {
                System.out.println("Invaild Input!");
            }

            switch (c) {
                case '1':
                    break;
                case '2':
                    break;
                case '3':
                    break;
            }

        } while (c != '4');
        System.out.println("bey bey ," + this.getfName() + "!");
        return 0;
    }
     void test3(){}

}
