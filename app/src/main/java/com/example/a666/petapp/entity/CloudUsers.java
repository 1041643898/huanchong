/**   
 * @描述:	
 * @作�??:	Android - CXP
 * @创建日期:	2016�??3�??28�?? 下午4:11:54  
 */
package com.example.a666.petapp.entity;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * @描述: 寄养家庭信息
 * @作�??: Android - CXP
 * @创建日期: 2016�??3�??28�?? 下午4:11:54
 */
public class CloudUsers implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String price;
	private String usersId;
	private String score;
	private String isUse;

	// 地址
	private String address;
	// 标题
	private String family;
	// 位置
	private String location;
	// 头像
	private String userImage;
	// 经度
	private String coordX;
	// 纬度
	private String coordY;
	// 距离
	private String distance;

	public boolean equal(String userId) {
		if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(usersId)) {
			return this.usersId.equals(userId);
		}
		return false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUsersId() {
		return usersId;
	}

	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getCoordX() {
		return coordX;
	}

	public void setCoordX(String coordX) {
		this.coordX = coordX;
	}

	public String getCoordY() {
		return coordY;
	}

	public void setCoordY(String coordY) {
		this.coordY = coordY;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

}
