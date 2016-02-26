package ua.lorien.bestinwholeworld.model;

import java.io.Serializable;
import java.util.ArrayList;
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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "GAME")
public class Game implements Serializable {

	private static final long serialVersionUID = 952811803736599994L;

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	@NotNull
	@Column(unique = true)
	private String name;

	@Size(max = 400)
	private String description;

	@Column(name = "dev_company_name")
	private String devCompanyName;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "game_fk")
	private List<HiScore> hiScores = new ArrayList<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "game_fk")
	private List<Comment> comments = new ArrayList<>();

	public Game() {
	}

	public Game(String name, String devCompanyName) {
		this.name = name;
		this.devCompanyName = devCompanyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDevCompanyName() {
		return devCompanyName;
	}

	public void setDevCompanyName(String devCompanyName) {
		this.devCompanyName = devCompanyName;
	}

	public List<HiScore> getHiScores() {
		return hiScores;
	}

	public void setHiScores(List<HiScore> hiScores) {
		this.hiScores = hiScores;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public Comment addComment(Comment comment) {
		if (comment != null) {
			comments.add(comment);
			return comment;
		}
		return null;
	}

	public HiScore addHiScore(HiScore hiScore) {
		if (hiScore != null) {
			hiScores.add(hiScore);
			return hiScore;
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((devCompanyName == null) ? 0 : devCompanyName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Game other = (Game) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (devCompanyName == null) {
			if (other.devCompanyName != null)
				return false;
		} else if (!devCompanyName.equals(other.devCompanyName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", description=" + description + ", devCompanyName="
				+ devCompanyName + "]";
	}
}
