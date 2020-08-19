package com.brasilprev.client.dao;

import java.util.Random;

import org.springframework.stereotype.Repository;

import com.brasilprev.client.model.AddressDto;

@Repository
public class AddAddressDao {

	/*
	 * Mock process to save address.
	 */
	public Long saveAddress(AddressDto address) {
		Random r = new Random();
		return Math.abs(r.nextLong());
	}
}
