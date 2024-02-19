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

    public Project findProjectByName(String name) {
        return projectRepository.findByName(name);
    }

    public Optional<Project> findProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> findAllProjectsByUser(String username) {
        return projectRepository.findAllByUsername(username);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }


    public List<Project> findAllProjectsByUsername(String username) {
        return projectRepository.findAllByUsername(username);
    }
}
