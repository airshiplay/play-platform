package com.airshiplay.play.app.mapper;

import com.airshiplay.play.app.model.CoreUserRole;
import com.airshiplay.play.app.model.CoreUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CoreUserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_user_role
     * @mbggenerated
     */
    int countByExample(CoreUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_user_role
     * @mbggenerated
     */
    int deleteByExample(CoreUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_user_role
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_user_role
     * @mbggenerated
     */
    int insert(CoreUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_user_role
     * @mbggenerated
     */
    int insertSelective(CoreUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_user_role
     * @mbggenerated
     */
    List<CoreUserRole> selectByExample(CoreUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_user_role
     * @mbggenerated
     */
    CoreUserRole selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_user_role
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CoreUserRole record, @Param("example") CoreUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_user_role
     * @mbggenerated
     */
    int updateByExample(@Param("record") CoreUserRole record, @Param("example") CoreUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_user_role
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CoreUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_user_role
     * @mbggenerated
     */
    int updateByPrimaryKey(CoreUserRole record);
}