package com.example.a666.petapp.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @Description:(服务定价信息实体)
 * @ClassName: ServicePricingInfo
 * @author zf zhangfeng@tctogether.com
 * @date 2016-03-14 15:14:37
 *       <p>
 *       北京天成合科�??有限公司版权�??�??
 *       </p>
 */
public class ServicePricingInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String serviceCode;// 服务code
	private String serviceName;// 服务名称
	private String servicePrice;// 服务定价
	private String servicePicture;// 服务图片
	private int isUse;// 是否启用
	private Date createTime;// 创建时间
	private Date updateTime;// 修改时间
	private int serviceType;// 服务分类
	private String serviceTypeName;// 分类名称
	private int isStandard;// 是否是标准定�??
	private String petPricingCode;// 宠物服务定价
	private String unit; // 单位
	private String petTypeCode;// 宠物类型code
	private String petTypeName;// 宠物类型名称

	public long getId() {
		return id;
	}

	public String getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(String servicePrice) {
		this.servicePrice = servicePrice;
	}

	public String getPetTypeCode() {
		return petTypeCode;
	}

	public void setPetTypeCode(String petTypeCode) {
		this.petTypeCode = petTypeCode;
	}

	public String getPetTypeName() {
		return petTypeName;
	}

	public void setPetTypeName(String petTypeName) {
		this.petTypeName = petTypeName;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServicePicture() {
		return servicePicture;
	}

	public void setServicePicture(String servicePicture) {
		this.servicePicture = servicePicture;
	}

	public int getIsUse() {
		return isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getServiceType() {
		return serviceType;
	}

	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceTypeName() {
		return serviceTypeName;
	}

	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}

	public int getIsStandard() {
		return isStandard;
	}

	public void setIsStandard(int isStandard) {
		this.isStandard = isStandard;
	}

	public String getPetPricingCode() {
		return petPricingCode;
	}

	public void setPetPricingCode(String petPricingCode) {
		this.petPricingCode = petPricingCode;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
