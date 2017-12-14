package com.example.a666.petapp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @Description:(宠物信息实体)
 * @ClassName: PetInfo
 * @author zf zhangfeng@tctogether.com
 * @date 2016-03-14 15:14:37
 *       <p>
 *       北京天成合科�??有限公司版权�??�??
 *       </p>
 */
public class PetType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String typeCode;// 类型code
	private String typeName;// 类型名称
	private String typeIndex;// 数据索引
	private List<String> typeCodes;// 多类�??
	private Date createTime;// 创建时间
	private int isUse; // 是否启用
	private String parentTypeCode;// 父分类code
	private String parentTypeName;// 父分类名�??
	private BigDecimal petPrice;// 宠物价格
	private String petTypeImage;// 宠物类型图片
	private int isRe; // 是否热门

	public String getPetTypeImage() {
		return petTypeImage;
	}

	public void setPetTypeImage(String petTypeImage) {
		this.petTypeImage = petTypeImage;
	}

	public BigDecimal getPetPrice() {
		return petPrice;
	}

	public List<String> getTypeCodes() {
		return typeCodes;
	}

	public void setTypeCodes(List<String> typeCodes) {
		this.typeCodes = typeCodes;
	}

	public int getIsRe() {
		return isRe;
	}

	public void setIsRe(int isRe) {
		this.isRe = isRe;
	}

	public void setPetPrice(BigDecimal petPrice) {
		this.petPrice = petPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeIndex() {
		return typeIndex;
	}

	public void setTypeIndex(String typeIndex) {
		this.typeIndex = typeIndex;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getIsUse() {
		return isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}

	public String getParentTypeCode() {
		return parentTypeCode;
	}

	public void setParentTypeCode(String parentTypeCode) {
		this.parentTypeCode = parentTypeCode;
	}

	public String getParentTypeName() {
		return parentTypeName;
	}

	public void setParentTypeName(String parentTypeName) {
		this.parentTypeName = parentTypeName;
	}

	@Override
	public String toString() {
		return "PetType [id=" + id + ", typeCode=" + typeCode + ", typeName="
				+ typeName + ", typeIndex=" + typeIndex + ", typeCodes="
				+ typeCodes + ", createTime=" + createTime + ", isUse=" + isUse
				+ ", parentTypeCode=" + parentTypeCode + ", parentTypeName="
				+ parentTypeName + ", petPrice=" + petPrice + ", petTypeImage="
				+ petTypeImage + ", isRe=" + isRe + "]";
	}

}
