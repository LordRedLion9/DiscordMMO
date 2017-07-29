package mmoServer;

public class User {

	private String name;
	private String ID;
	private boolean loggedIn;
	
	public User(String name, String ID){
		
		this.name = name;
		this.ID = ID;
		
	}
	
	public void setLoginStatus(boolean loginStatus){
		loggedIn = loginStatus;
	}
	
	public boolean getLoginStatus(){
		return loggedIn;
	}
	
	public String getID(){
		return ID;
	}
	
	public String getName(){
		return name;
	}
	
}
