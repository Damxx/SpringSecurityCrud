package springsecurity.demo.dao;

import java.util.List;

import org.apache.lucene.queryparser.flexible.core.builders.QueryBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.search.FullTextSession;
//import org.hibernate.search.FullTextSession;
//import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springsecurity.demo.entity.User;
import springsecurity.demo.entity.Workers_salary;
import springsecurity.demo.entity.Workers_salarySearch;

@Repository
public class Workers_salaryDAOImp implements Workers_salaryDAO {

	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Workers_salary> getWorkers_salaries() {
		
		
		// get the current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
						
				// create a query  ... sort by last name
				Query<Workers_salary> theQuery = 
						currentSession.createQuery("from Workers_salary order by id",
													Workers_salary.class);
				
				// execute query and get result list
				List<Workers_salary> workers_salaries = theQuery.getResultList();
						
				// return the results		
				return workers_salaries;
	
	}

	@Override
	public Workers_salary getWorkers_salary(int id_pracownika) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Workers_salary> theQuery = currentSession.createQuery("FROM Workers_salary where id=:i ");
		
		theQuery.setParameter("i", id_pracownika);
		
		Workers_salary theWorkers_salary = theQuery.getSingleResult();
				
		
		
		return theWorkers_salary;
	}

	@Override
	public void deteleWorkers(int id_workers) {

		
		
		Session currentsession = sessionFactory.getCurrentSession();
		
		Query<Workers_salary> theQuery =
								 currentsession.createQuery("delete from Workers_salary where id=:id_workers");
		
		theQuery.setParameter("id_workers", id_workers);
		
		theQuery.executeUpdate();
		
		
		
	}

	@Override
	public void addNew(Workers_salary workers_salary) {
		
			Session currentsession = sessionFactory.getCurrentSession();
				
				System.out.println(workers_salary+"----");
			
				currentsession.saveOrUpdate(workers_salary);
				
		
	}

	
	@Override
	public List<Workers_salarySearch> getSearch(String text) {
		
		System.out.println("Poczatek");
		
		List<Workers_salarySearch> workers_salaries = null;
		
		
		
		Session currentsession = sessionFactory.getCurrentSession();
		
		
		FullTextSession fullTextSession = org.hibernate.search.Search.getFullTextSession(currentsession);
		
		try {
			fullTextSession.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		org.hibernate.search.query.dsl.QueryBuilder qb = fullTextSession.getSearchFactory().
				buildQueryBuilder().forEntity(Workers_salarySearch.class).get();
		
		org.apache.lucene.search.Query luceneQuery = qb.keyword().onField("name").
				matching(text).createQuery();
		
		@SuppressWarnings("unchecked")
		Query<Workers_salarySearch> query = fullTextSession.createFullTextQuery(luceneQuery, Workers_salarySearch.class);
		
		workers_salaries = query.getResultList();
	    
		System.out.println(workers_salaries);
	   
		return workers_salaries;
	}


}