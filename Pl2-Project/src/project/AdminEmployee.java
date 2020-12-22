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
                    + "\nUpdate an employee information.     (Enter 3)"// all except id
                    + "\nlist all employees.                 (Enter 4)"// all shown
                    + "\nSearch for an employee.             (Enter 5)"// Search id or username // print value only
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
                    Add_new_employee();
                    break;
                case '2':
                    Delete_an_employee();
                    break;
                case '3':
                    Update_an_employee_information();
                    break;
                case '4':
                    list_all_employees();
                    break;
                case '5':
                    search();
                    break;
                case '6':
                    update_general_info();
                    break;
                case '7': 
                    update_general();
                    break;
            }

        } while (c != '8');
        System.out.println("bey bey ," + this.getfName() + "!");
        return 0;
    }
    private void Add_new_employee(){
        String fName;
        String lName;
        String fullName;
        String userName;
        String password;
        String eType;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter employee's first name: ");
        fName = input.next();
        System.out.print("Enter employee's last name: ");
        lName = input.next();
        fullName=fName+lName;
        System.out.print("Enter employee's username: ");
        userName = input.next();
        System.out.print("Enter employee's password: ");
        password = input.next();
        System.out.print("Enter employee's type: ");
        eType = input.next();
        
        Employee new_emp = new Employee(fName,lName,userName,password,eType.toUpperCase());
        EmployeeDB.add_employee(new_emp);
    }
    private void Delete_an_employee(){
        int id;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the id of the employee you want to delete: ");
        id = input.nextInt();
        EmployeeDB.delete_employee(id);
    }
    private void Update_an_employee_information(){
        int id ;
        String fName;
        String lName;
        //String fullName;
        String userName;
        String password;
        String eType;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter employee's id: ");
        id = input.nextInt();
        System.out.print("Enter employee's first name: ");
        fName = input.next();
        System.out.print("Enter employee's last name: ");
        lName = input.next();
        //fullName=fName+lName;
        System.out.print("Enter employee's username: ");
        userName = input.next();
        System.out.print("Enter employee's password: ");
        password = input.next();
        System.out.print("Enter employee's type: ");
        eType = input.next();
        EmployeeDB.update_employee(id, fName, lName, userName, password, eType.toUpperCase());
    }
    private void list_all_employees(){
        ArrayList<Employee> list = new ArrayList<>();
        list = EmployeeDB.get_employees();
        System.out.printf("%-5s %-15s %-15s %-15s %-15s %-10s \n","ID","First Name","Last Name","User Name","Password","Employee Type");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%-5d %-15s %-15s %-15s %-15s %-10s \n",list.get(i).getId(),list.get(i).getfName(),list.get(i).getlName(),list.get(i).getUserName(),list.get(i).getPassword(),list.get(i).getEType());
        }
    }
    
     void update_general_info()
   {
   Scanner input=new Scanner(System.in);
       System.out.printf("Enter first name : ");
       String fname=input.next();
       System.out.printf("Enter last name : ");
       String lname=input.next();
       EmployeeDB.update_employee_info(this.getId(), fname, lname);
   }

    void update_general()
   {

       Scanner input=new Scanner(System.in);
       System.out.printf("Enter user name : ");
       String username=input.next();
       System.out.printf("Enter password : ");
       String password=input.next(); 
       EmployeeDB.update_employee(this.getId(), username, password);
   }


     public  void search() {
        Scanner input=new Scanner(System.in); 
        ArrayList<Employee> list = new ArrayList<>();
        list = EmployeeDB.get_employees();
         System.out.printf("Enter id : ");
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