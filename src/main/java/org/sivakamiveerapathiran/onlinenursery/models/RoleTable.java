package org.sivakamiveerapathiran.onlinenursery.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
public class RoleTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public RoleTable(String name) {
        this.name = name;
    }
}