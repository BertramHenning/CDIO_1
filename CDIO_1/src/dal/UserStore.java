package dal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import dto.UserDTO;

public class UserStore implements Serializable {
	private static final long serialVersionUID = -9162958810235685263L;
	private Map<Integer, UserDTO> users = new HashMap<Integer, UserDTO>();
	
	public boolean addUser(UserDTO user)  {
        if (this.users.containsKey(user.getUserId())) {
            return false;
        }
        this.users.put(user.getUserId(), user);
        return true;
    }

	public Map<Integer, UserDTO> getUsers() {
		return users;
	}

	public void setUsers(Map<Integer, UserDTO> users) {
		this.users = users;
	}
	
	 public UserDTO getUser(int userID) {
	        return this.users.get(userID);
	    }

}
