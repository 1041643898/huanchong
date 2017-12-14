package com.example.a666.petapp.entity;

import java.io.Serializable;

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
public class PetInfo implements Serializable {
	private long id;
	private String petCode;// 宠物code
	private String petName;// 宠物昵称
	private String userId;// 用户id
	private String userName;// 用户名称
	private int petSex;// 宠物性别
	private String petBirthTime;// 宠物出生日期
	private String petInfo;// 宠物�??�??
	private String createTime;// 创建时间
	private String updateTime;// 修改时间
	private String petImage;// 宠物图片
	private String petType;// 宠物类型
	private String petTypeName;// 宠物类型名称
	private int isSterilization;// 是否绝育
	private double petWeight;// 宠物体重
	private String petPricingCode;// 宠物定价code
	private int petState;// 宠物状�??
	private int isUse;// 是否启用
	private int isimmune;// 是否免疫
	private String petParentTypeCode;// 父类code
	private String petParentTypeName;// 父类型名�??

	public String getPetParentTypeName() {
		return petParentTypeName;
	}

	public void setPetParentTypeName(String petParentTypeName) {
		this.petParentTypeName = petParentTypeName;
	}

	public String getUserName() {
		return userName;
	}

	public String getPetParentTypeCode() {
		return petParentTypeCode;
	}

	public void setPetParentTypeCode(String petParentTypeCode) {
		this.petParentTypeCode = petParentTypeCode;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getIsUse() {
		return isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPetCode() {
		return petCode;
	}

	public void setPetCode(String petCode) {
		this.petCode = petCode;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getPetSex() {
		return petSex;
	}

	public void setPetSex(int petSex) {
		this.petSex = petSex;
	}

	public String getPetBirthTime() {
		return petBirthTime;
	}

	public void setPetBirthTime(String petBirthTime) {
		this.petBirthTime = petBirthTime;
	}

	public String getPetInfo() {
		return petInfo;
	}

	public void setPetInfo(String petInfo) {
		this.petInfo = petInfo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getPetType() {
		return petType;
	}

	public void setPetType(String petType) {
		this.petType = petType;
	}

	public String getPetTypeName() {
		return petTypeName;
	}

	public void setPetTypeName(String petTypeName) {
		this.petTypeName = petTypeName;
	}

	public int getIsSterilization() {
		return isSterilization;
	}

	public void setIsSterilization(int isSterilization) {
		this.isSterilization = isSterilization;
	}

	public double getPetWeight() {
		return petWeight;
	}

	public void setPetWeight(double petWeight) {
		this.petWeight = petWeight;
	}

	public String getPetPricingCode() {
		return petPricingCode;
	}

	public void setPetPricingCode(String petPricingCode) {
		this.petPricingCode = petPricingCode;
	}

	public int getPetState() {
		return petState;
	}

	public void setPetState(int petState) {
		this.petState = petState;
	}

	public String getPetImage() {
		return petImage;
	}

	public void setPetImage(String petImage) {
		this.petImage = petImage;
	}

	public int getIsimmune() {
		return isimmune;
	}

	public void setIsimmune(int isimmune) {
		this.isimmune = isimmune;
	}

	@Override
	public String toString() {
		return "PetInfo [id=" + id + ", petCode=" + petCode + ", petName="
				+ petName + ", userId=" + userId + ", userName=" + userName
				+ ", petSex=" + petSex + ", petBirthTime=" + petBirthTime
				+ ", petInfo=" + petInfo + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", petImage=" + petImage
				+ ", petType=" + petType + ", petTypeName=" + petTypeName
				+ ", isSterilization=" + isSterilization + ", petWeight="
				+ petWeight + ", petPricingCode=" + petPricingCode
				+ ", petState=" + petState + ", isUse=" + isUse + ", isimmune="
				+ isimmune + ", petParentTypeCode=" + petParentTypeCode
				+ ", petParentTypeName=" + petParentTypeName + "]";
	}

}
