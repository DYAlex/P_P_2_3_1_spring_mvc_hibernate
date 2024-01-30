package web.dao;

import web.models.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    User getUserById(Long id);
    void updateUser(User user);
    void removeUser(Long id);
    List<User> getAllUsers();
}
