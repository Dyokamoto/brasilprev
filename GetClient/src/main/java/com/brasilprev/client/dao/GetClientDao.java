package com.brasilprev.client.dao;

import java.util.ArrayList;
import java.util.List;

import com.brasilprev.client.model.ClientDto;

public class GetClientDao {

	/* mock data */
	private List<ClientDto> clientList = new ArrayList<ClientDto>();

	public GetClientDao() {
		/* populating mock client data */
		clientList.add(new ClientDto(Long.valueOf(1), "Zé", "000.000.000-00"));
		clientList.add(new ClientDto(Long.valueOf(2), "Ana", "111.111.111-11"));
		clientList.add(new ClientDto(Long.valueOf(3), "Paulo", "222.222.222-22"));
		clientList.add(new ClientDto(Long.valueOf(4), "Roberta", "333.333.333-33"));
		clientList.add(new ClientDto(Long.valueOf(5), "Bruno", "444.444.444-44"));
		clientList.add(new ClientDto(Long.valueOf(6), "Cláudia", "555.555.555-55"));
	}

	/*
	 * Mock process to get client.
	 */
	public ClientDto getClientByCpf(String cpf) {
		/* faking db search for client */
		for (ClientDto item : clientList) {
			if (item.getCpf().equals(cpf))
				return item;
		}
		return null;
	}
}
