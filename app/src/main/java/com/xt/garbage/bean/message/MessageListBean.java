package com.xt.garbage.bean.message;

import com.alibaba.android.arouter.facade.service.SerializationService;

import java.util.List;

/**
 * @author:DIY
 * @date: 2021/3/28
 */
public class MessageListBean {

    /**
     * errorCode : (int32)
     * errorMsg : (string)
     * result : {"cleanMessageList":[{"createTime":"(date-time)创建时间","id":"(int64)消息ID","isRead":"(boolean)是否已读","msgBussId":"(int64)业务ID","msgContent":"(string)消息内容","msgTitle":"(string)消息标题","msgType":"(int32)消息类型：{0.系统消息 1.预约上门 2.垃圾清运 3.送货上门}"}],"deliveryMessageList":[{"createTime":"(date-time)创建时间","id":"(int64)消息ID","isRead":"(boolean)是否已读","msgBussId":"(int64)业务ID","msgContent":"(string)消息内容","msgTitle":"(string)消息标题","msgType":"(int32)消息类型：{0.系统消息 1.预约上门 2.垃圾清运 3.送货上门}"}],"subscribeMessageList":[{"createTime":"(date-time)创建时间","id":"(int64)消息ID","isRead":"(boolean)是否已读","msgBussId":"(int64)业务ID","msgContent":"(string)消息内容","msgTitle":"(string)消息标题","msgType":"(int32)消息类型：{0.系统消息 1.预约上门 2.垃圾清运 3.送货上门}"}],"sysMessageList":[{"createTime":"(date-time)创建时间","id":"(int64)消息ID","isRead":"(boolean)是否已读","msgBussId":"(int64)业务ID","msgContent":"(string)消息内容","msgTitle":"(string)消息标题","msgType":"(int32)消息类型：{0.系统消息 1.预约上门 2.垃圾清运 3.送货上门}"}],"unreadCleanMessage":"(int32)未读清运消息","unreadDeliveryMessage":"(int32)未读送货上门消息","unreadSubscribeMessage":"(int32)未读预约上门消息","unreadSysMessage":"(int32)未读系统消息"}
     * success : (boolean)
     */

    private int errorCode;
    private String errorMsg;
    private ResultDTO result;
    private boolean success;

    public MessageListBean() {
    }

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

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class ResultDTO {
        /**
         * cleanMessageList : [{"createTime":"(date-time)创建时间","id":"(int64)消息ID","isRead":"(boolean)是否已读","msgBussId":"(int64)业务ID","msgContent":"(string)消息内容","msgTitle":"(string)消息标题","msgType":"(int32)消息类型：{0.系统消息 1.预约上门 2.垃圾清运 3.送货上门}"}]
         * deliveryMessageList : [{"createTime":"(date-time)创建时间","id":"(int64)消息ID","isRead":"(boolean)是否已读","msgBussId":"(int64)业务ID","msgContent":"(string)消息内容","msgTitle":"(string)消息标题","msgType":"(int32)消息类型：{0.系统消息 1.预约上门 2.垃圾清运 3.送货上门}"}]
         * subscribeMessageList : [{"createTime":"(date-time)创建时间","id":"(int64)消息ID","isRead":"(boolean)是否已读","msgBussId":"(int64)业务ID","msgContent":"(string)消息内容","msgTitle":"(string)消息标题","msgType":"(int32)消息类型：{0.系统消息 1.预约上门 2.垃圾清运 3.送货上门}"}]
         * sysMessageList : [{"createTime":"(date-time)创建时间","id":"(int64)消息ID","isRead":"(boolean)是否已读","msgBussId":"(int64)业务ID","msgContent":"(string)消息内容","msgTitle":"(string)消息标题","msgType":"(int32)消息类型：{0.系统消息 1.预约上门 2.垃圾清运 3.送货上门}"}]
         * unreadCleanMessage : (int32)未读清运消息
         * unreadDeliveryMessage : (int32)未读送货上门消息
         * unreadSubscribeMessage : (int32)未读预约上门消息
         * unreadSysMessage : (int32)未读系统消息
         */

        private List<CleanMessageListDTO> cleanMessageList;
        private List<DeliveryMessageListDTO> deliveryMessageList;
        private List<SubscribeMessageListDTO> subscribeMessageList;
        private List<SysMessageListDTO> sysMessageList;
        private int unreadCleanMessage;
        private int unreadDeliveryMessage;
        private int unreadSubscribeMessage;
        private int unreadSysMessage;

        public ResultDTO() {
        }

        public List<CleanMessageListDTO> getCleanMessageList() {
            return cleanMessageList;
        }

        public void setCleanMessageList(List<CleanMessageListDTO> cleanMessageList) {
            this.cleanMessageList = cleanMessageList;
        }

        public List<DeliveryMessageListDTO> getDeliveryMessageList() {
            return deliveryMessageList;
        }

        public void setDeliveryMessageList(List<DeliveryMessageListDTO> deliveryMessageList) {
            this.deliveryMessageList = deliveryMessageList;
        }

        public List<SubscribeMessageListDTO> getSubscribeMessageList() {
            return subscribeMessageList;
        }

        public void setSubscribeMessageList(List<SubscribeMessageListDTO> subscribeMessageList) {
            this.subscribeMessageList = subscribeMessageList;
        }

        public List<SysMessageListDTO> getSysMessageList() {
            return sysMessageList;
        }

        public void setSysMessageList(List<SysMessageListDTO> sysMessageList) {
            this.sysMessageList = sysMessageList;
        }

        public int getUnreadCleanMessage() {
            return unreadCleanMessage;
        }

        public void setUnreadCleanMessage(int unreadCleanMessage) {
            this.unreadCleanMessage = unreadCleanMessage;
        }

        public int getUnreadDeliveryMessage() {
            return unreadDeliveryMessage;
        }

        public void setUnreadDeliveryMessage(int unreadDeliveryMessage) {
            this.unreadDeliveryMessage = unreadDeliveryMessage;
        }

        public int getUnreadSubscribeMessage() {
            return unreadSubscribeMessage;
        }

        public void setUnreadSubscribeMessage(int unreadSubscribeMessage) {
            this.unreadSubscribeMessage = unreadSubscribeMessage;
        }

        public int getUnreadSysMessage() {
            return unreadSysMessage;
        }

        public void setUnreadSysMessage(int unreadSysMessage) {
            this.unreadSysMessage = unreadSysMessage;
        }

        public static class CleanMessageListDTO {
            /**
             * createTime : (date-time)创建时间
             * id : (int64)消息ID
             * isRead : (boolean)是否已读
             * msgBussId : (int64)业务ID
             * msgContent : (string)消息内容
             * msgTitle : (string)消息标题
             * msgType : (int32)消息类型：{0.系统消息 1.预约上门 2.垃圾清运 3.送货上门}
             */

            private String createTime;
            private long id;
            private boolean isRead;
            private long msgBussId;
            private String msgContent;
            private String msgTitle;
            private int msgType;

            public CleanMessageListDTO() {
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public boolean isRead() {
                return isRead;
            }

            public void setRead(boolean read) {
                isRead = read;
            }

            public long getMsgBussId() {
                return msgBussId;
            }

            public void setMsgBussId(long msgBussId) {
                this.msgBussId = msgBussId;
            }

            public String getMsgContent() {
                return msgContent;
            }

            public void setMsgContent(String msgContent) {
                this.msgContent = msgContent;
            }

            public String getMsgTitle() {
                return msgTitle;
            }

            public void setMsgTitle(String msgTitle) {
                this.msgTitle = msgTitle;
            }

            public int getMsgType() {
                return msgType;
            }

            public void setMsgType(int msgType) {
                this.msgType = msgType;
            }
        }


        public static class DeliveryMessageListDTO {
            /**
             * createTime : (date-time)创建时间
             * id : (int64)消息ID
             * isRead : (boolean)是否已读
             * msgBussId : (int64)业务ID
             * msgContent : (string)消息内容
             * msgTitle : (string)消息标题
             * msgType : (int32)消息类型：{0.系统消息 1.预约上门 2.垃圾清运 3.送货上门}
             */

            private String createTime;
            private long id;
            private boolean isRead;
            private long msgBussId;
            private String msgContent;
            private String msgTitle;
            private int msgType;

            public DeliveryMessageListDTO() {
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public boolean isRead() {
                return isRead;
            }

            public void setRead(boolean read) {
                isRead = read;
            }

            public long getMsgBussId() {
                return msgBussId;
            }

            public void setMsgBussId(long msgBussId) {
                this.msgBussId = msgBussId;
            }

            public String getMsgContent() {
                return msgContent;
            }

            public void setMsgContent(String msgContent) {
                this.msgContent = msgContent;
            }

            public String getMsgTitle() {
                return msgTitle;
            }

            public void setMsgTitle(String msgTitle) {
                this.msgTitle = msgTitle;
            }

            public int getMsgType() {
                return msgType;
            }

            public void setMsgType(int msgType) {
                this.msgType = msgType;
            }
        }


        public static class SubscribeMessageListDTO {
            /**
             * createTime : (date-time)创建时间
             * id : (int64)消息ID
             * isRead : (boolean)是否已读
             * msgBussId : (int64)业务ID
             * msgContent : (string)消息内容
             * msgTitle : (string)消息标题
             * msgType : (int32)消息类型：{0.系统消息 1.预约上门 2.垃圾清运 3.送货上门}
             */

            private String createTime;
            private long id;
            private boolean isRead;
            private long msgBussId;
            private String msgContent;
            private String msgTitle;
            private int msgType;

            public SubscribeMessageListDTO() {
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public boolean isRead() {
                return isRead;
            }

            public void setRead(boolean read) {
                isRead = read;
            }

            public long getMsgBussId() {
                return msgBussId;
            }

            public void setMsgBussId(long msgBussId) {
                this.msgBussId = msgBussId;
            }

            public String getMsgContent() {
                return msgContent;
            }

            public void setMsgContent(String msgContent) {
                this.msgContent = msgContent;
            }

            public String getMsgTitle() {
                return msgTitle;
            }

            public void setMsgTitle(String msgTitle) {
                this.msgTitle = msgTitle;
            }

            public int getMsgType() {
                return msgType;
            }

            public void setMsgType(int msgType) {
                this.msgType = msgType;
            }
        }

        public static class SysMessageListDTO {
            /**
             * createTime : (date-time)创建时间
             * id : (int64)消息ID
             * isRead : (boolean)是否已读
             * msgBussId : (int64)业务ID
             * msgContent : (string)消息内容
             * msgTitle : (string)消息标题
             * msgType : (int32)消息类型：{0.系统消息 1.预约上门 2.垃圾清运 3.送货上门}
             */

            private String createTime;
            private long id;
            private boolean isRead;
            private long msgBussId;
            private String msgContent;
            private String msgTitle;
            private int msgType;

            public SysMessageListDTO() {
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public boolean isRead() {
                return isRead;
            }

            public void setRead(boolean read) {
                isRead = read;
            }

            public long getMsgBussId() {
                return msgBussId;
            }

            public void setMsgBussId(long msgBussId) {
                this.msgBussId = msgBussId;
            }

            public String getMsgContent() {
                return msgContent;
            }

            public void setMsgContent(String msgContent) {
                this.msgContent = msgContent;
            }

            public String getMsgTitle() {
                return msgTitle;
            }

            public void setMsgTitle(String msgTitle) {
                this.msgTitle = msgTitle;
            }

            public int getMsgType() {
                return msgType;
            }

            public void setMsgType(int msgType) {
                this.msgType = msgType;
            }
        }
    }
}
