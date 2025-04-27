package com.baidu.mapapi.search.bean.result.recommend;

import com.baidu.mapapi.search.core.RecommendStationStopInfo;
import com.baidu.mapapi.search.core.RecommendStopInfo;

import java.util.ArrayList;
import java.util.List;

public class RecommendStationStopInfoBean {

    /**
     * 场站名称
     */
    private String stationName;

    /**
     * 场站信息
     */
    private List<RecommendStopInfoBean> recommendStopsInfoList = new ArrayList<>();

    public RecommendStationStopInfoBean(RecommendStationStopInfo stationStopInfo) {
        if (stationStopInfo == null) {
            return;
        }

        this.stationName = stationStopInfo.getStationName();
        List<RecommendStopInfo> infoList = stationStopInfo.getRecommendStopInfoList();
        if (infoList == null || infoList.size() <= 0) {
            return;
        }

        for (RecommendStopInfo recommendStopInfo : infoList) {
            RecommendStopInfoBean recommendStopInfoBean = new RecommendStopInfoBean(recommendStopInfo);
            this.recommendStopsInfoList.add(recommendStopInfoBean);
        }
    }
}
