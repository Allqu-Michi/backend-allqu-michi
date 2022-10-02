package com.pet.service.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BlogAppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus state;
	private String message;

	public BlogAppException(HttpStatus state, String message, String message1) {
		super();
		this.state = state;
		this.message = message;
		this.message = message1;
	}

}
