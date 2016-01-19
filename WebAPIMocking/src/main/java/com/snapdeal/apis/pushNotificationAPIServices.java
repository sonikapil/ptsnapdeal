package com.snapdeal.apis;

import static net.jadler.Jadler.onRequest;
import static org.hamcrest.Matchers.containsString;

import java.util.ArrayList;
import java.util.List;

import com.snapdeal.base.exception.SerializationException;
import com.snapdeal.base.transport.service.ITransportService.Protocol;
import com.snapdeal.common.CommonClass;
import com.snapdeal.nextBestActionServices.sro.GetCategoryProductSRO;
import com.snapdeal.nextBestActionServices.sro.POGIdSRO;
import com.snapdeal.notificationManager.response.GetPersonalizedContentResponse;
import com.snapdeal.notificationManager.response.GetWidgetStructureResponse;
import com.snapdeal.notificationManager.sro.ABTestingSRO;
import com.snapdeal.notificationManager.sro.WidgetStructureSRO;

public class pushNotificationAPIServices extends CommonClass{
	static int ruleId = 11;
	static String widgetId = "12";

	@SuppressWarnings("deprecation")
	private static GetWidgetStructureResponse createGetWidgetStructureResponse() throws SerializationException
	{
		GetWidgetStructureResponse widgetResponse =new  GetWidgetStructureResponse();
		String category = "10";
		boolean personalise = true;
		String widgetLabel = "recommendation";
		widgetResponse.setCode(null);
		widgetResponse.setProtocol(Protocol.PROTOCOL_JSON);
		widgetResponse.setSuccessful(true);
		//List <ABTestingSRO> ABTlist = new ArrayList<ABTestingSRO>();
		ABTestingSRO ABT = new ABTestingSRO();
		ABT.setRuleId("1234");
		ABT.setRuleVersion(1);
		ABT.setThrottlingId("000");
		ABT.setThrottlingVersion(00);
		widgetResponse.setAbTesting(ABT);
		List <WidgetStructureSRO> WSSlist = new ArrayList<WidgetStructureSRO>();
		WidgetStructureSRO WSS = new WidgetStructureSRO();
		WSS.setCategoryId(category);
		WSS.setPersonalise(personalise);
		WSS.setRuleId(ruleId);
		WSS.setWidgetId(widgetId);
		WSS.setWidgetLabel(widgetLabel);
		widgetResponse.setWidgetMapping(WSSlist);
		return widgetResponse;
		
	}
	
	public static void getWidgetStructureResponse() throws SerializationException
	{
		GetWidgetStructureResponse widgetResponse =  createGetWidgetStructureResponse();
		byte[] responseStr1 = doSerialize(widgetResponse.getClass(), widgetResponse);
		String throttlingVersion = "00";
		onRequest()
		.havingMethodEqualTo("POST")
		.havingHeaderEqualTo("Content-Type", "application/json")
		.havingPathEqualTo("/service/push/getWidgetStructure")
		.havingBody(containsString(throttlingVersion))
		.respond().withBody(responseStr1);
	}
	
	@SuppressWarnings("deprecation")
	private static GetPersonalizedContentResponse createGetPersonalizedContentResponse() throws SerializationException
	{
		
		String categoryHID = "90";
		String categoryLabel = "recommendation";
		Long pogid = (long) 1234576859;
		String pogidHID = "mobile";
		GetPersonalizedContentResponse PersonalizedResponse = new GetPersonalizedContentResponse();
		String personalisedHID = "10";
		PersonalizedResponse.setCode(null);
		PersonalizedResponse.setMessage("verify");
		PersonalizedResponse.setPersonalisedHID(personalisedHID);
		PersonalizedResponse.setRuleId(ruleId);
		PersonalizedResponse.setSuccessful(true);
		PersonalizedResponse.setProtocol(Protocol.PROTOCOL_JSON);
		//List <GetCategoryProductSRO> CPSlist = new ArrayList<GetCategoryProductSRO>();
		GetCategoryProductSRO CPS = new GetCategoryProductSRO();
		CPS.setCategoryHID(categoryHID);
		CPS.setCategoryLabel(categoryLabel);
		CPS.setWidgetId(widgetId);
		List <POGIdSRO> POGList = new ArrayList<POGIdSRO>();
		POGIdSRO POG = new POGIdSRO();
		POG.setPogid(pogid);
		POG.setPogidHID(pogidHID);
		CPS.setPogid(POGList);
		PersonalizedResponse.setCategoryProductSRO(CPS);
		return PersonalizedResponse;
		
	}
	
	public static void GetPersonalizedContentResponse1() throws SerializationException
	{
		GetPersonalizedContentResponse PersonalizedResponse =createGetPersonalizedContentResponse();
		byte[] responseStr1 = doSerialize(PersonalizedResponse.getClass(), PersonalizedResponse);
		String widgetId = "12";
		onRequest()
		.havingMethodEqualTo("POST")
		.havingHeaderEqualTo("Content-Type", "application/json")
		.havingPathEqualTo("/service/push/getPersonalizedContent")
		.havingBody(containsString(widgetId))
        .respond()
			.withBody(responseStr1);
		
	}
	
}
