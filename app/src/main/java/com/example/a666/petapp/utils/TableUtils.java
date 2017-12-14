/**   
 * @描述						:	
 * @文件名称					:	TableUtils.java   
 * @�??属包名称					:	com.huanchong.pet.utils
 * @作�??						:	Android - yhq
 * @版本						:	v1.0
 * @创建日期					:	2016�??3�??14�?? 
 */
package com.example.a666.petapp.utils;



import android.provider.BaseColumns;

/**
 * @描述 : �??有数据库本地�??
 * @类名 : TableUtils
 * @作�?? : Android - yhq
 * @版本 : v1.0
 * @日期 : 2016�??3�??14�??
 */
public class TableUtils {

	/**
	 * 
	 * @描述 : 登录用户
	 * @类名 : LoginUser
	 * @作�?? : Android - yhq
	 * @版本 : v1.0
	 * @日期 : 2016�??3�??14�??
	 */
	public static final class LoginUser implements BaseColumns {
		public static final String TABLE_NAME = "LoginUser.db";// 表名
		public static final String PHOTO = "photo";// 头像
		public static final String USERNAME = "userName";// 用户�??
		public static final String UID = "uId";// 用户Id
		public static final String PASSWORD = "password";// 密码
	}
	
	/**
	 * 
	 * @Description:(用户信息实体)
	 * @ClassName: UserInfo
	 */
	public static final class UserInfoVO implements BaseColumns {
		public static final String ID = "id";
		public static final String ORDERID = "orderId"; // 订单id
		public static final String USERSID = "usersId";// 寄养师id
		public static final String USERSNAME = "usersName";// 寄养师名�?
		public static final String USERID = "userId";// 用户id
		public static final String USERNAME = "userName";// 用户名称
		public static final String ORDERSTATE = "orderState";// 订单状�??
		public static final String SERVICEBEGINTIME = "serviceBeginTime";// 服务�?始时�?
		public static final String SERVICEENDTIME = "serviceEndTime";// 服务结束时间
		public static final String ORDERAMOUNT = "orderAmount";// 订单金额
		public static final String RECEIVABLEAMOUNT = "receivableAmount";// 应收金额
		public static final String PAIDUPAMOUNT = "paidUpAmount";// 实收金额
		public static final String CREATETIME = "createTime";// 创建时间
		public static final String UPDATETIME = "updateTime";// 更新时间
		public static final String USERWORD = "userWord";// 用户留言
		public static final String ORDERITEMINFOVOS = "orderItemInfoVOs";// 订单详情信息
	}


	/**
	 * @描述 : 宠物信息实体�??
	 * @类名 : PetInfo
	 * @作�?? : Android - yhq
	 * @版本 : v1.0
	 * @日期 : 2016�??3�??22�??
	 */
	public static final class PetInfo implements BaseColumns {
		public static final String TABLE_NAME = "PetInfo.db";// 表名
		public static final String ID = "id"; // ID编号
		public static final String PETCODE = "petCode";// 宠物代码
		public static final String PETNAME = "petName";// 宠物昵称
		public static final String USERID = "userid"; // 用户id
		public static final String PETSEX = "petsex"; // 宠物性别
		public static final String PETBIRTHTIME = "petBirthTime"; // 宠物生日
		public static final String PETINFO = "petInfo"; // 宠物信息
		public static final String CREATETIME = "createTime"; // 宠物创建时间
		public static final String UPDATETIME = "updateTime"; // 宠物修改时间
		public static final String PET_PICTRUE = "petImage"; // 宠物图片
		public static final String PETPRICE = "petPrice"; // 宠物图片
		public static final String PETTYPE = "pettype"; // 宠物类型
		public static final String PETTYPENAME = "petTypeName";// 宠物类型名称
		public static final String ISSTERILIZATION = "isSterilization";// 是否绝育
		public static final String PETWEIGHT = "petweight";// 宠物体重
		public static final String PETSTATE = "petstate";// 宠物状�??
		public static final String ISIMMUNE = "isimmune";// 宠物是否免疫
		public static final String USERNAME = "userName";// 用户名称
		public static final String PETPRICINGCODE = "petPricingCode";// 宠物定价code
		public static final String ISUSE = "isUse";// 是否启用
	}

	/**
	 * @描述 :数据字典实体
	 * @类名 : DictInfo
	 * @作�?? : Android - yhq
	 * @版本 : v1.0
	 * @日期 : Mar 23, 2016
	 */
	public static final class DictInfo implements BaseColumns {
		public static final String TABLE_NAME = "DictInfo.db";// 表名
		public static final String ID = "id";
		public static final String DICTKEY = "dictKey";// 字典标识key
		public static final String DICTVALUE = "dictValue";// 字典标识value
		public static final String ISUSE = "isUse";// 是否启用
	}

	/**
	 * @描述 :免疫信息实体
	 * @类名 : DictInfo
	 * @作�?? : Android - yhq
	 * @版本 : v1.0
	 * @日期 : Mar 23, 2016
	 */
	public static final class ImmuneInfo implements BaseColumns {
		public static final String TABLE_NAME = "ImmuneInfo.db";// 表名
		public static final String ID = "id";
		public static final String IMMUNECODE = "immuneCode";// 免疫code
		public static final String IMMUNENAME = "immuneName";// 免疫名称
		public static final String IMMUNETIME = "immuneTime";// 免疫时间
		public static final String ISUSE = "isUse"; // 是否启用
	}

	/**
	 * @描述 :消息记录实体
	 * @类名 : DictInfo
	 * @作�?? : Android - yhq
	 * @版本 : v1.0
	 * @日期 : Mar 23, 2016
	 */
	public static final class MessageLog implements BaseColumns {
		public static final String TABLE_NAME = "MessageLog.db";// 表名
		public static final String ID = "id";
		public static final String FROMUSERID = "fromUserId";// 发�?�用�??
		public static final String TOUSERID = "toUserId";// 接受用户
		public static final String MESSAGETYPE = "messageType";// 消息类型
		public static final String MESSAGEINFO = "messageInfo";// 消息内容
		public static final String CREATETIME = "createTime";// 创建时间
	}

	/**
	 * @描述 :订单信息实体
	 * @类名 : DictInfo
	 * @作�?? : Android - yhq
	 * @版本 : v1.0
	 * @日期 : Mar 23, 2016
	 */
	public static final class OrderInfo implements BaseColumns {
		public static final String TABLE_NAME = "OrderInfo.db";// 表名
		public static final String ID = "id";
		public static final String ORDERID = "orderId"; // 订单id
		public static final String PETID = "petId"; // 宠物id
		public static final String USERSID = "usersId";// 寄养师id
		public static final String USERID = "userId";// 用户id
		public static final String ORDERSTATE = "orderState";// 订单状�??
		public static final String SERVICEBEGSTRINGIME = "serviceBeginTime";// 服务�??始时�??
		public static final String SERVICEENDTIME = "serviceEndTime";// 服务结束时间
		public static final String ORDERAMOUNT = "orderAmount";// 订单金额
		public static final String PAIDUPAMOUNT = "paidUpAmount";// 实收金额
		public static final String CREATETIME = "createTime";// 创建时间
		public static final String UPSTRINGTIME = "upStringTime";// 更新时间
		public static final String USERWORD = "userWord";// 用户留言
		public static final String REFUSEREASON = "refuseReason";// 用户留言
	}

	/**
	 * @描述 :订单详情信息实体
	 * @类名 : DictInfo
	 * @作�?? : Android - yhq
	 * @版本 : v1.0
	 * @日期 : Mar 23, 2016
	 */
	public static final class OrderItemInfo implements BaseColumns {
		public static final String TABLE_NAME = "OrderItemInfo.db";// 表名
		public static final String ID = "id";
		public static final String ORDERID = "orderId";// 订单id
		public static final String PETID = "petId";// 宠物id
		public static final String PETPRICINGCODE = "petPricingCode";// 宠物定价code
		public static final String PETDURATION = "petDuration";// 寄养时长
		public static final String PRICE = "price";// 金额
		public static final String CREATETIME = "createTime";// 创建时间
		public static final String UPSTRINGTIME = "upStringTime";// 修改时间
		public static final String SERVICECODE = "serviceCode";// 服务code
		public static final String SERVICEPRICE = "servicePrice";// 服务定价
		public static final String PETPRICE = "petPrice";// 宠物类型定价
	}

	/**
	 * @描述 :支付日志实体
	 * @类名 : DictInfo
	 * @作�?? : Android - yhq
	 * @版本 : v1.0
	 * @日期 : Mar 23, 2016
	 */
	public static final class PayLog implements BaseColumns {
		public static final String TABLE_NAME = "PayLog.db";// 表名
		public static final String ID = "id";
		public static final String USERID = "userId";// 用户id
		public static final String CREATETIME = "createTime";// 创建时间
		public static final String PAYTYPE = "payType";// 支付类型
		public static final String PRICE = "price";// 支付金额
	}

	/**
	 * @描述 :宠物免疫信息实体
	 * @类名 : DictInfo
	 * @作�?? : Android - yhq
	 * @版本 : v1.0
	 * @日期 : Mar 23, 2016
	 */
	public static final class PetImmuneInfo implements BaseColumns {
		public static final String TABLE_NAME = "PetImmuneInfo.db";// 表名
		public static final String ID = "id";
		public static final String IMMUNECODE = "immuneCode";// 免疫code
		public static final String IMMUNENAME = "immuneName";// 免疫名称
		public static final String PETCODE = "petCode";// 宠物code
		public static final String IMMUNETIME = "immuneTime";// 免疫时间
		public static final String ISSTANDARD = "isStandard";// 免疫标准

	}

	/**
	 * @描述 :宠物定价信息VO
	 * @类名 : DictInfo
	 * @作�?? : Android - yhq
	 * @版本 : v1.0
	 * @日期 : Mar 23, 2016
	 */
	public static final class PetPricingInfo implements BaseColumns {
		public static final String TABLE_NAME = "PetPricingInfo.db";// 表名
		public static final String ID = "id";
		public static final String PETTYPECODE = "petTypeCode";// 宠物类型code
		public static final String PETTYPENAME = "petTypeName";// 宠物类型名称
		public static final String PETPRICE = "petPrice";// 价格
		public static final String UNIT = "unit";// 单位
		public static final String ISUSE = "isUse";// 是否启用
		public static final String CREATETIME = "createTime";// 创建时间
		public static final String UPSTRINGTIME = "upStringTime";// 修改时间
		public static final String BIGTYPE = "bigType";
		public static final String USERID = "userId";
	}

	/**
	 * @描述 :宠物信息实体
	 * @类名 : DictInfo
	 * @作�?? : Android - yhq
	 * @版本 : v1.0
	 * @日期 : Mar 23, 2016
	 */
	public static final class PetType implements BaseColumns {
		public static final String TABLE_NAME = "PetType.db";// 表名
		public static final String ID = "id";
		public static final String TYPECODE = "typeCode";// 类型code
		public static final String TYPENAME = "typeName";// 类型名称
		public static final String TYPEINDEX = "typeIndex";// 数据索引
		public static final String CREATETIME = "createTime";// 创建时间
		public static final String ISUSE = "isUse"; // 是否启用
		public static final String BIGTYPE = "bigType"; // �??属大�??
		public static final String PARENTTYPECODE = "parentTypeCode"; // �??属大�??
		public static final String TYPECODES = "typeCodes"; // �??属大�??
		
	}

	/**
	 * @描述 :服务定价信息实体
	 * @类名 : DictInfo
	 * @作�?? : Android - yhq
	 * @版本 : v1.0
	 * @日期 : Mar 23, 2016
	 */
	public static final class ServicePricingInfo implements BaseColumns {
		public static final String TABLE_NAME = "ServicePricingInfo.db";// 表名
		public static final String ID = "id";
		public static final String USERID = "userId";// 用户id
		public static final String SERVICECODE = "serviceCode";// 服务code
		public static final String SERVICENAME = "serviceName";// 服务名称
		public static final String SERVICEPRICE = "servicePrice";// 服务定价
		public static final String SERVICEPICTURE = "servicePicture";// 服务图片
		public static final String ISUSE = "isUse";// 是否启用
		public static final String CREATETIME = "createTime";// 创建时间
		public static final String UPSTRINGTIME = "upStringTime";// 修改时间
		public static final String SERVICETYPE = "serviceType";// 服务分类
		public static final String SERVICETYPENAME = "serviceTypeName";// 分类名称
		public static final String ISSTANDARD = "isStandard";// 是否是标准定�??
		public static final String PETPRICINGCODE = "petPricingCode";// 宠物服务定价
	}

	/**
	 * @描述 :用户信息实体
	 * @类名 : DictInfo
	 * @作�?? : Android - yhq
	 * @版本 : v1.0
	 * @日期 : Mar 23, 2016
	 */
	public static final class UserInfo implements BaseColumns {
		public static final String TABLE_NAME = "UserInfo.db";// 表名
		public static final String ID = "id";
		public static final String USERID = "userId";// 用户id
		public static final String USERSID = "usersId";// 寄养师Id
		public static final String USERNAME = "userName";// 用户�??
		public static final String PASSWORD = "password";// 密码
		public static final String USERPHONE = "userPhone";// 手机�??
		public static final String POSITION = "position";// 用户身份
		public static final String ADDRESS = "address";// 用户地址
		public static final String IDENTIFY = "identify";// 地图标识
		public static final String USERPRICE = "userPrice";// 帐号金额
		public static final String CREATETIME = "createTime";// 创建时间
		public static final String UPSTRINGTIME = "upStringTime";// 修改时间
		public static final String QQ = "qq";// QQ
		public static final String REALNAME = "realName";// 真实姓名
		public static final String FAMILY = "family";// 寄养家庭
		public static final String IDENTITYCARD = "identityCard";// 身份证号
		public static final String IDENTITYIMAGE = "identityImage";// 身份证照�??
		public static final String LASTREGISTERTIME = "lastRegisterTime";// �??后登录时�??
		public static final String THREEID = "threeId";// 第三方登录标�??
		public static final String USERIMAGE = "userImage";// 用户图片
		public static final String USERSEX = "userSex";// 用户年龄
		public static final String BIRTHDAY = "birthday";// 出生日期
		public static final String ISUSE = "isUse";// 是否启用
		public static final String TOKEN = "token";
		public static final String INTRO = "intro";// 家庭�??�??
		public static final String WECHAT = "wechat";// 微信
		public static final String OPENBEGINTIME = "openBeginTime";// 寄养�??始时�??
		public static final String OPENENDTIME = "openEndTime";// 寄养结束时间
		public static final String OPENTIME = "openTime";// 寄养�??始时�??
		public static final String ENDTIME = "endTime";// 寄养结束时间
	}

	/**
	 * @描述 :寄养师评价信息实�??
	 * @类名 : DictInfo
	 * @作�?? : Android - yhq
	 * @版本 : v1.0
	 * @日期 : Mar 23, 2016
	 */
	public static final class UsersEvaluatedInfo implements BaseColumns {
		public static final String TABLE_NAME = "UsersEvaluatedInfo.db";// 表名
		public static final String ID = "id";
		public static final String ORDERID = "orderId";// 订单id
		public static final String USERID = "userId";// 用户id
		public static final String USERSID = "usersId";// 寄养师id
		public static final String SCORE = "score";// 评分
		public static final String DESCRIPTION = "description";// 描述
		public static final String ISUSE = "isUse";// 是否有效
		public static final String CREATETIME = "createTime";// 评价时间
		public static final String PETID = "petId";// 宠物id
		public static final String USERNAME = "userName";// 用户�??
		public static final String USERSNAME = "usersName";// 寄养师名�??
		public static final String USERIMAGE = "userImage";// 用户图片
		public static final String PETDURATION = "petDuration";// 寄养时长
		public static final String PETTYPEDESC = "petTypeDesc";// 宠物类型描述
	}

}
