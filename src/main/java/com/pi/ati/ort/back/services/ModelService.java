package com.pi.ati.ort.back.services;

import com.pi.ati.ort.back.entities.Model;
import com.pi.ati.ort.back.entities.Project;
import com.pi.ati.ort.back.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public Optional<Model> findModelById(Long id) {
        return modelRepository.findById(id);
    }

    public List<Model> findAllModels() {
        return modelRepository.findAll();
    }

    public Model createModel(Model model) {
        return modelRepository.save(model);
    }

    public void deleteModelById(Long id) {
        modelRepository.deleteById(id);
    }

    public List<Model> findAllModelsByUsername(String username) {
        return modelRepository.findAllByUsername(username);
    }

}