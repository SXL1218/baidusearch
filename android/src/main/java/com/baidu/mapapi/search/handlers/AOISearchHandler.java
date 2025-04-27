package com.baidu.mapapi.search.handlers;

import android.text.TextUtils;
import android.util.Log;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.Constants;
import com.baidu.mapapi.search.MethodChannelManager;
import com.baidu.mapapi.search.MethodID;
import com.baidu.mapapi.search.aoi.AoiResult;
import com.baidu.mapapi.search.aoi.AoiSearch;
import com.baidu.mapapi.search.aoi.AoiSearchOption;
import com.baidu.mapapi.search.aoi.OnGetAoiSearchResultListener;
import com.baidu.mapapi.search.bean.option.aoi.AOIOptionBean;
import com.baidu.mapapi.search.bean.result.aoi.AOIResultBean;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.utils.GsonFactory;
import com.baidu.mapapi.search.utils.ParseErrorCode;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class AOISearchHandler extends MethodChannelHandler
                implements OnGetAoiSearchResultListener {

    private static final String TAG = AOISearchHandler.class.getSimpleName();

    private AoiSearch mAoiSearch;

    public AOISearchHandler() {
        mAoiSearch = AoiSearch.newInstance();
        mGson = GsonFactory.getInstance().getGson();
        mMethodChannel = MethodChannelManager.getInstance().getSearchChannel();
    }

    @Override
    public void handleMethodCallResult(MethodCall call, MethodChannel.Result result) {
        super.handleMethodCallResult(call, result);
        if (null == mGson || null == mAoiSearch) {
            sendReturnResult(false);
            return;
        }

        HashMap<String, Object> argumentsMap = (HashMap<String, Object>) call.arguments;
        if (null == argumentsMap) {
            sendReturnResult(false);
            return;
        }

        HashMap<String, Object> aoiOptionMap =
                (HashMap<String, Object>) argumentsMap.get("AOISearchOption");
        if (null == aoiOptionMap) {
            sendReturnResult(false);
            return;
        }

        String jsonStr = mGson.toJson(aoiOptionMap);
        if (null == jsonStr) {
            sendReturnResult(false);
            return;
        }

        AOIOptionBean aoiOptionBean = mGson.fromJson(jsonStr, AOIOptionBean.class);
        if (null == aoiOptionBean) {
            sendReturnResult(false);
            return;
        }

        if (aoiOptionBean.locations == null) {
            sendReturnResult(false);
            return;
        }

        ArrayList<LatLng> latLngList = new ArrayList<>();

        if (aoiOptionBean.locations.contains(";")) {
            String[] locationList = aoiOptionBean.locations.split(";");
            for (String location : locationList) {
                if (!location.isEmpty()) {
                    String[] split = location.split(",");
                    if (split.length == 2) {
                        String lon = split[0];
                        String lat = split[1];
                        try {
                            double longitude = Double.parseDouble(lon);
                            double latitude = Double.parseDouble(lat);
                            latLngList.add(new LatLng(latitude, longitude));
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        AoiSearchOption aoiSearchOption = new AoiSearchOption();
        aoiSearchOption.setLatLngList(latLngList);

        mAoiSearch.setOnGetAoiSearchResultListener(this);
        boolean ret = mAoiSearch.requestAoi(aoiSearchOption);

        sendReturnResult(ret);
    }

    public void sendSearchResult(final Object value, final int errorCode) {
        if (null == mMethodChannel) {
            return;
        }

        mMethodChannel
                .invokeMethod(MethodID.AOISearchMethodID.AOI_SEARCH, new HashMap<String,
                        Object>() {
                    {
                        put(Constants.RESULT_KEY, value);
                        put(Constants.ERROR_KEY, errorCode);
                    }
                });

    }

    @Override
    public void destroy() {
        if (mAoiSearch != null) {
            mAoiSearch.destroy();
        }
    }

    @Override
    public void onGetAoiResult(AoiResult aoiResult) {
        if (null == aoiResult) {
            sendSearchResult(null, -1);
            return;
        }

        AOIResultBean aoiResultBean = new AOIResultBean(aoiResult);

        String aoiResultJson = mGson.toJson(aoiResultBean);
        if (TextUtils.isEmpty(aoiResultJson)) {
            sendSearchResult(null, -1);
            return;
        }

        try {
            final HashMap<String, Object> resultMap =
                    mGson.fromJson(aoiResultJson, new TypeToken<HashMap<String, Object>>() {
                    }.getType());

            String errorStr = (String) resultMap.get("error");
            if (TextUtils.isEmpty(errorStr)) {
                sendSearchResult(null, -1);
                return;
            }

            int errorCode = ParseErrorCode.getInstance()
                    .getErrorCode(SearchResult.ERRORNO.valueOf(errorStr));

            sendSearchResult(resultMap, errorCode);
        } catch (JsonSyntaxException e) {
            Log.e(TAG, e.toString());
        }
    }
}
