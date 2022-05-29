package com.Soldat.MWS.repository;

import com.Soldat.MWS.entity.supporting_classes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
