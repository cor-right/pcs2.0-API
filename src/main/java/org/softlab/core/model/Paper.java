package org.softlab.core.model;

/**
 * created by Jiaxu_Zou on 2017-12-09
 */
public class Paper {

	
	private String record_title;
	private String record_summary;
	
	
	public String getRecord_title() {
		return record_title;
	}
	public void setRecord_title(String record_title) {
		this.record_title = record_title;
	}
	public String getRecord_summary() {
		return record_summary;
	}
	public void setRecord_summary(String record_summary) {
		this.record_summary = record_summary;
	}

	public Paper() {}
	
	public Paper(String record_title, String record_summary) {
		super();
		this.record_title = record_title;
		this.record_summary = record_summary;
	}
	
	
}
