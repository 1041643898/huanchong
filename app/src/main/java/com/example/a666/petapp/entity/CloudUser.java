/**   
 * @描述:	
 * @作�??:	Android - CXP
 * @创建日期:	2016�?4�?20�? 上午11:48:58  
 */
package com.example.a666.petapp.entity;

/**
 * @描述: 云数据对�?
 * @作�??: Android - CXP
 * @创建日期: 2016�?4�?20�? 上午11:48:58
 */
public class CloudUser {

	private Integer _id;
	private String _name;
	private String _location;
	private String _address;
	private String photo;
	private String state;
	private String usersId;

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_location() {
		return _location;
	}

	public void set_location(String _location) {
		this._location = _location;
	}

	public String get_address() {
		return _address;
	}

	public void set_address(String _address) {
		this._address = _address;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUsersId() {
		return usersId;
	}

	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}

}
