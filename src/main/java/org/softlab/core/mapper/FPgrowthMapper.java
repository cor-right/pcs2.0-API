package org.softlab.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.softlab.core.model.FPgrowth;
import org.springframework.stereotype.Repository;

/**
 * Created by Jiaxu_Zou on 2018-4-26
 */
@Repository
@Mapper
public interface FPgrowthMapper {

    /**
     * select fpgrowth data by rid
     * @param rid
     * @return FPgrowth model
     */
    @Select("SELECT * FROM `fpgrowth` WHERE `rid`=#{rid}")
    FPgrowth selectFPgrowthDataByID(Integer rid);


}
