package com.pi.ati.ort.back.services;

import com.pi.ati.ort.back.entities.Project;
import com.pi.ati.ort.back.entities.User;
import com.pi.ati.ort.back.repositories.ProjectRepository;
import com.pi.ati.ort.back.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project findByName(String name) {
        return projectRepository.findByName(name);
    }

    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project createUser(Project project) {
        return projectRepository.save(project);
    }


    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }


}
