package com.kekelian.event;

public class VipEvent {
    /**
     * Type
     *  1.了解会员
     * 2.购买会员
     */
    private int type;

    public VipEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
