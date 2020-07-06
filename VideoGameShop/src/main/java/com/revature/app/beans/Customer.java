package com.revature.app.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Customer{
	@Id
	@SequenceGenerator(name="customerGen",sequenceName="customer_seq",allocationSize=1)
	@GeneratedValue(generator="customerGen",strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Column
	private String username;
	@Column(name="passwd")
	private String password;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private Double money;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="CUSTOMER_GAME", 
				joinColumns=@JoinColumn(name="customerID"),
				inverseJoinColumns=@JoinColumn(name="gameID"))
	private List<Game> ownedGames;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="CUSTOMER_GAMEORDER", 
				joinColumns=@JoinColumn(name="customerID"),
				inverseJoinColumns=@JoinColumn(name="orderID"))
	private List<Order> orderHistory;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((money == null) ? 0 : money.hashCode());
		result = prime * result + ((orderHistory == null) ? 0 : orderHistory.hashCode());
		result = prime * result + ((ownedGames == null) ? 0 : ownedGames.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (money == null) {
			if (other.money != null)
				return false;
		} else if (!money.equals(other.money))
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", money=" + money + ", ownedGames=" + ownedGames + ", orderHistory="
				+ orderHistory + "]";
	}
	
}
