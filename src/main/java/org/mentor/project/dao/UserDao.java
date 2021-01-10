package org.mentor.project.dao;

import org.mentor.project.model.User;

import java.util.List;

public interface UserDao {

    void save(User user);
    List<User> getAll();
    User findById(Long id);
    User findByName(String name);
    void delete(Long id);
}
