package com.pi.ati.ort.back.controllers;

import com.pi.ati.ort.back.classes.TestRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.bimserver.emf.IfcModelInterface;
import org.bimserver.models.ifc4.IfcWallStandardCase;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @Operation(summary = "Test BIM server connection via web with hardcoded credentials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Connection OK between back and BIM server",
                    content = { @Content(mediaType = "String",
                            schema = @Schema(implementation = String.class))}),
    })
    @GetMapping("/test-web")
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
                e.printStackTrace();
                return "Failed to perform BIM server operations. Check logs for details.";
            }

        } catch (Exception e1) {
            e1.printStackTrace();
            return "Failed to connect to BIM server. Check logs for details.";
        }
    }

    //Docs TEST CONNECTION TO BIM SERVER using TestRequest and PostMapping
    @Operation(summary = "Test BIM server connection via api using TestRequest")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Connection OK between back and BIM server",
                    content = { @Content(mediaType = "String",
                            schema = @Schema(implementation = String.class))}),
    })
    @PostMapping("/test-api")
    @ResponseBody
    public ResponseEntity<String> test(@RequestBody TestRequest testRequest) {
        try (JsonBimServerClientFactory factory = new JsonBimServerClientFactory(testRequest.getAdress())) {

            try (BimServerClient client = factory.create(new UsernamePasswordAuthenticationInfo(testRequest.getUsername(), testRequest.getPassword()))) {

                ServiceInterface serviceInterface = client.getServiceInterface();

                //Display all the projects
                List existingProjects = serviceInterface.getAllProjects(true, true);

                // Display the number of projects in the BIM server
                System.out.println("El BIM server tiene " + existingProjects.size() + " proyectos");

                // Return a response string indicating successful connection
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.TEXT_PLAIN);

                return new ResponseEntity<>("BIM connection between back and server working OK", headers, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("Failed to perform BIM server operations. Check logs for details.", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e1) {
            e1.printStackTrace();
            return new ResponseEntity<>("Failed to connect to BIM server. Check logs for details.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
