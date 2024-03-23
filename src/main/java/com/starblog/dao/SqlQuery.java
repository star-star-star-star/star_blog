package com.starblog.dao;

import com.starblog.entry.AdministratorEntry;
import com.starblog.entry.CommentEntry;
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
     * @return 存有所有管理员信息的列表
     * @apiNote 获取所有管理员信息
     */
    @Select("SELECT * FROM admins")
    List<AdministratorEntry> getAllAdministrators();

    /**
     * @return 存有所有评论信息的列表
     * @apiNote 获取所有评论信息
     */
    @Select("SELECT * FROM comments")
    List<CommentEntry> getAllComments();

    /**
     * @return 存有所有文章信息的列表
     * @apiNote 获取所有文章信息
     */
    @Select("SELECT * FROM contents")
    List<ContentEntry> getAllContents();


    /**
     * @apiNote 获取指定作者的所有文章
     * @param userEntry 用户实体
     * @return 指定作者的所有文章
     */
    @Select("SELECT * FROM contents WHERE uid=(SELECT uid FROM users WHERE name=#{name})")
    List<ContentEntry> getContentByUser(UserEntry userEntry);

    /**
     * @return 存有所有用户信息的列表
     * @apiNote 获取所有用户信息
     */
    @Select("SELECT * FROM users")
    List<UserEntry> getAllUsers();

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
    @Insert("INSERT INTO users(name,password,email,registerTime,activeTime,instruction) VALUES(#{name},#{password},#{email},#{registerTime},#{activeTime},#{instruction})")
    void addUser(UserEntry userEntry);

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
     * @apiNote 删除指定用户的所有评论
     * @param userEntry 用户实体
     */
    @Delete("DELETE FROM comments WHERE uid=(SELECT uid FROM users WHERE name=#{name})")
    void deleteCommentsByUser(UserEntry userEntry);

    /**
     * @apiNote 删除指定用户的所有文章
     * @param userEntry 用户实体
     */
    @Delete("DELETE FROM contents WHERE uid=(SELECT uid FROM users WHERE name=#{name})")
    void deleteContentsByUser(UserEntry userEntry);
}
