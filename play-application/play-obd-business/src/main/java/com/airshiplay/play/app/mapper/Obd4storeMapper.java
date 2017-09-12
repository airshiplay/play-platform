package com.airshiplay.play.app.mapper;

import com.airshiplay.play.app.model.Obd4store;
import com.airshiplay.play.app.model.Obd4storeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Obd4storeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table obd_4store
     *
     * @date
     */
    long countByExample(Obd4storeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table obd_4store
     *
     * @date
     */
    int deleteByExample(Obd4storeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table obd_4store
     *
     * @date
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table obd_4store
     *
     * @date
     */
    int insert(Obd4store record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table obd_4store
     *
     * @date
     */
    int insertSelective(Obd4store record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table obd_4store
     *
     * @date
     */
    List<Obd4store> selectByExample(Obd4storeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table obd_4store
     *
     * @date
     */
    Obd4store selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table obd_4store
     *
     * @date
     */
    int updateByExampleSelective(@Param("record") Obd4store record, @Param("example") Obd4storeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table obd_4store
     *
     * @date
     */
    int updateByExample(@Param("record") Obd4store record, @Param("example") Obd4storeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table obd_4store
     *
     * @date
     */
    int updateByPrimaryKeySelective(Obd4store record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table obd_4store
     *
     * @date
     */
    int updateByPrimaryKey(Obd4store record);
}