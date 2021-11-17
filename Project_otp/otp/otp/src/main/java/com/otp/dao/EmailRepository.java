package com.otp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otp.entities.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, String>{}
