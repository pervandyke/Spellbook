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

import com.vandyke.demoproject.exceptions.SpellNotFoundException;
import com.vandyke.demoproject.model.Spell;
import com.vandyke.demoproject.model.User;


@Repository
public class SpellDao {
    
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public String createSpell(Spell spell) {
        Session session = sessionFactory.openSession();
        session.save(spell);
        session.close();
        return "Spell " + spell.getName() + " was created.";
    }

    public String editSpell(Spell spell) {
        Transaction tx = null;
        Session session = sessionFactory.openSession();

        String response = "";

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("UPDATE Spell SET level= :level, name= :name, castingtime= :castingTime, range= :range, damageAmount= :damageAmount, damageType= :damageType, components= :components, duration= :duration, save= :save, description= :description WHERE id = :spellId");
            query.setParameter("spellId", spell.getId());
            query.setParameter("level", spell.getLevel());
            query.setParameter("name", spell.getName());
            query.setParameter("castingTime", spell.getCastingTime());
            query.setParameter("range", spell.getRange());
            query.setParameter("damageAmount", spell.getDamageAmount());
            query.setParameter("damageType", spell.getDamageType());
            query.setParameter("components", spell.getComponents());
            query.setParameter("duration", spell.getDuration());
            query.setParameter("save", spell.getSave());
            query.setParameter("description", spell.getDescription());
            query.executeUpdate();
            tx.commit();
            response = "Spell " + spell.getName() + " was updated.";
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            response = "Error occured while trying to edit spell " + spell.getId() + ".";
        } finally {
            session.close();
        }
        return response;
    }

    @Transactional(readOnly = true)
    public Spell findSpellById(Long id) throws SpellNotFoundException {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Spell WHERE id = :spellId");
        query.setParameter("spellId", id);

        try {
            return (Spell) query.getSingleResult();
        } catch (NoResultException e) {
            throw new SpellNotFoundException("Spell with id " + id + " not found;");
        } finally {
            session.close();
        }

    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Spell> findUserSpells(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Spell WHERE userId = :userId");
        query.setParameter("userId", id);

        try {
            return query.getResultList();
        } finally {
            session.close();
        }

    }

    @Transactional(readOnly = true)
    public List<Spell> findUserSpellsWithCriteria(User user) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Spell> criteria = builder.createQuery(Spell.class);
        Root<Spell> root = criteria.from(Spell.class);
        
        criteria.select(root);
        criteria.where(builder.equal(root.get("user"), user));
        List<Spell> spells = session.createQuery(criteria).getResultList();
        return spells;
    }

    @Transactional(readOnly = true)
    public List<Spell> findAllSpells() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Spell> criteria = builder.createQuery(Spell.class);
        Root<Spell> rootEntry = criteria.from(Spell.class);
        CriteriaQuery<Spell> all = criteria.select(rootEntry);

        TypedQuery<Spell> allQuery = session.createQuery(all);
        List<Spell> spells = allQuery.getResultList();
        session.close();
        return spells;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Spell> findAllSpellsWithHQL() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Spell");
        List<Spell> results = query.getResultList();
        session.close();
        return results;
    }

    public String deleteSpell(Long id) {
        Transaction tx = null;
        Session session = sessionFactory.openSession();

        String response = "";

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Spell WHERE id = :spellId");
            query.setParameter("spellId", id);
            query.executeUpdate();
            tx.commit();
            response = "Spell " + id + " successfully deleted.";
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            response = "Error occured while trying to delete spell " + id + ".";
        } finally {
            session.close();
        }
        return response;
    }

}
