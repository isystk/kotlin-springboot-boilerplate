package com.isystk.sample.domain.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

/**
 * パスワードリセット
 *
 * 自動生成のため原則修正禁止!!
 *
 */
public class PasswordResetCriteria {


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

    String emailEq;
    String emailNe;
    String emailLt;
    String emailLe;
    String emailGt;
    String emailGe;
    boolean emailIsNull;
    boolean emailIsNotNull;
    List<String> emailIn;
    List<String> emailNotIn;
    String emailLike;
    String emailNotLike;
    String emailStarts;
    String emailNotStarts;
    String emailEnds;
    String emailNotEnds;

    String tokenEq;
    String tokenNe;
    String tokenLt;
    String tokenLe;
    String tokenGt;
    String tokenGe;
    boolean tokenIsNull;
    boolean tokenIsNotNull;
    List<String> tokenIn;
    List<String> tokenNotIn;
    String tokenLike;
    String tokenNotLike;
    String tokenStarts;
    String tokenNotStarts;
    String tokenEnds;
    String tokenNotEnds;

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

    public String getEmailEq() {
        return this.emailEq;
    }

    public String getEmailNe() {
        return this.emailNe;
    }

    public String getEmailLt() {
        return this.emailLt;
    }

    public String getEmailLe() {
        return this.emailLe;
    }

    public String getEmailGt() {
        return this.emailGt;
    }

    public String getEmailGe() {
        return this.emailGe;
    }

    public boolean isEmailIsNull() {
        return this.emailIsNull;
    }

    public boolean isEmailIsNotNull() {
        return this.emailIsNotNull;
    }

    public List<String> getEmailIn() {
        return this.emailIn;
    }

    public List<String> getEmailNotIn() {
        return this.emailNotIn;
    }

    public String getEmailLike() {
        return this.emailLike;
    }

    public String getEmailNotLike() {
        return this.emailNotLike;
    }

    public String getEmailStarts() {
        return this.emailStarts;
    }

    public String getEmailNotStarts() {
        return this.emailNotStarts;
    }

    public String getEmailEnds() {
        return this.emailEnds;
    }

    public String getEmailNotEnds() {
        return this.emailNotEnds;
    }

    public String getTokenEq() {
        return this.tokenEq;
    }

    public String getTokenNe() {
        return this.tokenNe;
    }

    public String getTokenLt() {
        return this.tokenLt;
    }

    public String getTokenLe() {
        return this.tokenLe;
    }

    public String getTokenGt() {
        return this.tokenGt;
    }

    public String getTokenGe() {
        return this.tokenGe;
    }

    public boolean isTokenIsNull() {
        return this.tokenIsNull;
    }

    public boolean isTokenIsNotNull() {
        return this.tokenIsNotNull;
    }

    public List<String> getTokenIn() {
        return this.tokenIn;
    }

    public List<String> getTokenNotIn() {
        return this.tokenNotIn;
    }

    public String getTokenLike() {
        return this.tokenLike;
    }

    public String getTokenNotLike() {
        return this.tokenNotLike;
    }

    public String getTokenStarts() {
        return this.tokenStarts;
    }

    public String getTokenNotStarts() {
        return this.tokenNotStarts;
    }

    public String getTokenEnds() {
        return this.tokenEnds;
    }

    public String getTokenNotEnds() {
        return this.tokenNotEnds;
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

    public void setEmailEq(String emailEq) {
        this.emailEq = emailEq;
    }

    public void setEmailNe(String emailNe) {
        this.emailNe = emailNe;
    }

    public void setEmailLt(String emailLt) {
        this.emailLt = emailLt;
    }

    public void setEmailLe(String emailLe) {
        this.emailLe = emailLe;
    }

    public void setEmailGt(String emailGt) {
        this.emailGt = emailGt;
    }

    public void setEmailGe(String emailGe) {
        this.emailGe = emailGe;
    }

    public void setEmailIsNull(boolean emailIsNull) {
        this.emailIsNull = emailIsNull;
    }

    public void setEmailIsNotNull(boolean emailIsNotNull) {
        this.emailIsNotNull = emailIsNotNull;
    }

    public void setEmailIn(List<String> emailIn) {
        this.emailIn = emailIn;
    }

    public void setEmailNotIn(List<String> emailNotIn) {
        this.emailNotIn = emailNotIn;
    }

    public void setEmailLike(String emailLike) {
        this.emailLike = emailLike;
    }

    public void setEmailNotLike(String emailNotLike) {
        this.emailNotLike = emailNotLike;
    }

    public void setEmailStarts(String emailStarts) {
        this.emailStarts = emailStarts;
    }

    public void setEmailNotStarts(String emailNotStarts) {
        this.emailNotStarts = emailNotStarts;
    }

    public void setEmailEnds(String emailEnds) {
        this.emailEnds = emailEnds;
    }

    public void setEmailNotEnds(String emailNotEnds) {
        this.emailNotEnds = emailNotEnds;
    }

    public void setTokenEq(String tokenEq) {
        this.tokenEq = tokenEq;
    }

    public void setTokenNe(String tokenNe) {
        this.tokenNe = tokenNe;
    }

    public void setTokenLt(String tokenLt) {
        this.tokenLt = tokenLt;
    }

    public void setTokenLe(String tokenLe) {
        this.tokenLe = tokenLe;
    }

    public void setTokenGt(String tokenGt) {
        this.tokenGt = tokenGt;
    }

    public void setTokenGe(String tokenGe) {
        this.tokenGe = tokenGe;
    }

    public void setTokenIsNull(boolean tokenIsNull) {
        this.tokenIsNull = tokenIsNull;
    }

    public void setTokenIsNotNull(boolean tokenIsNotNull) {
        this.tokenIsNotNull = tokenIsNotNull;
    }

    public void setTokenIn(List<String> tokenIn) {
        this.tokenIn = tokenIn;
    }

    public void setTokenNotIn(List<String> tokenNotIn) {
        this.tokenNotIn = tokenNotIn;
    }

    public void setTokenLike(String tokenLike) {
        this.tokenLike = tokenLike;
    }

    public void setTokenNotLike(String tokenNotLike) {
        this.tokenNotLike = tokenNotLike;
    }

    public void setTokenStarts(String tokenStarts) {
        this.tokenStarts = tokenStarts;
    }

    public void setTokenNotStarts(String tokenNotStarts) {
        this.tokenNotStarts = tokenNotStarts;
    }

    public void setTokenEnds(String tokenEnds) {
        this.tokenEnds = tokenEnds;
    }

    public void setTokenNotEnds(String tokenNotEnds) {
        this.tokenNotEnds = tokenNotEnds;
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