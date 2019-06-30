/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Student;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.StudentService;

/**
 *
 * @author George.Pasparakis
 */
public class UpdateStudent extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateStudent</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateStudent at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentService ss = new StudentService();
        long id = Long.parseLong(request.getParameter("update"));
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateStudent</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateStudent at " + request.getContextPath() + "</h1>");
            
            Student st = new Student();
            st = ss.getStudentByID(id);
            
            RequestDispatcher rd = request.getRequestDispatcher("newstudent.jsp");
            request.setAttribute("id", id);
            request.setAttribute("title", "Update Student");
            request.setAttribute("action", "UpdateStudent");
            request.setAttribute("student",st);
            request.setAttribute("button", "Update Student");
            rd.forward(request, response);
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String qString = request.getQueryString();
        StudentService stuServ = new StudentService();
        Student st = new Student();
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
                st.setId(Long.parseLong(request.getParameter("id")));
                st.setName(request.getParameter("name"));
                st.setSurname(request.getParameter("surname"));
                st.setGrade(Float.parseFloat(request.getParameter("grade")));
                st.setBirthDate(request.getParameter("birthdate"));
                if(stuServ.UpdateStudent(st)) out.println("All Updated!"); else out.println("Nothing Updated!");
//                out.println(qString);
//                out.print("Param delete: " + Integer.parseInt(request.getParameter("delete")));
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
