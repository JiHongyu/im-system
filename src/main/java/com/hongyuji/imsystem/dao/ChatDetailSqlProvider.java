package com.hongyuji.imsystem.dao;

import com.hongyuji.imsystem.domain.ChatDetail;
import org.apache.ibatis.jdbc.SQL;

public class ChatDetailSqlProvider {

    public String insertSelective(ChatDetail record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_chat_detail");
        
        if (record.getSender() != null) {
            sql.VALUES("sender", "#{sender,jdbcType=BIGINT}");
        }
        
        if (record.getReceiver() != null) {
            sql.VALUES("receiver", "#{receiver,jdbcType=BIGINT}");
        }
        
        if (record.getContext() != null) {
            sql.VALUES("context", "#{context,jdbcType=VARCHAR}");
        }
        
        if (record.getYn() != null) {
            sql.VALUES("yn", "#{yn,jdbcType=BIT}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ChatDetail record) {
        SQL sql = new SQL();
        sql.UPDATE("t_chat_detail");
        
        if (record.getSender() != null) {
            sql.SET("sender = #{sender,jdbcType=BIGINT}");
        }
        
        if (record.getReceiver() != null) {
            sql.SET("receiver = #{receiver,jdbcType=BIGINT}");
        }
        
        if (record.getContext() != null) {
            sql.SET("context = #{context,jdbcType=VARCHAR}");
        }
        
        if (record.getYn() != null) {
            sql.SET("yn = #{yn,jdbcType=BIT}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}