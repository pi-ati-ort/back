package com.pi.ati.ort.back.classes;
import com.pi.ati.ort.back.entities.Model;
import com.pi.ati.ort.back.entities.User;
import com.pi.ati.ort.back.services.UserService;
import org.bimserver.client.BimServerClient;
import org.bimserver.client.json.JsonBimServerClientFactory;
import org.bimserver.interfaces.objects.SProject;
import org.bimserver.interfaces.objects.SUser;
import org.bimserver.interfaces.objects.SUserType;
import org.bimserver.shared.ChannelConnectionException;
import org.bimserver.shared.UsernamePasswordAuthenticationInfo;
import org.bimserver.shared.exceptions.BimServerClientException;
import org.bimserver.shared.exceptions.ServiceException;
import org.bimserver.shared.interfaces.AuthInterface;
import org.bimserver.shared.interfaces.LowLevelInterface;
import org.bimserver.shared.interfaces.ServiceInterface;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import java.util.List;

@Service
public class BimClient {

    BimServerClient client = new JsonBimServerClientFactory("http://localhost:8082").create(new UsernamePasswordAuthenticationInfo("admin@admin.com", "1234"));
    AuthInterface authInterface = client.getAuthInterface();
    ServiceInterface serviceInterface = client.getServiceInterface();
    LowLevelInterface lowLevelInterface = client.getLowLevelInterface();

    public BimClient() throws ServiceException, ChannelConnectionException, BimServerClientException {
    }

    public BimClient(BimServerClient client, AuthInterface authInterface, ServiceInterface serviceInterface, LowLevelInterface lowLevelInterface) throws ServiceException, ChannelConnectionException, BimServerClientException {
        this.client = client;
        this.authInterface = authInterface;
        this.serviceInterface = serviceInterface;
        this.lowLevelInterface = lowLevelInterface;
    }

    public BimServerClient getClient() {
        return client;
    }

    public AuthInterface getAuthInterface() {
        return authInterface;
    }

    public ServiceInterface getServiceInterface() {
        return serviceInterface;
    }

    public LowLevelInterface getLowLevelInterface() {
        return lowLevelInterface;
    }


    // --------------------------------------------------------------------------------------------
    //AUTH INTERFACE METHODS
    //login
    public LoginResponse login(String username, String password) throws ServiceException {
        String token = authInterface.login(username, password);
        if (token != null) {
            return new LoginResponse(token);
        }
        return null;
    }

    //logout
    public void logout() throws ServiceException {
        authInterface.logout();
    }

    //logged user
    public SUser getLoggedUser() throws ServiceException {
        return authInterface.getLoggedInUser();
    }

    // --------------------------------------------------------------------------------------------
    //SERVICE INTERFACE METHODS
    //register an admin
    public void registerAdmin(String username, String password, String name) throws ServiceException {
        serviceInterface.addUserWithPassword(username, password, name, SUserType.ADMIN, true, "");
    }

    //register a user
    public void registerUser(String username, String password, String name) throws ServiceException {
        serviceInterface.addUserWithPassword(username, password, name, SUserType.USER, true, "");
    }

    //create a project
    public SProject createProject(String projectName, String schema) throws ServiceException {
        return serviceInterface.addProject(projectName, schema);
    }

    //delete a project by id
    public void deleteProject(long poid) throws ServiceException {
        serviceInterface.deleteProject(poid);
    }

    //get a project by id
    public void getProject(long poid) throws ServiceException {
        serviceInterface.getProjectByPoid(poid);
    }

    //get a project by name
    public SProject getProjectByName(String projectName) throws ServiceException {
        return serviceInterface.getTopLevelProjectByName(projectName);
    }

    //get all projects by user
    public List<SProject> getAllProjectsByUser(boolean onlyTopLevel, boolean onlyActive ) throws ServiceException {
        return serviceInterface.getAllProjects(onlyTopLevel, onlyActive);
    }

    //upload a model to a project
    public void uploadModelToProject(Long poid, String comment, Long deserializer, Model file) throws ServiceException {
        DataHandler dataHandler = new DataHandler(file.getFile(), "application/octet-stream");
        serviceInterface.checkinSync(poid, comment, deserializer, (Long)file.getSize(), file.getFilename(), dataHandler, false);
    }

    // --------------------------------------------------------------------------------------------
    //LOW LEVEL INTERFACE METHODS
    // to be done
}
