package com.myl.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Path("/msgPost")
@Component("MsgPostResource")
public class MsgPostResource {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MsgPostResource.class);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response receiveCalif(JSONArray jsonObject) throws JSONException {
		String result = "Recibido";
		LOGGER.info("Recibido: " + jsonObject);

		return Response.status(201).entity(result).build();
	}
}
