package com.pvmeinster.users.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
//import javax.persistence.jar.javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USERS", uniqueConstraints={
        @UniqueConstraint( name = "email",  columnNames ={"email"})
})
@SQLDelete(sql = "UPDATE USERS " + "SET DELETED = 1 " + "WHERE id = ?")
@Where(clause = "DELETED = 0")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 3343003280979161263L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "DATE_ENTERED")
    private LocalDateTime dateCreated;

    @Column(name = "DELETED")
    private Integer deleted;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

}
