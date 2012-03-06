/**
 * 
 */
package org.agilebackoffice.jira.workflow.condition.vcs;

import java.util.Date;

import com.atlassian.jira.issue.Issue;

/**
 * This interface provides methods to work with different integration of version control systems (vcs).
 * For every vcs there must be a concrete implementation.
 * 
 * @author Thorsten Kamann
 */
public interface VCSConnector {
	
	/**
	 * Setups this connector. This is specific to the concrete connector. All informations about permission should be retrieved through Jira components.
	 * @return  The setup instance
	 * @throws VCSConnectorException
	 */
	VCSConnector setup() throws VCSConnectorException;
	
	/**
	 * @return The {@link Date} of the last checkin change for an {@link Issue}
	 */
	Date findLatestChangeDateByIssue(Issue issue);

}
