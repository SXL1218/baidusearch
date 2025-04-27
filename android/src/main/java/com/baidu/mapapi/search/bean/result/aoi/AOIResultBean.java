package com.baidu.mapapi.search.bean.result.aoi;

import com.baidu.mapapi.search.aoi.AoiResult;
import com.baidu.mapapi.search.bean.result.ResultBean;
import com.baidu.mapapi.search.core.AoiInfo;

import java.util.ArrayList;
import java.util.List;

public class AOIResultBean extends ResultBean {

    public List<AOIInfo> aoiInfoList = new ArrayList<>();

    public AOIResultBean(AoiResult result) {
        if (result != null) {
            List<AoiInfo> tempAoiList = result.getAoiList();
            if (null != tempAoiList && tempAoiList.size() > 0) {
                for (AoiInfo aoiInfo : tempAoiList) {
                    this.aoiInfoList.add(new AOIInfo(aoiInfo));
                }
            }
            this.error = result.error;
            this.id = String.valueOf(result.hashCode());
        }
    }
}
