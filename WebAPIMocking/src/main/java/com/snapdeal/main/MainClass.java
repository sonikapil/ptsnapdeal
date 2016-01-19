/**
 * kapil soni
 */
package com.snapdeal.main;

import static net.jadler.Jadler.initJadlerListeningOn;
import static net.jadler.Jadler.port;

import com.snapdeal.apis.ScoreAPIServices;
import com.snapdeal.apis.pushNotificationAPIServices;
import com.snapdeal.common.CommonClass;

public class MainClass extends CommonClass {


	public static void main(String[] args) throws Exception {
		
		/*Jadler starting on Port 8086*/
		initJadlerListeningOn(9086);
		
		checkServerStatus();
		ScoreAPIServices.getSellerwiseDeliveryInfo();
		//pushNotificationAPIServices.GetPersonalizedContentResponse1();
		//pushNotificationAPIServices.getWidgetStructureResponse();
		/*Registering the APIs of Points System*/

		System.out.println("\nAPIs mocking System.\nStarted on Port = " + port());
		
	
	}
}
