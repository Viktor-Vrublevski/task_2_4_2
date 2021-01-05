package org.mentor.project.dao;

import org.mentor.project.model.Role;

import java.util.List;

public interface RoleDao {
    Role getRole(int id);
    List<Role> getAllRoles();
    void create();
}
