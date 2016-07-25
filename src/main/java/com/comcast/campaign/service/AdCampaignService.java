/**
 * 
 */
package com.comcast.campaign.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.comcast.campaign.db.CampaignData;
import com.comcast.campaign.model.AdCampaign;
import com.comcast.campaign.model.ErrorMessage;

public class AdCampaignService {

	private Map<String, List<AdCampaign>> adCampaignsMap=CampaignData.getAdCampaigns();
	
	public AdCampaignService(){
	}
	
	public List<AdCampaign> getAllAdCampaigns(){
		
		List<AdCampaign> campaigns = new ArrayList<AdCampaign>();
		for(List<AdCampaign> allAd : adCampaignsMap.values()){
			campaigns.addAll(getActiveCampaign(allAd));
		}

	    if(campaigns.isEmpty()){
			ErrorMessage errorMessage=new ErrorMessage("Add Records First", 400, "http://google.com");
			Response response= Response.status(Status.NOT_FOUND).entity(errorMessage).build();
			throw new WebApplicationException(response);
		}
	    return campaigns;
	}
	
	private List<AdCampaign> getActiveCampaign(List<AdCampaign> campaigns){
		Iterator<AdCampaign> it = campaigns.iterator();
		while(it.hasNext()){
			AdCampaign campaign = it.next();
			if(!campaign.isActive()){
				it.remove();
			}
		}
		return campaigns;
	}
	
	public List<AdCampaign> getAdCampaign(String partnerId){
		List<AdCampaign> campaigns = getActiveCampaign(adCampaignsMap.get(partnerId.toLowerCase()));
		if(campaigns==null || campaigns.isEmpty()){
			ErrorMessage errorMessage=new ErrorMessage("NO Records Found", 404, "http://google.com");
			Response response= Response.status(Status.NOT_FOUND).entity(errorMessage).build();
			throw new WebApplicationException(response);
		}
		return campaigns;
	}
	
	public AdCampaign addAdCampaign(AdCampaign adCampaign){
		//Validate if partnerId exists
		List<AdCampaign> campaigns = adCampaignsMap.get(adCampaign.getPartnerId().toLowerCase());
		if(campaigns != null){
			for(AdCampaign campaign : campaigns ) {
				if(campaign.isActive()){
					ErrorMessage errorMessage=new ErrorMessage("Active campaign exist", 406, "");
					Response response= Response.status(Status.NOT_ACCEPTABLE).entity(errorMessage).build();
					throw new WebApplicationException(response);
				}
				
			}
		}else {
			campaigns = new ArrayList<AdCampaign>();
			adCampaignsMap.put(adCampaign.getPartnerId().toLowerCase(), campaigns);
		}
		adCampaign.setCreatedOn(Calendar.getInstance());
		campaigns.add(adCampaign);
		return adCampaign;
	}

	public AdCampaign addAdMultiCampaign(AdCampaign adCampaign) {
		List<AdCampaign> campaigns = adCampaignsMap.get(adCampaign.getPartnerId().toLowerCase());
		if(campaigns == null){
			campaigns = new ArrayList<AdCampaign>();
			adCampaignsMap.put(adCampaign.getPartnerId().toLowerCase(), campaigns);
		}
		adCampaign.setCreatedOn(Calendar.getInstance());
		campaigns.add(adCampaign);
		return adCampaign;
	}
	
}
