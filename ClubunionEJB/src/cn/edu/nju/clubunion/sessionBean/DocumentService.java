package cn.edu.nju.clubunion.sessionBean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import cn.edu.nju.clubunion.abstractEntity.AComment;
import cn.edu.nju.clubunion.abstractEntity.ADocument;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IDocumentService;
import cn.edu.nju.clubunion.daoRemote.DocumentDAORemote;
import cn.edu.nju.clubunion.entity.Comment;
import cn.edu.nju.clubunion.entity.Document;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.PPW;



@Stateless
public class DocumentService implements IDocumentService {
	
	@EJB(beanName = "DocumentDAO")
	DocumentDAORemote documentDAO;

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	public void deleteComment(IAccountService user, int commentId)
			throws ErrorException {

		boolean access = false;
		if (user.isAdmin())
			access = true;
		else {
			Comment comment = documentDAO.getCommentById(commentId);
			if (comment.getUserId() == user.getId())
				access = true;
		}
		if (access)
			documentDAO.deleteComment(commentId);
		else
			throw new ErrorException("您没有权限删除评论");
	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	public void deleteDocument(IAccountService user, int documentId)
			throws ErrorException {
		boolean access = false;
		if (user.isAdmin())
			access = true;
		else {
			Document document = documentDAO.getDocumentById(documentId);
			if (user.getPermissionInClub(document.getClubId()) == PPW.CLUB_MANAGER)
				access = true;
			else {
				if (document.getUserId() == user.getId())
					access = true;
			}
		}

		if (access == true)
			documentDAO.deleteDocument(documentId);
		else
			throw new ErrorException("您没有权限删除文章");

	}

	@SuppressWarnings("unchecked")
	public List<ADocument> getAllNotices(IAccountService user)
			throws ErrorException {
		return documentDAO.getAllNotices();
		

	}

	@SuppressWarnings("unchecked")
	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	public List<ADocument> getAllPrivateDocuments(IAccountService user)
			throws ErrorException {

		List<ADocument> allDocuments = documentDAO.getAllPrivateDocuments();
		for (int i = 0; i < allDocuments.size(); i++) {
			ADocument d = allDocuments.get(i);
			if (!documentDAO.canAccessDocument(d.getId(), user.getId())) {
				allDocuments.remove(allDocuments.get(i));
				i--;
			}
		}
		return allDocuments;
	
	}

	@SuppressWarnings("unchecked")
	public List<ADocument> getAllPublicDocuments(IAccountService user)
			throws ErrorException {

		return documentDAO.getAllPublicDocuments();

	
	}

	@SuppressWarnings("unchecked")
	public List<AComment> getComments(IAccountService user, int documentId)
			throws ErrorException {
		return documentDAO.getComments(documentId);
		
	}

	public ADocument getDocumentInfo(IAccountService user, int documentId)
			throws ErrorException {
		if (!documentDAO.canAccessDocument(documentId, user.getId()))
			throw new ErrorException("您没有权限查看该文章");
		Document document = documentDAO.getDocumentById(documentId);
		if (document == null)
			throw new ErrorException("该文章不存在");
		return document;

	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	public void makeComment(IAccountService user, AComment commentInfo)
			throws ErrorException {
		if (!documentDAO.canAccessDocument(commentInfo.getDocumentId(), user
				.getId()))
			throw new ErrorException("您没有评论查看该文章");
		Comment c = (Comment) commentInfo;
		documentDAO.insertComment(c);
	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	public int createDocument(IAccountService user, ADocument document)
			throws ErrorException {

		if ((user.getPermissionInClub(document.getClubId()) != PPW.CLUB_MANAGER)
				&& (!user.isAdmin()))
			throw new ErrorException("您没有权限创建文章");

		Document d = (Document) document;

		int id = documentDAO.insertDocument(d);
		return id;
	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	public void editDocument(IAccountService user, ADocument document)
			throws ErrorException {
		boolean access = false;

		Document documentEntity = documentDAO.getDocumentById(document.getId());
		if (documentEntity == null)
			throw new ErrorException("该文章不存在");
		if (user.getPermissionInClub(document.getClubId()) == PPW.CLUB_MANAGER)
			access = true;
		else {
			if (document.getUserId() == user.getId())
				access = true;
		}

		if (access == true) {
			documentEntity = (Document) document;

		

			documentDAO.mergeDocument(documentEntity);
		} else
			throw new ErrorException("您没有权限编辑文章");

	}

	public void specifyDatabase(String s) {
		documentDAO.specifyDatabase(s);
	}




}
