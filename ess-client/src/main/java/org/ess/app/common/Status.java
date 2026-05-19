package org.ess.app.common;

public enum Status {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String name;

     private Status(String name){
         this.name = name;
     }

    @Override
    public String toString() {
        return this.name;
    }
}
