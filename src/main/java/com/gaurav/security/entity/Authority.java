package com.gaurav.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;
    private String roleName;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_AUTHORITY", joinColumns = @JoinColumn(name = "ROLEID"),
            inverseJoinColumns = @JoinColumn(name = "USERNAME"))
    private List<User> users;

    public Authority(String roleName) {
        this.roleName = roleName;
    }
}
