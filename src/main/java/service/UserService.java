package main.java.service;

import main.java.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 22.08.2017.
 */
@Service
public class UserService implements IUserService {

    List<User> users = new ArrayList<>();

    public UserService() {
        init();
    }

    private void init() {
        users.add(new User(1L, "Vlad"));
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void updateUser(User user) {
        users.forEach(
                us -> {
                    if (us.getId().longValue() == user.getId().longValue()) {
                        int index = users.indexOf(us);
                        users.set(index, user);
                    }
                }
        );
    }

    @Override
    public void deleteUser(Long id) {
        users.forEach(
                us -> {
                    if (us.getId().longValue() == id.longValue())
                        users.remove(id);
                }
        );
    }

    @Override
    public void addUser(User user) {
        user.setId((long) users.size());
        users.add(user);
    }
}
