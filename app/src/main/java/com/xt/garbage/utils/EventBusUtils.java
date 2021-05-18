package com.xt.garbage.utils;

import com.xt.garbage.workmain.EventMessage;

import org.greenrobot.eventbus.EventBus;

/**
 * @author:DIY
 * @date: 2021/3/23
 */
public class EventBusUtils {
    private EventBusUtils() {
    }

    /**
     * 注册 EventBus
     * @param subscribe
     */
    public static void register(Object subscribe) {
        EventBus eventBus = EventBus.getDefault();
        if(!eventBus.isRegistered(subscribe)) {
            eventBus.register(subscribe);
        }
    }

    /**
     * 解除注册
     * @param subscribe
     */
    public static void unRegister(Object subscribe) {
        EventBus eventBus = EventBus.getDefault();
        if(!eventBus.isRegistered(subscribe)) {
            eventBus.unregister(subscribe);
        }
    }

    /**
     * 发送事件消息
     * @param event
     */
    public static void post(EventMessage event) {
        EventBus.getDefault().post(event);
    }

    /**
     * 发送粘性事件
     * @param event
     */
    public static void postSticky(EventMessage event) {
        EventBus.getDefault().postSticky(event);
    }
}
