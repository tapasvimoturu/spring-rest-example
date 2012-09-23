package com.mkyong.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mkyong.common.controller.Movie;

@Controller
@RequestMapping("/movie")
public class MovieController {

	// Something missing because I get a 406.....
	@RequestMapping(value="/get/{name}", method = RequestMethod.GET)
	public @ResponseBody Movie getMovie(@PathVariable String name) {
		Movie m = new Movie(name,"pg13","Dunno");
		System.out.println(m.toString());
		return m;
	}
	
	// Works because it returns text
	@RequestMapping(value="/test", method = RequestMethod.GET) 
	public @ResponseBody String test(){
		return "BAAAAAA";
	}
	
}