package top.vo;

public class ChainVO {

	String chainID;
	String chainName;
	String chainAddress;
	String chainRegDate;
	String refresh;
	String hqID;
	String hqName;

	public ChainVO() {

	}

	public ChainVO(String chainID, String chainName, String chainAddress, String chainRegDate, String refresh,
			String hqID, String hqName) {
		super();
		this.chainID = chainID;
		this.chainName = chainName;
		this.chainAddress = chainAddress;
		this.chainRegDate = chainRegDate;
		this.refresh = refresh;
		this.hqID = hqID;
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

	public String getChainAddress() {
		return chainAddress;
	}

	public void setChainAddress(String chainAddress) {
		this.chainAddress = chainAddress;
	}

	public String getChainRegDate() {
		return chainRegDate;
	}

	public void setChainRegDate(String chainRegDate) {
		this.chainRegDate = chainRegDate;
	}

	public String getRefresh() {
		return refresh;
	}

	public void setRefresh(String refresh) {
		this.refresh = refresh;
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

	@Override
	public String toString() {
		return "ChainVO [chainID=" + chainID + ", chainName=" + chainName + ", chainAddress=" + chainAddress
				+ ", chainRegDate=" + chainRegDate + ", refresh=" + refresh + ", hqID=" + hqID + ", hqName=" + hqName
				+ "]";
	}

}
