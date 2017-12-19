package com.example.a666.petapp.entity;

/**
 * Created by 666 on 2017/12/18.
 */

public class LoginBen {

    /**
     * ret : true
     * result : {"isUse":0,"userPhone":15690848152,"password":"A84B413C2DECAFAC69F2467BBF431065","id":0,"state":0,"qq":555555555,"userSex":1,"wechat":"15093977545","userName":"555555","userId":"55e2b3fafc0f40058d4bc6225057c01c","position":1}
     */

    private boolean ret;
    private ResultBean result;

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * isUse : 0
         * userPhone : 15690848152
         * password : A84B413C2DECAFAC69F2467BBF431065
         * id : 0
         * state : 0
         * qq : 555555555
         * userSex : 1
         * wechat : 15093977545
         * userName : 555555
         * userId : 55e2b3fafc0f40058d4bc6225057c01c
         * position : 1
         */

        private int isUse;
        private long userPhone;
        private String password;
        private int id;
        private int state;
        private int qq;
        private int userSex;
        private String wechat;
        private String userName;
        private String userId;
        private int position;

        public int getIsUse() {
            return isUse;
        }

        public void setIsUse(int isUse) {
            this.isUse = isUse;
        }

        public long getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(long userPhone) {
            this.userPhone = userPhone;
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

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getQq() {
            return qq;
        }

        public void setQq(int qq) {
            this.qq = qq;
        }

        public int getUserSex() {
            return userSex;
        }

        public void setUserSex(int userSex) {
            this.userSex = userSex;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }
}
