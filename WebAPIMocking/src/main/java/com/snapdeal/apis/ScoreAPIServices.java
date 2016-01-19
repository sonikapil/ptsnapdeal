package com.snapdeal.apis;
/**
 * kapil soni
 */

import static net.jadler.Jadler.onRequest;
import static org.hamcrest.Matchers.containsString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.snapdeal.base.exception.SerializationException;
import com.snapdeal.base.transport.service.ITransportService.Protocol;
import com.snapdeal.common.CommonClass;
import com.snapdeal.score.model.response.GetOtoDRangeWOCPResponse;
import com.snapdeal.score.model.response.SellerWiseDeliveryInfoResponse;
import com.snapdeal.score.sro.DDRange;
import com.snapdeal.score.sro.PVCDeliveryInfoSRO;
import com.snapdeal.score.sro.SellerCompositeDDRangeSRO;
import com.snapdeal.score.sro.ServiceabilityRejectionReasons;
import com.snapdeal.score.sro.StdCodDatesSRO;
import com.snapdeal.score.sro.TaxSRO;

public class ScoreAPIServices extends CommonClass {
	
	@SuppressWarnings("deprecation")
	private static SellerWiseDeliveryInfoResponse  createGetSellerWiseDeliveryInfoResponse()throws SerializationException{
		//SellerWiseDeliveryInfoRequest 
		SellerWiseDeliveryInfoResponse  response=new SellerWiseDeliveryInfoResponse();
		response.setCode(null);
		response.setSuccessful(true);
		response.setMessage(null);
		response.setProtocol(Protocol.PROTOCOL_JSON);
		//response.setValidationErrors(validationErrors);
		Integer pickupSlaInHrs = 0;
		double sellingPrice =660.0d;
		String vendorCode= "927ead";
		String vendorType = "FC_VOI";
		String categoryURL="mobiles-mobile-phones";
		String supc= "SDL999059720";
		String pickupPincode ="301001";
		boolean isSTDAvailable=false; 
		boolean isCODAvailable=false;
		double nearestStoreDistance=0.0d;
		List<ServiceabilityRejectionReasons> stdRejectionReasonList=new ArrayList<ServiceabilityRejectionReasons>();
		List<ServiceabilityRejectionReasons> codRejectionReasonList=new ArrayList<ServiceabilityRejectionReasons>();
		//for same day dates
		Date stdDate=new Date(1438093800000L);
		stdDate =null;
		Date codDate=new Date(1438093800000L);
		codDate=null;
		StdCodDatesSRO sameDayDates=new StdCodDatesSRO(stdDate,codDate);
		//For next day
		Date stdDate1=new Date(1438180200000L);
		stdDate1=null;
		Date codDate1=new Date(1438180200000L);
		codDate1=null;
		StdCodDatesSRO nextDayDates=new StdCodDatesSRO(stdDate1,codDate1);
		//For expected Delivery dates
		Date stdDate2=null;
		Date codDate2=null;
		StdCodDatesSRO expectedDeliveryDates=new StdCodDatesSRO(stdDate2,codDate2);
		List<PVCDeliveryInfoSRO> deliveryInfolist=new ArrayList<PVCDeliveryInfoSRO>();
		PVCDeliveryInfoSRO deliveryInfo =new PVCDeliveryInfoSRO(sameDayDates, nextDayDates, expectedDeliveryDates);
		deliveryInfo.setVendorCode(vendorCode);
		deliveryInfo.setCategoryURL(categoryURL);
		deliveryInfo.setSupc(supc);
		deliveryInfo.setSTDAvailable(isSTDAvailable);
		deliveryInfo.setCODAvailable(isCODAvailable);
		deliveryInfo.setStdRejectionReasonList(stdRejectionReasonList);
		deliveryInfo.setCodRejectionReasonList(codRejectionReasonList);
		BigDecimal rate = new BigDecimal(10);
		BigDecimal amount = new BigDecimal(212);
		TaxSRO taxSRO = new TaxSRO("taxClass", 1L, rate, amount);
		deliveryInfo.setTaxSRO(taxSRO);
		deliveryInfo.setShipFarCharge(new BigDecimal(515));
		deliveryInfo.setNearestStoreDistance(nearestStoreDistance);
		deliveryInfo.setPickupPincode(pickupPincode);
		deliveryInfo.setPickupSlaInHrs(pickupSlaInHrs);
		deliveryInfo.setSellingPrice(sellingPrice);
		deliveryInfo.setSupc(supc);
		deliveryInfo.setVendorCode(vendorCode);
		deliveryInfo.setVendorType(vendorType);
		deliveryInfolist.add(deliveryInfo);
		response.setDeliveryInfoList(deliveryInfolist);
		
		
		return response;
	}
	public static void getSellerwiseDeliveryInfo() throws SerializationException{
		SellerWiseDeliveryInfoResponse response =createGetSellerWiseDeliveryInfoResponse();
		byte[] responseStr1 = doSerialize(response.getClass(), response);
		String supc="SDL894667848";
		
		onRequest()
			.havingMethodEqualTo("POST")
			.havingHeaderEqualTo("Content-Type", "application/json")
			.havingPathEqualTo("/service/score/getSellerWiseDeliveryInfo")
			.havingBody(containsString(supc))
        .respond()
			.withBody(responseStr1);
		
	}
	
	
	private static GetOtoDRangeWOCPResponse createGetOtoDRangeWOCPResponse() throws SerializationException
	{
		String Sellercode = "4gfljdkj";
		int max = 10;
		int min = 1;
		GetOtoDRangeWOCPResponse response = new GetOtoDRangeWOCPResponse();
		response.setCode("200");
		response.setMessage("mock");
		response.setProtocol(Protocol.PROTOCOL_JSON);
		SellerCompositeDDRangeSRO ddrv = new SellerCompositeDDRangeSRO();
		ddrv.setSellerCode(Sellercode);
		ddrv.setErrorMessage("error");
		DDRange ddr = new DDRange();
		ddr.setMax(max);
		ddr.setMin(min);
		ddrv.setOtoDRange(ddr);
		ddrv.setOtoSRange(ddr);
		List <SellerCompositeDDRangeSRO> CDDR = new ArrayList <SellerCompositeDDRangeSRO>();
		return response;
		
	}
	
	public static void getOtoDRangeWOCPResponse1() throws SerializationException
	{
		GetOtoDRangeWOCPResponse ODresponse = createGetOtoDRangeWOCPResponse();
	}


}