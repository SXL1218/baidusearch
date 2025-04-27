package com.baidu.mapapi.search.bean.option.route;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.TransitResultNode;
import com.baidu.mapapi.search.route.PlanNode;

import android.text.TextUtils;

public class BMFRoutePlanNode {
    /** 节点所在城市 */
    public String cityName;

    /** 节点所在城市ID */
    public int cityID;

    /** 节点名称 */
    public String name;

    /** 节点坐标 */
    public LatLng pt;

    /** 节点uid */
    public String uid;

    public PlanNode toPlanNode(){
        PlanNode planNode = null;
        if (!TextUtils.isEmpty(uid)) {
            if (null != pt) {
                planNode = PlanNode.withLocationAndPoiId(pt, uid);
            } else if (!TextUtils.isEmpty(cityName) && !TextUtils.isEmpty(name)) {
                planNode = PlanNode.withCityNameAndPlaceNameAndPoiId(cityName.trim(), name.trim(), uid);
            } else if (cityID >= 0 && !TextUtils.isEmpty(name)) {
                planNode = PlanNode.withCityCodeAndPlaceNameAndPoiId(cityID, name.trim(), uid);
            }
        } else {
            if (null != pt) {
                planNode = PlanNode.withLocation(pt);
            } else if (!TextUtils.isEmpty(cityName) && !TextUtils.isEmpty(name)) {
                planNode = PlanNode.withCityNameAndPlaceName(cityName.trim(), name.trim());
            } else if (cityID >= 0 && !TextUtils.isEmpty(name)) {
                planNode = PlanNode.withCityCodeAndPlaceName(cityID, name.trim());
            }
        }


        return planNode;
    }

    public BMFRoutePlanNode(TransitResultNode transitResultNode) {
        if (null == transitResultNode) {
            return;
        }

        this.cityID = transitResultNode.getCityId();
        this.cityName = transitResultNode.getCityName();
        this.name = transitResultNode.getSearchWord();
        this.pt = transitResultNode.getLocation();
    }

    public BMFRoutePlanNode(RouteNode routeNode) {
        if (null == routeNode) {
            return;
        }

        this.pt = routeNode.getLocation();
        this.name = routeNode.getTitle();
    }
}
