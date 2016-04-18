package com.jjm.triphelper.controller.exceptions;

public class TripNotFoundException extends RuntimeException {

    private String id;

    public TripNotFoundException(String id) {
        super("We did not found any trip with the id " + id);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}