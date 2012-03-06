/**
 * 
 */
package org.agilebackoffice.jira.workflow.condition;

import java.util.HashMap;
import java.util.Map;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.plugin.ext.subversion.MultipleSubversionRepositoryManager;
import com.atlassian.jira.plugin.workflow.AbstractWorkflowPluginFactory;
import com.atlassian.jira.plugin.workflow.WorkflowPluginConditionFactory;
import com.opensymphony.workflow.loader.AbstractDescriptor;

/**
 * @author kamann
 *
 */
public class WorkflowLatestBuildIsGreenConditionPluginFactory extends
		AbstractWorkflowPluginFactory implements WorkflowPluginConditionFactory {
	private MultipleSubversionRepositoryManager multipleSubversionRepositoryManager;
	
	public WorkflowLatestBuildIsGreenConditionPluginFactory(MultipleSubversionRepositoryManager multipleSubversionRepositoryManager){
		this.multipleSubversionRepositoryManager = multipleSubversionRepositoryManager;
	}

	/* (non-Javadoc)
	 * @see com.atlassian.jira.plugin.workflow.WorkflowPluginFactory#getDescriptorParams(java.util.Map)
	 */
	@Override
	public Map<String, ?> getDescriptorParams(Map<String, Object> formParams) {
		Map<String, ?> descriptorParams = new HashMap<String, Object>();
		System.out.println(multipleSubversionRepositoryManager.getRevisionIndexer().getIndexPath());
		return descriptorParams;
	}

	/* (non-Javadoc)
	 * @see com.atlassian.jira.plugin.workflow.AbstractWorkflowPluginFactory#getVelocityParamsForEdit(java.util.Map, com.opensymphony.workflow.loader.AbstractDescriptor)
	 */
	@Override
	protected void getVelocityParamsForEdit(Map<String, Object> arg0,
			AbstractDescriptor arg1) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.atlassian.jira.plugin.workflow.AbstractWorkflowPluginFactory#getVelocityParamsForInput(java.util.Map)
	 */
	@Override
	protected void getVelocityParamsForInput(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.atlassian.jira.plugin.workflow.AbstractWorkflowPluginFactory#getVelocityParamsForView(java.util.Map, com.opensymphony.workflow.loader.AbstractDescriptor)
	 */
	@Override
	protected void getVelocityParamsForView(Map<String, Object> arg0,
			AbstractDescriptor arg1) {
		// TODO Auto-generated method stub

	}

}
