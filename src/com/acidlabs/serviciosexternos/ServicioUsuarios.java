package com.acidlabs.serviciosexternos;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import com.acidlabs.bo.User;

@Path("/")
public class ServicioUsuarios {
	
	public ServicioUsuarios() {
		super();		
	}

	@GET
	@Path("/get")
	public String getUser() {
		return "Solo llamadas por post";
	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser (String texto) {
		
		ObjectMapper mapper = new ObjectMapper();
		try {
		    User user = mapper.readValue(texto, User.class);
		    
		    if(user.getUsername().equals("usuario1")){
		    	String result = "{\"image\":\""  +user.getImage()+ "\"}";
			    return Response.status(200).entity(result).build();
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		String result = "{\"message\":\"Unauthorized\"}";
		return Response.status(401).entity(result).build();
	}
}