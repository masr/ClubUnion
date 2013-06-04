package cn.edu.nju.clubunion.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import cn.edu.nju.clubunion.abstractEntity.APoster;
import cn.edu.nju.clubunion.daoRemote.PosterDAORemote;
import cn.edu.nju.clubunion.entity.Document;
import cn.edu.nju.clubunion.entity.Poster;

@Stateless
public class PosterDAO extends AbstractDAO implements PosterDAORemote{

	public int addPoster(Poster poster, Integer documentId) {
		Document d =entitymanager.find(Document.class, documentId);
		poster.setNotice(d);
		entitymanager.persist(poster);
		return poster.getId();
	}

	public List<Poster> getPostersByDate(Date startDate,Date endDate) {
		String jpql = "select d from Document d where d.activityDate>=:startDate AND d.activityDate<=:endDate";
		Query query = entitymanager.createQuery(jpql);
		query.setParameter("startDate", startDate,TemporalType.TIMESTAMP);
		query.setParameter("endDate", endDate,TemporalType.TIMESTAMP);
		List<Document> documents=query.getResultList();
	

		List<Poster> posters=new ArrayList<Poster>();
		for(Document d:documents)
		{
			Poster p=d.getPoster();
		
				posters.add(p);
			
		}
		return posters;
	}
	
}
