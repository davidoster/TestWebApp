<%-- 
    Document   : newstudent
    Created on : Jun 26, 2019, 11:58:06 AM
    Author     : George.Pasparakis
--%>

<%@page import="entities.Student"%>
<%! private Student st; %>
<% st = (Student)request.getAttribute("student"); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Student</title>
    </head>
    <body>
        <!--<jsp:useBean id="student" class="entities.Student" scope="page" />
        <jsp:setProperty name="student" property="surname" value="Tasos" />
        Student Surname  :<%= student.getSurname()%>-->
        
        <h1><%= request.getAttribute("title")%></h1>
        <form method="POST" action="<%= request.getAttribute("action")%>">
            <input type="text" hidden name="id" <% if(request.getAttribute("action") == "UpdateStudent") { %> 
                         value="<%= st.getId()%>" <% } %> />            
            Name: <input name="name" type="text" <% if(request.getAttribute("action") == "UpdateStudent") { %> 
                         value="<%= st.getName() %>" <% } %>/><br />
            Surname: <input name="surname" type="text" <% if(request.getAttribute("action") == "UpdateStudent") { %> 
                         value="<%= st.getSurname() %>" <% } %>/><br />
            Grade: <input name="grade" type="number" <% if(request.getAttribute("action") == "UpdateStudent") { %> 
                         value="<%= st.getGrade() %>" <% } %>/><br />
            Birth Date: <input name="birthdate" type="date"  <% if(request.getAttribute("action") == "UpdateStudent") { %> 
                         value="<%= st.getBirthDate() %>" <% } %>/><br />
            <input type="Submit" name="new" value="<%= request.getAttribute("button") %>" /><br />
        </form>
    </body>
</html>
