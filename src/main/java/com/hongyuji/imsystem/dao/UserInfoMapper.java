package com.hongyuji.imsystem.dao;

import com.hongyuji.imsystem.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;


@Mapper
public interface UserInfoMapper {
    @Delete({
        "delete from t_user_info",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_user_info (uid, nickname, ",
        "password, icon_url, ","yn)",
        "values (#{uid,jdbcType=BIGINT}, #{nickname,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{iconUrl,jdbcType=VARCHAR},","0)"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UserInfo record);

    @InsertProvider(type=UserInfoSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UserInfo record);

    @Select({
        "select",
        "id, uid, nickname, password, icon_url, yn, create_time, update_time",
        "from t_user_info",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results(id = "userInfo", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon_url", property="iconUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="yn", property="yn", jdbcType=JdbcType.BIT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    UserInfo selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserInfo record);

    @Update({
        "update t_user_info",
        "set uid = #{uid,jdbcType=BIGINT},",
          "nickname = #{nickname,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "icon_url = #{iconUrl,jdbcType=VARCHAR},",
          "yn = #{yn,jdbcType=BIT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserInfo record);

    @Select("SELECT * FROM t_user_info WHERE uid=#{uid} AND password=#{password} limit 1")
    @ResultMap("userInfo")
    UserInfo selectValidUser(UserInfo userInfo);
}