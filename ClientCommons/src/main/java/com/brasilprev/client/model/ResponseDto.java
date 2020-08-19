package com.brasilprev.client.model;

import java.util.Random;

public class ResponseDto {
	private Long requestId;
	private Long entityId;
	private String msg;

	public ResponseDto() {
	}

	public ResponseDto(Long entityId, String msg) {
		Random r = new Random();
		/* mock requestId for request tracing */
		requestId = r.nextLong();
		this.entityId = entityId;
		this.msg = msg;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
