package com.brasilprev.client.dao;

import java.util.Random;

import org.springframework.stereotype.Repository;

import com.brasilprev.client.model.ClientDto;

@Repository
public class CreateClientDao {

	/*
	 * Mock process to save client.
	 */
	public Long saveClient(ClientDto client) {
		Random r = new Random();
		return Math.abs(r.nextLong());
	}
}
