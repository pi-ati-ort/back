package com.pi.ati.ort.back.repositories;

import com.pi.ati.ort.back.entities.Project;
import com.pi.ati.ort.back.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    public Project findByName(String name);

    public List<Project> findAll();

    public Optional<Project> findById(Long id);

    public Project save(Project project);

    public void deleteById(Long id);

    public List<Project> findAllByUser(String username);
}