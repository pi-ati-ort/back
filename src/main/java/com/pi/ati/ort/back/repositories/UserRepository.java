package com.pi.ati.ort.back.repositories;

import com.pi.ati.ort.back.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);

    public List<User> findAll();

    public Optional<User> findById(Long id);

    public User save(User user);

    public void deleteById(Long id);
}
