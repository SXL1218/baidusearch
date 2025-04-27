package com.baidu.mapapi.search.bean.result;

import com.baidu.mapapi.search.core.SearchResult;

public class ResultBean {
    public SearchResult.ERRORNO error;

    // 用于对应IOS解析，否则会解析时会报错
    public String id;
}
