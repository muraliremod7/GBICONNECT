package com.brain.revanth.sampleapplication2.Model;

/**
 * Created by Hari Prahlad on 19-05-2016.
 */
public class ServicesCommonClass {
    private String id;
    private String serviceName;
    private String ownerName;
    private String serviceDesk;
    private String phone;
    private String pin;
    public ServicesCommonClass(){

    }
    public ServicesCommonClass(String id, String ownerName, String phone, String pin, String serviceDesk, String serviceName) {
        this.id = id;
        this.ownerName = ownerName;
        this.phone = phone;
        this.pin = pin;
        this.serviceDesk = serviceDesk;
        this.serviceName = serviceName;
    }
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getServiceDesk() {
        return serviceDesk;
    }

    public void setServiceDesk(String serviceDesk) {
        this.serviceDesk = serviceDesk;
    }


}
