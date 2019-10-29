package springsecurity.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "workers")
public class Workers_salary {

	
	public Workers_salary() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "Name")
	private String name;
	
	
	@Column(name = "Position")
	private String position;
	

	@Column(name = "Office")
	private String office;
	
	@Column(name = "Age")
	private int age;
	
	@Column(name = "Start")
	private String start;
	
	@Column(name = "Salary")
	private int salary;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Workers_salary [id=" + id + ", name=" + name + ", position=" + position + ", office=" + office
				+ ", age=" + age + ", start=" + start + ", salary=" + salary + "]";
	}

	public Workers_salary(int id, String name, String position, String office, int age, String start, int salary) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.office = office;
		this.age = age;
		this.start = start;
		this.salary = salary;
	}

	
}
