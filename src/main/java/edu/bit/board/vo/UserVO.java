package edu.bit.board.vo;

public class UserVO {
	private String userName;
	private String password;
	private char enabled;
	
	public UserVO () { }
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public char getEnabled() {
		return enabled;
	}
	public void setEnabled(char enabled) {
		this.enabled = enabled;
	}
}
