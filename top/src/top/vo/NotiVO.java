package top.vo;

public class NotiVO {
	
	String notiId;
	String applycnt;
	String regDate;
	String chainid;
	String chainname;
	String userid;
	String username;
	String refresh;
	

	public NotiVO() {
	
	}


	public NotiVO(String notiId, String applycnt, String regDate, String chainid, String chainname, String userid,
			String username, String refresh) {
		
		this.notiId = notiId;
		this.applycnt = applycnt;
		this.regDate = regDate;
		this.chainid = chainid;
		this.chainname = chainname;
		this.userid = userid;
		this.username = username;
		this.refresh = refresh;
	}


	public String getNotiId() {
		return notiId;
	}


	public void setNotiId(String notiId) {
		this.notiId = notiId;
	}


	public String getApplycnt() {
		return applycnt;
	}


	public void setApplycnt(String applycnt) {
		this.applycnt = applycnt;
	}


	public String getRegDate() {
		return regDate;
	}


	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}


	public String getChainid() {
		return chainid;
	}


	public void setChainid(String chainid) {
		this.chainid = chainid;
	}


	public String getChainname() {
		return chainname;
	}


	public void setChainname(String chainname) {
		this.chainname = chainname;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getRefresh() {
		return refresh;
	}


	public void setRefresh(String refresh) {
		this.refresh = refresh;
	}


	@Override
	public String toString() {
		return "NotiVO [notiId=" + notiId + ", applycnt=" + applycnt + ", regDate=" + regDate + ", chainid=" + chainid
				+ ", chainname=" + chainname + ", userid=" + userid + ", username=" + username + ", refresh=" + refresh
				+ "]";
	}
	
	
	


	
	
}
