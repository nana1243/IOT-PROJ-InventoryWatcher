package top.vo;

public class OrderDetailVO {
	String orderDetailID;
	String orderState;
	Double totWeight;
	Double ingWeight;
	int ingQuantity;
	String ingID;
	String ingName;
	double ingPrice;
	double ingTotPrice;
	String ingUnit;
	String orderID;
	String hqID;
	String hqName;
	String chainID;
	String chainName;
	String userID;
	String userName;
	String conID;
	
	public OrderDetailVO() {
		super();
	}
	
	public OrderDetailVO(String orderDetailID, String orderState, Double totWeight, Double ingWeight, int ingQuantity,
			String ingID, String ingName, double ingPrice, double ingTotPrice, String ingUnit, String orderID,
			String hqID, String hqName, String chainID, String chainName, String userID, String userName,
			String conID) {
		super();
		this.orderDetailID = orderDetailID;
		this.orderState = orderState;
		this.totWeight = totWeight;
		this.ingWeight = ingWeight;
		this.ingQuantity = ingQuantity;
		this.ingID = ingID;
		this.ingName = ingName;
		this.ingPrice = ingPrice;
		this.ingTotPrice = ingTotPrice;
		this.ingUnit = ingUnit;
		this.orderID = orderID;
		this.hqID = hqID;
		this.hqName = hqName;
		this.chainID = chainID;
		this.chainName = chainName;
		this.userID = userID;
		this.userName = userName;
		this.conID = conID;
	}



	public String getOrderDetailID() {
		return orderDetailID;
	}

	public void setOrderDetailID(String orderDetailID) {
		this.orderDetailID = orderDetailID;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public Double getTotWeight() {
		return totWeight;
	}

	public void setTotWeight(Double totWeight) {
		this.totWeight = totWeight;
	}

	public Double getIngWeight() {
		return ingWeight;
	}

	public void setIngWeight(Double ingWeight) {
		this.ingWeight = ingWeight;
	}

	public int getIngQuantity() {
		return ingQuantity;
	}

	public void setIngQuantity(int ingQuantity) {
		this.ingQuantity = ingQuantity;
	}

	public String getIngID() {
		return ingID;
	}

	public void setIngID(String ingID) {
		this.ingID = ingID;
	}

	public String getIngName() {
		return ingName;
	}

	public void setIngName(String ingName) {
		this.ingName = ingName;
	}

	public double getIngPrice() {
		return ingPrice;
	}

	public void setIngPrice(double ingPrice) {
		this.ingPrice = ingPrice;
	}

	public double getIngTotPrice() {
		return ingTotPrice;
	}

	public void setIngTotPrice(double ingTotPrice) {
		this.ingTotPrice = ingTotPrice;
	}

	public String getIngUnit() {
		return ingUnit;
	}

	public void setIngUnit(String ingUnit) {
		this.ingUnit = ingUnit;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getHqID() {
		return hqID;
	}

	public void setHqID(String hqID) {
		this.hqID = hqID;
	}

	public String getHqName() {
		return hqName;
	}

	public void setHqName(String hqName) {
		this.hqName = hqName;
	}

	public String getChainID() {
		return chainID;
	}

	public void setChainID(String chainID) {
		this.chainID = chainID;
	}

	public String getChainName() {
		return chainName;
	}

	public void setChainName(String chainName) {
		this.chainName = chainName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getConID() {
		return conID;
	}

	public void setConID(String conID) {
		this.conID = conID;
	}

	@Override
	public String toString() {
		return "OrderDetailVO [orderDetailID=" + orderDetailID + ", orderState=" + orderState + ", totWeight="
				+ totWeight + ", ingWeight=" + ingWeight + ", ingQuantity=" + ingQuantity + ", ingID=" + ingID
				+ ", ingName=" + ingName + ", ingPrice=" + ingPrice + ", ingTotPrice=" + ingTotPrice + ", ingUnit="
				+ ingUnit + ", orderID=" + orderID + ", hqID=" + hqID + ", hqName=" + hqName + ", chainID=" + chainID
				+ ", chainName=" + chainName + ", userID=" + userID + ", userName=" + userName + ", conID=" + conID
				+ "]";
	}
}
