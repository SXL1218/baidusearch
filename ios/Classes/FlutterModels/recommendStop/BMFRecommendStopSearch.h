//
//  BMFRecommendStopSearch.h
//  flutter_baidu_mapapi_search
//
//  Created by Zhang,Baojin on 2020/11/12.
//

#import <flutter_baidu_mapapi_base/BMFModel.h>

@class BMFCoordinate;
@class BMKRecommendStopSearchOption;
@class BMKRecommendStopInfo;
@class BMKRecommendStopSearchResult;
@class BMKStationRecommendStopInfo;

NS_ASSUME_NONNULL_BEGIN

@interface BMFRecommendStopSearchOption : BMFModel

/// 推荐上车点经纬度 （必选）
@property (nonatomic, strong) BMFCoordinate *location;


/// 是否需要场站上车点推荐（可选），默认：NO，设置为YES时，location位置在场站附近时返回对应的场站推荐上车点信息
@property (nonatomic, assign) BOOL isNeedStationInfo;

- (BMKRecommendStopSearchOption *)toBMKRecommendStopSearchOption;

@end


@interface BMFRecommendStopInfo : BMFModel

/// 推荐上车点名称
@property (nonatomic, copy) NSString *name;

/// 推荐上车点地址
@property (nonatomic, copy) NSString *address;

/// 推荐上车点经纬度
@property (nonatomic, strong) BMFCoordinate *location;

/// 推荐点poi的uid
@property (nonatomic, copy) NSString *uid;

/// 距查找点的距离，单位米
@property (nonatomic, assign) CGFloat distance;

+ (instancetype)fromBMKRecommendStopInfo:(BMKRecommendStopInfo *)result;

@end

@interface BMFStationRecommendStopInfo : BMFModel

/// 场站分层名称信息，逗号隔开，如:(北京首都国际机场,T1航站楼,停车场)，无场站分层信息时为空字符串
@property (nonatomic, copy) NSString *stationName;
/// 推荐上车点返回结果列表，无场站分层信息时为普通推荐上车点
@property (nonatomic, copy) NSArray<BMFRecommendStopInfo *> *recommendStopsInfoList;

+ (instancetype)fromBMKStationRecommendStopSearchResult:(BMKStationRecommendStopInfo *)result;

@end

@interface BMFRecommendStopSearchResult : BMFModel
/// flutter层的唯一id(用于区别哪次检索)
@property (nonatomic, copy) NSString *Id;

/// 推荐上车点返回结果列表
@property (nonatomic, copy) NSArray<BMFRecommendStopInfo *> *recommendStopInfoList;

/// 场站推荐上车点返回结果列表，当isNeedStationInfo为YES时返回
@property (nonatomic, copy) NSArray<BMFStationRecommendStopInfo *> *stationInfoList;

+ (instancetype)fromBMKRecommendStopSearchResult:(BMKRecommendStopSearchResult *)result;

@end



NS_ASSUME_NONNULL_END
