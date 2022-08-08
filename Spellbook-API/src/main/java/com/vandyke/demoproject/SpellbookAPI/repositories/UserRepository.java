package com.vandyke.demoproject.SpellbookAPI.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vandyke.demoproject.SpellbookAPI.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
}
