package com.fenibook.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quizzes")
public class QuizController {
	 @GetMapping("/new")
	    public String createQuizForm() { return "quizzes/form"; }
	}