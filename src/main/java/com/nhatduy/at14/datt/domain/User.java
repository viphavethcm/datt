package com.nhatduy.at14.datt.domain;


import com.nhatduy.at14.datt.domain.Role;
import com.nhatduy.at14.datt.utils.UserIdPrefixed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "user_id",updatable = false,nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_gen")
    @GenericGenerator(
            name = "user_gen",
            strategy = "com.seneca.senecaforum.service.com.nhatduy.at14.datt.utils.UserIdPrefixed",
            parameters = {
                    @Parameter(name = UserIdPrefixed.INCREMENT_PARAM,value = "1"),
                    @Parameter(name = UserIdPrefixed.CODE_NUMBER_SEPARATOR_PARAMETER,value = "_"),
                    @Parameter(name = UserIdPrefixed.NUMBER_FORMAT_PARAMETER,value = "%03d")
            }
    )
    private String userId;

    @Column(name = "email",length = 50,unique = true)
    private String email;


    @Column(name = "username",length = 50,unique = true)
    private String username;

    @Column(name = "password",length = 68,nullable = false)
    private String password;

    @Column(name="created_on",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdOn;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name="role_id",
            nullable = false,
            referencedColumnName="role_id",
            foreignKey=@ForeignKey(name = "FK_ROLE_USER")
    )
    private Role role;

    @Override
    public int hashCode() {
        int prime = 31;
        return prime+ ((userId==null)?0:prime+userId.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if(userId.getClass()==obj.getClass()){
            return true;
        }
        User userEntity = (User) obj;
        if(!this.userId.equals(userEntity.getUserId())){
            return false;
        }
        return true;
    }
}