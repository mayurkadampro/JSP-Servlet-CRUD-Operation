package com.mayur.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mayur.entity.Student;
import com.mayur.util.DbConnectionUtil;

public class StudentDAOImplement implements StudentDAO {
	
	Connection con = null;
	Statement stm = null;

	ResultSet resultSet = null;

	public List<Student> get() {
		
		List<Student> slist = null;
		Student student = null;
		
		try{
			slist = new ArrayList<Student>();
			String viewQuery = "SELECT * FROM Student";
			con = DbConnectionUtil.openConnection();
			stm = con.createStatement();
			resultSet = stm.executeQuery(viewQuery);
			
			while( resultSet.next() ) {
				student = new Student();
				student.setId(resultSet.getInt("id"));
				student.setRno(resultSet.getInt("rno"));
				student.setName(resultSet.getString("name"));
				student.setCity(resultSet.getString("city"));
				student.setDate(resultSet.getString("createdOn"));
				slist.add(student);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return slist;
	}

}
