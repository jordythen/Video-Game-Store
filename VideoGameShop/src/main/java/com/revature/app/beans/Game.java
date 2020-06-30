package com.revature.app.beans;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Game {
	
	private Integer id;
	private String name;
	private List<String> developer; // Develops the game: Naughty Dog, Ubisoft, Infinity Ward
	private List<String> publisher; // Pushlisher: Sony, Nintendo, Xbox
	private LocalDateTime dateReleased;
	private List<GameSystem> systems;
	private String esrbRating;
	private List<Category> category;
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
	public List<String> getDeveloper() {
		return developer;
	}
	public void setDeveloper(List<String> developer) {
		this.developer = developer;
	}
	public List<String> getPublisher() {
		return publisher;
	}
	public void setPublisher(List<String> publisher) {
		this.publisher = publisher;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((dateReleased == null) ? 0 : dateReleased.hashCode());
		result = prime * result + ((developer == null) ? 0 : developer.hashCode());
		result = prime * result + ((esrbRating == null) ? 0 : esrbRating.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((playerLimit == null) ? 0 : playerLimit.hashCode());
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
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
		if (developer == null) {
			if (other.developer != null)
				return false;
		} else if (!developer.equals(other.developer))
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
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
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
		return "Game [id=" + id + ", name=" + name + ", developer=" + developer + ", publisher=" + publisher
				+ ", dateReleased=" + dateReleased + ", systems=" + systems + ", esrbRating=" + esrbRating
				+ ", category=" + category + ", playerLimit=" + playerLimit + "]";
	}
	
}
