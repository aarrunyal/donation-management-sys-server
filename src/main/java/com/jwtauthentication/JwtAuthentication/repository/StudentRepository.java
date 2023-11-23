package com.jwtauthentication.JwtAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwtauthentication.JwtAuthentication.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
