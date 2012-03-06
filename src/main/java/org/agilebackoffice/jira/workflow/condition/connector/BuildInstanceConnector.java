/**
 * 
 */
package org.agilebackoffice.jira.workflow.condition.connector;

import org.agilebackoffice.jira.workflow.condition.Credential;
import org.agilebackoffice.jira.workflow.condition.bean.BuildJob;

import com.atlassian.jira.project.Project;

/**
 * Interface to connect to a Build Instance. Every Build Instance has it's own concrete class.
 * 
 * @author Thorsten Kamann
 *
 */
public interface BuildInstanceConnector {
	
	/**
	 * Setups this {@link BuildInstanceConnector}
	 * @param url The url you want to use to connect to the {@link BuildInstanceConnector}
	 * @param credential the credential to access the {@link BuildInstanceConnector}
	 */
	void setupBuildInstance(String url, Credential credential);

	/**
	 * Finds the job configured in the BuildInstance.
	 * @param project The Jira {@link Project} to use to find the mapped build job
	 */
	BuildJob findBuildJobByJiraProject(Project jIraProject);
}
