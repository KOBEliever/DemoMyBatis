package com.cqu.mybatis.pojo;

import java.io.Serializable;

public class Doc implements Serializable {
    private long docId;//主键id
    private long categoryId;//类型id
    private String docTitle;//文档标题
    private String docSummary;//文档概要
    private String docUploaduser;//上传者
    private String docCreatedate;//创建时间
    private String docModifyuser;//修改人
    private String docModifydate;//修改时间
    private String docImage;//文档图片

    @Override
    public String toString() {
        return "Doc{" +
                "docId=" + docId +
                ", categoryId=" + categoryId +
                ", docTitle='" + docTitle + '\'' +
                ", docSummary='" + docSummary + '\'' +
                ", docUploaduser='" + docUploaduser + '\'' +
                ", docCreatedate='" + docCreatedate + '\'' +
                ", docModifyuser='" + docModifyuser + '\'' +
                ", docModifydate='" + docModifydate + '\'' +
                ", docImage='" + docImage + '\'' +
                '}';
    }

    public long getDocId() {
        return docId;
    }

    public void setDocId(long docId) {
        this.docId = docId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getDocSummary() {
        return docSummary;
    }

    public void setDocSummary(String docSummary) {
        this.docSummary = docSummary;
    }

    public String getDocUploaduser() {
        return docUploaduser;
    }

    public void setDocUploaduser(String docUploaduser) {
        this.docUploaduser = docUploaduser;
    }

    public String getDocCreatedate() {
        return docCreatedate;
    }

    public void setDocCreatedate(String docCreatedate) {
        this.docCreatedate = docCreatedate;
    }

    public String getDocModifyuser() {
        return docModifyuser;
    }

    public void setDocModifyuser(String docModifyuser) {
        this.docModifyuser = docModifyuser;
    }

    public String getDocModifydate() {
        return docModifydate;
    }

    public void setDocModifydate(String docModifydate) {
        this.docModifydate = docModifydate;
    }

    public String getDocImage() {
        return docImage;
    }

    public void setDocImage(String docImage) {
        this.docImage = docImage;
    }

    public Doc() {
    }

    public Doc(long docId, long categoryId, String docTitle, String docSummary, String docUploaduser, String docCreatedate, String docModifyuser, String docModifydate, String docImage) {
        this.docId = docId;
        this.categoryId = categoryId;
        this.docTitle = docTitle;
        this.docSummary = docSummary;
        this.docUploaduser = docUploaduser;
        this.docCreatedate = docCreatedate;
        this.docModifyuser = docModifyuser;
        this.docModifydate = docModifydate;
        this.docImage = docImage;
    }
}
