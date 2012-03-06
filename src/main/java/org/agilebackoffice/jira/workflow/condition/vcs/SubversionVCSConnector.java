/**
 * 
 */
package org.agilebackoffice.jira.workflow.condition.vcs;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.SVNLogEntry;

import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.index.IndexException;
import com.atlassian.jira.plugin.ext.subversion.MultipleSubversionRepositoryManager;
import com.atlassian.jira.plugin.ext.subversion.revisions.RevisionIndexer;

/**
 * @author kamann
 * 
 */
public class SubversionVCSConnector implements VCSConnector {
	private static Logger log = Logger.getLogger(SubversionVCSConnector.class);
	private RevisionIndexer revisionIndexer;

	@Override
	public VCSConnector setup() throws VCSConnectorException {
		MultipleSubversionRepositoryManager multipleSubversionRepositoryManager = ComponentManager
				.getComponent(MultipleSubversionRepositoryManager.class);
		revisionIndexer = multipleSubversionRepositoryManager.getRevisionIndexer();
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.agilebackoffice.jira.workflow.condition.vcs.VCSConnector#
	 * findLatestChangeDateByIssue(com.atlassian.jira.issue.Issue)
	 */
	@Override
	public Date findLatestChangeDateByIssue(final Issue issue) {
		Date latestChange;
		Map<Long, List<SVNLogEntry>> entries;

		entries = findLogEntriesByIssue(issue);
		latestChange = findLatestLogEntryDateByRevision(entries);

		System.out.println("Latest CheckIn in SVN: " + latestChange);

		return latestChange;
	}

	private Map<Long, List<SVNLogEntry>> findLogEntriesByIssue(Issue issue) {
		Map<Long, List<SVNLogEntry>> entries = new HashMap<Long, List<SVNLogEntry>>();
		
		try {
			if (revisionIndexer != null){
				entries =  revisionIndexer.getLogEntriesByRepository(issue);
			}
		} catch (IndexException e) {
			log.error(e.getLocalizedMessage(), e);
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return entries;
	}

	private Date findLatestLogEntryDateByRevision(
			Map<Long, List<SVNLogEntry>> logEntriesByRevision) {
		List<SVNLogEntry> latestLogEntries = new ArrayList<SVNLogEntry>();

		for (Map.Entry<Long, List<SVNLogEntry>> entry : logEntriesByRevision
				.entrySet()) {
			latestLogEntries.add(findLatestLogEntry(entry.getValue()));
		}
		
		SVNLogEntry  latestLogEntry = findLatestLogEntry(latestLogEntries);
		return (latestLogEntry != null)? latestLogEntry.getDate(): null;
	}

	@SuppressWarnings("rawtypes")
	private SVNLogEntry findLatestLogEntry(List<SVNLogEntry> entries) {
		SVNLogEntry lastLogEntry = null;
		for (int i = 0; i < entries.size(); i++) {
			Object entry = entries.get(i);

			Method m;
			try {
				m = entry.getClass().getMethod("getDate", new Class[0]);
				Date ed = (Date) m.invoke(entry, new Object[0]);

				if (lastLogEntry == null
						|| ed.compareTo(lastLogEntry.getDate()) > 0) {
					lastLogEntry = new SVNLogEntry(new HashMap(), 0, "", ed, "");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lastLogEntry;
	}

}
