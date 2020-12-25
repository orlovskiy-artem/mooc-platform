package com.orlovsky.mooc_platform.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//        @Transient
//    @ManyToMany(mappedBy = "roles")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade =
                    {
                        CascadeType.DETACH,
                        CascadeType.MERGE,
                        CascadeType.REFRESH,
                        CascadeType.PERSIST
                    },
            targetEntity = User.class)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id",
                    nullable = false),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            uniqueConstraints = {@UniqueConstraint(name = "user_role_uq",
                    columnNames = {"user_id", "role_id"})}
//            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
//            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT)
    )
    private Set<User> users;

    public static final String ROLE_PREFIX = "ROLE_";

    protected boolean canEqual(final Object other) {
        return other instanceof Role;
    }
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    public String toString() {
        return "Role(id=" + this.getId() + ", name=" + this.getName() + ")";
    }

}