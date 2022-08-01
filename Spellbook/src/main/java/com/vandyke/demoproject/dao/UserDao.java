package com.vandyke.demoproject.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vandyke.demoproject.model.Login;
import com.vandyke.demoproject.model.ResponseString;
import com.vandyke.demoproject.model.User;

@Repository
public class UserDao {
    @Autowired
    SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
        Session session = sessionFactory.openSession();
        User result = null;
        try {
            Query query = session.createQuery("FROM User WHERE username = :providedUsername");
            query.setParameter("providedUsername", username);
            result = (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @Transactional
    public ResponseString createUser(Login userData) {

        User user = new User();
        user.setUsername(userData.getUsername());
        user.setPassword(userData.getPassword());

        Session session = sessionFactory.openSession();
        session.saveOrUpdate(user);
        session.close();
        return new ResponseString("User " + user.getUsername() + " created.");
    }
}
