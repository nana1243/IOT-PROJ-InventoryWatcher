package top.vo;

public class UserVO {

	String userID;
	String userName;
	String userPwd;
	String userEmail;
	String userPhone;
	String userRegDate;
	String chainID;
	String chainName;
	
	
	public UserVO() {
		super();
	}


	public UserVO(String userID, String userName, String userPwd, String userEmail, String userPhone,
			String userRegDate, String chainID, String chainName) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userRegDate = userRegDate;
		this.chainID = chainID;
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


	public String getUserPwd() {
		return userPwd;
	}


	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserPhone() {
		return userPhone;
	}


	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}


	public String getUserRegDate() {
		return userRegDate;
	}


	public void setUserRegDate(String userRegDate) {
		this.userRegDate = userRegDate;
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


	@Override
	public String toString() {
		return "UserVO [userID=" + userID + ", userName=" + userName + ", userPwd=" + userPwd + ", userEmail="
				+ userEmail + ", userPhone=" + userPhone + ", userRegDate=" + userRegDate + ", chainID=" + chainID
				+ ", chainName=" + chainName + "]";
	}
	
	
	
	


}
