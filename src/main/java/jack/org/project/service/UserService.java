package jack.org.project.service;

import java.util.List;
import jack.org.project.model.User;

public interface UserService {
	
    public User findByUsername(String username);
    public List<User> getAllUser();
	public User findUser(long id);
	void update(User userForm, User user);
    void save(User user);
	public void checkout(User user);

}
