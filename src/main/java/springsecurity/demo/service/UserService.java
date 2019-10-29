package springsecurity.demo.service;

import springsecurity.demo.entity.*;
import springsecurity.demo.user.CrmUser;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(CrmUser crmUser);
    
    java.util.List<Workers_salary> getWorkers_salaries();
    
    Workers_salary getWorkers_salary(int id_pracownika);
    
    void deteleWorkers(int id_workers);
    
    void addNew(Workers_salary workers_salary);
 
     java.util.List<Workers_salarySearch> getSearch(String text);
}
