package com.starblog.dao;

import com.starblog.entry.ContentEntry;
import com.starblog.entry.UserEntry;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SqlQuery {
    /*
        SELECTS
     */
    /**
     * @apiNote 获取指定作者的所有文章
     * @param userEntry 用户实体
     * @return 指定作者的所有文章
     */
    @Select("SELECT * FROM contents WHERE uid=(SELECT uid FROM users WHERE name=#{name})")
    List<ContentEntry> getContentByUser(UserEntry userEntry);

    /**
     * @return 用户实体
     * @apiNote 按用户名查找指定用户信息
     */
    @Select("SELECT * FROM users WHERE name=#{name}")
    UserEntry getUserByUserName(String name);

    /*
        INSERTS
     */

    /**
     * @param userEntry 用户实体
     * @apiNote 添加用户信息
     */
    @Insert("INSERT INTO users(name,password,email,registerTime,activeTime) VALUES(#{name},#{password},#{email},#{registerTime},#{activeTime})")
    void addUser(UserEntry userEntry);

    /**
     * @apiNote 添加文章
     * @param contentEntry 文章实体
     */
    @Insert("INSERT INTO contents(title,createTime,modifiedTime,content,uid) VALUES(#{title},#{createTime},#{modifiedTime},#{content},#{uid})")
    void addContent(ContentEntry contentEntry);

    /*
        UPDATES
     */

    /**
     * @apiNote 更新用户最近登录时间
     * @param name 用户名
     * @param activeTime 登录时间
     */
    @Update("UPDATE users SET activeTime=#{activeTime} WHERE name=#{name}")
    void updateUserActiveTime(String name, String activeTime);

    /**
     * @apiNote 更新文章
     * @param contentEntry 文章实体
     */
    @Update("UPDATE contents SET title=#{title},modifiedTime=#{modifiedTime},content=#{content} WHERE uid=#{uid}")
    void updateContent(ContentEntry contentEntry);

    /*
        DELETES
     */

    /**
     * @apiNote 注销用户
     * @param userEntry 用户实体
     */
    @Delete("DELETE FROM users WHERE name=#{name}")
    void deleteUser(UserEntry userEntry);

    /**
     * @apiNote 删除指定用户的所有文章
     * @param userEntry 用户实体
     */
    @Delete("DELETE FROM contents WHERE uid=(SELECT uid FROM users WHERE name=#{name})")
    void deleteContentsByUser(UserEntry userEntry);
}
