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
		
		List<Student> theList = studentDAO.get();
		request.setAttribute("list", theList);
		dispatcher = request.getRequestDispatcher("/views/StudentList.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
