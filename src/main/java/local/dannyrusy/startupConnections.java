package local.dannyrusy;

import java.io.File;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.xml.bind.JAXBException;

import local.dannyrusy.dbManager.ConnectionsManager;


public class startupConnections extends HttpServlet {

	//public static List<Connection> connectionsList = ;
	
	public void init() throws ServletException {
		
		System.out.println("----------");
		System.out.println("---------- AUTOSTART Servlet Initialized successfully ----------");
		System.out.println("----------");
		
		File connectionsFile = new File(this.getClass().getClassLoader().getResource("connections.xml").getFile());
		File dbTypesFile = new File(this.getClass().getClassLoader().getResource("dbTypes.xml").getFile());
		try {
			ConnectionsManager connMan = new ConnectionsManager(connectionsFile, dbTypesFile);
			connMan.openAllConnections();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
