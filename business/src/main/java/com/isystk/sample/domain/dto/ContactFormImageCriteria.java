package com.isystk.sample.domain.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

/**
 * お問い合わせ画像
 *
 * 自動生成のため原則修正禁止!!
 *
 */
public class ContactFormImageCriteria {


    BigInteger idEq;
    BigInteger idNe;
    BigInteger idLt;
    BigInteger idLe;
    BigInteger idGt;
    BigInteger idGe;
    boolean idIsNull;
    boolean idIsNotNull;
    List<BigInteger> idIn;
    List<BigInteger> idNotIn;

    BigInteger contactFormIdEq;
    BigInteger contactFormIdNe;
    BigInteger contactFormIdLt;
    BigInteger contactFormIdLe;
    BigInteger contactFormIdGt;
    BigInteger contactFormIdGe;
    boolean contactFormIdIsNull;
    boolean contactFormIdIsNotNull;
    List<BigInteger> contactFormIdIn;
    List<BigInteger> contactFormIdNotIn;

    String fileNameEq;
    String fileNameNe;
    String fileNameLt;
    String fileNameLe;
    String fileNameGt;
    String fileNameGe;
    boolean fileNameIsNull;
    boolean fileNameIsNotNull;
    List<String> fileNameIn;
    List<String> fileNameNotIn;
    String fileNameLike;
    String fileNameNotLike;
    String fileNameStarts;
    String fileNameNotStarts;
    String fileNameEnds;
    String fileNameNotEnds;

    LocalDateTime createdAtEq;
    LocalDateTime createdAtNe;
    LocalDateTime createdAtLt;
    LocalDateTime createdAtLe;
    LocalDateTime createdAtGt;
    LocalDateTime createdAtGe;
    boolean createdAtIsNull;
    boolean createdAtIsNotNull;

    LocalDateTime updatedAtEq;
    LocalDateTime updatedAtNe;
    LocalDateTime updatedAtLt;
    LocalDateTime updatedAtLe;
    LocalDateTime updatedAtGt;
    LocalDateTime updatedAtGe;
    boolean updatedAtIsNull;
    boolean updatedAtIsNotNull;

    boolean deleteFlgTrue;
    boolean deleteFlgFalse;


    String orderBy;

    public BigInteger getIdEq() {
        return this.idEq;
    }

    public BigInteger getIdNe() {
        return this.idNe;
    }

    public BigInteger getIdLt() {
        return this.idLt;
    }

    public BigInteger getIdLe() {
        return this.idLe;
    }

    public BigInteger getIdGt() {
        return this.idGt;
    }

    public BigInteger getIdGe() {
        return this.idGe;
    }

    public boolean isIdIsNull() {
        return this.idIsNull;
    }

    public boolean isIdIsNotNull() {
        return this.idIsNotNull;
    }

    public List<BigInteger> getIdIn() {
        return this.idIn;
    }

    public List<BigInteger> getIdNotIn() {
        return this.idNotIn;
    }

    public BigInteger getContactFormIdEq() {
        return this.contactFormIdEq;
    }

    public BigInteger getContactFormIdNe() {
        return this.contactFormIdNe;
    }

    public BigInteger getContactFormIdLt() {
        return this.contactFormIdLt;
    }

    public BigInteger getContactFormIdLe() {
        return this.contactFormIdLe;
    }

    public BigInteger getContactFormIdGt() {
        return this.contactFormIdGt;
    }

    public BigInteger getContactFormIdGe() {
        return this.contactFormIdGe;
    }

    public boolean isContactFormIdIsNull() {
        return this.contactFormIdIsNull;
    }

    public boolean isContactFormIdIsNotNull() {
        return this.contactFormIdIsNotNull;
    }

    public List<BigInteger> getContactFormIdIn() {
        return this.contactFormIdIn;
    }

    public List<BigInteger> getContactFormIdNotIn() {
        return this.contactFormIdNotIn;
    }

    public String getFileNameEq() {
        return this.fileNameEq;
    }

    public String getFileNameNe() {
        return this.fileNameNe;
    }

    public String getFileNameLt() {
        return this.fileNameLt;
    }

    public String getFileNameLe() {
        return this.fileNameLe;
    }

    public String getFileNameGt() {
        return this.fileNameGt;
    }

    public String getFileNameGe() {
        return this.fileNameGe;
    }

    public boolean isFileNameIsNull() {
        return this.fileNameIsNull;
    }

    public boolean isFileNameIsNotNull() {
        return this.fileNameIsNotNull;
    }

    public List<String> getFileNameIn() {
        return this.fileNameIn;
    }

    public List<String> getFileNameNotIn() {
        return this.fileNameNotIn;
    }

    public String getFileNameLike() {
        return this.fileNameLike;
    }

    public String getFileNameNotLike() {
        return this.fileNameNotLike;
    }

    public String getFileNameStarts() {
        return this.fileNameStarts;
    }

    public String getFileNameNotStarts() {
        return this.fileNameNotStarts;
    }

    public String getFileNameEnds() {
        return this.fileNameEnds;
    }

    public String getFileNameNotEnds() {
        return this.fileNameNotEnds;
    }

    public LocalDateTime getCreatedAtEq() {
        return this.createdAtEq;
    }

    public LocalDateTime getCreatedAtNe() {
        return this.createdAtNe;
    }

    public LocalDateTime getCreatedAtLt() {
        return this.createdAtLt;
    }

    public LocalDateTime getCreatedAtLe() {
        return this.createdAtLe;
    }

    public LocalDateTime getCreatedAtGt() {
        return this.createdAtGt;
    }

    public LocalDateTime getCreatedAtGe() {
        return this.createdAtGe;
    }

    public boolean isCreatedAtIsNull() {
        return this.createdAtIsNull;
    }

    public boolean isCreatedAtIsNotNull() {
        return this.createdAtIsNotNull;
    }

    public LocalDateTime getUpdatedAtEq() {
        return this.updatedAtEq;
    }

    public LocalDateTime getUpdatedAtNe() {
        return this.updatedAtNe;
    }

    public LocalDateTime getUpdatedAtLt() {
        return this.updatedAtLt;
    }

    public LocalDateTime getUpdatedAtLe() {
        return this.updatedAtLe;
    }

    public LocalDateTime getUpdatedAtGt() {
        return this.updatedAtGt;
    }

    public LocalDateTime getUpdatedAtGe() {
        return this.updatedAtGe;
    }

    public boolean isUpdatedAtIsNull() {
        return this.updatedAtIsNull;
    }

    public boolean isUpdatedAtIsNotNull() {
        return this.updatedAtIsNotNull;
    }

    public boolean isDeleteFlgTrue() {
        return this.deleteFlgTrue;
    }

    public boolean isDeleteFlgFalse() {
        return this.deleteFlgFalse;
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public void setIdEq(BigInteger idEq) {
        this.idEq = idEq;
    }

    public void setIdNe(BigInteger idNe) {
        this.idNe = idNe;
    }

    public void setIdLt(BigInteger idLt) {
        this.idLt = idLt;
    }

    public void setIdLe(BigInteger idLe) {
        this.idLe = idLe;
    }

    public void setIdGt(BigInteger idGt) {
        this.idGt = idGt;
    }

    public void setIdGe(BigInteger idGe) {
        this.idGe = idGe;
    }

    public void setIdIsNull(boolean idIsNull) {
        this.idIsNull = idIsNull;
    }

    public void setIdIsNotNull(boolean idIsNotNull) {
        this.idIsNotNull = idIsNotNull;
    }

    public void setIdIn(List<BigInteger> idIn) {
        this.idIn = idIn;
    }

    public void setIdNotIn(List<BigInteger> idNotIn) {
        this.idNotIn = idNotIn;
    }

    public void setContactFormIdEq(BigInteger contactFormIdEq) {
        this.contactFormIdEq = contactFormIdEq;
    }

    public void setContactFormIdNe(BigInteger contactFormIdNe) {
        this.contactFormIdNe = contactFormIdNe;
    }

    public void setContactFormIdLt(BigInteger contactFormIdLt) {
        this.contactFormIdLt = contactFormIdLt;
    }

    public void setContactFormIdLe(BigInteger contactFormIdLe) {
        this.contactFormIdLe = contactFormIdLe;
    }

    public void setContactFormIdGt(BigInteger contactFormIdGt) {
        this.contactFormIdGt = contactFormIdGt;
    }

    public void setContactFormIdGe(BigInteger contactFormIdGe) {
        this.contactFormIdGe = contactFormIdGe;
    }

    public void setContactFormIdIsNull(boolean contactFormIdIsNull) {
        this.contactFormIdIsNull = contactFormIdIsNull;
    }

    public void setContactFormIdIsNotNull(boolean contactFormIdIsNotNull) {
        this.contactFormIdIsNotNull = contactFormIdIsNotNull;
    }

    public void setContactFormIdIn(List<BigInteger> contactFormIdIn) {
        this.contactFormIdIn = contactFormIdIn;
    }

    public void setContactFormIdNotIn(List<BigInteger> contactFormIdNotIn) {
        this.contactFormIdNotIn = contactFormIdNotIn;
    }

    public void setFileNameEq(String fileNameEq) {
        this.fileNameEq = fileNameEq;
    }

    public void setFileNameNe(String fileNameNe) {
        this.fileNameNe = fileNameNe;
    }

    public void setFileNameLt(String fileNameLt) {
        this.fileNameLt = fileNameLt;
    }

    public void setFileNameLe(String fileNameLe) {
        this.fileNameLe = fileNameLe;
    }

    public void setFileNameGt(String fileNameGt) {
        this.fileNameGt = fileNameGt;
    }

    public void setFileNameGe(String fileNameGe) {
        this.fileNameGe = fileNameGe;
    }

    public void setFileNameIsNull(boolean fileNameIsNull) {
        this.fileNameIsNull = fileNameIsNull;
    }

    public void setFileNameIsNotNull(boolean fileNameIsNotNull) {
        this.fileNameIsNotNull = fileNameIsNotNull;
    }

    public void setFileNameIn(List<String> fileNameIn) {
        this.fileNameIn = fileNameIn;
    }

    public void setFileNameNotIn(List<String> fileNameNotIn) {
        this.fileNameNotIn = fileNameNotIn;
    }

    public void setFileNameLike(String fileNameLike) {
        this.fileNameLike = fileNameLike;
    }

    public void setFileNameNotLike(String fileNameNotLike) {
        this.fileNameNotLike = fileNameNotLike;
    }

    public void setFileNameStarts(String fileNameStarts) {
        this.fileNameStarts = fileNameStarts;
    }

    public void setFileNameNotStarts(String fileNameNotStarts) {
        this.fileNameNotStarts = fileNameNotStarts;
    }

    public void setFileNameEnds(String fileNameEnds) {
        this.fileNameEnds = fileNameEnds;
    }

    public void setFileNameNotEnds(String fileNameNotEnds) {
        this.fileNameNotEnds = fileNameNotEnds;
    }

    public void setCreatedAtEq(LocalDateTime createdAtEq) {
        this.createdAtEq = createdAtEq;
    }

    public void setCreatedAtNe(LocalDateTime createdAtNe) {
        this.createdAtNe = createdAtNe;
    }

    public void setCreatedAtLt(LocalDateTime createdAtLt) {
        this.createdAtLt = createdAtLt;
    }

    public void setCreatedAtLe(LocalDateTime createdAtLe) {
        this.createdAtLe = createdAtLe;
    }

    public void setCreatedAtGt(LocalDateTime createdAtGt) {
        this.createdAtGt = createdAtGt;
    }

    public void setCreatedAtGe(LocalDateTime createdAtGe) {
        this.createdAtGe = createdAtGe;
    }

    public void setCreatedAtIsNull(boolean createdAtIsNull) {
        this.createdAtIsNull = createdAtIsNull;
    }

    public void setCreatedAtIsNotNull(boolean createdAtIsNotNull) {
        this.createdAtIsNotNull = createdAtIsNotNull;
    }

    public void setUpdatedAtEq(LocalDateTime updatedAtEq) {
        this.updatedAtEq = updatedAtEq;
    }

    public void setUpdatedAtNe(LocalDateTime updatedAtNe) {
        this.updatedAtNe = updatedAtNe;
    }

    public void setUpdatedAtLt(LocalDateTime updatedAtLt) {
        this.updatedAtLt = updatedAtLt;
    }

    public void setUpdatedAtLe(LocalDateTime updatedAtLe) {
        this.updatedAtLe = updatedAtLe;
    }

    public void setUpdatedAtGt(LocalDateTime updatedAtGt) {
        this.updatedAtGt = updatedAtGt;
    }

    public void setUpdatedAtGe(LocalDateTime updatedAtGe) {
        this.updatedAtGe = updatedAtGe;
    }

    public void setUpdatedAtIsNull(boolean updatedAtIsNull) {
        this.updatedAtIsNull = updatedAtIsNull;
    }

    public void setUpdatedAtIsNotNull(boolean updatedAtIsNotNull) {
        this.updatedAtIsNotNull = updatedAtIsNotNull;
    }

    public void setDeleteFlgTrue(boolean deleteFlgTrue) {
        this.deleteFlgTrue = deleteFlgTrue;
    }

    public void setDeleteFlgFalse(boolean deleteFlgFalse) {
        this.deleteFlgFalse = deleteFlgFalse;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}