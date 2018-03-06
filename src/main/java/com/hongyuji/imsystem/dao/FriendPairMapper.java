package com.hongyuji.imsystem.dao;

import com.hongyuji.imsystem.domain.FriendPair;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface FriendPairMapper {
    @Delete({
        "delete from t_friend_pair",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_friend_pair (uid, fid, ",
        "yn, create_time, update_time)",
        "values (#{uid,jdbcType=BIGINT}, #{fid,jdbcType=BIGINT}, ",
        "#{yn,jdbcType=BIT}, #{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(FriendPair record);

    @InsertProvider(type=FriendPairSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(FriendPair record);

    @Select({
        "select",
        "id, uid, fid, yn, create_time, update_time",
        "from t_friend_pair",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="fid", property="fid", jdbcType=JdbcType.BIGINT),
        @Result(column="yn", property="yn", jdbcType=JdbcType.BIT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    FriendPair selectByPrimaryKey(Long id);

    @UpdateProvider(type=FriendPairSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FriendPair record);

    @Update({
        "update t_friend_pair",
        "set uid = #{uid,jdbcType=BIGINT},",
          "fid = #{fid,jdbcType=BIGINT},",
          "yn = #{yn,jdbcType=BIT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(FriendPair record);
}