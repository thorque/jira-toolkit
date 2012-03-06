/**
 * 
 */
package org.agilebackoffice.jira.workflow.condition;

/**
 * This class holds the credentials to connect to BuildInstance.
 * 
 * @author kamann
 */
public class Credential {
	private String uid;
	private String password;
	
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
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
	
	
}
