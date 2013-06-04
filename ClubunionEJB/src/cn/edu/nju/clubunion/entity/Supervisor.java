package cn.edu.nju.clubunion.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.edu.nju.clubunion.abstractEntity.ASupervisor;

@Entity
@Table(name = "supervisors")
public class Supervisor extends ASupervisor {

}
