package org.softlab.biz.service;


import org.softlab.core.model.PestInfo;
import org.softlab.core.vo.PaperVo;

import java.util.List;

/**
 * created by Jiaxu_Zou on 2017-12-09
 */
public interface SolrService {

	/**
	 * select pests key words  by name
	 */
	List<PestInfo> selectPest(String content);

	/**
	 * select papers about a specific living
	 */
	PaperVo selectPapers(String content, String curPage);



	
	
}
