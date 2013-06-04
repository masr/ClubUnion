package cn.edu.nju.clubunion.businessLogicClient;

import java.util.List;

import javax.ejb.Remote;

import cn.edu.nju.clubunion.abstractEntity.AComment;
import cn.edu.nju.clubunion.abstractEntity.ADocument;
import cn.edu.nju.clubunion.exception.ErrorException;

@Remote
public interface IDocumentService {

	public int createDocument(IAccountService user, ADocument document)
			throws ErrorException; // 发表文章，仅社团管理员具有权限，并要判断文章的作者与文章的所属社团是否满足管理关系（userId,clubId）

	public void editDocument(IAccountService user, ADocument document)
			throws ErrorException;// 更改文章，仅作者可改

	public ADocument getDocumentInfo(IAccountService user, int documentId)
			throws ErrorException;// 得到文章信息，对于非私密文件任何人可得到信息，对于私密文章，唯有本社团成员可访问

	public void deleteDocument(IAccountService user, int documentId)
			throws ErrorException;// 删除文章，唯有作者，超级管理员可用。

	public List<AComment> getComments(IAccountService user, int documentId)
			throws ErrorException;// 得到评论，依赖于特定文章，任何人可用

	public void makeComment(IAccountService user, AComment commentInfo)
			throws ErrorException;// 评论，只有登录用户才可用

	public void deleteComment(IAccountService user, int commentId)
			throws ErrorException;// 删除评论，超级管理员与文章作者可用

	public List<ADocument> getAllNotices(IAccountService user)
			throws ErrorException;// 得到所有大平台通知，任何人可用

	public List<ADocument> getAllPrivateDocuments(IAccountService user)
			throws ErrorException;// 得到所有大平台私密文章，唯有有权限访问文章者（该文章所属社团成员）可用

	public List<ADocument> getAllPublicDocuments(IAccountService user)
			throws ErrorException;// 得到所有大平台公开文章，任何人可用

	public void specifyDatabase(String s);



}
