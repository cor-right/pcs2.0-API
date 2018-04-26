package org.softlab.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SourceMapper {

    /**
     * select source id by name
     * @param name
     * @return
     */
    @Select("SELECT `id` FROM `t_source` WHERE `name`=#{name}")
    Integer selectIdByName(String name);

    /**
     * select source id by name
     * @param id
     * @return
     */
    @Select("SELECT `name` FROM `t_source` WHERE `id`=#{id}")
    String selectNameByID(Integer id);

}
