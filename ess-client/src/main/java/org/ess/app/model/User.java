package org.ess.app.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class User {

    private long id;

    private String email;

    private String password;

}
