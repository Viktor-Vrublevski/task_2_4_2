package org.mentor.project.dao;

import org.mentor.project.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{

    private EntityManagerFactory factory;

    @Autowired
    public void setFactory(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Role getRole(int id) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Role role = manager.find(Role.class, id);
        manager.getTransaction().commit();
        return role;
    }

    @Override
    public List<Role> getAllRoles() {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        List<Role> list = manager
                .createNativeQuery("SELECT * FROM roles",Role.class).getResultList();
        manager.getTransaction().commit();
        return list;
    }

    @Override
    public void create() {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery("INSERT INTO roles(role) VALUES " +
                "('ROLE_ADMIN'), ('ROLE_USER')");
        query.executeUpdate();
        manager.getTransaction().commit();
    }
}
