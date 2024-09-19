package com.example.ramesh.demo.ramesh.Entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForException {

	private Date timestamp;
	private String message;
	private String details;
	
}
