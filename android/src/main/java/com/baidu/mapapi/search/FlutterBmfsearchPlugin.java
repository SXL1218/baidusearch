package com.baidu.mapapi.search;

import androidx.annotation.NonNull;

import com.baidu.mapapi.search.handlers.HandlersFactory;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** FlutterBmfsearchPlugin */
public class FlutterBmfsearchPlugin implements FlutterPlugin, MethodCallHandler {

    private MethodChannel searchChannel;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        if (flutterPluginBinding == null) {
            return;
        }
        initMethodChannel(flutterPluginBinding.getBinaryMessenger());
    }

    private void initMethodChannel(BinaryMessenger binaryMessenger) {
        if (binaryMessenger == null) {
            return;
        }

        searchChannel = new MethodChannel(binaryMessenger, Constants.MethodChannelName.SEARCH_CHANNEL);
        searchChannel.setMethodCallHandler(this);
        MethodChannelManager.getInstance().putSearchChannel(searchChannel);
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        if (searchChannel != null) {
            searchChannel.setMethodCallHandler(null); // 清除 MethodChannel
        }
        HandlersFactory.getInstance().destroy();
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        HandlersFactory.getInstance().dispatchMethodHandler(call, result);
    }
}
