import java.io.*;
import java.util.*;

class Employee implements Serializable{
	int empno;
	String ename;
	int salary;
	
	Employee(int empno, String ename, int salary){
		this.empno = empno;
		this.ename = ename;
		this.salary = salary;
	}
	
	public String toString() {
		return empno + " " + ename + " " + salary;
	}
	
	
}


public class EmployeeData{
	public static void main(String[] args) throws Exception{
		int choice = -1;
		Scanner scInt = new Scanner(System.in);
		Scanner scStr = new Scanner(System.in);
		File file = new File("employee.txt");
		
		ArrayList<Employee> al = new ArrayList<Employee>();
		
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		ListIterator li = null;
		
		if(file.isFile()) {		//If file exist
			ois = new ObjectInputStream(new FileInputStream(file));		//Read from File
			al = (ArrayList<Employee>)ois.readObject();		//load the data into arraylist
			ois.close();
		}
		
		do {
			System.out.println("1. INSERT");
			System.out.println("2. DISPLAY");
			System.out.println("3. SEARCH");
			System.out.println("4. DELETE");
			System.out.println("5. UPDATE");
			System.out.println("6. SORT");
			System.out.println("0. EXIT");
			System.out.println("Enter Your Choice : ");
			
			choice = scInt.nextInt();
			
			
			switch(choice) {
			case 1:		//INSERT
				System.out.println("How many Employees yoou want to add : ");
				int n = scInt.nextInt();
				for(int i=0; i<n; i++) {
					
				System.out.println("Enter Employee Number: ");
				int empno = scInt.nextInt();
				
				System.out.println("Enter Employee Name: ");
				String ename = scStr.nextLine();
				
				System.out.println("Enter Employee Salary: ");
				int salary = scInt.nextInt();
				
				al.add(new Employee(empno, ename, salary));
				
				
				}
				
				
				oos = new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(al);
				oos.close();
				
				break;
				
			case 2:		//DISPLAY
				if(file.isFile()) {
					ois = new ObjectInputStream(new FileInputStream(file));		//Read from File
					al = (ArrayList<Employee>)ois.readObject();		//load the data into arraylist
					ois.close();
					
					System.out.println("---------------------------------------------");
					li = al.listIterator();
					while(li.hasNext()) {
						System.out.println(li.next());
					}
					System.out.println("---------------------------------------------");
				}
				else {
					System.out.println("File not Exists...!");
				}
				
				break;
				
				
			case 3:		//SEARCH
				
				if(file.isFile()) {
					ois = new ObjectInputStream(new FileInputStream(file));		//Read from File
					al = (ArrayList<Employee>)ois.readObject();		//load the data into arraylist
					ois.close();
					
					boolean found = false;
					System.out.println("Enter Employee no to Search: ");
					int empno = scInt.nextInt();
					System.out.println("---------------------------------------------");
					li = al.listIterator();
					while(li.hasNext()) {
						Employee e = (Employee)li.next();
						
						if(e.empno == empno) {
							System.out.println(e);
							found = true;
						}
					}
					
					if(!found) {
						System.out.println("record Not Found...!");
					}
					System.out.println("---------------------------------------------");
				}
				else {
					System.out.println("File not Exists...!");
				}
				
				break;
				
				
				
			case 4:		//DLETE
				
				if(file.isFile()) {
					ois = new ObjectInputStream(new FileInputStream(file));		//Read from File
					al = (ArrayList<Employee>)ois.readObject();		//load the data into arraylist
					ois.close();
					
					boolean found = false;
					System.out.println("Enter Employee no to Delete: ");
					int empno = scInt.nextInt();
					System.out.println("---------------------------------------------");
					li = al.listIterator();
					while(li.hasNext()) {
						Employee e = (Employee)li.next();
						
						if(e.empno == empno) {
							li.remove();
							found = true;
						}
					}
					
					if(found) {
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(al);
						oos.close();
						System.out.println("Record Deleted Successfully..");
						
					}
					else {
						System.out.println("Record Not Found...!");
					}
					System.out.println("---------------------------------------------");
				}
				else {
					System.out.println("File not Exists...!");
				}
				
				break;
				
			
			case 5:		//UPDATE
				
				if(file.isFile()) {
					ois = new ObjectInputStream(new FileInputStream(file));		//Read from File
					al = (ArrayList<Employee>)ois.readObject();		//load the data into arraylist
					ois.close();
					
					boolean found = false;
					System.out.println("Enter Employee no to Update: ");
					int empno = scInt.nextInt();
					System.out.println("---------------------------------------------");
					li = al.listIterator();
					while(li.hasNext()) {
						Employee e = (Employee)li.next();
						
						if(e.empno == empno) {
							System.out.println("Enter new Employee Name: ");
							String ename = scStr.nextLine();
							
							System.out.println("Enter new Salary: ");
							int salary = scInt.nextInt();
							
							li.set(new Employee(empno, ename, salary));
							found = true;
						}
					}
					
					if(found) {
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(al);
						oos.close();
						System.out.println("Record Updated Successfully..");
						
					}
					else {
						System.out.println("Record Not Found...!");
					}
					System.out.println("---------------------------------------------");
				}
				else {
					System.out.println("File not Exists...!");
				}
				
				break;
				
				
			case 6:		//Sorting
				int ch = -1;
				do {
					
					System.out.println("1. SORT By No - On Screen");
					System.out.println("2. SORT By No - In File");
					System.out.println("3. SORT By EmpName - On Screen - Ascending");
					System.out.println("4. SORT By EmpName - On Screen - Descending");
					System.out.println("5. SORT By EmpName - In File - Ascending");
					System.out.println("6. SORT By EmpName - In File - Descending");
					System.out.println("7. SORT By Salary - On Screen - Ascending");
					System.out.println("8. SORT By Salary - On Screen - Descending");
					System.out.println("9. SORT By Salary - In File - Ascending");
					System.out.println("10. SORT By Salary - In File - Descending");
					System.out.println("0. Main Menu");
					
					ch = scInt.nextInt();
					
					
					switch(ch) {
					
					case 1:		//SORT By No - On Screen
						if(file.isFile()) {
							ois = new ObjectInputStream(new FileInputStream(file));		//Read from File
							al = (ArrayList<Employee>)ois.readObject();		//load the data into arraylist
							ois.close();
							
							Collections.sort(al, new Comparator<Employee>() {
								public int compare(Employee e1, Employee e2) {
									return e1.empno - e2.empno;
								}
							});
							System.out.println("---------------------------------------------");
							li = al.listIterator();
							while(li.hasNext()) {
								System.out.println(li.next());
							}
							System.out.println("---------------------------------------------");
						}
						else {
							System.out.println("File not Exists...!");
						}
						
						break;
						
						
					case 2:		//SORT By No - In File
						if(file.isFile()) {
							ois = new ObjectInputStream(new FileInputStream(file));		//Read from File
							al = (ArrayList<Employee>)ois.readObject();		//load the data into arraylist
							ois.close();
							
							Collections.sort(al, new Comparator<Employee>() {
								public int compare(Employee e1, Employee e2) {
									return e1.empno - e2.empno;
								}
							});
							
							oos = new ObjectOutputStream(new FileOutputStream(file));
							oos.writeObject(al);
							oos.close();
							
							System.out.println("---------------------------------------------");
							li = al.listIterator();
							while(li.hasNext()) {
								System.out.println(li.next());
							}
							System.out.println("---------------------------------------------");
						}
						else {
							System.out.println("File not Exists...!");
						}
						
						break;
						
						
						
						
						
					case 3:		//SORT By EmpName - On Screen - Ascending
						if(file.isFile()) {
							ois = new ObjectInputStream(new FileInputStream(file));		//Read from File
							al = (ArrayList<Employee>)ois.readObject();		//load the data into arraylist
							ois.close();
							
							Collections.sort(al, new Comparator<Employee>() {
								public int compare(Employee e1, Employee e2) {
									return e1.ename.compareTo(e2.ename);
								}
							});
							System.out.println("---------------------------------------------");
							li = al.listIterator();
							while(li.hasNext()) {
								System.out.println(li.next());
							}
							System.out.println("---------------------------------------------");
						}
						else {
							System.out.println("File not Exists...!");
						}
						
						break;
						
						
					case 4:		//SORT By EmpName - On Screen - Descending
						if(file.isFile()) {
							ois = new ObjectInputStream(new FileInputStream(file));		//Read from File
							al = (ArrayList<Employee>)ois.readObject();		//load the data into arraylist
							ois.close();
							
							Collections.sort(al, new Comparator<Employee>() {
								public int compare(Employee e1, Employee e2) {
									return e2.ename.compareTo(e1.ename);
								}
							});
							System.out.println("---------------------------------------------");
							li = al.listIterator();
							while(li.hasNext()) {
								System.out.println(li.next());
							}
							System.out.println("---------------------------------------------");
						}
						else {
							System.out.println("File not Exists...!");
						}
						
						break;
						
						
					case 5:		//SORT By EmpName - In File - Ascending
						if(file.isFile()) {
							ois = new ObjectInputStream(new FileInputStream(file));		//Read from File
							al = (ArrayList<Employee>)ois.readObject();		//load the data into arraylist
							ois.close();
							
							Collections.sort(al, new Comparator<Employee>() {
								public int compare(Employee e1, Employee e2) {
									return e1.ename.compareTo(e2.ename);
								}
							});
							
							oos = new ObjectOutputStream(new FileOutputStream(file));
							oos.writeObject(al);
							oos.close();
							
							System.out.println("---------------------------------------------");
							li = al.listIterator();
							while(li.hasNext()) {
								System.out.println(li.next());
							}
							System.out.println("---------------------------------------------");
						}
						else {
							System.out.println("File not Exists...!");
						}
						
						break;
						
						
					case 6:		//SORT By EmpName - In File - Ascending
						if(file.isFile()) {
							ois = new ObjectInputStream(new FileInputStream(file));		//Read from File
							al = (ArrayList<Employee>)ois.readObject();		//load the data into arraylist
							ois.close();
							
							Collections.sort(al, new Comparator<Employee>() {
								public int compare(Employee e1, Employee e2) {
									return e2.ename.compareTo(e1.ename);
								}
							});
							
							oos = new ObjectOutputStream(new FileOutputStream(file));
							oos.writeObject(al);
							oos.close();
							
							System.out.println("---------------------------------------------");
							li = al.listIterator();
							while(li.hasNext()) {
								System.out.println(li.next());
							}
							System.out.println("---------------------------------------------");
						}
						else {
							System.out.println("File not Exists...!");
						}
						
						break;
						
						
					case 7:		//SORT By Salary - On Screen - Ascending
						if(file.isFile()) {
							ois = new ObjectInputStream(new FileInputStream(file));		//Read from File
							al = (ArrayList<Employee>)ois.readObject();		//load the data into arraylist
							ois.close();
							
							Collections.sort(al, new Comparator<Employee>() {
								public int compare(Employee e1, Employee e2) {
									return e1.salary - e2.salary;
								}
							});
							System.out.println("---------------------------------------------");
							li = al.listIterator();
							while(li.hasNext()) {
								System.out.println(li.next());
							}
							System.out.println("---------------------------------------------");
						}
						else {
							System.out.println("File not Exists...!");
						}
						
						break;
						
					case 8:		//SORT By Salary - On Screen - Descending
						if(file.isFile()) {
							ois = new ObjectInputStream(new FileInputStream(file));		//Read from File
							al = (ArrayList<Employee>)ois.readObject();		//load the data into arraylist
							ois.close();
							
							Collections.sort(al, new Comparator<Employee>() {
								public int compare(Employee e1, Employee e2) {
									return e2.salary - e1.salary;
								}
							});
							System.out.println("---------------------------------------------");
							li = al.listIterator();
							while(li.hasNext()) {
								System.out.println(li.next());
							}
							System.out.println("---------------------------------------------");
						}
						else {
							System.out.println("File not Exists...!");
						}
						
						break;
						
						
					case 9:		//SORT By Salary - In File - Ascending
						if(file.isFile()) {
							ois = new ObjectInputStream(new FileInputStream(file));		//Read from File
							al = (ArrayList<Employee>)ois.readObject();		//load the data into arraylist
							ois.close();
							
							Collections.sort(al, new Comparator<Employee>() {
								public int compare(Employee e1, Employee e2) {
									return e1.salary - e2.salary;
								}
							});
							
							oos = new ObjectOutputStream(new FileOutputStream(file));
							oos.writeObject(al);
							oos.close();
							
							System.out.println("---------------------------------------------");
							li = al.listIterator();
							while(li.hasNext()) {
								System.out.println(li.next());
							}
							System.out.println("---------------------------------------------");
						}
						else {
							System.out.println("File not Exists...!");
						}
						
						break;
						
						
					case 10:		//SORT By Salary - In File - Descending
						if(file.isFile()) {
							ois = new ObjectInputStream(new FileInputStream(file));		//Read from File
							al = (ArrayList<Employee>)ois.readObject();		//load the data into arraylist
							ois.close();
							
							Collections.sort(al, new Comparator<Employee>() {
								public int compare(Employee e1, Employee e2) {
									return e2.salary - e1.salary;
								}
							});
							
							oos = new ObjectOutputStream(new FileOutputStream(file));
							oos.writeObject(al);
							oos.close();
							
							System.out.println("---------------------------------------------");
							li = al.listIterator();
							while(li.hasNext()) {
								System.out.println(li.next());
							}
							System.out.println("---------------------------------------------");
						}
						else {
							System.out.println("File not Exists...!");
						}
						
						break;
					}
					
				}while(ch != 0);
				
					
			
			
			}
			
		}while(choice != 0);
		
	}
}


