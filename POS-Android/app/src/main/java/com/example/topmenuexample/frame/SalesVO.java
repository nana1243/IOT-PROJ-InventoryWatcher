package com.example.topmenuexample.frame;

import java.io.Serializable;

public class SalesVO implements Serializable {

	String salesID;
	String salesRegDate;
	int totSales;
	String chainID;

	String dailySales;
	String revenue;

	public SalesVO() {
		super();
	}

	public SalesVO(String salesID, String salesRegDate, int totSales, String chainID) {
		super();
		this.salesID = salesID;
		this.salesRegDate = salesRegDate;
		this.totSales = totSales;
		this.chainID = chainID;
	}

	public SalesVO(String dailySales, String revenue) {
		this.dailySales = dailySales;
		this.revenue = revenue;

	}

	public String getDailySales() {
		return dailySales;
	}

	public void setDailySales(String dailySales) {
		this.dailySales = dailySales;
	}

	public String getRevenue() {
		return revenue;
	}

	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}

	public String getSalesID() {
		return salesID;
	}

	public void setSalesID(String salesID) {
		this.salesID = salesID;
	}

	public String getSalesRegDate() {
		return salesRegDate;
	}

	public void setSalesRegDate(String salesRegDate) {
		this.salesRegDate = salesRegDate;
	}

	public int getTotSales() {
		return totSales;
	}

	public void setTotSales(int totSales) {
		this.totSales = totSales;
	}

	public String getChainID() {
		return chainID;
	}

	public void setChainID(String chainID) {
		this.chainID = chainID;
	}


//	@Override
//	public String toString() {
//		return "SalesVO [salesID=" + salesID + ", salesRegDate=" + salesRegDate + ", totSales=" + totSales
//				+ ", chainID=" + chainID + "]";
//	}

	@Override
	public String toString() {
		return "SalesVO [salesID=" + salesID + ", salesRegDate=" + salesRegDate + ", totSales=" + totSales
				+ ", chainID=" + chainID + ", dailySales=" + dailySales + ", revenue=" + revenue + "]";
	}

//	public String toString(String dailySales, String revenue) {
//		return "SalesVO [dailySales=" + dailySales + ", revenue=" + revenue + "]";
//
//	}
//
//	public String toString2(String dailySales, String revenue) {
//		return "SalesVO [dailySales=" + dailySales + ", revenue=" + revenue + "]";
//
//	}

}
