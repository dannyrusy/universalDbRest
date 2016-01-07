package local.dannyrusy.dbManager;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dbType")
public class DbType {
	

	private String name;
	private String driver;
	private String jarFile;
	
	public DbType() {
	}

	/**
	 * @param name
	 * @param driver
	 */
	public DbType(String name, String driver, String jarFile) {
		this.name = name;
		this.driver = driver;
		this.jarFile = jarFile;
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
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * @param driver the driver to set
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * @return the jarFile
	 */
	public String getJarFile() {
		return jarFile;
	}

	/**
	 * @param jarFile the jarFile to set
	 */
	public void setJarFile(String jarFile) {
		this.jarFile = jarFile;
	}

}
