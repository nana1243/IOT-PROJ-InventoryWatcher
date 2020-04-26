package top.vo;

public class SalesDetailVO {

	String salesDetailID;
	String salesDetailRegDate;
	String salesID;
	String menuID;
	String menuName;
	String menuPrice;
	String menuCount;

	public SalesDetailVO() {
		super();
	}

	public SalesDetailVO(String salesDetailID, String salesDetailRegDate, String salesID, String menuID,
			String menuName, String menuPrice, String menuCount) {
		super();
		this.salesDetailID = salesDetailID;
		this.salesDetailRegDate = salesDetailRegDate;
		this.salesID = salesID;
		this.menuID = menuID;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuCount = menuCount;
	}

	public String getSalesDetailID() {
		return salesDetailID;
	}

	public void setSalesDetailID(String salesDetailID) {
		this.salesDetailID = salesDetailID;
	}

	public String getSalesDetailRegDate() {
		return salesDetailRegDate;
	}

	public void setSalesDetailRegDate(String salesDetailRegDate) {
		this.salesDetailRegDate = salesDetailRegDate;
	}

	public String getSalesID() {
		return salesID;
	}

	public void setSalesID(String salesID) {
		this.salesID = salesID;
	}

	public String getMenuID() {
		return menuID;
	}

	public void setMenuID(String menuID) {
		this.menuID = menuID;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(String menuPrice) {
		this.menuPrice = menuPrice;
	}

	public String getMenuCount() {
		return menuCount;
	}

	public void setMenuCount(String menuCount) {
		this.menuCount = menuCount;
	}

	@Override
	public String toString() {
		return "SalesDetailVO [salesDetailID=" + salesDetailID + ", salesDetailRegDate=" + salesDetailRegDate
				+ ", salesID=" + salesID + ", menuID=" + menuID + ", menuName=" + menuName + ", menuPrice=" + menuPrice
				+ ", menuCount=" + menuCount + "]";
	}
	
	
	
	

}
