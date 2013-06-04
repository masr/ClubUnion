package cn.edu.nju.clubunion.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import cn.edu.nju.clubunion.daoRemote.DocumentDAORemote;
import cn.edu.nju.clubunion.entity.Club;
import cn.edu.nju.clubunion.entity.Comment;
import cn.edu.nju.clubunion.entity.Document;

import cn.edu.nju.clubunion.entity.User;
import cn.edu.nju.clubunion.helper.EJBH;
import cn.edu.nju.clubunion.helper.PPW;

@Stateless

public class DocumentDAO extends AbstractDAO implements DocumentDAORemote {

	public int insertDocument(Document document) {


		User u = entitymanager.find(User.class, document.getUserId());
		Club c = entitymanager.find(Club.class, document.getClubId());
		document.setClub(c);
		document.setUser(u);
		entitymanager.persist(document);
		return document.getId();
	}

	public void mergeDocument(Document document) {
		entitymanager.merge(document);

	}

	public void deleteDocument(int documentid) {
		Document document = entitymanager.find(Document.class, documentid);
		if (document != null)
			entitymanager.remove(document);
	}

	public void deleteComment(int commentId) {
		Comment comment = this.getCommentById(commentId);
		entitymanager.remove(comment);
	}

	@SuppressWarnings("unchecked")
	public List<Document> getAllDocuments() {
		Query query = entitymanager.createQuery("select c from Document c ");
		List<Document> documents = query.getResultList();
		return documents;
	}

	public List<Document> getAllNotices() {
		Query query = entitymanager
				.createQuery("select c from Document c where c.documentType=:type");
		query.setParameter("type", PPW.NOTICE_TYPE);
		List<Document> documents = query.getResultList();
		return documents;
	}

	public List<Document> getAllPrivateDocuments() {
		Query query = entitymanager
				.createQuery("select c from Document c where c.documentType=:type");
		query.setParameter("type", PPW.PRIVATE_DOCUMENT_TYPE);
		List<Document> documents = query.getResultList();
		return documents;
	}

	public List<Document> getAllPublicDocuments() {
		Query query = entitymanager
				.createQuery("select c from Document c where c.documentType=:type");
		query.setParameter("type", PPW.PUBLIC_DOCUMENT_TYPE);
		List<Document> documents = query.getResultList();
		return documents;
	}

	public List<Comment> getComments(int documentId) {
		Document document = this.getDocumentById(documentId);
		List<Comment> c = new ArrayList<Comment>();

		if (document == null)
			return c;
		
		c =EJBH.getListOneByOne( document.getComments());
		
		return c;
	}

	public Document getDocumentById(int documentId) {
		return entitymanager.find(Document.class, documentId);
	}
	
	public Comment getCommentById(int commentId) {
		Comment comment = entitymanager.find(Comment.class, commentId);
		return comment;
	}

	@SuppressWarnings("unchecked")
	public boolean canAccessDocument(int documentId, int userId) {
		Document document = this.getDocumentById(documentId);
		if (document.getDocumentType() != PPW.PRIVATE_DOCUMENT_TYPE)
			return true;

		Query query = entitymanager
				.createNativeQuery("select * from supervisors where user_id="
						+ userId);
		List<User> sups = query.getResultList();
		User user = entitymanager.find(User.class, userId);
		if (sups.contains(user))
			return true;

		if (document.getClub().getMembers().contains(user))
			return true;

		return false;

	}

	public void insertComment(Comment comment) {
		User user = entitymanager.find(User.class, comment.getUserId());
		Document document = entitymanager.find(Document.class, comment
				.getDocumentId());
		comment.setDocument(document);
		comment.setUser(user);
		entitymanager.persist(comment);
	}









	public List getNoticesByActivityDate(Date startDate,Date endDate) {
		String jpql = "select d from Document d where d.activityDate>=:startDate AND d.activityDate<=:endDate";
		Query query = entitymanager.createQuery(jpql);
		query.setParameter("startDate", startDate,TemporalType.TIMESTAMP);
		query.setParameter("endDate", endDate,TemporalType.TIMESTAMP);
		List<Document> documents=query.getResultList();
		return documents;
		
	}

	


}
