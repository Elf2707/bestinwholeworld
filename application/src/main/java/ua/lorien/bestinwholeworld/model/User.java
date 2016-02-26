package ua.lorien.bestinwholeworld.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

//Users
@Entity
@Table(name = "user_of_game")
public class User implements Serializable {

	private static final long serialVersionUID = 4839393368219218210L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(min = 2, max = 20)
	@Column(name = "user_name")
	private String userName;

	@Email
	@NotNull
	@Column(unique = true)
	private String email;

	@NotNull
	private String password;

	@Column(name = "name_to_display", unique = true)
	private String displayUserName;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "user_fk")
	private List<Comment> comments;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDisplayUserName() {
		return displayUserName;
	}

	public void setDisplayUserName(String displayUserName) {
		this.displayUserName = displayUserName;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", email=" + email + ", displayUserName=" + displayUserName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((displayUserName == null) ? 0 : displayUserName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		User other = (User) obj;
		if (displayUserName == null) {
			if (other.displayUserName != null)
				return false;
		} else if (!displayUserName.equals(other.displayUserName))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
}