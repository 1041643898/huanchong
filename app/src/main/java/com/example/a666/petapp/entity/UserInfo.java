package com.example.a666.petapp.entity;

import android.widget.TextView;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @Description:(用户信息实体)
 * @ClassName: UserInfo
 * @author zf zhangfeng@tctogether.com
 * @date 2016-03-14 15:14:37
 *       <p>
 *       北京天成合科�??有限公司版权�??�??
 *       </p>
 */
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 45615615515151L;
	private int id;
	private String userId;// 用户id
	private String userName;// 用户�??
	private String password;// 密码
	private long userPhone;// 手机�??
	private int position;// 用户身份
	private String address;// 用户地址
	private String identify;// 地图标识
	private BigDecimal userPrice;// 帐号金额
	private Date createTime;// 创建时间
	private Date updateTime;// 修改时间
	private long qq;// QQ
	private String realName;// 真实姓名
	private String family;// 寄养家庭
	private String identityCard;// 身份证号
	private String identityImage;// 身份证照�??
	private Date lastRegisterTime;// �??后登录时�??
	private String threeId;// 第三方登录标�??
	private String userImage;// 用户图片
	private int userSex;// 用户年龄
	private Date birthday;// 出生日期
	private int isUse;// 是否启用
	private String token;
	private String cityId;
	private String openTime;// 寄养师服务开始时�??
	private String endTime;// 寄养师服务结束时�??
	private String openBeginTime;// 寄养师服务开始时�??
	private String openEndTime;// 寄养师服务结束时�??
	private String wechat;// 微信
	private String coordX;// 横坐�??
	private String coordY;// 纵坐�??
	private String intro;// �??�??
	private int state; // 标识是否审核通过

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getThreeId() {
		return threeId;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public void setThreeId(String threeId) {
		this.threeId = threeId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public BigDecimal getUserPrice() {
		return userPrice;
	}

	public void setUserPrice(BigDecimal userPrice) {
		this.userPrice = userPrice;
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

	public long getQq() {
		return qq;
	}

	public void setQq(long qq) {
		this.qq = qq;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getIdentityImage() {
		return identityImage;
	}

	public void setIdentityImage(String identityImage) {
		this.identityImage = identityImage;
	}

	public Date getLastRegisterTime() {
		return lastRegisterTime;
	}

	public void setLastRegisterTime(Date lastRegisterTime) {
		this.lastRegisterTime = lastRegisterTime;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public int getUserSex() {
		return userSex;
	}

	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getIsUse() {
		return isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOpenBeginTime() {
		return openBeginTime;
	}

	public void setOpenBeginTime(String openBeginTime) {
		this.openBeginTime = openBeginTime;
	}

	public String getOpenEndTime() {
		return openEndTime;
	}

	public void setOpenEndTime(String openEndTime) {
		this.openEndTime = openEndTime;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", userId=" + userId + ", userName="
				+ userName + ", password=" + password + ", userPhone="
				+ userPhone + ", position=" + position + ", address=" + address
				+ ", identify=" + identify + ", userPrice=" + userPrice
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", qq=" + qq + ", realName=" + realName + ", family="
				+ family + ", identityCard=" + identityCard
				+ ", identityImage=" + identityImage + ", lastRegisterTime="
				+ lastRegisterTime + ", threeId=" + threeId + ", userImage="
				+ userImage + ", userSex=" + userSex + ", birthday=" + birthday
				+ ", isUse=" + isUse + ", token=" + token + ", cityId="
				+ cityId + ", openTime=" + openTime + ", endTime=" + endTime
				+ ", openBeginTime=" + openBeginTime + ", openEndTime="
				+ openEndTime + ", wechat=" + wechat + ", coordX=" + coordX
				+ ", coordY=" + coordY + ", intro=" + intro + ", state="
				+ state + "]";
	}


}
