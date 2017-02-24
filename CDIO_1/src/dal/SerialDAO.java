package dal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dal.IUserDAO.DALException;
import dto.UserDTO;

public class SerialDAO implements IUserDAO {

	@Override
	public UserDTO getUser(int userId) throws DALException {
        UserDTO user = loadUsers().getUsers().get(userId);
        if (user == null) {
            throw new IUserDAO.DALException("Ingen user med den id");
        }
        return user;
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		UserStore users = this.loadUsers();
        if (users == null) {
            throw new IUserDAO.DALException("Ingen gemte users");
        }
        return new ArrayList<UserDTO>(users.getUsers().values());
	}

	@Override
	public void createUser(UserDTO user) throws DALException {
		UserStore users = this.loadUsers();
		if(users.addUser(user)){
			saveUsers(users);
		} else {
			throw new IUserDAO.DALException("Der er allerede en user med den id");
		}
        

	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		UserStore userStore = loadUsers();
		UserDTO oldUser = getUser(user.getUserId());
		if (oldUser != null){
			Map<Integer, UserDTO> users = userStore.getUsers();
	        users.put(user.getUserId(), user);
	        userStore.setUsers(users);
	        saveUsers(userStore);
		} else {
			throw new DALException("User findes ikke");
		}

	}

	@Override
	public void deleteUser(int userId) throws DALException {
		UserStore userStore = loadUsers();
        Map<Integer, UserDTO> users = userStore.getUsers();
        if (!users.containsKey(userId)) {
            throw new IUserDAO.DALException("User findes ikke");
        }
        users.remove(userId);
        userStore.setUsers(users);
        this.saveUsers(userStore);

	}
	
	private UserStore loadUsers() throws DALException {
		UserStore userStore = new UserStore();
		ObjectInputStream oIS = null;
		try {
			FileInputStream fIS = new FileInputStream("users.data");
			oIS = new ObjectInputStream(fIS);
			Object inObj = oIS.readObject();
			if (inObj instanceof UserStore){
				userStore = (UserStore) inObj;
			} else {
				throw new DALException("Wrong object in file");
			}
		} catch (FileNotFoundException e) {
			//No problem - just returning empty userstore
		} catch (IOException e) {
			throw new DALException("Error while reading disk!", e);
		} catch (ClassNotFoundException e) {
			throw new DALException("Error while reading file - Class not found!", e);
		} finally {
			if (oIS!=null){
				try {
					oIS.close();
				} catch (IOException e) {
					throw new DALException("Error closing pObjectStream!", e);
				}
			}
		}
		return userStore;
	}
	
	public void saveUsers(UserStore users) throws DALException {
		ObjectOutputStream oOS =null;
		try {
			FileOutputStream fOS = new FileOutputStream("users.data");
			oOS = new ObjectOutputStream(fOS);
			oOS.writeObject(users);
		} catch (FileNotFoundException e) {
			throw new DALException("Error locating file", e);
		} catch (IOException e) {
				throw new DALException("Error writing to disk", e);
		} finally {
			if (oOS!=null) {
				try {
					oOS.close();
				} catch (IOException e) {
					throw new DALException("Unable to close ObjectStream", e);
				}
			}
		}	
	}

}
