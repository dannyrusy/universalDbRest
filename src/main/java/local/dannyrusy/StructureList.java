package local.dannyrusy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	
	@Path("db/{dbName}/tablesList")
	@GET
	@Produces("application/json")
	public Response TablesList(@PathParam("dbName") String dbName) throws SQLException {
		
		JSONObject jsonObject = ConnectionsManager.getTablesList(dbName, true);
		String result = "" + jsonObject;
		
		return Response.status(200).entity(result).build();
		
	}
	
	@Path("db/{dbName}/{tableName}/columnsList")
	@GET
	@Produces("application/json")
	public Response ColumnsTableList(@PathParam("dbName") String dbName, @PathParam("tableName") String tableName) throws SQLException {
		
		JSONObject jsonObject = ConnectionsManager.getColumnsList(dbName, tableName);
		String result = "" + jsonObject;
		
		return Response.status(200).entity(result).build();
		
	}
	
}
