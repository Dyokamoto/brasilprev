package com.brasilprev.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.brasilprev.client.dao.CreateClientDao;
import com.brasilprev.client.model.ClientDto;
import com.brasilprev.client.model.ResponseDto;

@RestController
public class CreateClient {

	private CreateClientDao dao = null;

	@PostMapping("/create")
	public ResponseDto create(@RequestBody ClientDto client) {
		System.out.println("Client received -> name:" + client.getName() + " - cpf:" + client.getCpf());

		try {
			Long id = checkClient(client.getCpf());
			if (id != null) {
				System.out.println("client already exists");
				return new ResponseDto(id, "client already exists");
			} else {
				Long newid = getDao().saveClient(client);
				if (newid != null) {
					System.out.println("new client created: " + newid);
					return new ResponseDto(newid, "");
				} else {
					System.out.println("Failed to create client");
					return new ResponseDto(null, "Failed to create client");
				}
			}
		} catch (Exception e) {
			System.out.println("Failed to create client:" + e.getMessage());
			return new ResponseDto(null, e.getMessage());
		}
	}

	private CreateClientDao getDao() {
		if (dao == null)
			dao = new CreateClientDao();
		return dao;
	}

	private Long checkClient(String cpf) throws Exception {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<ResponseDto> ret = rt.getForEntity("http://localhost:8081/get/" + cpf, ResponseDto.class);
		if (ret != null) {
			if (ret.getBody().getEntityId() != null)
				return ret.getBody().getEntityId();
			else
				return null;
		}
		/* service call failed */
		throw new Exception("Failed to check client. Try again later");
	}
}
