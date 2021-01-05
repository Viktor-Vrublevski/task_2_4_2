package org.mentor.project.service;

import org.mentor.project.model.Role;

import java.util.List;

public interface RoleService {
    Role getRole(int id);
    List<Role> getAllRoles();
    void create();
}
