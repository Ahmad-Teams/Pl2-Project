/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import database.EmployeeDB;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminEmployee extends Employee {

    public AdminEmployee(int id, String fName, String lName, String userName, String password, String eType) {
        super(id, fName, lName, userName, password, eType);
    }

    public int openList() {
        Scanner input = new Scanner(System.in);
        char c;
        System.out.println("\nHello ," + this.getfName() + "!");
        do {
            System.out.printf("\nAdminstration Menu:"
                    + "\nAdd a new employee.                 (Enter 1)"
                    + "\nDelete an employee.                 (Enter 2)"
                    + "\nUpdate an employee information.     (Enter 3)"
                    + "\nlist all employees.                 (Enter 4)"
                    + "\nSearch for an employee.             (Enter 5)"
                    + "\nAlter your general information.     (Enter 6)"
                    + "\nAlter your User-Name and Password.  (Enter 7)"
                    + "\nLogOut                              (Enter 8)\n");
            System.out.printf("?: ");
            c = input.nextLine().charAt(0);

            if (c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' & c != '8') {
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
                case '5': search();
                    break;
                case '6': update_general_info();
                    break; 
                case '7': update_general();
                    break;
            }

        } while (c != '8');
        System.out.println("bey bey ," + this.getfName() + "!");
        return 0;
    }
    
   void update_general_info()
   {
   Scanner input=new Scanner(System.in);
       System.out.println("Enter first name : ");
       String fname=input.next();
       System.out.println("Enter last name : ");
       String lname=input.next();
       System.out.println("Enter ID : ");
       int ID=input.nextInt();
       EmployeeDB.update_employee_info(ID, fname, lname);
   }
   
    void update_general()
   {
       
       Scanner input=new Scanner(System.in);
       System.out.println("Enter ID : ");
       int ID=input.nextInt();
       System.out.println("Enter first name : ");
       String fname=input.next();
       System.out.println("Enter last name : ");
       String lname=input.next();
       System.out.println("Enter user name : ");
       String username=input.next();
       System.out.println("Enter password : ");
       String password=input.next();
       System.out.println("Enter type : ");
       String type=input.next();       
       EmployeeDB.update_employee(ID, fname, lname, username, password, type);
       
   }
   
 
     public  void search() {
        Scanner input=new Scanner(System.in); 
        ArrayList<Employee> list = new ArrayList<>();
        list = EmployeeDB.get_employees();
         System.out.println("Enter id : ");
        int id =input.nextInt();
        
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId()==id ){
                System.out.println("id : \t"+list.get(i).getId());
                System.out.println("first name : \t"+list.get(i).getfName());
                System.out.println("last name : \t"+list.get(i).getlName());
                System.out.println("user name : \t"+list.get(i).getUserName());
                System.out.println("password : \t"+list.get(i).getfName());
                System.out.println("employee type : "+list.get(i).getEType());
                
            }
        }
        
        
    }
       
  

}
