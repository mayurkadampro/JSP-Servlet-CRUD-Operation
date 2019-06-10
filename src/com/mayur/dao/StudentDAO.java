package com.mayur.dao;

import java.util.List;

import com.mayur.entity.Student;

public interface StudentDAO {
	
	List<Student> get();
	
	Student get(int id);
	
	boolean save(Student student);
	
	boolean delete(int id);
	
	boolean update(Student student);
}
