package org.ess.app.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthModel {

    /**
     * The user id
     * <p>
     * <p>
     * -- GETTER --
     * Retrieve the user id number
     *
     * @field id {@link Integer}
     * @return {@link String}
     */
    private String idNumber;

    /**
     * The username
     * <p>
     * <p>
     * -- GETTER --
     * Retrieve the user name
     *
     * @field username {@link String}
     * @return {@link String}
     */
    private String username;

    /**
     * The user role
     * <p>
     * -- GETTER --
     * Retrieve the user role
     *
     * @field role {@link String}
     * @return {@link String}
     */
    private String role;

    /**
     * The user password
     * <p>
     * -- GETTER --
     * Retrieve the user password
     *
     * @field password {@link String}
     * @return {@link String}
     */
    private String password;

    /**
     * The default constructor
     */
    public AuthModel() {

        idNumber = "";
        username = "";
        role = "";
        password = "";
    }

    /**
     * The primary constructor
     *
     * @param id       {@link Integer}
     * @param role     {@link String}
     * @param password {@link String}
     */
    public AuthModel(String id, String username, String role, String password) {
        this.idNumber = id;
        this.username = username;
        this.role = role;
        this.password = password;
    }
}
