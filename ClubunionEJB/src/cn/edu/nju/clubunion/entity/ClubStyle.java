package cn.edu.nju.clubunion.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.edu.nju.clubunion.abstractEntity.AClubStyle;

@Entity
@Table(name = "club_styles")
public class ClubStyle extends AClubStyle {
	@OneToOne
	@JoinColumn(name = "club_id")
	private Club club;
	@OneToMany(mappedBy = "clubStyle")
	private List<ContainerBlock> containers = new ArrayList<ContainerBlock>();

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public List<ContainerBlock> getContainers() {
		return containers;
	}

	public void setContainers(List<ContainerBlock> containers) {
		this.containers = containers;
	}
	

	


}
