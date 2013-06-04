package cn.edu.nju.clubunion.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
public abstract class AbstractDAO {
	@PersistenceContext(unitName = "development")
	protected EntityManager entitymanager;
	@PersistenceContext(unitName = "test")
	protected EntityManager entitymanager2;

	public void specifyDatabase(String s) {
		if (s==null)
			{
			entitymanager2 = null;
			return;
			}
		if (s.equals("test"))
			entitymanager = entitymanager2;
		else {
			if (s.equals("development"))
				entitymanager2 = null; 
		}
	}
}
