package org.agilebackoffice.jira.workflow.condition.vcs;


import org.junit.Assert;
import org.junit.Test;
import org.tmatesoft.svn.core.SVNLogEntry;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.ext.subversion.revisions.RevisionIndexer;
import groovy.mock.interceptor.MockFor

class SubversionVCSConnectorTest {
	
	@Test
	final void "test if the latest SVNLogEntry can be computed"(){
		List entries = []

		10.times{
			entries << new SVNLogEntry([:], it, "", new Date()-it, "")
		}
		
		def result = new SubversionVCSConnector().findLatestLogEntry(entries)		
		Assert.assertEquals(new Date().toCalendar().get(Calendar.DAY_OF_MONTH), result.date.toCalendar().get(Calendar.DAY_OF_MONTH))
	}
	
	@Test
	final void "test if one entry returns if two entries have the same date"(){
		def date = new Date()
		List entries = []
		
		2.times{
			entries << new SVNLogEntry([:], it, "", date, "")
		}
		
		def result = new SubversionVCSConnector().findLatestLogEntry(entries)
		Assert.assertNotNull(result)
	}
	
	@Test
	final void "test if the latest entry of a map with revisions and multiple entries per revision can be computed"(){
		Map map = [:]
		3.times{
			List entries = []
			5.times {
				entries << new SVNLogEntry([:], it, "", new Date()-it, "")
			}
			map[it] = entries
		}
		
		def result = new SubversionVCSConnector().findLatestLogEntryDateByRevision(map)
		Assert.assertEquals(new Date().toCalendar().get(Calendar.DAY_OF_MONTH), result.toCalendar().get(Calendar.DAY_OF_MONTH))		
	}
	
	@Test
	final void "test if the search for the latest change returns nothing"(){
		Assert.assertNull(new SubversionVCSConnector().findLatestChangeDateByIssue([:] as Issue))
	}
}
