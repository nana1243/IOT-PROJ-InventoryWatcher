package top.vo;

public class NotiVO {
	
	String chainid;
	String userid;
	Integer applycnt;
	
	public NotiVO() {
		super();
	}

	public NotiVO(String chainid, String userid, Integer applycnt) {
		super();
		this.chainid = chainid;
		this.userid = userid;
		this.applycnt = applycnt;
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

	public Integer getApplycnt() {
		return applycnt;
	}

	public void setApplycnt(Integer applycnt) {
		this.applycnt = applycnt;
	}

	@Override
	public String toString() {
		return "NotiVO [chainid=" + chainid + ", userid=" + userid + ", applycnt=" + applycnt + "]";
	}
	
	
	
	
	

}
