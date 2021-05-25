package com.xt.garbage.bean.shop;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.List;

/**
 * @author:DIY
 * @date: 2021/5/25
 */
public class GetAddressResultBean {

    /**
     * errorCode : (int32)
     * errorMsg : (string)
     * result : [{"area":"(string)区","city":"(string)市","detailAddress":"(string)详细地址","id":"(int64)收货地址ID","isUse":"(boolean)是否常用地址","mobile":"(string)手机号","name":"(string)姓名","province":"(string)省","remark":"(string)备注","telephone":"(string)座机"}]
     * success : (boolean)
     */

    private int errorCode;
    private String errorMsg;
    private List<ResultDTO> result;
    private boolean success;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<ResultDTO> getResult() {
        return result;
    }

    public void setResult(List<ResultDTO> result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class ResultDTO  implements Parcelable {
        /**
         * area : (string)区
         * city : (string)市
         * detailAddress : (string)详细地址
         * id : (int64)收货地址ID
         * isUse : (boolean)是否常用地址
         * mobile : (string)手机号
         * name : (string)姓名
         * province : (string)省
         * remark : (string)备注
         * telephone : (string)座机
         */

        private String area;
        private String city;
        private String detailAddress;
        private int id;
        private boolean isUse;
        private String mobile;
        private String name;
        private String province;
        private String remark;
        private String telephone;

        protected ResultDTO(Parcel in) {
            area = in.readString();
            city = in.readString();
            detailAddress = in.readString();
            id = in.readInt();
            isUse = in.readByte() != 0;
            mobile = in.readString();
            name = in.readString();
            province = in.readString();
            remark = in.readString();
            telephone = in.readString();
        }

        public static final Creator<ResultDTO> CREATOR = new Creator<ResultDTO>() {
            @Override
            public ResultDTO createFromParcel(Parcel in) {
                return new ResultDTO(in);
            }

            @Override
            public ResultDTO[] newArray(int size) {
                return new ResultDTO[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(area);
            dest.writeString(city);
            dest.writeString(detailAddress);
            dest.writeInt(id);
            dest.writeBoolean(isUse);
            dest.writeString(mobile);
            dest.writeString(name);
            dest.writeString(province);
            dest.writeString(remark);
            dest.writeString(telephone);

        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDetailAddress() {
            return detailAddress;
        }

        public void setDetailAddress(String detailAddress) {
            this.detailAddress = detailAddress;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isUse() {
            return isUse;
        }

        public void setUse(boolean use) {
            isUse = use;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }


    }
}
