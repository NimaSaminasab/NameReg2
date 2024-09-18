package com.example.NameReg2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Elev {
    @Id
    @GeneratedValue

    private long id ;
    private String name ;

    private String telephone ;

}
