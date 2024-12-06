package com.asm;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.asm.DAO.userDAO;
import com.asm.bean.User;
import com.asm.excel.write;

@SpringBootApplication
public class Ps37670Lab4Java5Application {
	@Autowired
    static userDAO userdao;
	public static void main(String[] args) throws IOException {
	
		SpringApplication.run(Ps37670Lab4Java5Application.class, args);
	}

}
