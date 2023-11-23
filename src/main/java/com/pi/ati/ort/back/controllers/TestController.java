package com.pi.ati.ort.back.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.bimserver.emf.IfcModelInterface;
import org.bimserver.models.ifc4.IfcWallStandardCase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.bimserver.client.BimServerClient;
import org.bimserver.client.json.JsonBimServerClientFactory;
import org.bimserver.interfaces.objects.SProject;

import org.bimserver.shared.UsernamePasswordAuthenticationInfo;
import org.bimserver.shared.interfaces.ServiceInterface;

import java.util.List;

@Controller
public class TestController {

    //Docs TEST CONNECTION TO BIM SERVER
    @Operation(summary = "Test connection to BIM server")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Connection OK between back and BIM server",
                    content = { @Content(mediaType = "String",
                            schema = @Schema(implementation = String.class))}),
    })
    @GetMapping("/test")
    @ResponseBody
    public String test() {
                                                                    // Direccion Elastic IP de AWS: "http://34.233.151.86:8082"
        try (JsonBimServerClientFactory factory = new JsonBimServerClientFactory("http://localhost:8082")) {

            try (BimServerClient client = factory.create(new UsernamePasswordAuthenticationInfo("admin@admin.com", "1234"))) {

                ServiceInterface serviceInterface = client.getServiceInterface();

                //Display all the projects
                List existingProjects = serviceInterface.getAllProjects(true, true);

                // Display the number of projects in the BIM server
                System.out.println("El BIM server tiene " + existingProjects.size() + " proyectos");

                SProject testProject = null;
                testProject = (SProject) existingProjects.get(1);

                IfcModelInterface model = client.getModel(testProject, testProject.getLastRevisionId(), false, true, true);
                List ifcWallStandardCases = model.getAllWithSubTypes(IfcWallStandardCase.class);

                // Display the number of walls in the project
                System.out.println("El modelo IFC tiene " + ifcWallStandardCases.size() + " paredes");

                // Return a response string indicating successful connection
                return "Connected to BIM server. Mostrando en consola la conexion de front a back";

            } catch (Exception e) {
                // Handle exceptions related to BIM server operations
                e.printStackTrace();
                return "Failed to perform BIM server operations. Check logs for details.";
            }

        } catch (Exception e1) {
            // Handle exceptions related to creating the BIM server client
            e1.printStackTrace();
            return "Failed to connect to BIM server. Check logs for details.";
        }
    }
}
