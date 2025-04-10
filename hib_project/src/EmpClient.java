import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmpClient {
    public static void main(String[] args) {
        // Load Hibernate Configuration
        //Configuration cfg = new Configuration().configure();
        //SessionFactory sf = cfg.buildSessionFactory();
       // Session session = sf.openSession();
        
    	
        Configuration cfg = new Configuration();
        cfg.configure("/hibernate.cfg.xml");
        // cfg.addAnnotatedClass(Emp.class);  // ✅ Ensure Hibernate knows about Emp
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Insert Employee");
            System.out.println("2. Display Employee by ID");
            System.out.println("3. Delete Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Display All Employees");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    // Insert Employee
                    System.out.print("Enter Ename: ");
                    sc.nextLine();
                    String ename = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double sal = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter Designation: ");
                    String desig = sc.nextLine();

                    Emp emp = new Emp(ename, sal, desig);
                    Transaction tx = session.beginTransaction();
                    session.save(emp);
                    tx.commit();
                    System.out.println("Employee Inserted Successfully.");
                    break;

                case 2:
                    // Display Employee
                    System.out.print("Enter Employee ID: ");
                    int eid = sc.nextInt();
                    Emp e = session.get(Emp.class, eid);
                    if (e != null) {
                        System.out.println(e);
                    } else {
                        System.out.println("Employee not found!");
                    }
                    break;

                case 3:
                    // Delete Employee
                    System.out.print("Enter Employee ID to Delete: ");
                    eid = sc.nextInt();
                    e = session.get(Emp.class, eid);
                    if (e != null) {
                        tx = session.beginTransaction();
                        session.delete(e);
                        tx.commit();
                        System.out.println("Employee Deleted Successfully.");
                    } else {
                        System.out.println("Employee not found!");
                    }
                    break;

                case 4:
                    // Update Employee
                    System.out.print("Enter Employee ID to Update: ");
                    eid = sc.nextInt();
                    e = session.get(Emp.class, eid);
                    if (e != null) {
                        System.out.print("Enter New Ename: ");
                        sc.nextLine();
                        ename = sc.nextLine();
                        System.out.print("Enter New Salary: ");
                        sal = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Enter New Designation: ");
                        desig = sc.nextLine();

                        e.setEname(ename);
                        e.setSal(sal);
                        e.setDesig(desig);

                        tx = session.beginTransaction();
                        session.update(e);
                        tx.commit();
                        System.out.println("Employee Updated Successfully.");
                    } else {
                        System.out.println("Employee not found!");
                    }
                    break;

                case 5:
                    // Display All Employees
                    Query query = session.createQuery("FROM Emp");
                    List<Emp> empList = query.list();
                    System.out.println("\n******** Employee List ********");
                    for (Emp empObj : empList) {
                        System.out.println(empObj);
                    }
                    System.out.println("********************************");
                    break;

                case 6:
                    // Exit
                    System.out.println("Exiting...");
                    session.close();
                    sf.close();
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Option! Try Again.");
            }
        }
    }

}
