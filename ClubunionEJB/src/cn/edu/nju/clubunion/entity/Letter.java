/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.edu.nju.clubunion.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.edu.nju.clubunion.abstractEntity.ALetter;

/**
 * 
 * @author maosuhan
 */
@Entity
@Table(name = "letters")
public class Letter extends ALetter {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sender_id")
	private User sender;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "receiver_id")
	private User receiver;

	public String getSenderName() {
		return sender.getRealName();
	}

	public String getReceiverName() {
		return  receiver.getRealName();
	}

	public Letter() {

	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

}
