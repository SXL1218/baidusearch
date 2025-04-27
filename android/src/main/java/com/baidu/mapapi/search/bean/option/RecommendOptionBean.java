package com.baidu.mapapi.search.bean.option;

import com.baidu.mapapi.model.LatLng;

public class RecommendOptionBean {
    /**
     * 推荐上车点的位置坐标
     */
    public LatLng location;

    /**
     * 3.4.0 新增支持场站信息
     */
    public boolean isNeedStationInfo;
}
