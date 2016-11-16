package com.airshiplay.play.app.mapper;

import com.airshiplay.play.app.model.CoreUsr;
import com.airshiplay.play.app.model.CoreUsrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CoreUsrMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_usr
     * @mbggenerated
     */
    int countByExample(CoreUsrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_usr
     * @mbggenerated
     */
    int deleteByExample(CoreUsrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_usr
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_usr
     * @mbggenerated
     */
    int insert(CoreUsr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_usr
     * @mbggenerated
     */
    int insertSelective(CoreUsr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_usr
     * @mbggenerated
     */
    List<CoreUsr> selectByExample(CoreUsrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_usr
     * @mbggenerated
     */
    CoreUsr selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_usr
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CoreUsr record, @Param("example") CoreUsrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_usr
     * @mbggenerated
     */
    int updateByExample(@Param("record") CoreUsr record, @Param("example") CoreUsrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_usr
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CoreUsr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_usr
     * @mbggenerated
     */
    int updateByPrimaryKey(CoreUsr record);
}