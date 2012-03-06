/**
 * 
 */
package org.agilebackoffice.jira.workflow.condition;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.agilebackoffice.jira.workflow.condition.vcs.GitVCSConnector;
import org.eclipse.jgit.revwalk.RevCommit;
import org.springframework.beans.factory.annotation.Autowired;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.workflow.condition.AbstractJiraCondition;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.WorkflowException;
import com.xiplink.jira.git.MultipleGitRepositoryManager;

/**
 * @author kamann
 *
 */
public class LatestBuildIsGreenCondition extends AbstractJiraCondition {
	
	@Autowired
	MultipleGitRepositoryManager repositoryManager;
	
	
	public LatestBuildIsGreenCondition(){	}

	/* (non-Javadoc)
	 * @see com.opensymphony.workflow.Condition#passesCondition(java.util.Map, java.util.Map, com.opensymphony.module.propertyset.PropertySet)
	 */
	@Override
	public boolean passesCondition(
			@SuppressWarnings("rawtypes") Map transientVars,
			@SuppressWarnings("rawtypes") Map arg, PropertySet propertySet)
			throws WorkflowException {
		Date latestCodeChange = null;		
		Issue issue = getIssue(transientVars);
		
		latestCodeChange = new GitVCSConnector(repositoryManager).findLatestChangeDateByIssue(issue);
		System.out.println(latestCodeChange);
		return true;
	}
	
	


}
