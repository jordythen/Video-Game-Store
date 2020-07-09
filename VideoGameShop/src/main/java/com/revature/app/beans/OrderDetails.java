package com.revature.app.beans;


public class OrderDetails {
	private Integer id;
	
	private Integer gameID;

	private Integer orderID;
	
	private Integer quantity; 
	private Double taxAmount;

	//This will be the total amount for this particular game
	private Double totalLineAmount;
	
	public Double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public Double getTotalLineAmount() {
		return totalLineAmount;
	}
	public void setTotalLineAmount(Double totalLineAmount) {
		this.totalLineAmount = totalLineAmount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getGameID() {
		return gameID;
	}
	public void setGameID(Integer gameID) {
		this.gameID = gameID;
	}
	public Integer getOrderID() {
		return orderID;
	}
	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameID == null) ? 0 : gameID.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((taxAmount == null) ? 0 : taxAmount.hashCode());
		result = prime * result + ((totalLineAmount == null) ? 0 : totalLineAmount.hashCode());
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
		OrderDetails other = (OrderDetails) obj;
		if (gameID == null) {
			if (other.gameID != null)
				return false;
		} else if (!gameID.equals(other.gameID))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (taxAmount == null) {
			if (other.taxAmount != null)
				return false;
		} else if (!taxAmount.equals(other.taxAmount))
			return false;
		if (totalLineAmount == null) {
			if (other.totalLineAmount != null)
				return false;
		} else if (!totalLineAmount.equals(other.totalLineAmount))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", gameID=" + gameID + ", orderID=" + orderID + ", quantity=" + quantity
				+ ", taxAmount=" + taxAmount + ", totalLineAmount=" + totalLineAmount + "]";
	}
}
