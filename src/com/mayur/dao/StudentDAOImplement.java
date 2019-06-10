package com.mayur.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mayur.entity.Student;
import com.mayur.util.DbConnectionUtil;
import com.mysql.jdbc.PreparedStatement;

public class StudentDAOImplement implements StudentDAO {
	
	Connection con = null;
	Statement stm = null;
	PreparedStatement preparedStatement = null;
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

	public Student get(int id) {
		
		Student student = null;
		try {
			student = new Student();
			String sql = "SELECT * FROM Student where id="+id;
			con = DbConnectionUtil.openConnection();
			stm = con.createStatement();
			resultSet = stm.executeQuery(sql);
			if(resultSet.next()) {
				student.setId(resultSet.getInt("id"));
				student.setRno(resultSet.getInt("rno"));
				student.setName(resultSet.getString("name"));
				student.setCity(resultSet.getString("city"));
				student.setDate(resultSet.getString("createdOn"));
			}
			
		}catch(SQLException  e) {
			e.printStackTrace();
		}
		
		return student;
	}
	
	

	public boolean save(Student student) {
		boolean flag = false;
		
		try{
			String sql = "INSERT INTO Student(rno,name,city,createdOn)VALUES"
					+ "('"+student.getRno()+"', '"+student.getName()+"', '"+student.getCity()+"', '"+student.getDate()+"')";
			con = DbConnectionUtil.openConnection();
			preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean delete(int id) {
		boolean flag = false;
		try {
			String sql = "DELETE FROM Student where id="+id;
			con = DbConnectionUtil.openConnection();
			preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean update(Student student) {
		boolean flag = false;
		try {
			String sql = "UPDATE Student SET name = '"+student.getName()+"', "
					+ "city = '"+student.getCity()+"', rno = '"+student.getRno()+"' where id="+student.getId();
			con = DbConnectionUtil.openConnection();
			preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
