package cn.edu.nju.clubunion.daoRemote;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import cn.edu.nju.clubunion.entity.Comment;


import cn.edu.nju.clubunion.entity.Document;

@Remote
public interface DocumentDAORemote {
	public int insertDocument(Document document);

	public void mergeDocument(Document document);

	public void deleteDocument(int documentId);

	public Document getDocumentById(int documentId);

	public List getComments(int documentId);

	public void insertComment(Comment comment);

	public void deleteComment(int commentId);

	public List getAllDocuments();

	public Comment getCommentById(int commentId);



	public boolean canAccessDocument(int documentId, int userId);

	public void specifyDatabase(String s);





	public List getAllPublicDocuments();

	public List getAllPrivateDocuments();

	public List getAllNotices();
	
	public List getNoticesByActivityDate(Date startDate,Date endDate);
}
