//
//  BMFAOISearch.m
//  flutter_baidu_mapapi_base
//
//  Created by v_wangdachuan on 2023/12/25.
//

#import "BMFAOISearch.h"
#import <BaiduMapAPI_Search/BMKAOISearchOption.h>
#import <BaiduMapAPI_Search/BMKAOISearchResult.h>

@implementation BMFAOISearchOption

- (BMKAOISearchOption *)toBMKAOISearchOption {
    BMKAOISearchOption *option = [BMKAOISearchOption new];
    if (self.locations) {
        option.locations = self.locations;
    }
    return option;
}

@end




@implementation BMFAOISearchResult

+ (NSDictionary *)bmf_setupObjectClassInArray {
    return @{@"aoiInfoList" : @"BMFAOIInfo"};
}

+ (NSDictionary *)bmf_setupReplacedKeyFromPropertyName {
    return @{@"Id": @"id"};
}

+ (instancetype)fromBMKAOISearchResult:(BMKAOISearchResult *)result {
    BMFAOISearchResult *res = [BMFAOISearchResult new];
    if (result.aoiInfoList && result.aoiInfoList.count > 0) {
        NSMutableArray<BMFAOIInfo *> *mut = [NSMutableArray array];
        for (BMKAOIInfo *info in result.aoiInfoList) {
            [mut addObject:[BMFAOIInfo fromBMKAOIInfo:info]];
        }
        res.aoiInfoList = [mut copy];
    }
    return res;
}

@end

@implementation BMFAOIInfo

+ (instancetype)fromBMKAOIInfo:(BMKAOIInfo *)info {

    BMFAOIInfo *aoiInfo = [[BMFAOIInfo alloc] init];
    aoiInfo.order = info.order;
    aoiInfo.uid = info.uid;
    aoiInfo.name = info.name;
    aoiInfo.type = (BMFAOIType)info.type;
    aoiInfo.paths = info.paths;
    aoiInfo.nearestDistance = info.nearestDistance;
    aoiInfo.relation = info.relation;

    return aoiInfo;
}

@end
