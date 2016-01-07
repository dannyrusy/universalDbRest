package local.dannyrusy.dbManager;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "connections")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConnectionsInfoList {
	
	@XmlElement(name = "connection")
	private List<ConnectionInfo> connectionInfoList = new ArrayList<ConnectionInfo>();
	
		
	public List<ConnectionInfo> getConnectionsInfoList () {
		return connectionInfoList;
	}
	
}
