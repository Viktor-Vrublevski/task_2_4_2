package org.mentor.project.dao;


import org.mentor.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    private EntityManagerFactory factory;

    @Autowired
    public void setFactory(EntityManagerFactory factory) {
        this.factory = factory;
    }


    @Override
    public void save(User user) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(manager.contains(user) ? user : manager.merge(user));
        manager.getTransaction().commit();
    }

    @Override
    public List<User> getAll() {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        List<User> list =  manager
                .createNativeQuery("SELECT * FROM persons",User.class).getResultList();
        manager.getTransaction().commit();
        return list;
    }

    @Override
    public User findById(Long id) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        User user = manager.find(User.class,id);
        manager.getTransaction().commit();
        return user;
    }

    @Override
    public User findByName(String username) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query query = manager
                .createQuery("From User where username=?1");
        query.setParameter(1,username);
        User user = (User) query.getSingleResult();
        manager.getTransaction().commit();
        return user;
    }


    @Override
    public void delete(Long id) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        User user = manager.find(User.class,id);
        manager.remove(user);
        manager.getTransaction().commit();
    }
}

