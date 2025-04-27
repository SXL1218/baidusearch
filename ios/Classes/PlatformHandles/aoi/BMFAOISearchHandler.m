//
//  BMFAOISearchHandler.m
//  flutter_baidu_mapapi_base
//
//  Created by v_wangdachuan on 2023/12/25.
//

#import "BMFAOISearchHandler.h"
#import <BaiduMapAPI_Base/BMKBaseComponent.h>
#import <BaiduMapAPI_Search/BMKSearchComponent.h>
#import <flutter_baidu_mapapi_base/BMFMapModels.h>
#import <flutter_baidu_mapapi_base/BMFDefine.h>

#import "BMFSearchHandles.h"
#import "BMFSearchMethodConst.h"
#import "BMFAOISearch.h"

@interface BMFAOISearchHandler ()<BMKAOISearchDelegate>

@end

@implementation BMFAOISearchHandler
@synthesize _channel;
@synthesize Id;

- (nonnull NSObject<BMFSearchHandle> *)initWith:(nonnull FlutterMethodChannel *)channel {
    _channel = channel;
    return self;
}

- (void)handleMethodCall:(nonnull FlutterMethodCall *)call result:(nonnull FlutterResult)result {
    if ([call.method isEqualToString:kBMFAOISearchMethod]) {
        BMKAOISearch *search = [BMKAOISearch new];
        search.delegate = self;
        BMFAOISearchOption *option = [BMFAOISearchOption bmf_modelWith:[call.arguments safeObjectForKey:@"AOISearchOption"]];
        Id = [[call.arguments safeObjectForKey:@"AOISearchOption"] safeObjectForKey:@"id"];
        BOOL flag = [search aoiSearch:[option toBMKAOISearchOption]];
         result(@{@"result":@(flag)});
    } else {
        result(FlutterMethodNotImplemented);
    }
}

#pragma mark - BMKAOISearchDelegate

- (void)onGetAOIResult:(BMKAOISearch *)searcher result:(BMKAOISearchResult *)result errorCode:(BMKSearchErrorCode)errorCode {
    if (!_channel) return;
    [[BMFSearchHandles defalutCenter].handlerArray removeObject:self];
    BMFAOISearchResult *res = [BMFAOISearchResult fromBMKAOISearchResult:result];
    res.Id = Id;
    [_channel invokeMethod:kBMFAOISearchMethod arguments:@{@"result": [res bmf_toDictionary], @"errorCode" : @(errorCode)} result:nil];
}

@end
