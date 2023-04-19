package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {

	private static Map<String, Product> productRepo=new HashMap<String,Product>();
	
	static
	{
		Product honey=new Product("1", "Honey");
		productRepo.put(honey.getId(), honey);
		
		Product almonds=new Product("2", "Almonds");
		productRepo.put(almonds.getId(), almonds);
	}
	
	//Get All Products
	@RequestMapping("/products")
	public ResponseEntity<Object> showall()
	{
		return new ResponseEntity<Object>(productRepo.values(),HttpStatus.OK);
	}
	
	//Get specific product based on id
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> show(@PathVariable("id")String id)
	{
		return new ResponseEntity<Object>(productRepo.get(id),HttpStatus.OK);
	}
	
	//Delete specific Product based on id
	@RequestMapping(value = "/products/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id")String id)
	{
		productRepo.remove(id);
		return new ResponseEntity<Object>("Product Deleted...",HttpStatus.OK);
	}
	
	//Update Product
	@RequestMapping(value = "/products/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@PathVariable("id")String id,@RequestBody Product p)
	{
		productRepo.remove(id);
		p.setId(id);
		productRepo.put(id, p);
		return new ResponseEntity<Object>("Product Updated",HttpStatus.OK);
	}
	
	//Add a Product
	@RequestMapping(value = "/products",method = RequestMethod.POST)
	public ResponseEntity<Object> add(@RequestBody Product p)
	{
		productRepo.put(p.getId(), p);
		return new ResponseEntity<Object>("Product Added...",HttpStatus.CREATED);
	}
}
