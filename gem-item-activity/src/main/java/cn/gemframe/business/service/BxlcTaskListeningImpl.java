package cn.gemframe.business.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gemframe.business.domain.ProcessVersion;
import cn.gemframe.config.exception.GemFrameException;
import cn.gemframe.config.exception.status.GemFrameErrorStatus;
import cn.gemframe.config.utils.GemFrameJsonUtils;
import cn.gemframe.config.utils.GemFrameStringUtlis;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gemframe.business.domain.TaskDetails;

@Service
public class BxlcTaskListeningImpl implements TaskListener {

	private static final long serialVersionUID = 1L;

	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;

	@Override
	public void notify(DelegateTask delegateTask) {
		String name = delegateTask.getTaskDefinitionKey();
		name=name.trim().replaceAll(" ","");
		if(GemFrameStringUtlis.isBlank(name) || name.equalsIgnoreCase("null") && name.length()==0) {
			throw new GemFrameException(GemFrameErrorStatus.PARAMETER_ERROR);
		}
		if (name.equalsIgnoreCase("submit")) {
			delegateTask.setAssignee("1");
		}
		if (name.equalsIgnoreCase("project")) {
			delegateTask.setAssignee("2");
		}
		if (name.equalsIgnoreCase("department")) {
			delegateTask.setAssignee("3");
		}
	}

	public Integer submitBxlc(String rmb, String key) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("rmb", rmb);
		runtimeService.startProcessInstanceByKey(key, variables);
		return null;
	}

	/**
	 * @Description:发布流程
	 * @param type 流程的key
	 * @author: 赵兴炎
	 * @date 2018年11月18日
	 */
	public String deployment(String type) {
		DeploymentBuilder createDeployment = repositoryService.createDeployment();
		createDeployment.addClasspathResource("processes/"+type+".bpmn");
		createDeployment.addClasspathResource("processes/"+type+".png");
		Deployment deploy = createDeployment.deploy();
		return deploy.getId();
	}

	/**
	 * @Description:填写申请的表单
	 * @param rmb 报销金额
	 * @param key 流程的key
	 * @author: 赵兴炎
	 * @date 2018年11月18日
	 */
	public String saveBxlcForm(String rmb, String key) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("rmb", rmb);
		ProcessInstance startProcessInstanceByKey = runtimeService.startProcessInstanceByKey(key, variables);
		String processInstanceId = startProcessInstanceByKey.getProcessInstanceId();
		return processInstanceId;
	}

	/**
	 * @Description:流程列表
	 * @param id 用户主键
	 * @author: 赵兴炎
	 * @date 2018年11月18日
	 */
	public List<TaskDetails> findTaskList(String id) {
		List<TaskDetails> arrayList = new ArrayList<TaskDetails>();
		TaskQuery createTaskQuery = taskService.createTaskQuery();
		createTaskQuery.taskAssignee(id);
		List<Task> list = createTaskQuery.list();
		if(list!=null && list.size()>0) {
			for (Task task : list) {
				TaskDetails taskDetails = new TaskDetails();
				String id2 = task.getId();
				taskDetails.setId(id2);
				taskDetails.setName(task.getName());
				taskDetails.setVariables(taskService.getVariables(id2));
				arrayList.add(taskDetails);
			}
		}
		return arrayList;
	}

	/**
	 * @Description:执行任务
	 * @param id 任务主键
	 * @param cause 备注
	 * @author: 赵兴炎
	 * @date 2018年11月18日
	 */
	public String performTask(String id, String cause) {
		Map<String, Object> variables = taskService.getVariables(id);
		variables.put(id, cause);
		System.out.println(GemFrameJsonUtils.ObjectToJson(variables));
		taskService.complete(id, variables);
		return null;
	}

	/**
	 * @Description:流程列表
	 * @author: 赵兴炎
	 * @date 2018年11月18日
	 */
	public List<ProcessVersion> findProcessList() {
		List<ProcessVersion> arrayList = new ArrayList<ProcessVersion>();
		ProcessDefinitionQuery createProcessDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		createProcessDefinitionQuery.latestVersion();
		List<ProcessDefinition> list = createProcessDefinitionQuery.list();
		if(list!=null && list.size()>0) {
			for (ProcessDefinition processDefinition : list) {
				ProcessVersion processVersion = new ProcessVersion();
				processVersion.setId(processDefinition.getId());
				processVersion.setName(processDefinition.getName());
				processVersion.setKey(processDefinition.getKey());
				arrayList.add(processVersion);
			}
		}
		return arrayList;
	}

	/**
	 * @Description:删除流程
	 * @param id 流程主键
	 * @author: 赵兴炎
	 * @date 2018年11月18日
	 */
	public String deletProcess(String id) {
		repositoryService.deleteDeployment(id,true);
		return null;
	}

	/**
	 * @Description:查看流程图
	 * @param id 流程主键
	 * @author: 赵兴炎
	 * @date 2018年11月18日
	 */
	public InputStream image(String id) {
		return repositoryService.getProcessDiagram(id);
	}

}
