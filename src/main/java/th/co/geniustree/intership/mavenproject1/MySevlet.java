/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.internship.mavenproject1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import th.co.geniustree.intership.mavenproject1.model.Student;

/**
 *
 * @author pramoth
 */
public class MySevlet extends HttpServlet {

    @PersistenceContext(unitName = "test")
    private EntityManager em;
    @Resource
    private UserTransaction tx;
//MVC

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

        } finally {
            em.close();
        }
        Student t = findStudentById(req.getParameter("id"));
        req.setAttribute("student", t);
        req.getRequestDispatcher("/view.jsp").forward(req, resp);
    }

    private Student findStudentById(String id) {
        Student t = new Student();
        t.setName("Jasin");
        t.setGrade(4.00);
        return t;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student();
        System.out.println("" + req.getParameter("name"));

        try {
            tx.begin();
            student.setName(req.getParameter("name"));
            student.setGrade(Double.parseDouble(req.getParameter("grade")));
            em.persist(student);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                tx.rollback();
            } catch (Exception ex) {
                Logger.getLogger(MySevlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        req.setAttribute("student", student);
        req.getRequestDispatcher("/view.jsp").forward(req, resp);
    }

}
