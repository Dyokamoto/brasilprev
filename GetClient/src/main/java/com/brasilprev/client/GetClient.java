package com.brasilprev.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.brasilprev.client.dao.GetClientDao;
import com.brasilprev.client.model.ClientDto;
import com.brasilprev.client.model.ResponseDto;

@RestController
public class GetClient {

	private GetClientDao dao = null;

	@GetMapping("/get/{cpf}")
	public ResponseDto get(@PathVariable String cpf) {
		System.out.println("Get request received cpf:" + cpf);

		ClientDto ret = getDao().getClientByCpf(cpf);
		return processDto(ret);
	}

	private ResponseDto processDto(ClientDto ret) {
		if (ret == null) {
			System.out.println("Client not found");
			return new ResponseDto(null, "Client not found");
		}
		System.out.println("Client found");
		return new ResponseDto(ret.getId(), "");
	}

	private GetClientDao getDao() {
		if (dao == null)
			dao = new GetClientDao();
		return dao;
	}
}
