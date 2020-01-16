package br.com.zukeran.tiojiro.authenticator.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDAO extends CrudRepository<Login, Integer> {
	Login findByUsername(String username);
}