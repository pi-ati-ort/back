package com.pi.ati.ort.back.controllers;

import com.pi.ati.ort.back.classes.BimClient;
import com.pi.ati.ort.back.classes.ProjectRequest;
import com.pi.ati.ort.back.entities.Project;
import com.pi.ati.ort.back.services.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.bimserver.interfaces.objects.SProject;
import org.bimserver.shared.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ProjectController {
    private final ProjectService projectService;
    private final BimClient bimClient;

    @Autowired
    public ProjectController(ProjectService projectService, BimClient bimClient) {
        this.projectService = projectService;
        this.bimClient = bimClient;
    }

    // GET ALL PROJECTS -----------------------------------------------------
    @Operation(summary = "Get all the projects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Project.class))}),
    })
    @GetMapping("/projects")
    public List<Project> getAllProjects() throws ServiceException {
        //List<SProject> projects = bimClient.getAllProjectsByUser(true, true);
        return projectService.findAllProjects();
    }

    // CREATE PROJECT -----------------------------------------------------
    @Operation(summary = "Create a new project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Project created Ok",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProjectRequest.class))}),
    })
    @PostMapping("/projects")
    public ResponseEntity<Project> createProject(@Valid @RequestBody ProjectRequest projectRequest) throws ServiceException {
        if (projectRequest.getUsername() == null || projectRequest.getSchema() == null || projectRequest.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Project newProject = Project.fromRequest(projectRequest);
        SProject bimProject = bimClient.createProject(newProject.getName(), newProject.getSchema());
        newProject.setPoid((int) bimProject.getOid());
        Project createdProject = projectService.createProject(newProject);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    // GET PROJECT BY ID -----------------------------------------------------
    @Operation(summary = "Get the project by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Project.class))}),
    })
    @GetMapping("/projects/id/{id}")
    public Optional<Project> getProjectById(@Parameter(description="The Project's id") @PathVariable long id) {
        return projectService.findProjectById(id);
    }

    // GET PROJECT BY NAME -----------------------------------------------------
    @Operation(summary = "Get the project by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Project.class))}),
    })
    @GetMapping("/projects/{name}")
    public Project getProjectByName(@Parameter(description="The Project's name") @PathVariable String name) throws ServiceException {
        SProject project = bimClient.getProjectByName(name);
        System.out.println(project.getDescription());
        return projectService.findProjectByName(name);
    }

    // DELETE PROJECT -----------------------------------------------------
    @Operation(summary = "Delete a project by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project deleted Ok",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class))}),
    })
    @DeleteMapping("/projects/id/{id}")
    public ResponseEntity<HttpStatus> deleteProject(@Parameter(description="The Project's id") @PathVariable long id) throws ServiceException {
        projectService.deleteProjectById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
