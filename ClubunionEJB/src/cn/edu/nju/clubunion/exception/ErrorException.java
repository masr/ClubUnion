package cn.edu.nju.clubunion.exception;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class ErrorException extends Exception implements Serializable { 
	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private static final long serialVersionUID = 6859908652365953373L;
	/**
	 * 
	 */
	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public ErrorException(String reason) {
		this.reason = reason;
	}

}
