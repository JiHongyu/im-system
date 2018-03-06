package com.hongyuji.imsystem.dao;

import com.hongyuji.imsystem.domain.FriendPair;
import org.apache.ibatis.jdbc.SQL;

public class FriendPairSqlProvider {

    public String insertSelective(FriendPair record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_friend_pair");
        
        if (record.getUid() != null) {
            sql.VALUES("uid", "#{uid,jdbcType=BIGINT}");
        }
        
        if (record.getFid() != null) {
            sql.VALUES("fid", "#{fid,jdbcType=BIGINT}");
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

    public String updateByPrimaryKeySelective(FriendPair record) {
        SQL sql = new SQL();
        sql.UPDATE("t_friend_pair");
        
        if (record.getUid() != null) {
            sql.SET("uid = #{uid,jdbcType=BIGINT}");
        }
        
        if (record.getFid() != null) {
            sql.SET("fid = #{fid,jdbcType=BIGINT}");
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