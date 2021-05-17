package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallIntroduce;
import org.linlinjava.litemall.db.domain.LitemallIntroduceExample;

public interface LitemallIntroduceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_introduce
     *
     * @mbg.generated
     */
    long countByExample(LitemallIntroduceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_introduce
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallIntroduceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_introduce
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_introduce
     *
     * @mbg.generated
     */
    int insert(LitemallIntroduce record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_introduce
     *
     * @mbg.generated
     */
    int insertSelective(LitemallIntroduce record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_introduce
     *
     * @mbg.generated
     */
    LitemallIntroduce selectOneByExample(LitemallIntroduceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_introduce
     *
     * @mbg.generated
     */
    LitemallIntroduce selectOneByExampleSelective(@Param("example") LitemallIntroduceExample example, @Param("selective") LitemallIntroduce.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_introduce
     *
     * @mbg.generated
     */
    List<LitemallIntroduce> selectByExampleSelective(@Param("example") LitemallIntroduceExample example, @Param("selective") LitemallIntroduce.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_introduce
     *
     * @mbg.generated
     */
    List<LitemallIntroduce> selectByExample(LitemallIntroduceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_introduce
     *
     * @mbg.generated
     */
    LitemallIntroduce selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallIntroduce.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_introduce
     *
     * @mbg.generated
     */
    LitemallIntroduce selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_introduce
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallIntroduce record, @Param("example") LitemallIntroduceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_introduce
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallIntroduce record, @Param("example") LitemallIntroduceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_introduce
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallIntroduce record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_introduce
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallIntroduce record);
}