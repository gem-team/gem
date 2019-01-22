package cn.gemframe.business.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProcessVersion implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	private String key;

}
