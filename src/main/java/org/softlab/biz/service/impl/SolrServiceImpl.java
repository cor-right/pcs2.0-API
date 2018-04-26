package org.softlab.biz.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softlab.biz.manager.Solr;
import org.softlab.biz.service.SolrService;
import org.softlab.common.constant.PageConfig;
import org.softlab.core.mapper.SourceMapper;
import org.softlab.core.model.Paper;
import org.softlab.core.model.PestInfo;
import org.softlab.core.vo.PaperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jiaxu_Zou on 2017-12-09
 */
@Service
public class SolrServiceImpl implements SolrService {

	// solr dao
	private Solr solr;

	// mapper
	private final SourceMapper sourceMapper;

	// logger
	private static final Logger logger = LoggerFactory.getLogger(SolrServiceImpl.class);
	
	@Autowired
	public SolrServiceImpl(Solr solr, SourceMapper sourceMapper) {
		this.solr = solr;
		this.sourceMapper = sourceMapper;
	}

	@Override
	public List<PestInfo> selectPest(String content) {
		// get pests name list by algorithm
		List<String> pests = new LinkedList();
		pests.add("蝗虫");
		pests.add("草履蚧");
		pests.add("杨小舟蛾");
		// find concerned paper number of each pest
		List<PestInfo> data = new LinkedList();
		try {
			for (String pestName : pests) {
				Integer paperNumber = solr.selectPaperCount(pestName);
				data.add(new PestInfo(pestName, paperNumber));
			}
		}catch(Exception ee) {
			logger.error(ee.getMessage());
			return null;
		}
		return data;
	}

	@Override
	public PaperVo selectPapers(String content, String curPage) {
		PaperVo vo = new PaperVo();
		try {
			Map<String, Object> data = solr.selectPapersData(content, Integer.parseInt(curPage));
			vo.setPaperList((List<Paper>)(data.get("data")));
			Integer pageNum = ((((Long)(data.get("paper_num"))).intValue() + PageConfig.PAPER_LIST_PAGESIZE - 1 ) / PageConfig.PAPER_LIST_PAGESIZE);
			vo.setAllPage(pageNum);
			vo.setCurPage(Integer.parseInt(curPage));
			vo.setPageSize(PageConfig.PAPER_LIST_PAGESIZE);
		} catch (Exception ee) {
			logger.error(ee.getMessage());
			return null;
		}
		return vo;
	}



}
