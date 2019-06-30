/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Student;
import dao.StudentDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author George.Pasparakis
 */
public class StudentService {
    
    public StudentService() {
    }
    
    public String getStudents() {
       StudentDAO stuDao = new StudentDAO();
       List<Student> students = stuDao.getStudents();
       StringBuilder str = new StringBuilder(); 
       str.append("<!DOCTYPE html>")
                .append("<html>")
                .append("<head>")
                .append("<title>My First Servlet</title>")
                .append("</head>")
                .append("<body>");
        for (Student s : students) {
            String delete = " <a href='DeleteStudent?delete=" + s.getId()+ "'>Delete</a>";
            String update = " <a href='UpdateStudent?update=" + s.getId()+ "'>Update</a>";
            str.append("<p>").append(s).append(delete).append(update).append("</p>");
        }
        str.append("</body>")
                .append("</html>");
        return str.toString();
    }
    
    public Student getStudentByID(long id) {
        StudentDAO stuDao = new StudentDAO();
        Student st = new Student();
        st = stuDao.getStudentById(id);
        return st;
    }
    
    public boolean InsertStudent(Student st) {
        StudentDAO stuDao = new StudentDAO();
        if(stuDao.InsertStudentJPA(st)) return true;
        return false;
    }
    
    public boolean DeleteStudent(int id) {
        StudentDAO stuDao = new StudentDAO();
        if(stuDao.DeleteStudent(id)) return true;
        return false;
    }
    
    public boolean UpdateStudent(Student st) {
        StudentDAO stuDao = new StudentDAO();
        if(stuDao.UpdateStudentJPA(st)) return true;
        return false;
    }
}
