package com.vogella.jersey.first1;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;



@Path("/hello")
public class Hello {

	@GET
		@Path("/{param}")
		public Response getMsg(@PathParam("param") String msg)
		{
			String output = "Jersey say : " + msg;
			return Response.status(200).entity(output).build();
			
		
		}
	
	@GET 
	@Path("/getRemoteFxRateGeneral")
	@Produces(MediaType.APPLICATION_JSON)
	public String GetRemoteFxRateGeneral(@QueryParam("username") String username,
			@QueryParam("password") String password) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String result = null;
		String bankDesc = null;
			String exRate = null;
			JSONObject jObj = new JSONObject();
			JSONArray jArray = new JSONArray();
			String jString= "";
			System.out.println("hello");
			System.out.println("username : "+ username + "password :" + password);
			
			try {
				System.out.println("123");
				con = Utilities.getDbConnection();
				stmt = con.prepareStatement("SELECT bank_desc,exchange_rate from bank where agent_type = 'R' and exchange_rate>'1' order by bank_desc");
				rs = stmt.executeQuery();
				System.out.println("Stmt------" + stmt);
				while(rs.next())
				{
					bankDesc = rs.getString("bank_desc");
					exRate = rs.getString("exchange_rate");
					
					
					jObj.put("bankDesc", bankDesc);
					jObj.put("exRate", exRate);
					jString = jObj.toString();
					
					JSONObject jsonObject = new JSONObject(jString);
					jArray.put(jsonObject);
					
				}
			}
			catch(Exception ex)
			{}
				System.out.println("json Array : " + jArray);
				return jArray.toString();
			
	}}