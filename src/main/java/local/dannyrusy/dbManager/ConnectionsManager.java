package local.dannyrusy.dbManager;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConnectionsManager {
	
	private static Map<String, Connection> jdbcConnections = new HashMap<String, Connection>();
	private List<ConnectionInfo> connections = new ArrayList<ConnectionInfo>();
	private List<DbType> dbTypes = new ArrayList<DbType>();
	
	/**
	 * Connection manager
	 * @param connectionsFile
	 * @param dbTypesFile
	 * @throws JAXBException
	 */
	public ConnectionsManager(File connectionsFile, File dbTypesFile) throws JAXBException {
		readConnectionsFile(connectionsFile);
		readDbTypesFile(dbTypesFile);
	}
	
	/**
	 * Read the connections configuration list file
	 * @param connectionsFile
	 * @throws JAXBException
	 */
	private void readConnectionsFile (File connectionsFile) throws JAXBException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] { ConnectionsInfoList.class, ConnectionInfo.class });
		Unmarshaller um = jaxbContext.createUnmarshaller();
		connections = ((ConnectionsInfoList) um.unmarshal(connectionsFile)).getConnectionsInfoList();
		
	}
	
	/**
	 * Read the db types list file
	 * @param dbTypesFile
	 * @throws JAXBException
	 */
	private void readDbTypesFile (File dbTypesFile) throws JAXBException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] { DbTypesList.class, DbType.class });
		Unmarshaller um = jaxbContext.createUnmarshaller();
		dbTypes = ((DbTypesList) um.unmarshal(dbTypesFile)).getDbTypesListList();
		
	}
	
	/**
	 * Open all configured connections 
	 */
	public void openAllConnections () {
		
		System.out.println("---------------------------------");
		for (ConnectionInfo connectionInfo : connections) {
			
			System.out.println("Database connection name: "+connectionInfo.getName());
			String driverClassName = "";
			//String driverJarFile = "";
			
			try {
				for (DbType dbType : dbTypes) {
					if (dbType.getName().equals(connectionInfo.getDbType())) {
						driverClassName = dbType.getDriver();
						break;
					}
				}
				if (driverClassName.isEmpty()) {
					System.err.println("TYPE OF DATABASE NOT ALLOWED! CHECK THE CONFIGURATION");
					continue;
				}
				
				Class.forName(driverClassName);
				Connection conn = DriverManager.getConnection(connectionInfo.getjdbcUrl(),connectionInfo.getUser(), connectionInfo.getPassword());
				conn.setAutoCommit(connectionInfo.getReadOnly());
				conn.setAutoCommit(connectionInfo.getAutoCommit());
				jdbcConnections.put(connectionInfo.getName(), conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block - sistemare eventuale errore 
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block - sistemare per errore di drivere non trovato
				System.err.println("DRIVER JDBC "+driverClassName+" NOT FOUND !!! ");
				e.printStackTrace();
			}
			
			System.out.println("DATABASE CONNECTION '"+connectionInfo.getName()+"' COMPLETED!!!");
		}
		System.out.println("---------------------------------");
		System.out.println("");
		
		System.out.println("---------------------------------");
		System.out.println("DATABASE CONNECTIONS COMPLETED!!!");
		System.out.println("CONNECTIONS OPENED: "+jdbcConnections.size()); 
		System.out.println("---------------------------------");
		
		
	}
	
	public static Map<String, Connection> getConnectionsList() {
		return jdbcConnections;
	}
	

	public static JSONObject getTablesList (String dbName, boolean columnsList) throws SQLException  {
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonTables = new JSONArray();
		JSONArray jsonViews = new JSONArray();

		Map<String, Connection> connList = ConnectionsManager.getConnectionsList();
		Connection conn = connList.get(dbName);
		
		DatabaseMetaData md = conn.getMetaData();
		ResultSet rs = md.getTables(null, null, "%", null);
		while (rs.next()) {
			
			String tableName = rs.getString(3); 
			JSONObject jsonTab = new JSONObject().put("NAME", tableName);
			if (columnsList == true) {
				JSONObject jsonCol = getColumnsList(dbName, tableName);
				jsonTab.put("COLUMNS", jsonCol.get("COLUMNS"));
			}
			
			if (rs.getString(4).equalsIgnoreCase("table")) {
				jsonTables.put(jsonTab);
			}
			if (rs.getString(4).equalsIgnoreCase("view")) {
				jsonViews.put(jsonTab);
			}
			
			/*
			System.out.println("1: "+rs.getString(1)); //NOME DATABASE
			System.out.println("2: "+rs.getString(2)); //null ???
			System.out.println("3: "+rs.getString(3)); //NOME OGGETTO
			System.out.println("4: "+rs.getString(4)); //TIPO... TABELLA O VISTA
			*/
		}
		
		jsonObject.put("TABLES", jsonTables);
		jsonObject.put("VIEWS", jsonViews);
		
		return jsonObject;
		
	}
	
	public static JSONObject getColumnsList (String dbName, String tableName) throws SQLException  {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		Map<String, Connection> connList = ConnectionsManager.getConnectionsList();
		Connection conn = connList.get(dbName);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM "+tableName+"");
		ResultSetMetaData rsmd = rs.getMetaData();
		int totColumn = rsmd.getColumnCount();
		System.out.println(totColumn); 
		for (int i = 1; i < totColumn+1; i++) {
			jsonArray.put(rsmd.getColumnName(i));
		}
		jsonObject.put("COLUMNS", jsonArray);
		
		return jsonObject;
	}
	
}
