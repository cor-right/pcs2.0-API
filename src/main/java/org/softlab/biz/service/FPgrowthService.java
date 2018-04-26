package org.softlab.biz.service;

import java.util.Map;

/**
 * Created by Jiaxu_Zou on 2018-4-26
 */
public interface FPgrowthService {

    /**
     * get id by name
     */
    Integer getIdByName(String name);

    /**
     * get id's related data by fpgrowth
     * @param id
     * @return
     */
    Map getFPgrowthResult(Integer id, String name);

}
