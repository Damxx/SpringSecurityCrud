package springsecurity.demo.service;

import springsecurity.demo.dao.RoleDao;
import springsecurity.demo.dao.UserDao;
import springsecurity.demo.dao.Workers_salaryDAO;
import springsecurity.demo.entity.Role;
import springsecurity.demo.entity.User;
import springsecurity.demo.entity.Workers_salary;
import springsecurity.demo.entity.Workers_salarySearch;
import springsecurity.demo.user.CrmUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	// need to inject user dao
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private Workers_salaryDAO workers_salary;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public void save(CrmUser crmUser) {
		User user = new User();
		 // assign user details to the user object
		user.setUserName(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());

		// give user default role of "employee"
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));

		 // save user in the database
		userDao.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public List<Workers_salary> getWorkers_salaries() {
		// TODO Auto-generated method stub
		return workers_salary.getWorkers_salaries();
	}

	@Transactional
	@Override
	public Workers_salary getWorkers_salary(int id_pracownika) {
		// TODO Auto-generated method stub
		return workers_salary.getWorkers_salary(id_pracownika);
	}

	@Transactional
	@Override
	public void deteleWorkers(int id_workers) {
	
		workers_salary.deteleWorkers(id_workers);
		
	}

	
	@Transactional
	@Override
	public void addNew(Workers_salary workers_salary) {
		
		this.workers_salary.addNew(workers_salary);
		
	}

	
	@Transactional
	@Override
	public List<Workers_salarySearch> getSearch(String text) {
		// TODO Auto-generated method stub
		return workers_salary.getSearch(text);
	}
	
	
	
	
}
