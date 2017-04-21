package com.mystudy.hiber.javaconfig.entities;

import lombok.Data;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by bbose on 4/20/17.
 */
@Data
@Entity
@Table(name = "employee1")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column(name = "id")  optional when variable and column name are same
    private Integer id;

    private String companyname;
}
