package com.baidu.mapapi.search.bean.result.route.ridingrouteresult;

import java.util.List;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.bean.result.route.BMFRouteNode;
import com.baidu.mapapi.search.bean.result.route.BMFStep;
import com.baidu.mapapi.search.route.BikingRouteLine;

public class BMFRidingStep extends BMFStep {
    /**
     * 该路段起点方向值
     */
    int direction;

    /**
     * 路段入口信息
     */
    BMFRouteNode entrace;

    /**
     * 获取该路段入口指示信息
     */
    String entraceInstruction;

    /**
     * 路段出口信息
     */
    BMFRouteNode exit;

    /**
     * 获取该路段出口指示信息
     */
    String exitInstruction;

    /**
     * 获取该路段指示信息
     */
    String instruction;

    /**
     * 路段转弯类型
     */
    String turnType;

    /**
     * 限行类型,0:正常路段，无限行； 1表示禁行；2表示逆行
     */
    int restrictionsStatus;

    /**
     * 限行信息,如 "包含禁行路段|包含逆行路段"
     */
    String restrictionsInfo;

    public BMFRidingStep(BikingRouteLine.BikingStep bikingStep) {
        super(bikingStep);
        if (null == bikingStep) {
            return;
        }

        this.direction = bikingStep.getDirection();
        this.entrace = new BMFRouteNode(bikingStep.getEntrance());
        this.entraceInstruction = bikingStep.getEntranceInstructions();
        this.exit = new BMFRouteNode(bikingStep.getExit());
        this.exitInstruction = bikingStep.getExitInstructions();
        this.instruction = bikingStep.getInstructions();
        this.turnType = bikingStep.getTurnType();
        this.restrictionsStatus = bikingStep.getRestrictionsStatus();
        this.restrictionsInfo = bikingStep.getRestrictionsInfo();
    }
}
