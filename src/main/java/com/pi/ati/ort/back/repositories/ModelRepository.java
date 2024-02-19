package com.pi.ati.ort.back.repositories;

import com.pi.ati.ort.back.entities.Model;
import com.pi.ati.ort.back.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {
    public List<Model> findAll();

    public Optional<Model> findById(Long id);

    public Model save(Model model);

    public void deleteById(Long id);

    public List<Model> findAllByUsername(String username);

}