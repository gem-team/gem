package cn.gemframe.business.domain;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

@Data
public class TaskDetails implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private Map<String,Object> variables;
}
