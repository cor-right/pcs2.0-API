package org.softlab.biz.manager;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.softlab.common.constant.PageConfig;
import org.softlab.common.constant.SolrConfig;
import org.softlab.core.model.Paper;
import org.springframework.stereotype.Service;

/**
 * created by Jiaxu_Zou on 2017-12-09
 */
@Service
public class Solr {
	
	private HttpSolrServer solrServer;
	
	public Solr() {
		this.solrServer = new HttpSolrServer(SolrConfig.SOLR_SERVER);
	}
	
	
	/**
	 * search count contains key word
	 * @throws SolrServerException 
	 */
	public Integer selectPaperCount(String word) throws SolrServerException, IOException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("record_keyword:*" + word +"*\r\nrecord_title:*" + word + "*"+"*\r\nrecord_summary:*" + word + "*");
		QueryResponse qr = solrServer.query(solrQuery);
		SolrDocumentList resultList = qr.getResults();
		System.out.println(resultList.getNumFound());
		return (int)(resultList.getNumFound());
	}
	
	/**
	 * select papers about specific key word
	 * @throws SolrServerException 
	 */
	public Map<String, Object> selectPapersData(String word, Integer page) throws SolrServerException, IOException {
		Map<String, Object> data = new HashMap<>();
		System.out.println("!!! " + word);
		// query
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("record_keyword:*" + word +"*\r\nrecord_title:*" + word + "*"+"*\r\nrecord_summary:*" + word + "*");
		solrQuery.setStart((page - 1) * PageConfig.PAPER_LIST_PAGESIZE );
		solrQuery.setRows(PageConfig.PAPER_LIST_PAGESIZE);
		QueryResponse qr = solrServer.query(solrQuery);
		// result process
		SolrDocumentList resultList = qr.getResults();
		List<Paper> paperList = new LinkedList<>();
		for (SolrDocument doc : resultList) {
			String summary = String.valueOf(doc.get("record_summary"));
			if (summary.length() > 256)
				summary = summary.substring(0, 256);
			summary = summary.concat(" ... ");
			paperList.add(new Paper(String.valueOf(doc.get("record_title")), summary));
		}
		data.put("paper_num", resultList.getNumFound());
		data.put("data", paperList);
		return data;
	}
	
	
}
