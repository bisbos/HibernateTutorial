package com.mystudy.hiber.javaconfig;

import com.mystudy.hiber.javaconfig.config.HibernateUtil;
import com.mystudy.hiber.javaconfig.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

/**
 * Created by bbose on 4/20/17.
 */
public class SecondLevelCacheMain {

    public static void main(String[] args){
        System.out.println("Temp Dir:"+System.getProperty("java.io.tmpdir"));
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Statistics stats = sessionFactory.getStatistics();
        stats.setStatisticsEnabled(true);

        Session session = sessionFactory.openSession();
        Session otherSession = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Transaction otherTransaction = otherSession.beginTransaction();

        printStats(stats, 0);

        Employee emp = (Employee)session.get(Employee.class,new Integer(1));
        printData(emp, stats, 1);

        emp = (Employee) session.load(Employee.class, new Integer(1));
        printData(emp, stats, 2);

        //clear first level cache, so that second level cache is used
        session.evict(emp);
        emp = (Employee) session.load(Employee.class, new Integer(1));
        printData(emp, stats, 3);

      /*  emp = (Employee) session.load(Employee.class, new Integer(3));
        printData(emp, stats, 4); */

        emp = (Employee) otherSession.load(Employee.class, new Integer(1));
        printData(emp, stats, 4);

        emp = (Employee) otherSession.load(Employee.class, new Integer(1));
        printData(emp, stats, 5);

        //Release resources
        transaction.commit();
        otherTransaction.commit();
        sessionFactory.close();

    }


    private static void printStats(Statistics stats, int i) {
        System.out.println("***** " + i + " *****");
        System.out.println("Fetch Count="
                + stats.getEntityFetchCount());
        System.out.println("Second Level Hit Count="
                + stats.getSecondLevelCacheHitCount());
        System.out
                .println("Second Level Miss Count="
                        + stats
                        .getSecondLevelCacheMissCount());
        System.out.println("Second Level Put Count="
                + stats.getSecondLevelCachePutCount());
    }

    private static void printData(Employee emp, Statistics stats, int count) {
        System.out.println(count+":: Name="+emp.getCompanyname());
        printStats(stats, count);
    }
}
