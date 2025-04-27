package com.baidu.mapapi.search.bean.option;

import com.baidu.mapapi.search.base.LanguageType;

/**
 * 公交检索bean
 */
public class BuslineOptionBean {
    public String busLineUid = null;

    // 7.6.4删除了, 不再使用
    public String city = null;

    public String startUid = null;

    public String endUid = null;

    public int languageType = LanguageType.LanguageTypeChinese.ordinal();
}
