/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Student;
import controllers.MyServlet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author George.Pasparakis
 */
public class StudentDAO extends Database {
    String server = "ra1.anystream.eu:1011";
    String database = "bootcampdb";
    String username = "root";
    String password = "aDifficultPassword$";
    
    public StudentDAO() {
        super();
    }
    
    public List<Student> getStudents() {
        Student st;
        String query = "SELECT * FROM `bootcampdb`.`students`";
        List<Student> students = new ArrayList<>();
        
        setOptions("?zeroDateTimeBehavior=convertToNull&serverTimezone=Europe/Athens&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false");
        ResultSet rs = Database(server, database, username, password, query);
        if(rs == null) { System.out.println("Error to the database"); } 
        try {
            while(rs.next()) {
                st = new Student(rs.getLong("ID"), rs.getString("SURNAME"), 
                                         rs.getString("NAME"), rs.getFloat("GRADE"), 
                                         rs.getString("BIRTHDATE")); 
                students.add(st);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return students;
    }
    
    public Student getStudentById(long id) {
        Student st = new Student();
        String query = "SELECT * FROM `bootcampdb`.`students` WHERE id = " + id + ";";
        
        setOptions("?zeroDateTimeBehavior=convertToNull&serverTimezone=Europe/Athens&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false");
        ResultSet rs = Database(server, database, username, password, query);
        if(rs == null) { 
            System.out.println("Error to the database"); 
        } else
        {
            try {
                rs.next();
                st.setId(rs.getLong("ID"));
                st.setName(rs.getString("SURNAME"));
                st.setSurname(rs.getString("NAME"));
                st.setGrade(rs.getFloat("GRADE"));
                st.setBirthDate(rs.getString("BIRTHDATE"));
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return st;
    }
    
    public boolean InsertStudent(Student st) {
        String query = "INSERT INTO `bootcampdb`.`students` \n" +
                        "(SURNAME,NAME,GRADE,BIRTHDATE) \n" +
                        "VALUES(\"" + st.getSurname() + "\",\"" + st.getName() + "\"," + st.getGrade() + "," + "\"" + st.getBirthDate() + "\")";
        int i = Database(server, database, username, password, query, (byte)1);
        if(i >= 1) return true; else return false;
    }
    
    public boolean InsertStudentJPA(Student st) {
        boolean completed = false;
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("TestWebAppPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            // transaction
            em.persist(st);
            em.getTransaction().commit();
            completed = true;
        } catch(Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        finally {
            em.close();
            emf.close();
        }
        return completed;
    }
    
    public boolean UpdateStudentJPA(Student st) {
        boolean completed = false;
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("TestWebAppPU");
        EntityManager em = emf.createEntityManager();
        
        Student fromDBStudent = em.find(entities.Student.class, st.getId());
        fromDBStudent.setSurname(st.getSurname());
        fromDBStudent.setGrade(st.getGrade());
        fromDBStudent.setBirthDate(st.getBirthDate());
        em.getTransaction().begin();
        try {
            // transaction
            em.persist(fromDBStudent);
            em.getTransaction().commit();
            completed = true;
        } catch(Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        finally {
            em.close();
            emf.close();
        }
        return completed;
    }
    
    public boolean DeleteStudent(int id) {
        String query = "DELETE FROM `bootcampdb`.`students` WHERE `id`=" + id + ";";
        int i = Database(server, database, username, password, query, (byte)1);
        if(i >= 1) return true; else return false;
    }
}
