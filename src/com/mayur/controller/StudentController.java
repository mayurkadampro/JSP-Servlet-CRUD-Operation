package com.mayur.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mayur.dao.StudentDAO;
import com.mayur.dao.StudentDAOImplement;
import com.mayur.entity.Student;

public class StudentController extends HttpServlet {
	
	RequestDispatcher dispatcher = null;
	StudentDAO studentDAO = null;
	
	
    public StudentController(){
    	studentDAO = new StudentDAOImplement();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action == null) {
			action = "LIST";
		}
		
		switch(action) {
		
		case "LIST":
			listStudent(request, response);
			break;
			
		case "EDIT":
			getSingleEmployee(request, response);
			break;
			
		case "DELETE":
			deleteEmployee(request, response);
			break;
			
		default:
			listStudent(request, response);
			break;
			
	}
		

	}
	
	private void listStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Student> theList = studentDAO.get();
		request.setAttribute("list", theList);
		dispatcher = request.getRequestDispatcher("/views/StudentList.jsp");
		dispatcher.forward(request, response);
	}
	
	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		if(studentDAO.delete(Integer.parseInt(id))) {
			request.setAttribute("NOTIFICATION", "Student Deleted Successfully!");
		}
		listStudent(request, response);
	}

	private void getSingleEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String id = request.getParameter("id");
		Student Student = studentDAO.get(Integer.parseInt(id));
		request.setAttribute("student", Student);
		dispatcher = request.getRequestDispatcher("/views/Studentform.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		Student student = new Student();
		student.setId(Integer.parseInt(request.getParameter("id")));
		student.setRno(Integer.parseInt(request.getParameter("rno")));
		student.setName(request.getParameter("name"));
		student.setCity(request.getParameter("city"));
		student.setDate(request.getParameter("createdOn"));
		
		if(id.isEmpty() || id == null) {
			
			if(studentDAO.save(student)) {
				request.setAttribute("NOTIFICATION", "Student Saved Successfully!");
			}
		
		}else {
			
			student.setId(Integer.parseInt(id));
			if(studentDAO.update(student)) {
				request.setAttribute("NOTIFICATION", "Student Updated Successfully!");
			}
			
		}
		
		listStudent(request, response);
	}

}
