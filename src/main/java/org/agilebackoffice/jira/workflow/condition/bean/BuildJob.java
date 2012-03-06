/**
 * 
 */
package org.agilebackoffice.jira.workflow.condition.bean;

import java.util.Date;

/**
 * @author kamann
 * 
 */
public class BuildJob {
	private String jobName;
	private BuildResult latestBuildResult;
	private Date latestBuildDate;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public BuildResult getLatestBuildResult() {
		return latestBuildResult;
	}

	public void setLatestBuildResult(BuildResult latestBuildResult) {
		this.latestBuildResult = latestBuildResult;
	}

	public Date getLatestBuildDate() {
		return latestBuildDate;
	}

	public void setLatestBuildDate(Date latestBuildDate) {
		this.latestBuildDate = latestBuildDate;
	}

}
