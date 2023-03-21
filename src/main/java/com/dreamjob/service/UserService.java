package com.dreamjob.service;

import com.dreamjob.model.User;
import net.jcip.annotations.ThreadSafe;

import java.util.Collection;
import java.util.Optional;

@ThreadSafe
public interface UserService {

    Optional<User> save(User user);

    Optional<User> findByEmailAndPassword(String email, String password);

    Collection<User> findAll();
}
