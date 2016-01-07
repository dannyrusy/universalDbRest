package local.dannyrusy.dbManager;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dbTypes")
@XmlAccessorType(XmlAccessType.FIELD)
public class DbTypesList {

	@XmlElement(name = "dbType")
	private List<DbType> DbTypesListList = new ArrayList<DbType>();
	
	public List<DbType> getDbTypesListList () {
		return DbTypesListList;
	}
	
}
