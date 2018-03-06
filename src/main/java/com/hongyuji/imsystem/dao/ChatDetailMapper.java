package com.hongyuji.imsystem.dao;

import com.hongyuji.imsystem.domain.ChatDetail;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;


@Mapper
public interface ChatDetailMapper {
    @Delete({
        "delete from t_chat_detail",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_chat_detail (sender, receiver, ",
        "context, yn)",
        "values (#{sender,jdbcType=BIGINT}, #{receiver,jdbcType=BIGINT}, ",
        "#{context,jdbcType=VARCHAR}, 0)"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(ChatDetail record);

    @InsertProvider(type=ChatDetailSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(ChatDetail record);

    @Select({
        "select",
        "id, sender, receiver, context, yn, create_time, update_time",
        "from t_chat_detail",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="sender", property="sender", jdbcType=JdbcType.BIGINT),
        @Result(column="receiver", property="receiver", jdbcType=JdbcType.BIGINT),
        @Result(column="context", property="context", jdbcType=JdbcType.VARCHAR),
        @Result(column="yn", property="yn", jdbcType=JdbcType.BIT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    ChatDetail selectByPrimaryKey(Long id);

    @UpdateProvider(type=ChatDetailSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ChatDetail record);

    @Update({
        "update t_chat_detail",
        "set sender = #{sender,jdbcType=BIGINT},",
          "receiver = #{receiver,jdbcType=BIGINT},",
          "context = #{context,jdbcType=VARCHAR},",
          "yn = #{yn,jdbcType=BIT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ChatDetail record);
}