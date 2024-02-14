package com.pi.ati.ort.back.utils;

import org.bimserver.interfaces.objects.SUser;

public class ServerUser {

    private SUser user;

    public ServerUser() {
    }

    public ServerUser(SUser user) {
        this.user = user;
    }

    public SUser getUser() {
        return user;
    }

    public void setUser(SUser user) {
        this.user = user;
    }
}
