package com.sec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    private String name;


    /**
     * @return the id
     */
    public Integer getId() {
        return Id;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.Id = id;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }   
}