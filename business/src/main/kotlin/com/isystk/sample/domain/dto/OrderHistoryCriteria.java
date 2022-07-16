package com.isystk.sample.domain.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 注文履歴
 *
 * 自動生成のため原則修正禁止!!
 *
 */
public class OrderHistoryCriteria {


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

    BigInteger stockIdEq;
    BigInteger stockIdNe;
    BigInteger stockIdLt;
    BigInteger stockIdLe;
    BigInteger stockIdGt;
    BigInteger stockIdGe;
    boolean stockIdIsNull;
    boolean stockIdIsNotNull;
    List<BigInteger> stockIdIn;
    List<BigInteger> stockIdNotIn;

    BigInteger userIdEq;
    BigInteger userIdNe;
    BigInteger userIdLt;
    BigInteger userIdLe;
    BigInteger userIdGt;
    BigInteger userIdGe;
    boolean userIdIsNull;
    boolean userIdIsNotNull;
    List<BigInteger> userIdIn;
    List<BigInteger> userIdNotIn;

    Integer priceEq;
    Integer priceNe;
    Integer priceLt;
    Integer priceLe;
    Integer priceGt;
    Integer priceGe;
    boolean priceIsNull;
    boolean priceIsNotNull;
    List<Integer> priceIn;
    List<Integer> priceNotIn;

    Integer quantityEq;
    Integer quantityNe;
    Integer quantityLt;
    Integer quantityLe;
    Integer quantityGt;
    Integer quantityGe;
    boolean quantityIsNull;
    boolean quantityIsNotNull;
    List<Integer> quantityIn;
    List<Integer> quantityNotIn;

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

    public BigInteger getStockIdEq() {
        return this.stockIdEq;
    }

    public BigInteger getStockIdNe() {
        return this.stockIdNe;
    }

    public BigInteger getStockIdLt() {
        return this.stockIdLt;
    }

    public BigInteger getStockIdLe() {
        return this.stockIdLe;
    }

    public BigInteger getStockIdGt() {
        return this.stockIdGt;
    }

    public BigInteger getStockIdGe() {
        return this.stockIdGe;
    }

    public boolean isStockIdIsNull() {
        return this.stockIdIsNull;
    }

    public boolean isStockIdIsNotNull() {
        return this.stockIdIsNotNull;
    }

    public List<BigInteger> getStockIdIn() {
        return this.stockIdIn;
    }

    public List<BigInteger> getStockIdNotIn() {
        return this.stockIdNotIn;
    }

    public BigInteger getUserIdEq() {
        return this.userIdEq;
    }

    public BigInteger getUserIdNe() {
        return this.userIdNe;
    }

    public BigInteger getUserIdLt() {
        return this.userIdLt;
    }

    public BigInteger getUserIdLe() {
        return this.userIdLe;
    }

    public BigInteger getUserIdGt() {
        return this.userIdGt;
    }

    public BigInteger getUserIdGe() {
        return this.userIdGe;
    }

    public boolean isUserIdIsNull() {
        return this.userIdIsNull;
    }

    public boolean isUserIdIsNotNull() {
        return this.userIdIsNotNull;
    }

    public List<BigInteger> getUserIdIn() {
        return this.userIdIn;
    }

    public List<BigInteger> getUserIdNotIn() {
        return this.userIdNotIn;
    }

    public Integer getPriceEq() {
        return this.priceEq;
    }

    public Integer getPriceNe() {
        return this.priceNe;
    }

    public Integer getPriceLt() {
        return this.priceLt;
    }

    public Integer getPriceLe() {
        return this.priceLe;
    }

    public Integer getPriceGt() {
        return this.priceGt;
    }

    public Integer getPriceGe() {
        return this.priceGe;
    }

    public boolean isPriceIsNull() {
        return this.priceIsNull;
    }

    public boolean isPriceIsNotNull() {
        return this.priceIsNotNull;
    }

    public List<Integer> getPriceIn() {
        return this.priceIn;
    }

    public List<Integer> getPriceNotIn() {
        return this.priceNotIn;
    }

    public Integer getQuantityEq() {
        return this.quantityEq;
    }

    public Integer getQuantityNe() {
        return this.quantityNe;
    }

    public Integer getQuantityLt() {
        return this.quantityLt;
    }

    public Integer getQuantityLe() {
        return this.quantityLe;
    }

    public Integer getQuantityGt() {
        return this.quantityGt;
    }

    public Integer getQuantityGe() {
        return this.quantityGe;
    }

    public boolean isQuantityIsNull() {
        return this.quantityIsNull;
    }

    public boolean isQuantityIsNotNull() {
        return this.quantityIsNotNull;
    }

    public List<Integer> getQuantityIn() {
        return this.quantityIn;
    }

    public List<Integer> getQuantityNotIn() {
        return this.quantityNotIn;
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

    public void setStockIdEq(BigInteger stockIdEq) {
        this.stockIdEq = stockIdEq;
    }

    public void setStockIdNe(BigInteger stockIdNe) {
        this.stockIdNe = stockIdNe;
    }

    public void setStockIdLt(BigInteger stockIdLt) {
        this.stockIdLt = stockIdLt;
    }

    public void setStockIdLe(BigInteger stockIdLe) {
        this.stockIdLe = stockIdLe;
    }

    public void setStockIdGt(BigInteger stockIdGt) {
        this.stockIdGt = stockIdGt;
    }

    public void setStockIdGe(BigInteger stockIdGe) {
        this.stockIdGe = stockIdGe;
    }

    public void setStockIdIsNull(boolean stockIdIsNull) {
        this.stockIdIsNull = stockIdIsNull;
    }

    public void setStockIdIsNotNull(boolean stockIdIsNotNull) {
        this.stockIdIsNotNull = stockIdIsNotNull;
    }

    public void setStockIdIn(List<BigInteger> stockIdIn) {
        this.stockIdIn = stockIdIn;
    }

    public void setStockIdNotIn(List<BigInteger> stockIdNotIn) {
        this.stockIdNotIn = stockIdNotIn;
    }

    public void setUserIdEq(BigInteger userIdEq) {
        this.userIdEq = userIdEq;
    }

    public void setUserIdNe(BigInteger userIdNe) {
        this.userIdNe = userIdNe;
    }

    public void setUserIdLt(BigInteger userIdLt) {
        this.userIdLt = userIdLt;
    }

    public void setUserIdLe(BigInteger userIdLe) {
        this.userIdLe = userIdLe;
    }

    public void setUserIdGt(BigInteger userIdGt) {
        this.userIdGt = userIdGt;
    }

    public void setUserIdGe(BigInteger userIdGe) {
        this.userIdGe = userIdGe;
    }

    public void setUserIdIsNull(boolean userIdIsNull) {
        this.userIdIsNull = userIdIsNull;
    }

    public void setUserIdIsNotNull(boolean userIdIsNotNull) {
        this.userIdIsNotNull = userIdIsNotNull;
    }

    public void setUserIdIn(List<BigInteger> userIdIn) {
        this.userIdIn = userIdIn;
    }

    public void setUserIdNotIn(List<BigInteger> userIdNotIn) {
        this.userIdNotIn = userIdNotIn;
    }

    public void setPriceEq(Integer priceEq) {
        this.priceEq = priceEq;
    }

    public void setPriceNe(Integer priceNe) {
        this.priceNe = priceNe;
    }

    public void setPriceLt(Integer priceLt) {
        this.priceLt = priceLt;
    }

    public void setPriceLe(Integer priceLe) {
        this.priceLe = priceLe;
    }

    public void setPriceGt(Integer priceGt) {
        this.priceGt = priceGt;
    }

    public void setPriceGe(Integer priceGe) {
        this.priceGe = priceGe;
    }

    public void setPriceIsNull(boolean priceIsNull) {
        this.priceIsNull = priceIsNull;
    }

    public void setPriceIsNotNull(boolean priceIsNotNull) {
        this.priceIsNotNull = priceIsNotNull;
    }

    public void setPriceIn(List<Integer> priceIn) {
        this.priceIn = priceIn;
    }

    public void setPriceNotIn(List<Integer> priceNotIn) {
        this.priceNotIn = priceNotIn;
    }

    public void setQuantityEq(Integer quantityEq) {
        this.quantityEq = quantityEq;
    }

    public void setQuantityNe(Integer quantityNe) {
        this.quantityNe = quantityNe;
    }

    public void setQuantityLt(Integer quantityLt) {
        this.quantityLt = quantityLt;
    }

    public void setQuantityLe(Integer quantityLe) {
        this.quantityLe = quantityLe;
    }

    public void setQuantityGt(Integer quantityGt) {
        this.quantityGt = quantityGt;
    }

    public void setQuantityGe(Integer quantityGe) {
        this.quantityGe = quantityGe;
    }

    public void setQuantityIsNull(boolean quantityIsNull) {
        this.quantityIsNull = quantityIsNull;
    }

    public void setQuantityIsNotNull(boolean quantityIsNotNull) {
        this.quantityIsNotNull = quantityIsNotNull;
    }

    public void setQuantityIn(List<Integer> quantityIn) {
        this.quantityIn = quantityIn;
    }

    public void setQuantityNotIn(List<Integer> quantityNotIn) {
        this.quantityNotIn = quantityNotIn;
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