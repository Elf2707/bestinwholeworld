package ua.lorien.bestinwholeworld.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class HiScore implements Serializable {
	
	private static final long serialVersionUID = 5827241931226199514L;

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private Long score;
	
	@Size(min= 2, max = 20)
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_description")
	private String userDescription;

	public HiScore() {
	}

	public HiScore(String userName, String userDescription, Long score) {
		this.score = score;
		this.userName = userName;
		this.userDescription = userDescription;
	}

	public Long getScore() {
		return score;
	}


	public void setScore(Long score) {
		this.score = score;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserDescription() {
		return userDescription;
	}


	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}


	public Long getId() {
		return id;
	}


	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result + ((userDescription == null) ? 0 : userDescription.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HiScore other = (HiScore) obj;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (userDescription == null) {
			if (other.userDescription != null)
				return false;
		} else if (!userDescription.equals(other.userDescription))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "HiScore [id=" + id + ", score=" + score + ", userName=" + userName + ", userDescription="
				+ userDescription + "]";
	}
}
