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
public class Screen_Pet_Bean {

    /**
     * ret : true
     * desc : [{"isUse":0,"parentTypeName":"","petTypeImage":"cat.png","parentTypeCode":"","typeName":"大型犬","id":0,"isRe":0,"petPrice":"10,20,30,40","typeCode":"23c8d60ef10644ee96314c11c4d3f86b"},{"isUse":0,"parentTypeName":"","petTypeImage":"cat.png","parentTypeCode":"","typeName":"中型犬","id":0,"isRe":0,"petPrice":"10,20,30","typeCode":"fe013d906bae4945a468780a94212ff7"},{"isUse":0,"parentTypeName":"","petTypeImage":"cat.png","parentTypeCode":"","typeName":"小型犬","id":0,"isRe":0,"petPrice":"20,30","typeCode":"20706e878a7b4625be0c5460371a6c25"},{"isUse":0,"parentTypeName":"","petTypeImage":"cat.png","parentTypeCode":"","typeName":"猫","id":0,"isRe":0,"petPrice":"10,60","typeCode":"2aa312a64be44067a4eee43b94c1f9b8"},{"isUse":0,"parentTypeName":"","petTypeImage":"cat.png","parentTypeCode":"","typeName":"小宠","id":0,"isRe":0,"petPrice":"30,50","typeCode":"ffd1209b320c4bb382c5bdac4f722cf4"}]
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
        /**
         * isUse : 0
         * parentTypeName :
         * petTypeImage : cat.png
         * parentTypeCode :
         * typeName : 大型犬
         * id : 0
         * isRe : 0
         * petPrice : 10,20,30,40
         * typeCode : 23c8d60ef10644ee96314c11c4d3f86b
         */

        private int isUse;
        private String parentTypeName;
        private String petTypeImage;
        private String parentTypeCode;
        private String typeName;
        private int id;
        private int isRe;
        private String petPrice;
        private String typeCode;

        public int getIsUse() {
            return isUse;
        }

        public void setIsUse(int isUse) {
            this.isUse = isUse;
        }

        public String getParentTypeName() {
            return parentTypeName;
        }

        public void setParentTypeName(String parentTypeName) {
            this.parentTypeName = parentTypeName;
        }

        public String getPetTypeImage() {
            return petTypeImage;
        }

        public void setPetTypeImage(String petTypeImage) {
            this.petTypeImage = petTypeImage;
        }

        public String getParentTypeCode() {
            return parentTypeCode;
        }

        public void setParentTypeCode(String parentTypeCode) {
            this.parentTypeCode = parentTypeCode;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsRe() {
            return isRe;
        }

        public void setIsRe(int isRe) {
            this.isRe = isRe;
        }

        public String getPetPrice() {
            return petPrice;
        }

        public void setPetPrice(String petPrice) {
            this.petPrice = petPrice;
        }

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }
    }
}
