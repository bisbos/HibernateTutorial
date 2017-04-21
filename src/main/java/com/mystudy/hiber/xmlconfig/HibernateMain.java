package com.mystudy.hiber.xmlconfig;

import com.mystudy.hiber.xmlconfig.config.HibernateUtil;
import com.mystudy.hiber.xmlconfig.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by bbose on 4/20/17.
 */
public class HibernateMain {

    public static void main(String[] args){
        Employee employee = new Employee();
        employee.setCompanyname("Google");

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
    //    session.close(); session will be closed when you commit transaction
        HibernateUtil.getSessionFactory().close();


    }
}
