package com.baidu.mapapi.search.bean.result.recommend;

import com.baidu.mapapi.search.bean.result.ResultBean;
import com.baidu.mapapi.search.core.RecommendStationStopInfo;
import com.baidu.mapapi.search.core.RecommendStopInfo;
import com.baidu.mapapi.search.recommendstop.RecommendStopResult;

import java.util.ArrayList;
import java.util.List;

public class RecommendStopBean extends ResultBean {

    /**
     * 推荐上车点信息
     */
    List<RecommendStopInfoBean> recommendStopInfoList = new ArrayList<>();

    /**
     * 3.4.0新增 场站信息
     */
    List<RecommendStationStopInfoBean> stationInfoList = new ArrayList<>();

    public RecommendStopBean(RecommendStopResult recommendStopResult) {

        if (recommendStopResult == null) {
            return;
        }
        this.error = recommendStopResult.error;
        this.id = String.valueOf(recommendStopResult.hashCode());

        List<RecommendStopInfo> infoList = recommendStopResult.getRecommendStopInfoList();
        if (infoList != null && infoList.size() > 0) {
            for (RecommendStopInfo recommendStopInfo : infoList) {
                RecommendStopInfoBean recommendStopInfoBean = new RecommendStopInfoBean(recommendStopInfo);
                recommendStopInfoList.add(recommendStopInfoBean);
            }
        }

        List<RecommendStationStopInfo> stationList = recommendStopResult.getStationInfoList();
        if (stationList != null && stationList.size() > 0) {
            for (RecommendStationStopInfo recommendStationInfo : stationList) {
                RecommendStationStopInfoBean recommendStationStopInfoBean =
                        new RecommendStationStopInfoBean(recommendStationInfo);
                stationInfoList.add(recommendStationStopInfoBean);
            }
        }
    }

}
