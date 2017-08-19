package com.ygst.cenggeche.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/12.
 */

public class BaseBean implements Serializable {

    private String indexTag;
    private String name;

    public String getIndexTag() {
        return indexTag;
    }

    public void setIndexTag(String indexTag) {
        this.indexTag = indexTag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
