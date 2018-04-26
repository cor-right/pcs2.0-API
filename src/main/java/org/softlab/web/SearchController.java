package org.softlab.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softlab.biz.service.FPgrowthService;
import org.softlab.biz.service.SolrService;
import org.softlab.common.RESTData;
import org.softlab.core.model.PestInfo;
import org.softlab.core.vo.PaperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * created by Jiaxu_Zou on 2017-12-09
 */
@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("api/search")
public class SearchController {

    // service
    private final SolrService solrService;
    private final FPgrowthService fpgrowthService;


    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    public SearchController(SolrService solrService, FPgrowthService fPgrowthService) {
        this.solrService = solrService;
        this.fpgrowthService = fPgrowthService;
    }


    /**
     * search in solr
     */
    @RequestMapping(value = "/artifact")
    public RESTData searchSolr(String name, String curPage) {
        logger.info("POST Search Solr : {\"name\" : " + name + " , \"curPage\" : " + curPage + "}");
        if (name == null || name.trim().equals("") == true)
            return new RESTData(1, "关键词参数不能为空");
        //
        PaperVo vo = solrService.selectPapers(name, curPage);
        return vo != null ? new RESTData(vo)
            : new RESTData(1, "数据库中文章信息为空");
    }

    /**
     * search related kind
     */
    @RequestMapping(value = "fpgrowth", method = RequestMethod.GET)
    public RESTData fpgrowthSearch(String name) {
        logger.info("Get Fpgrowth Kind Name. ");
        if (name == null || name.trim().equals("") == true)
            return new RESTData(1, "name不能为空. ");
        // get id
        Integer id = fpgrowthService.getIdByName(name);
        if (id == null)
            return new RESTData(1, "数据库中缺失该name的信息. ");
        // get gpgrowth data and support
        Map fpgrowthResultMap = fpgrowthService.getFPgrowthResult(id, name);
        return fpgrowthResultMap == null ? new RESTData(1, "为查询到FPgrowth的计算结果. ")
                : new RESTData(fpgrowthResultMap);
    }



}
