package local.dannyrusy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import local.dannyrusy.dbManager.ConnectionsManager;

@Path("/")
public class StructureList {
	
	@Path("dbsList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response DbList1() {
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		Map<String, Connection> connList = ConnectionsManager.getConnectionsList();
		Set<String> listaDB = connList.keySet();
		for (String string : listaDB) {
			jsonArray.put(string);
		}
		
		jsonObject.put("DATABASES", jsonArray);
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
		
	}
	
	@Path("db/{dbName}")
	@GET
	@Produces("application/json")
	public Response TablesList(@PathParam("dbName") String dbName) throws SQLException {
		return TablesList1(dbName);
	}
	
	@Path("db/{dbName}/tablesList")
	@GET
	@Produces("application/json")
	public Response TablesList1(@PathParam("dbName") String dbName) throws SQLException {
		
		JSONObject jsonObject = ConnectionsManager.getTablesList(dbName, true);
		String result = "" + jsonObject;
		
		return Response.status(200).entity(result).build();
		
	}
	@Path("db/{dbName}/{tableName}")
	@GET
	@Produces("application/json")
	public Response ColumnsTableList(@PathParam("dbName") String dbName, @PathParam("tableName") String tableName) throws SQLException {
		
		return ColumnsTableList1(dbName, tableName);
		
	}
	
	@Path("db/{dbName}/{tableName}/columnsList")
	@GET
	@Produces("application/json")
	public Response ColumnsTableList1(@PathParam("dbName") String dbName, @PathParam("tableName") String tableName) throws SQLException {
		
		JSONObject jsonObject = ConnectionsManager.getColumnsList(dbName, tableName);
		String result = "" + jsonObject;
		
		return Response.status(200).entity(result).build();
		
	}
	
	@Path("db/{dbName}/{tableName}/rows")
	@GET
	@Produces("application/json")
	public Response selectAll(@PathParam("dbName") String dbName, @PathParam("tableName") String tableName, 
			@DefaultValue("") @QueryParam("select") String select,
			@DefaultValue("") @QueryParam("where") String where
			) throws SQLException {
		
		String result = "";
		if (select.equals("") && where.equals("")) {
			System.out.println("tutto");
			JSONObject jsonObject = ConnectionsManager.getAllRow(dbName, tableName);
			result = "" + jsonObject;
		} else {
			System.out.println("parte");
			JSONObject jsonObject = ConnectionsManager.getAllRow(dbName, tableName);
			result = "" + jsonObject;
		}
		
		
		return Response.status(200).entity(result).build();
		
	}
	
}
