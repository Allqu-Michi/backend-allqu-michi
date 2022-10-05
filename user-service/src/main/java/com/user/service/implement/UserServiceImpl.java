package com.user.service.implement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.service.dto.UserDto;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.models.User;
import com.user.service.repositories.UserRepository;
import com.user.service.services.IUserService;


@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    PasswordEncoder passwordEncoder;
    
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Mascota", "id", id));
	}

	@Override
	public UserDto save(UserDto userDto) {
		
		Optional<User> userExist = userRepository.findByEmail(userDto.getEmail());
		
        if(userExist.isPresent())
            return null;
        
        String password = passwordEncoder.encode(userDto.getPassword());     

		User newUser = mapearEntidad(userDto);
		
        newUser.setPassword(password);
        newUser.setStatus(false);
        newUser.setRole_id(1L);
        newUser.setCreate_at(LocalDateTime.now());
        newUser.setUpdate_at(LocalDateTime.now());
        
        User savedUser = userRepository.save(newUser);
        
        UserDto newUserDto = mapearDTO(savedUser);
		
        return newUserDto;
        
	}	

	@Override
	public UserDto update(UserDto userDto, long id) {
		User user_ = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
		
		Optional<User> userExist = userRepository.findByEmail(userDto.getEmail());
		
        if(userExist.isPresent())
            return null;
        
        String password = passwordEncoder.encode(userDto.getPassword());
        
		user_.setName(userDto.getName());
		user_.setFirst_name(userDto.getFirst_name());
		user_.setLast_name(userDto.getLast_name());
		user_.setCellphone(userDto.getCellphone());
		user_.setEmail(userDto.getEmail());
		user_.setPassword(password);
		//user_.setStatus(userDto.getStatus());
		//user_.setRole_id(userDto.getRole_id());
		user_.setUpdate_at(LocalDateTime.now());
		
		User updateUser_ = userRepository.save(user_);
		
		UserDto newUserDto = mapearDTO(updateUser_);
		
		return newUserDto;
	}	

	@Override
	public void delete(long id) {
    	User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));

    	userRepository.delete(user);
	}
	
	private UserDto mapearDTO(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		return userDto;
	}
	
	// Convierte de DTO a Entidad
	private User mapearEntidad(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		return user;
	}
}