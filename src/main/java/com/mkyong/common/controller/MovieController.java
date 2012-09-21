package com.mkyong.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.mkyong.common.controller.Movie;

@Controller
@RequestMapping("/movie")
public class MovieController {

	@RequestMapping(value="/{name}", method = RequestMethod.GET)
	public Movie getMovie(@PathVariable String name) {

		return new Movie("Blade","pg13","Dunno");

	}
	
}