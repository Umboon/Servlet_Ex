/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.intership.mavenproject1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import th.co.geniustree.intership.mavenproject1.model.Student;

/**
 *
 * @author oom
 */
public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        Student student = new Student();
        em.getTransaction().begin();
        student.setName("mo");
        student.setGrade(4.00);
        em.persist(student);
       
        em.getTransaction().commit();
    }
}
