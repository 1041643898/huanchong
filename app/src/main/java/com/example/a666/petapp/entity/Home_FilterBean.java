package com.example.a666.petapp.entity;

import java.util.List;

//
//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//
//
//     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//               佛祖保佑         永无BUG
//
//
//
public class Home_FilterBean {

    /**
     * ret : true
     * desc : [{"score":0,"address":"北京市朝阳\n","coordX":"41.573734","coordY":"120.450372","distance":0,"price":20,"orderCount":1,"usersId":"06dc8e51c9444774a3f183d2243c4fb9","family":"hong\nhong"},{"score":0,"address":"北京市九寨沟","coordX":"36.856329","userImage":"1","coordY":"114.494758","distance":0,"orderCount":0,"usersId":"0e6a26bb351c4a818512c72d4b6bd7e5","family":"家家"},{"score":0,"address":"广东省广州市东山区","coordX":"20.044002","userImage":"/15ecf9c30f0847cb99c0c284ec411a24/IMG_20160622_101243.jpg","coordY":"116.365868","distance":0,"price":10,"orderCount":6,"usersId":"15ecf9c30f0847cb99c0c284ec411a24","family":"ABC"},{"score":0,"address":"北京市  西城区","coordX":"39.912289","coordY":"116.365868","distance":0,"price":10,"orderCount":0,"usersId":"43e2a6c8829245d488f90197e3c84b08","family":"昵称"},{"score":3.6667,"address":"北京市昌平区沙河沙阳路18号北京科技职业学院","coordX":"40.11765","userImage":"http://q.qlogo.cn/qqapp/100371282/290BB8E0BEC8DF5989060A6947C3E75D/40","coordY":"116.250639","distance":0,"price":20,"orderCount":53,"usersId":"536e2c7b99204bbb81ad8fa7e6b2860f","family":"小街爆的家"},{"score":0,"address":"吕梁市汾阳市东关村牛王堂","coordX":"36.856329","userImage":"/63344fce512f449a988b1f330ee0e8db/IMG_20160424_162747.png","coordY":"114.494758","distance":0,"price":40,"orderCount":2,"usersId":"63344fce512f449a988b1f330ee0e8db","family":"家家"},{"score":0,"address":"福建省.福州市.枞阳县","coordX":"40.0493","userImage":"http://q.qlogo.cn/qqapp/100371282/B368CC7246CC4A360C4305F64FE9173A/40","coordY":"116.296482","distance":0,"price":30,"orderCount":2,"usersId":"6e710fd188b94d12bf12a6509ff3fe1f","family":"寄养998"},{"score":0,"address":"安徽省安庆市","coordX":"30.543494","coordY":"117.063755","distance":0,"price":30,"orderCount":0,"usersId":"8176d07f00424c0f950da7b2f913729d","family":"gaas"},{"score":0,"address":"北京市","coordX":"39.90403","coordY":"116.407526","distance":0,"price":10,"orderCount":1,"usersId":"893cb45fa9ea4ffb8c9b28656f41a146","family":"zachay"},{"score":0,"address":"安徽省安庆市","coordX":"30.543494","userImage":"/9898fd90343b49ed82f71d10a084da28/IMG_20160517_114349.png","coordY":"117.063755","distance":0,"price":10,"orderCount":0,"usersId":"9898fd90343b49ed82f71d10a084da28","family":"12321321"}]
     */

    private boolean ret;
    private List<DescBean> desc;

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public List<DescBean> getDesc() {
        return desc;
    }

    public void setDesc(List<DescBean> desc) {
        this.desc = desc;
    }

    public static class DescBean {
        // ��������
        private String family;
        // �����Ǽ�(�����Ǽ�����Ҳ��֪������ɶ�Ǽ�)
        private String score;
        // ����
        private String distance;

        // ������ͼ۸�?
        private String price;
        // ����λ��
        private String address;
        // ������ͷ��
        private String userImage;
        // ����ʦId
        private String userId;
        // γ��
        private String coordY;
        // ����
        private String coordX;
        private String orderCount;

        public DescBean() {
            super();
        }

        public DescBean(String family, String score, String distance,
                        String price, String address, String userImage, String userId,
                        String coordY, String coordX, String orderCount) {
            super();
            this.family = family;
            this.score = score;
            this.distance = distance;
            this.price = price;
            this.address = address;
            this.userImage = userImage;
            this.userId = userId;
            this.coordY = coordY;
            this.coordX = coordX;
            this.orderCount = orderCount;
        }

        @Override
        public String toString() {
            return "BoardMessage [family=" + family + ", score=" + score
                    + ", distance=" + distance + ", price=" + price + ", address="
                    + address + ", userImage=" + userImage + ", userId=" + userId
                    + ", coordY=" + coordY + ", coordX=" + coordX + ", orderCount="
                    + orderCount + "]";
        }

        public String getFamily() {
            return family;
        }

        public void setFamily(String family) {
            this.family = family;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUserImage() {
            return userImage;
        }

        public void setUserImage(String userImage) {
            this.userImage = userImage;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCoordY() {
            return coordY;
        }

        public void setCoordY(String coordY) {
            this.coordY = coordY;
        }

        public String getCoordX() {
            return coordX;
        }

        public void setCoordX(String coordX) {
            this.coordX = coordX;
        }

        public String getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(String orderCount) {
            this.orderCount = orderCount;
        }
    }
}
