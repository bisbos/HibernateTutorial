package com.mystudy.hiber.javaconfig;

import com.mystudy.hiber.javaconfig.config.HibernateUtil;
import com.mystudy.hiber.javaconfig.entities.Employee;
import org.hibernate.Session;

/**
 * Created by bbose on 4/20/17.
 */
public class JavaConfigHibernateMain {

    public static void main(String[] args){
      // createEmployee();
        getEmployee();
        loadEmployee();

    }

    public static void createEmployee(){
        Employee employee = new Employee();
        employee.setCompanyname("Quicken");

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }

    public static void getEmployee(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        System.out.println("-----Calling the Get Method----");
        Employee employee = (Employee)session.get(Employee.class,new Integer(1));
        System.out.println("I hit the database already");
        System.out.println(employee.getCompanyname());

        System.out.println("---Calling get method again with same data");
        employee = (Employee)session.get(Employee.class,new Integer(1));
        System.out.println(employee.getCompanyname());
        HibernateUtil.getSessionFactory().close();
    }

    public static void loadEmployee(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        System.out.println("-----Calling the Load Method----");
        Employee employee = (Employee)session.load(Employee.class,new Integer(1));
        System.out.println("I am going to hit the database");
        System.out.println(employee.getCompanyname());

        System.out.println("-----Calling the Load Method again with Same data----");
        employee = (Employee)session.load(Employee.class,new Integer(1));
        System.out.println(employee.getCompanyname());
        HibernateUtil.getSessionFactory().close();

    }
}
