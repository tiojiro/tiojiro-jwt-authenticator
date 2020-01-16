package br.com.zukeran.tiojiro.authenticator.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.zukeran.tiojiro.authenticator.dao.Login;
import br.com.zukeran.tiojiro.authenticator.dao.LoginDAO;
import br.com.zukeran.tiojiro.authenticator.dao.LoginDTO;

@Service
public class JwtLoginService implements UserDetailsService {
	
	@Autowired
	private LoginDAO loginDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Login login = loginDao.findByUsername(username);
		if (login == null)
			throw new UsernameNotFoundException("Login not found with username: " + username);

		return new org.springframework.security.core.userdetails.User(login.getUsername(), login.getPassword(), new ArrayList<>());
	}
	
	public Login save(LoginDTO login) throws Exception{
		
		if(loginDao.findByUsername(login.getUsername()) != null)
			throw new Exception("Login is already registered.");
		
		Login newLogin = new Login();
		newLogin.setUsername(login.getUsername());
		newLogin.setPassword(bcryptEncoder.encode(login.getPassword()));
		return loginDao.save(newLogin);
	}
}
