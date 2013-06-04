package cn.edu.nju.clubunion.daoRemote;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import cn.edu.nju.clubunion.abstractEntity.APoster;
import cn.edu.nju.clubunion.entity.Poster;

@Remote
public interface PosterDAORemote {
	public List<Poster> getPostersByDate(Date startDate,Date endDate);

	public int addPoster(Poster poster, Integer documentId);
	public void specifyDatabase(String s);

}
