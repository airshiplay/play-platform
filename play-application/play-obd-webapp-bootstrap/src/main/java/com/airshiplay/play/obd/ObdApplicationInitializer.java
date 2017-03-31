package com.airshiplay.play.obd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airshiplay.play.integration.ApplicationInitializer;
import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.main.init.InitDataTools;
import com.airshiplay.play.obd.entity.CarAlarmEntity;

@Component
public class ObdApplicationInitializer extends ApplicationInitializer {
	@Autowired
	private InitDataTools tools;

	@Override
	public void onRootContextRefreshed() {

		if (!tools.existMenuCode("obd_management")) {
			int sortNo = 5;
			MenuEntity obdManagement = tools.createMenuByParent("OBD管理", "obd_management", "glyphicon glyphicon-list", null, null, sortNo++, null);
			MenuEntity userMenu = tools.createMenuByParent("会员列表", "obd_user_list", "glyphicon glyphicon-list	", "page/obd/user/userList", null, sortNo++, obdManagement);
			tools.createMenuByParent("会员等级", "obd_memberrank_list", "glyphicon glyphicon-list	", "page/obd/memberRank/memberRankList", null, sortNo++, obdManagement);

			MenuEntity store4SMenu = tools.createMenuByParent("4S店管理", "obd_store4s_list", "glyphicon glyphicon-list	", "page/obd/store4S/store4SList", null, sortNo++, obdManagement);
			MenuEntity carMenu = tools.createMenuByParent("车辆管理", "obd_vehicle_list", "glyphicon glyphicon-list	", "page/obd/vehicle/vehicleList", null, sortNo++, obdManagement);
			MenuEntity orderMenu = tools.createMenuByParent("订单管理", "obd_order_list", "glyphicon glyphicon-list	", "page/obd/order/orderList", null, sortNo++, obdManagement);

			tools.createMenuByParent("告警管理", "obd_vehiclealarm_list", "glyphicon glyphicon-list	", "page/obd/vehicleAlarm/vehicleAlarmList", null, sortNo++, obdManagement);
			tools.createMenuByParent("预约管理", "obd_reserve_list", "glyphicon glyphicon-list	", "page/obd/reserve/reserveList", null, sortNo++, obdManagement);
			tools.createMenuByParent("OBD设备", "obd_obddevice_list", "glyphicon glyphicon-list	", "page/obd/obdDevice/obdDeviceList", null, sortNo++, obdManagement);

//			tools.createPemission(adMenu, PermissionType.page, "广告列表", "page:cms:ad:read");
//			tools.createPemission(adMenu, PermissionType.page, "广告创建", "page:cms:ad:create");

		}
		
		tools.initDict(CarAlarmEntity.AlarmType.class, "告警类型");
		tools.initDict(CarAlarmEntity.Status.class, "告警状态");

	}
}
