/**
 * 
 */
package org.agilebackoffice.jira.workflow.condition.vcs;

/**
 * This exception will be thrown if during the setup of a {@link VCSConnector} an exception will be thrown.
 * 
 * @see RuntimeException
 * @author Thorsten Kamann
 */
public class VCSConnectorException extends RuntimeException {
	private static final long serialVersionUID = 2928455036485598214L;

	/**
	 * Creates a new exception instance, if anything during the setup phase of a {@link VCSConnector} went wrong.
	 * @param message The message you want to log
	 * @param throwable An instance of a {@link Throwable}
	 */
	public VCSConnectorException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
