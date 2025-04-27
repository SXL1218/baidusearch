package com.baidu.mapapi.search.bean.result.building;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.bean.option.LatLngBean;
import com.baidu.mapapi.search.core.BuildingInfo;

public class BuildingInfoBean {

    /**
     * 高度
     */
    public double height;

    /**
     * 加密后的面
     */
    public String paths;

    /**
     * 加密后的中心点
     */
    public LatLngBean center;

    /**
     * 准确度
     */
    public int accuracy;

    public BuildingInfoBean (BuildingInfo buildingInfo) {
        if (null == buildingInfo) {
            return;
        }

        this.height = buildingInfo.getHeight();
        this.paths = buildingInfo.getGeom();
        LatLng center = buildingInfo.getCenter();
        if (null != center) {
            this.center = new LatLngBean(center.latitude, center.longitude);
        }
        this.accuracy = buildingInfo.getAccuracy();
    }
}
