package com.revature.app.beans;

public class GameSystem {
	
	private Integer id;
	private String consoleName; // ex: PC, Xbox One, PS4, Sega Genesis, Super Nintendo, Nintendo Switch
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getConsoleName() {
		return consoleName;
	}
	public void setConsoleName(String consoleName) {
		this.consoleName = consoleName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consoleName == null) ? 0 : consoleName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		GameSystem other = (GameSystem) obj;
		if (consoleName == null) {
			if (other.consoleName != null)
				return false;
		} else if (!consoleName.equals(other.consoleName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GameSystem [id=" + id + ", consoleName=" + consoleName + "]";
	}
	
}
