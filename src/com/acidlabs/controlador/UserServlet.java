package com.acidlabs.controlador;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@MultipartConfig
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public UserServlet() {
        super();       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*url desde properties*/
		ResourceBundle rb = ResourceBundle.getBundle("app");
		String uri = rb.getString("urlservicio");
		
		/* Datos del formulario*/
		String usuario = request.getParameter("username");
		final Part filePart = request.getPart("images");
		InputStream filecontent = filePart.getInputStream();		
		String image64 = inputStreamToBase64(filecontent);
	    
	    try {
	    	/* LLamada al rest */
			Client client = Client.create();
			WebResource webResource = client.resource(uri);
			String input = "{\"username\":\""  +usuario+ "\",\"image\":\""+image64+"\"}";
			ClientResponse responseEnviar = webResource.type("application/json").post(ClientResponse.class, input);
			String output = responseEnviar.getEntity(String.class);
			
			if (responseEnviar.getStatus() == 401) 
				/*manejo de errores*/
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED,output );
			else{
				/*Imprimimos el base 64*/
				PrintWriter out = response.getWriter();
				out.println(output);
			}

		  } catch (Exception e) {
			e.printStackTrace();
		  }	    
	    //RestTemplate restTemplate = new RestTemplate();
	    //User result = restTemplate.postForObject( uri, newUser, User.class);
	}
	
	/**
	 * Convierte un Input stream a base 64
	 * 
	 * @param in InputStream
	 * @return String
	 * @throws IOException
	 */
	private String inputStreamToBase64(InputStream in) throws IOException{
	    String base64="";
	    int bytesRead = 0;
	    int chunkSize = 10000000;
	    byte[] chunk = new byte[chunkSize];
	    while ((bytesRead = in.read(chunk)) > 0) {
	        byte[] ba = new byte[bytesRead];

	        for(int i=0; i<ba.length;i++){
	            ba[i] = chunk[i];
	        }
	        base64 = Base64.getEncoder().encodeToString(ba);        	        
	    }
	    in.close();
	    return base64;
	}
	
	
}