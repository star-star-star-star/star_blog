package com.starblog.service;

import com.starblog.dao.SqlQuery;
import com.starblog.entry.ContentEntry;
import com.starblog.entry.UserEntry;
import jakarta.annotation.Resource;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @apiNote 核心处理器
 */
@Service
public class Processor {
    /*
        fields
     */
    @Resource
    SqlQuery sqlQuery;

    /*
        methods
     */

    /**
     * @param password 明文密码
     * @return 加密后的密码
     * @apiNote 加密明文密码
     */
    @Contract("_ -> new")
    private @NotNull String enCodePassword(@NotNull String password) {
        byte[] src = password.getBytes();
        byte[] dst = new byte[255];
        byte[] temp = new byte[src.length * 2];

        for (int i = 0, j = 0; i < src.length; i++) {
            byte b = src[i];
            temp[j++] = (byte) (b % 10);
            temp[j++] = (byte) (b / 10);
        }
        double random = Math.random();
        while (random < 1.0d) {
            random *= 10;
        }
        int num = (int) random + 10;
        int start = num - 2;
        dst[start] = 1;
        dst[start + 1] = (byte) src.length;
        for (int i = start + 2, j = 0; j < temp.length; i++, j++) {
            dst[i] = temp[j];
        }

        return new String(dst);
    }

    /**
     * @param password 加密后的密码
     * @return 明文密码
     * @apiNote 解密加密密码
     */
    @Contract("_ -> new")
    private @NotNull String deCodePassword(@NotNull String password) {
        byte[] src = password.getBytes();
        int start = 0;
        for (int i = 0; i < src.length; i++) {
            if (src[i] == (byte) 1) {
                start = i;
                break;
            }
        }
        int length = src[start + 1];
        byte[] dst = new byte[length];
        for (int i = start + 2, j = 0; j < length; i += 2, j++) {
            dst[j] = (byte) (src[i] + src[i + 1] * 10);
        }

        return new String(dst);
    }

    /**
     * @param userName 用户名
     * @return 如果可用，返回true，否则返回false
     * @apiNote 用户名是否可用
     */
    public boolean isUserNameAvailable(@NotNull String userName) {
        return sqlQuery.getUserByUserName(userName) == null;
    }

    /**
     * @param userName 用户名
     * @param password 密码（未经加密）
     * @return 如果用户名和密码匹配，返回true，否则返回false
     * @apiNote 是否允许用户登录
     */
    public boolean isAllowToLogin(@NotNull String userName, @NotNull String password) {
        UserEntry user = sqlQuery.getUserByUserName(userName);
        return deCodePassword(user.getPassword()).equals(password);
    }

    /**
     * @param name 用户名
     * @apiNote 更新用户最近登录时间
     */
    public void updateUserActiveTime(@NotNull String name) {
        sqlQuery.updateUserActiveTime(name, ZonedDateTime.now().toString());
    }

    /**
     * @param name        用户名
     * @param password    密码（未加密）
     * @param email       邮箱
     * @apiNote 用户注册
     */
    public void addUser(@NotNull String name, @NotNull String password, @NotNull String email) {
        UserEntry user = new UserEntry(null, name, enCodePassword(password), email, ZonedDateTime.now().toString(), ZonedDateTime.now().toString());
        sqlQuery.addUser(user);
    }

    /**
     * @param name 用户名
     * @apiNote 注销用户
     */
    @Transactional
    public void deleteUser(@NotNull String name) {
        UserEntry user = getUserByName(name);
        sqlQuery.deleteContentsByUser(user);
        sqlQuery.deleteUser(user);
    }

    /**
     * @param name 用户名
     * @return 用户实体
     * @apiNote 获取指定用户的用户实体
     */
    public UserEntry getUserByName(@NotNull String name) {
        UserEntry user = sqlQuery.getUserByUserName(name);
        user.setPassword(deCodePassword(user.getPassword()));
        return user;
    }

    /**
     * @param name 用户名
     * @return 用户最近发布的文章
     * @apiNote 获取指定作者最近发布的文章
     */
    public ContentEntry[] getRecentContents(@NotNull String name) {
        UserEntry user = getUserByName(name);
        List<ContentEntry> allContents = sqlQuery.getContentByUser(user);
        if (allContents == null) {
            return null;
        }
        ContentEntry[] contentEntries = allContents.toArray(new ContentEntry[0]);
        Arrays.sort(contentEntries, Comparator.comparingInt(ContentEntry::getContentId));
        return contentEntries;
    }

    /**
     * @param objects 参数
     * @return 如果其中有参数为 {@code ""} 或者为 {@code null} ，返回true，否则返回false
     * @apiNote 判断若干参数是否为 {@code ""} 或者为 {@code null}
     */
    public boolean isEmptyOrNull(Object @NotNull ... objects) {
        for (Object obj : objects) {
            if (obj == "" || obj == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * @apiNote 添加文章
     * @param name 用户名
     * @param title 文章标题
     * @param text 文章内容
     */
    public void addContent(@NotNull String name,@NotNull String title,@NotNull String text){
        UserEntry user = getUserByName(name);
        ContentEntry content = new ContentEntry(null,title,ZonedDateTime.now().toString(),ZonedDateTime.now().toString(),text,user.getUid());
        sqlQuery.addContent(content);
    }

}
