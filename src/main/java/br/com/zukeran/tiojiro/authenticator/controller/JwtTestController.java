package br.com.zukeran.tiojiro.authenticator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtTestController {

	@GetMapping({ "/jwt" })
	public String jwt() {
		return "Token has been authorized!";
	}

}