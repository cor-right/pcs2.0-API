package org.softlab.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softlab.biz.service.SolrService;
import org.softlab.core.model.PestInfo;
import org.softlab.core.model.vo.PaperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * created by Jiaxu_Zou on 2017-12-09
 */
@Controller
public class SearchController {

	private final SolrService solrService;

	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	public SearchController(SolrService solrService) {
		this.solrService = solrService;
	}
	
	/**
     * get a key word and return
     * both the key words search result anits concerned key words
     */
    @RequestMapping(value = "/search")
    @ResponseBody
    public List<PestInfo> getSearch(String content) {
        logger.info("POST Get Search : " + content);
        try {
        	return solrService.selectPest(content);
        }catch(Exception ee) {
        	logger.error(ee.getMessage());
        	return null;
        }
    }
    
    /**
     * search in solr
     */
    @RequestMapping(value = "/searchSolr")
    @ResponseBody
    public PaperVo searchSolr(String content, String curPage) {
    	logger.info("POST Search Solr : {\"content\" : " + content + " , \"curPage\" : " + curPage + "}");
    	try {
    		PaperVo vo =  solrService.selectPapers(content, curPage);
    		return vo;
    	}catch(Exception ee) {
    		logger.error(ee.getMessage());
    		return null;
    	}
    }
	
}
