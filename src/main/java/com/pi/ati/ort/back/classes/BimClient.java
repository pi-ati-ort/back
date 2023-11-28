package com.pi.ati.ort.back.classes;

import org.bimserver.client.BimServerClient;
import org.bimserver.client.json.JsonBimServerClientFactory;
import org.bimserver.interfaces.objects.SProject;
import org.bimserver.interfaces.objects.SUserType;
import org.bimserver.shared.ChannelConnectionException;
import org.bimserver.shared.UsernamePasswordAuthenticationInfo;
import org.bimserver.shared.exceptions.BimServerClientException;
import org.bimserver.shared.exceptions.ServiceException;
import org.bimserver.shared.interfaces.ServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class BimClient {

    BimServerClient client = new JsonBimServerClientFactory("http://localhost:8082").create(new UsernamePasswordAuthenticationInfo("admin@admin.com", "1234"));
    ServiceInterface serviceInterface = client.getServiceInterface();

    public BimClient() throws ServiceException, ChannelConnectionException, BimServerClientException {
    }

    public BimClient(BimServerClient client, ServiceInterface serviceInterface) throws ServiceException, ChannelConnectionException, BimServerClientException {
        this.client = client;
        this.serviceInterface = serviceInterface;
    }

    public BimServerClient getClient() {
        return client;
    }

    public ServiceInterface getServiceInterface() {
        return serviceInterface;
    }

    //register a user
    public void registerUser(String username, String password, String name) throws ServiceException {
        serviceInterface.addUserWithPassword(username, password, name, SUserType.USER, true, "");
    }

    //register an admin
    public void registerAdmin(String username, String password, String name) throws ServiceException {
        serviceInterface.addUserWithPassword(username, password, name, SUserType.ADMIN, true, "");
    }

    //create a project
    public SProject createProject(String projectName, String schema) throws ServiceException {
        return serviceInterface.addProject(projectName, schema);
    }

    //delete a project
    public void deleteProject(long poid) throws ServiceException {
        serviceInterface.deleteProject(poid);
    }

    //get a project
    public SProject getProject(long poid) throws ServiceException {
        return serviceInterface.getProjectByPoid(poid);
    }

    //get a project by name
    public SProject getProjectByName(String projectName) throws ServiceException {
        return serviceInterface.getTopLevelProjectByName(projectName);
    }
}
