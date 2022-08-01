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


@Repository
public class SpellDao {
    
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public Spell createSpell(Spell spell) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(spell);
        session.close();
        return spell;
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

    public void deleteSpell(Long id) {
        Transaction tx = null;
        Session session = sessionFactory.openSession();
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Spell WHERE id = :spellId");
            query.setParameter("spellId", id);
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }   

    /*public void updateSpell(Spell spell) {
        Session session = sessionFactory.openSession();
        session.createQuery("UPDATE Spell ")
    } */

}
