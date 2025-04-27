package com.baidu.mapapi.search.bean.result.aoi;

import com.baidu.mapapi.search.core.AoiInfo;

public class AOIInfo {

    /**
     * 对应输入点顺序，用来区分是哪个点返回的aoi。
     * 注意：该字段从1开始计数
     */
    public int order;

    /**
     * aoi的uid
     */
    public String uid;

    /**
     * aoi的名称
     */
    public String name;

    /**
     * aoi的类型
     */
    public int type;

    /**
     * aoi的加密面数据
     */
    public String paths;

    /**
     * 点距离aoi边界最近距离，单位：米
     */
    public int nearestDistance;

    /**
     * 对应输入点与aoi的关系
     * 0（aoi内），1（aoi外），2（未找到）
     */
    public int relation;


    public AOIInfo(AoiInfo aoiInfo) {
        this.order = aoiInfo.getOrder();
        this.uid = aoiInfo.getUid();
        this.name = aoiInfo.getAoiName();
        this.type = aoiInfo.getAoiType().ordinal();
        this.paths = aoiInfo.getPolygon();
        this.nearestDistance = aoiInfo.getNearestDistance();
        this.relation = aoiInfo.getRelation();
    }
}
