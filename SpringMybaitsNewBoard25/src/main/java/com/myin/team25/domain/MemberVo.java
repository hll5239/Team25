package com.myin.team25.domain;

public class MemberVo {

	private int memberMidx;
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberSex;
	private long memberJumin;
	private String memberAddr;
	private String memberEmail;
	private String memberIp;
	private int Bidx;
	private String Writeday;
	private String Modifyday;
	private String useCookie;
	
	public String getUseCookie() {
		return useCookie;
	}

	public void setUseCookie(String useCookie) {
		this.useCookie = useCookie;
	}

	public int getMemberMidx() {
		return memberMidx;
	}

	public void setMemberMidx(int memberMidx) {
		this.memberMidx = memberMidx;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberSex() {
		return memberSex;
	}

	public void setMemberSex(String memberSex) {
		this.memberSex = memberSex;
	}

	public long getMemberJumin() {
		return memberJumin;
	}

	public void setMemberJumin(long memberJumin) {
		this.memberJumin = memberJumin;
	}

	public String getMemberAddr() {
		return memberAddr;
	}

	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberIp() {
		return memberIp;
	}

	public void setMemberIp(String memberIp) {
		this.memberIp = memberIp;
	}

	public int getBidx() {
		return Bidx;
	}

	public void setBidx(int bidx) {
		Bidx = bidx;
	}

	public String getWriteday() {
		return Writeday;
	}

	public void setWriteday(String writeday) {
		Writeday = writeday;
	}

	public String getModifyday() {
		return Modifyday;
	}

	public void setModifyday(String modifyday) {
		Modifyday = modifyday;
	}

}
