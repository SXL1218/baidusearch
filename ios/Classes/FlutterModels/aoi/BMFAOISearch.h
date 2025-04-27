//
//  BMFAOISearch.h
//  flutter_baidu_mapapi_base
//
//  Created by v_wangdachuan on 2023/12/25.
//

#import <flutter_baidu_mapapi_base/BMFModel.h>

@class BMKAOISearchOption;
@class BMKAOISearchResult;
@class BMKAOIInfo;

NS_ASSUME_NONNULL_BEGIN
/// aoi检索参数
@interface BMFAOISearchOption : BMFModel

/// 经纬度坐标
/// 注意以下两点：
/// 多个经纬度之间以 ; 号分割
/// 经纬度类型必须为 BMF_COORDTYPE_BD09LL
/// eg: 116.31380,40.07445;116.31087,40.07361
@property (nonatomic, copy) NSString *locations;

- (BMKAOISearchOption *)toBMKAOISearchOption;

@end


@class BMFAOIInfo;

/// aoi检索返回结果类
@interface BMFAOISearchResult : BMFModel
/// flutter层的唯一id(用于区别哪次检索)
@property (nonatomic, copy) NSString *Id;
/// aoi信息数组
@property (nonatomic, copy) NSArray<BMFAOIInfo *> *aoiInfoList;

+ (instancetype)fromBMKAOISearchResult:(BMKAOISearchResult *)result;

@end

/// aoi类型
typedef enum {
    BMF_AOI_TYPE_UNKNOWN              = 0,  ///< 未知类型
    BMF_AOI_TYPE_AIRPORT              = 1,  ///< 机场
    BMF_AOI_TYPE_RAILWAT_STATION      = 2,  ///< 火车站
    BMF_AOI_TYPE_SHOPPINGMALL         = 3,  ///< 商场
    BMF_AOI_TYPE_GAS_STATION          = 4,  ///< 加油站
    BMF_AOI_TYPE_SCHOOL               = 5,  ///< 学校
    BMF_AOI_TYPE_HOSPITAL             = 6,  ///< 医院
    BMF_AOI_TYPE_RESIDENTIAL_DISTRICT = 7,  ///< 住宅区
    BMF_AOI_TYPE_SCENIC_AREA          = 8,  ///< 风景区
    BMF_AOI_TYPE_PARK                 = 9,  ///< 公园
    BMF_AOI_TYPE_FREEWAY_SERVICE      = 10, ///< 服务区
    BMF_AOI_TYPE_WATER                = 11  ///< 水域
}BMFAOIType;


/// aoi信息类
@interface BMFAOIInfo : BMFModel

/// 对应输入点顺序，用来区分是哪个点返回的aoi。
/// 注意：该字段从1开始计数
@property (nonatomic, assign) int order;

/// aoid的uid
@property (nonatomic, copy) NSString *uid;

/// aoi的名称
@property (nonatomic, copy) NSString *name;

/// aoi的类型
@property (nonatomic, assign) BMFAOIType type;

/// aoi的加密面数据
@property (nonatomic, copy) NSString *paths;

/// 点距离aoi边界最近距离，单位：米
/// 注：点在aoi面内则返回0
@property (nonatomic, assign) int nearestDistance;

/// 对应输入点与aoi的关系
/// 0（aoi内），1（aoi外），2（未找到）
@property (nonatomic, assign) int relation;


+ (instancetype)fromBMKAOIInfo:(BMKAOIInfo *)info;

//- (BMKAOIInfo *)toBMKAOIInfo;

@end
NS_ASSUME_NONNULL_END
