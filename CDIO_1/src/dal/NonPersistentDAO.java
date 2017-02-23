package dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.UserDTO;

public class NonPersistentDAO implements IUserDAO {

	private Map<Integer, UserDTO> users = new HashMap<Integer, UserDTO>();

	private List<UserDTO> liste = new ArrayList<UserDTO>();

	@Override
	public UserDTO getUser(int userId) throws DALException {
		UserDTO user = users.get(userId);
		if (user.equals(null)) {
			throw new DALException("User findes ikke");
		} else {
			return user;
		}
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		return new ArrayList<UserDTO>(users.values());
	}

	@Override
	public void createUser(UserDTO user) throws DALException {
		if (this.users.containsKey(user.getUserId())) {
			throw new DALException("Id allered taget");
		}
		this.users.put(user.getUserId(), user);
	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		getUser(user.getUserId());
		users.put(user.getUserId(), user);
	}

	@Override
	public void deleteUser(int userId) throws DALException {
		if (!users.containsKey(userId)) {
			throw new DALException("User findes ikke");
		}
		users.remove(userId);
	}

}
