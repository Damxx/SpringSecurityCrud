package springsecurity.demo.dao;

import java.util.List;

import springsecurity.demo.entity.Workers_salary;
import springsecurity.demo.entity.Workers_salarySearch;

public interface Workers_salaryDAO {

	List<Workers_salary> getWorkers_salaries();
	
	
	Workers_salary getWorkers_salary(int id_pracownika);
	
	void deteleWorkers(int id_workers);

	void addNew(Workers_salary workers_salary);
	
	public List<Workers_salarySearch> getSearch(String text);
}
