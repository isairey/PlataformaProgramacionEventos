package org.ess.app.common;

public enum Role {

    ADMIN("ADMIN"),
    STAFF("STAFF");

    private final String name;

     private Role(String name){
         this.name = name;
     }

    @Override
    public String toString() {
        return this.name;
    }
}
