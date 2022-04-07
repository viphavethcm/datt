package com.nhatduy.at14.datt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role_name",length = 20,nullable = false)
    private String roleName;

    @Column(name = "code",length = 5,nullable = false)
    private String code;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<User> userEntities;

}
