package local.dannyrusy.dbManager;

import javax.xml.bind.annotation.XmlRootElement;

/*
 * information about connection
 */
@XmlRootElement(name = "connection")
public class ConnectionInfo {
	
	private String dbType;
	private String name;
	private String jdbcUrl;
	private String user;
	private String password;
	private boolean readOnly = true;
	private boolean autoCommit = false;
	
	
	/**
	 * Constructor - information about connection
	 */
	public ConnectionInfo() {};
	/**
	 * Constructor - information about connection
	 * @param dbType
	 * @param name
	 * @param jdbcUrl
	 * @param user
	 * @param password
	 * @param readOnly
	 * @param autoCommit
	 */
	public ConnectionInfo(String dbType, String name, String jdbcUrl, String user, String password, boolean readOnly,
			boolean autoCommit) {
		this.dbType = dbType;
		this.name = name;
		this.jdbcUrl = jdbcUrl;
		this.user = user;
		this.password = password;
		this.readOnly = readOnly;
		this.autoCommit = autoCommit;
	}

	/**
	 * @return the dbType
	 */
	public String getDbType() {
		return dbType;
	}

	/**
	 * @param dbType the dbType to set
	 */
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the jdbcUrl
	 */
	public String getjdbcUrl() {
		return jdbcUrl;
	}

	/**
	 * @param jdbcUrl the jdbcUrl to set
	 */
	public void setjdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the readOnly
	 */
	public boolean getReadOnly() {
		return readOnly;
	}

	/**
	 * @param readOnly the readOnly to set
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	/**
	 * @return the autoCommit
	 */
	public boolean getAutoCommit() {
		return autoCommit;
	}

	/**
	 * @param autoCommit the autoCommit to set
	 */
	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;
	}
	
	
	
}
