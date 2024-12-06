package com.asm.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.asm.bean.User;

public interface userService {
	Page<User> getAll(Integer pageNo);
}
