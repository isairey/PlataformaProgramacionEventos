package org.ess.module.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private int id;

    private String username;

    private String firstName;

    private String lastName;

    private String role;

    private String dateOfBirth;

    private String createdAt;

    private String updatedAt;
}
