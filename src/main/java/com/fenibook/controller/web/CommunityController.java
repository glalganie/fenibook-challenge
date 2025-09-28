package com.fenibook.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community")
public class CommunityController {
	 @GetMapping("/search")
	    public String searchUsers() { return "community/search"; }
	    
	    @GetMapping("/friends")
	    public String friends() { return "community/friends"; }
	}