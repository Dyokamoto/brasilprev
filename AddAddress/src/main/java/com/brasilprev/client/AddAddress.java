package com.brasilprev.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.brasilprev.client.dao.AddAddressDao;
import com.brasilprev.client.model.AddressDto;
import com.brasilprev.client.model.ResponseDto;

@RestController
public class AddAddress {

	private AddAddressDao dao = null;

	@PostMapping("/addAddress/{cpf}")
	public ResponseDto create(@RequestBody AddressDto address, @PathVariable String cpf) {
		System.out.println("Address received -> cpf:" + cpf + " - address" + address.getAddress() + " - number:"
				+ address.getNumber() + " - city:" + address.getCity() + " - state:" + address.getState()
				+ " - country:" + address.getCountry() + " - zipcode:" + address.getZipcode());

		try {
			Long id = checkClient(cpf);
			if (id == null) {
				System.out.println("client doesn't exists");
				return new ResponseDto(null, "client doesn't exists");
			} else {
				Long newid = getDao().saveAddress(address);
				if (newid != null) {
					System.out.println("new address added: " + newid);
					return new ResponseDto(newid, "");
				} else {
					System.out.println("Failed to add address");
					return new ResponseDto(null, "Failed to add address");
				}
			}
		} catch (Exception e) {
			System.out.println("Failed to add address:" + e.getMessage());
			return new ResponseDto(null, e.getMessage());
		}
	}

	private AddAddressDao getDao() {
		if (dao == null)
			dao = new AddAddressDao();
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
