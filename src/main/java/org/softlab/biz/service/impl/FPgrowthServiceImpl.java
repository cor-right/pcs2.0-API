package org.softlab.biz.service.impl;

import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softlab.biz.manager.Solr;
import org.softlab.biz.service.FPgrowthService;
import org.softlab.biz.service.SolrService;
import org.softlab.core.mapper.FPgrowthMapper;
import org.softlab.core.mapper.SourceMapper;
import org.softlab.core.model.FPgrowth;
import org.softlab.core.model.PestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class FPgrowthServiceImpl implements FPgrowthService {

    // mapper
    private final SourceMapper sourceMapper;
    private final FPgrowthMapper fpgrowthMapper;

    // solr dao
    private Solr solr;

    // service
    private SolrService solrService;

    // logger
    private static final Logger logger = LoggerFactory.getLogger(FPgrowthService.class);

    @Autowired
    public FPgrowthServiceImpl(SourceMapper sourceMapper, FPgrowthMapper fpgrowthMapper, SolrService solrService) {
        this.sourceMapper = sourceMapper;
        this.fpgrowthMapper = fpgrowthMapper;
        this.solrService = solrService;
    }

    @Override
    public Integer getIdByName(String name) {
        return sourceMapper.selectIdByName(name);
    }

    @Override
    public Map getFPgrowthResult(Integer id, String name) {
        // get data
        FPgrowth data = fpgrowthMapper.selectFPgrowthDataByID(id);
        if (data == null)
            return null;
        // parse data
        String originalRelationString = data.getRelation();
        if (originalRelationString.trim().equals("") == true)
            return null;
        List<PestInfo> related = new LinkedList<>();
        for (String strID : originalRelationString.split(" ")) {
            Integer intID = Integer.parseInt(strID.trim()); // get id
            FPgrowth temp = fpgrowthMapper.selectFPgrowthDataByID(intID);
            String pestName = sourceMapper.selectNameByID(intID);
//            PestInfo info = new PestInfo(pestName, (int) solrService.selectPapers(pestName, "1").getAllPage());
            PestInfo info = new PestInfo(pestName, temp.getSupport());
            related.add(info);
        }
        // load data into map
        Map fpgrowthMap = new HashMap();
        fpgrowthMap.put("name", name);
        fpgrowthMap.put("support", data.getSupport());
        fpgrowthMap.put("related", related);
        return fpgrowthMap;
    }

}
