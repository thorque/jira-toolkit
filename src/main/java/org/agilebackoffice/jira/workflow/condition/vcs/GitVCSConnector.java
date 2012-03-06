/**
 * 
 */
package org.agilebackoffice.jira.workflow.condition.vcs;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.index.IndexException;
import com.xiplink.jira.git.MultipleGitRepositoryManager;
import com.xiplink.jira.git.revisions.RevisionInfo;

/**
 * @author kamann
 *
 */
@Component
public class GitVCSConnector implements VCSConnector {
	
	//@Autowired
	private MultipleGitRepositoryManager repositoryManager;
	
	public GitVCSConnector(MultipleGitRepositoryManager repositoryManager){
		this.repositoryManager = repositoryManager;
	}
	

	/* (non-Javadoc)
	 * @see org.agilebackoffice.jira.workflow.condition.vcs.VCSConnector#setup()
	 */
	@Override
	public VCSConnector setup() throws VCSConnectorException {
		return this;
	}

	/* (non-Javadoc)
	 * @see org.agilebackoffice.jira.workflow.condition.vcs.VCSConnector#findLatestChangeDateByIssue(com.atlassian.jira.issue.Issue)
	 */
	@Override
	public Date findLatestChangeDateByIssue(Issue issue) {
		Date latestCodeChange = null;		
		
		Object ri = invokeMethod(repositoryManager, "getRevisionIndexer", new Class[0], new Object[0]);

		Object entries = invokeMethod(ri, "getLogEntriesByRepository", new Class[]{Issue.class}, new Object[]{issue});

		List entriesAsList = (List) entries;
		int latestTime = Integer.MIN_VALUE;
		for (Object entry : entriesAsList) {
			Object commit = invokeMethod(entry, "getCommit", new Class[0], new Object[0]);
			int commitTime = (Integer) invokeMethod(commit, "getCommitTime", new Class[0], new Object[0]);
			if (commitTime > latestTime){
				latestTime = commitTime;
			}
		}
		
		latestCodeChange = new Date(latestTime*1000L);
		System.out.println("Git: "+latestCodeChange);

		return latestCodeChange;
		
	}
	
	private Object invokeMethod(Object objectWithMethod2Invoke, String methodName, Class[] methodParameterTypes, Object[] methodParameters){
		Object resultingObject = null;
		
		try {
			Method m = objectWithMethod2Invoke.getClass().getMethod(methodName, methodParameterTypes);
			resultingObject =  m.invoke(objectWithMethod2Invoke, methodParameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		return resultingObject;
		
	}

}
