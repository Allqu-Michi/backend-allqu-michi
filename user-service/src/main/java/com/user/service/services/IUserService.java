package com.user.service.services;

import java.util.List;

import com.user.service.dto.UserDto;
import com.user.service.models.User;

public interface IUserService {
	public List<User> findAll();
	public User findById(Long id);
	public UserDto save(UserDto userDto);
	public UserDto update(UserDto userDto, long id);
	public void delete(long id);
}
