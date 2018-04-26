package org.softlab.core.vo;


import org.softlab.core.model.Paper;

import java.util.List;

/**
 *created by Jiaxu_Zou on 2017-12-10
 */
public class PaperVo {
	
	// data
	private List<Paper> paperList;
	
	// statistic
	private Integer curPage;
	private long allPage;
	private Integer pageSize;
	
	public List<Paper> getPaperList() {
		return paperList;
	}
	public void setPaperList(List<Paper> paperList) {
		this.paperList = paperList;
	}
	public Integer getCurPage() {
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	public long getAllPage() {
		return allPage;
	}
	public void setAllPage(long l) {
		this.allPage = l;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	@Override
	public String toString() {
		return "PaperVo [paperList=" + paperList + ", curPage=" + curPage + ", allPage=" + allPage + ", pageSize="
				+ pageSize + "]";
	}
	
	
	

}
