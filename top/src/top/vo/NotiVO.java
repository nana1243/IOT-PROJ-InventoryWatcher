package top.vo;

public class NotiVO {
	
	String notiId;
	String chainid;
	String userid;
	String applycnt;
	String regDate;
	String refresh;
	

	public NotiVO() {
		super();
	}


	public NotiVO(String notiId, String chainid, String userid, String applycnt, String regDate, String refresh) {
		this.notiId = notiId;
		this.chainid = chainid;
		this.userid = userid;
		this.applycnt = applycnt;
		this.regDate = regDate;
		this.refresh = refresh;
	}


	public String getNotiId() {
		return notiId;
	}


	public void setNotiId(String notiId) {
		this.notiId = notiId;
	}


	public String getChainid() {
		return chainid;
	}


	public void setChainid(String chainid) {
		this.chainid = chainid;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
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


	public String getRefresh() {
		return refresh;
	}


	public void setRefresh(String refresh) {
		this.refresh = refresh;
	}


	@Override
	public String toString() {
		return "NotiVO [notiId=" + notiId + ", chainid=" + chainid + ", userid=" + userid + ", applycnt=" + applycnt
				+ ", regDate=" + regDate + ", refresh=" + refresh + "]";
	}

	
	
	
	
}
