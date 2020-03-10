/**  
 * All rights Reserved, Designed By Hantu
 * @Title:  package-info.java   
 * @Package com.snail.entity   
 * @Description:*描述*  
 * @author: DrogenCat   
 * @date:   Nov 7, 2018 10:05:08 AM   
 * @version V1.0 
 * @Copyright: 2018 Hantu All rights reserved. 
 * 注意：本内容仅限于上海瀚途公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.snail.entity;

import java.util.Date;

public class UserInfo{
	private Integer userID;
	private String userName;
	private String password;
	private String nickName;
	private Integer status;
	private Integer createDate;
	private Integer updateDate;
	private String remark;
	private String faceIdentity;
	private Date recordDate;
	
	
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public String getFaceIdentity() {
		return faceIdentity;
	}
	public void setFaceIdentity(String faceIdentity) {
		this.faceIdentity = faceIdentity;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Integer createDate) {
		this.createDate = createDate;
	}
	public Integer getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Integer updateDate) {
		this.updateDate = updateDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "UserInfo [userID=" + userID + ", userName=" + userName + ", password=" + password + ", nickName="
				+ nickName + ", status=" + status + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", remark=" + remark + "]";
	}
	
	
	
}