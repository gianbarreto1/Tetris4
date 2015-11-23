package employeedb.model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {

	private final StringProperty firstName;
	private final StringProperty lastName;
	private final IntegerProperty salary;

	public Employee() {
		this(null, null, 0);
	}

	public Employee(String firstName, String lastName, int salary) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.salary = new SimpleIntegerProperty(salary);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public int getSalary() {
		return salary.get();
	}

	public void setSalary(int salary) {
		this.salary.set(salary);
	}

	public IntegerProperty salaryProperty() {
		return salary;
	}

}
