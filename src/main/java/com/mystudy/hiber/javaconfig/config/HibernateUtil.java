package com.mystudy.hiber.javaconfig.config;

import com.mystudy.hiber.javaconfig.entities.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

/**
 * Created by bbose on 4/20/17.
 */
public class HibernateUtil {


    private static SessionFactory sessionFactory;

    public static SessionFactory buildSessionFactory(){
        Configuration configuration = new Configuration();

        //Create Properties, can be read from property files too
        Properties props = new Properties();
        props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        props.put("hibernate.connection.url", "jdbc:mysql://localhost/studydb");
        props.put("hibernate.connection.username", "root");
        props.put("hibernate.connection.password", "");
        props.put("hibernate.current_session_context_class", "thread");
        props.put("hibernate.hbm2ddl.auto","update");
        props.put("hibernate.show_sql",true);

        //Second Level Cache
        //First define the Factory class for second level cache
        //Enable second level cache.It is disabled by default
        //Define the Ehcache configuration file location
        props.put("hibernate.cache.region.factory_class",
                "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        props.put("hibernate.cache.use_second_level_cache",true);
        props.put("net.sf.ehcache.configurationResourceName","/myehcache.xml");


        configuration.setProperties(props);

        //we can set mapping file or class with annotation
        //addClass(Employee1.class) will look for resource
        // com/journaldev/hibernate/model/Employee1.hbm.xml (not good)
        configuration.addAnnotatedClass(Employee.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null || sessionFactory.isClosed())
            sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
}
