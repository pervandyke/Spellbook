package com.vandyke.demoproject.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vandyke.demoproject.exceptions.UserAlreadyExistsException;
import com.vandyke.demoproject.exceptions.UserNotFoundException;
import com.vandyke.demoproject.model.Login;
import com.vandyke.demoproject.model.ResponseString;
import com.vandyke.demoproject.model.User;

@Repository
public class UserDao {
    @Autowired
    SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public User findUserByUsername(String username) throws UserNotFoundException{
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("FROM User WHERE username = :providedUsername");
            query.setParameter("providedUsername", username);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            throw new UserNotFoundException("User with username " + username + " not found.");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Transactional(readOnly = true)
    public User findUserById(Long userId) throws UserNotFoundException {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("FROM User WHERE id = :providedId");
            query.setParameter("providedId", userId);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            throw new UserNotFoundException("User with id " + userId + " not found.");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Transactional
    public ResponseString createUser(Login userData) throws UserAlreadyExistsException {

        User user = new User();
        user.setUsername(userData.getUsername());
        user.setPassword(userData.getPassword());

        Session session = sessionFactory.openSession();
        try {
            findUserByUsername(user.getUsername());
            throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists");
        } catch (UserNotFoundException e) {
            session.saveOrUpdate(user);
        } finally {
            session.close();
        }
        
        return new ResponseString("User " + user.getUsername() + " created.");
    }
}
