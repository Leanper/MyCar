package com.ygst.cenggeche.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/21.
 */

public class ConversationBean implements Serializable {
    //聊天目标的昵称
    private String targetNickname;

    //目标Id
    private String targetId;

    //最后的聊天内容
    protected String lastType;
    //最后会话时间
    protected long lastMsgDate;
}
