package org.sid.serviceproduct.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Product {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private Long quantity;

}