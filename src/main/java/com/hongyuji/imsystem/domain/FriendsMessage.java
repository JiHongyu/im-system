package com.hongyuji.imsystem.domain;

import java.util.List;

/**
 * @description:
 * @author: jihy
 * @date: 2018-07-15 21:19
 */
public class FriendsMessage implements MessageInterface {

    private List<Long> onlineUid;
    private List<Long> offlineUid;

    public List<Long> getOnlineUid() {
        return onlineUid;
    }

    public void setOnlineUid(List<Long> onlineUid) {
        this.onlineUid = onlineUid;
    }

    public List<Long> getOfflineUid() {
        return offlineUid;
    }

    public void setOfflineUid(List<Long> offlineUid) {
        this.offlineUid = offlineUid;
    }

    @Override
    public int getType() {
        return 2;
    }
}
