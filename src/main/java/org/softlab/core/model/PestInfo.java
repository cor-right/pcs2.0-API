package org.softlab.core.model;

/**
 * created by Jiaxu_Zou on 2017-12-09
 */
public class PestInfo {
	
	private String pestName;
	private Integer paperNumber;
	
	public String getPestName() {
		return pestName;
	}
	public void setPestName(String pestName) {
		this.pestName = pestName;
	}
	public Integer getPaperNumber() {
		return paperNumber;
	}
	public void setPaperNumber(Integer paperNumber) {
		this.paperNumber = paperNumber;
	}
	
	public PestInfo() {}
	
	public PestInfo(String pestName, Integer paperNumber) {
		super();
		this.pestName = pestName;
		this.paperNumber = paperNumber;
	}
	
	

}
