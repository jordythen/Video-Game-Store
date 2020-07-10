package com.revature.app.beans;

import java.util.List;

public class Customer extends User{
	
	private List<Game> ownedGames;
	
	private List<Order> orderHistory;
	
	private List<GameDetails> gamesToSell;
	
	public List<Game> getOwnedGames() {
		return ownedGames;
	}
	public void setOwnedGames(List<Game> ownedGames) {
		this.ownedGames = ownedGames;
	}
	public List<Order> getOrderHistory() {
		return orderHistory;
	}
	public void setOrderHistory(List<Order> orderHistory) {
		this.orderHistory = orderHistory;
	}
	public List<GameDetails> getGamesToSell() {
		return gamesToSell;
	}
	public void setGamesToSell(List<GameDetails> gamesToSell) {
		this.gamesToSell = gamesToSell;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gamesToSell == null) ? 0 : gamesToSell.hashCode());
		result = prime * result + ((orderHistory == null) ? 0 : orderHistory.hashCode());
		result = prime * result + ((ownedGames == null) ? 0 : ownedGames.hashCode());
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
		Customer other = (Customer) obj;
		if (gamesToSell == null) {
			if (other.gamesToSell != null)
				return false;
		} else if (!gamesToSell.equals(other.gamesToSell))
			return false;
		if (orderHistory == null) {
			if (other.orderHistory != null)
				return false;
		} else if (!orderHistory.equals(other.orderHistory))
			return false;
		if (ownedGames == null) {
			if (other.ownedGames != null)
				return false;
		} else if (!ownedGames.equals(other.ownedGames))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Customer [ownedGames=" + ownedGames + ", orderHistory=" + orderHistory + ", gamesToSell=" + gamesToSell
				+ "]";
	}
	
}
