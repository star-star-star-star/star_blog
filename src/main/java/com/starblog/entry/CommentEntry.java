package com.starblog.entry;

import java.util.Objects;

public class CommentEntry {
    /*
        fields
     */
    /**
     * 评论编号
     */
    private Integer commentId;
    /**
     * 评论所关联的文章编号
     */
    private Integer contentId;
    /**
     * 评论发布时间
     */
    private String publishTime;
    /**
     * 评论者uid
     */
    private Integer uid;
    /**
     * 父级评论id
     */
    private Integer parent;
    /**
     * 评论内容
     */
    private String content;

    /*
        constructors
     */

    public CommentEntry() {
    }

    public CommentEntry(Integer commentId, Integer contentId, String publishTime, Integer uid, Integer parent, String content) {
        this.commentId = commentId;
        this.contentId = contentId;
        this.publishTime = publishTime;
        this.uid = uid;
        this.parent = parent;
        this.content = content;
    }
    /*
        getter and setter
     */

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /*
        toString method
     */

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommentEntry{");
        sb.append("commentId=").append(commentId);
        sb.append(", contentId=").append(contentId);
        sb.append(", publishTime='").append(publishTime).append('\'');
        sb.append(", uid=").append(uid);
        sb.append(", parent=").append(parent);
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /*
        equals and hashCode
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntry that = (CommentEntry) o;
        return commentId.equals(that.commentId) && contentId.equals(that.contentId) && publishTime.equals(that.publishTime) && uid.equals(that.uid) && parent.equals(that.parent) && content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, contentId, publishTime, uid, parent, content);
    }
}
