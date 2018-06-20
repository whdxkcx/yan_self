package com.yan.packageAmountCalculate.mapper;

import com.yan.packageAmountCalculate.model.Performance;
import com.yan.packageAmountCalculate.model.PerformanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PerformanceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table performance
     *
     * @mbg.generated Tue Jun 19 15:49:37 CST 2018
     */
    long countByExample(PerformanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table performance
     *
     * @mbg.generated Tue Jun 19 15:49:37 CST 2018
     */
    int deleteByExample(PerformanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table performance
     *
     * @mbg.generated Tue Jun 19 15:49:37 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table performance
     *
     * @mbg.generated Tue Jun 19 15:49:37 CST 2018
     */
    int insert(Performance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table performance
     *
     * @mbg.generated Tue Jun 19 15:49:37 CST 2018
     */
    int insertSelective(Performance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table performance
     *
     * @mbg.generated Tue Jun 19 15:49:37 CST 2018
     */
    List<Performance> selectByExample(PerformanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table performance
     *
     * @mbg.generated Tue Jun 19 15:49:37 CST 2018
     */
    Performance selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table performance
     *
     * @mbg.generated Tue Jun 19 15:49:37 CST 2018
     */
    int updateByExampleSelective(@Param("record") Performance record, @Param("example") PerformanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table performance
     *
     * @mbg.generated Tue Jun 19 15:49:37 CST 2018
     */
    int updateByExample(@Param("record") Performance record, @Param("example") PerformanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table performance
     *
     * @mbg.generated Tue Jun 19 15:49:37 CST 2018
     */
    int updateByPrimaryKeySelective(Performance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table performance
     *
     * @mbg.generated Tue Jun 19 15:49:37 CST 2018
     */
    int updateByPrimaryKey(Performance record);
}