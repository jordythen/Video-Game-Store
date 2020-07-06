package com.revature.app.beans;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Game {
	@Id
	@SequenceGenerator(name="gameGen", sequenceName ="game_seq", allocationSize=1)
	@GeneratedValue(generator="gameGen", strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Column
	private String name;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="DEVELOPER_GAME", 
			joinColumns=@JoinColumn(name="gameID"),
			inverseJoinColumns=@JoinColumn(name="developerID"))
	private List<Developer> developers; // Develops the game: Naughty Dog, Ubisoft, Infinity Ward
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="PUBLISHER_GAME",
				joinColumns=@JoinColumn(name="gameID"),
				inverseJoinColumns=@JoinColumn(name="publisherID"))
	private List<Publisher> publishers; // Pushlisher: Sony, Nintendo, Xbox
	@Column
	private LocalDateTime dateReleased;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="GAME_GAMESYSTEM",
				joinColumns=@JoinColumn(name="gameID"),
				inverseJoinColumns=@JoinColumn(name="systemID"))
	private List<GameSystem> systems;
	
	@Column
	private String esrbRating;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="GAME_GAMECATEGORY",
				joinColumns=@JoinColumn(name="gameID"),
				inverseJoinColumns=@JoinColumn(name="categoryID"))
	private List<Category> category;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="GAME_GAMEDETAILS",
				joinColumns=@JoinColumn(name="gameID"),
				inverseJoinColumns=@JoinColumn(name="detailID"))
	private List<GameDetails> details;
	
	@Column
	private String playerLimit; 
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Developer> getDevelopers() {
		return developers;
	}
	public void setDevelopers(List<Developer> developers) {
		this.developers = developers;
	}
	public List<Publisher> getPublishers() {
		return publishers;
	}
	public void setPublishers(List<Publisher> publishers) {
		this.publishers = publishers;
	}
	public LocalDateTime getDateReleased() {
		return dateReleased;
	}
	public void setDateReleased(LocalDateTime dateReleased) {
		this.dateReleased = dateReleased;
	}
	public List<GameSystem> getSystems() {
		return systems;
	}
	public void setSystems(List<GameSystem> systems) {
		this.systems = systems;
	}
	public String getEsrbRating() {
		return esrbRating;
	}
	public void setEsrbRating(String esrbRating) {
		this.esrbRating = esrbRating;
	}
	public List<Category> getCategory() {
		return category;
	}
	public void setCategory(List<Category> category) {
		this.category = category;
	}
	public String getPlayerLimit() {
		return playerLimit;
	}
	public void setPlayerLimit(String playerLimit) {
		this.playerLimit = playerLimit;
	}
	public List<GameDetails> getDetails() {
		return details;
	}
	public void setDetails(List<GameDetails> details) {
		this.details = details;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((dateReleased == null) ? 0 : dateReleased.hashCode());
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((developers == null) ? 0 : developers.hashCode());
		result = prime * result + ((esrbRating == null) ? 0 : esrbRating.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((playerLimit == null) ? 0 : playerLimit.hashCode());
		result = prime * result + ((publishers == null) ? 0 : publishers.hashCode());
		result = prime * result + ((systems == null) ? 0 : systems.hashCode());
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
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (dateReleased == null) {
			if (other.dateReleased != null)
				return false;
		} else if (!dateReleased.equals(other.dateReleased))
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (developers == null) {
			if (other.developers != null)
				return false;
		} else if (!developers.equals(other.developers))
			return false;
		if (esrbRating == null) {
			if (other.esrbRating != null)
				return false;
		} else if (!esrbRating.equals(other.esrbRating))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (playerLimit == null) {
			if (other.playerLimit != null)
				return false;
		} else if (!playerLimit.equals(other.playerLimit))
			return false;
		if (publishers == null) {
			if (other.publishers != null)
				return false;
		} else if (!publishers.equals(other.publishers))
			return false;
		if (systems == null) {
			if (other.systems != null)
				return false;
		} else if (!systems.equals(other.systems))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", developers=" + developers + ", publishers=" + publishers
				+ ", dateReleased=" + dateReleased + ", systems=" + systems + ", esrbRating=" + esrbRating
				+ ", category=" + category + ", details=" + details + ", playerLimit=" + playerLimit + "]";
	}
	
}
