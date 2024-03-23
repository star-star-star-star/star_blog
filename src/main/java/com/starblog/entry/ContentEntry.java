package com.starblog.entry;

import java.util.Objects;

public class ContentEntry {
    /*
        fields
     */
    /**
     * 文章编号
     */
    private Integer contentId;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章创建时间
     */
    private String createTime;
    /**
     * 文章修改时间
     */
    private String modifiedTime;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 文章创建者uid
     */
    private Integer uid;
    /**
     * 文章点击次数
     */
    private Integer hits;
    /**
     * 文章评论次数
     */
    private Integer comments;

    /*
        constructors
     */

    public ContentEntry() {
    }

    public ContentEntry(Integer contentId, String title, String createTime, String modifiedTime, String content, Integer uid, Integer hits, Integer comments) {
        this.contentId = contentId;
        this.title = title;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
        this.content = content;
        this.uid = uid;
        this.hits = hits;
        this.comments = comments;
    }
    /*
        getter and setter
     */

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    /*
        toString method
     */

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContentEntry{");
        sb.append("contentId=").append(contentId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", createTime='").append(createTime).append('\'');
        sb.append(", modifiedTime='").append(modifiedTime).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", uid=").append(uid);
        sb.append(", hits=").append(hits);
        sb.append(", comments=").append(comments);
        sb.append('}');
        return sb.toString();
    }

    /*
        equals and hashCode method
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentEntry that = (ContentEntry) o;
        return contentId.equals(that.contentId) && title.equals(that.title) && createTime.equals(that.createTime) && modifiedTime.equals(that.modifiedTime) && content.equals(that.content) && uid.equals(that.uid) && hits.equals(that.hits) && comments.equals(that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentId, title, createTime, modifiedTime, content, uid, hits, comments);
    }
}
