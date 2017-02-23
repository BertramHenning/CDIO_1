package dto;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Packet imports
import validation.IException.InpExc;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 4545864587995944260L;
	private int	userId;                     
	private String userName, cpr, password, ini;                              
	private List<String> roles;
	
	public UserDTO() {
		this.roles = new ArrayList<>();
	}
	
	
	//Id operations
	public int getUserId(){
		return userId;
	}
	
	public void setUserId(int userId){
		this.userId = userId;
	}
	
	public boolean checkUserId(int uderId) throws InpExc{
		
		return false;
	}
	
	
	//Name operations
	public String getUserName(){
		return userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public boolean checkUserName(String userName) throws InpExc{
		
		return false;
	}
	
	
	//Ini operations
	public String getIni(){
		return ini;
	}
	
	public void setIni(String ini) {
		this.ini = ini;
	}
	
	public boolean checkIni(String ini) throws InpExc{
		
		return true;
	}

	
	//Role operations
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public void addRole(String role){
		this.roles.add(role);
	}
	/**
	 * 
	 * @param role
	 * @return true if role existed, false if not
	 */
	public boolean removeRole(String role){
		return this.roles.remove(role);
	}

	
	@Override
	public String toString() {
		return "\nUserDTO [userId=" + userId + ", userName=" + userName + ", ini=" + ini + ", roles=" + roles.toString() + "]";
	}


	public String getCpr() {
		return cpr;
	}


	public void setCpr(String cpr) {
		this.cpr = cpr;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
