package com.comcast.campaign.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.comcast.campaign.model.AdCampaign;
import com.comcast.campaign.service.AdCampaignService;

@Path("/ad")
public class AdCompaignResource {
	
	AdCampaignService service = new AdCampaignService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AdCampaign> getAllAdCampaigns(){
		return service.getAllAdCampaigns();
	}

	@Path("/{partnerId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AdCampaign> getAdCampaign(@PathParam("partnerId") String partnerId){
		return service.getAdCampaign(partnerId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AdCampaign getAdCampaign(@HeaderParam("multiple") String isMultiple, AdCampaign campaign){
		
		if(isMultiple != null && "true".equals(isMultiple)){
			return service.addAdMultiCampaign(campaign);
			
		}else {
			return service.addAdCampaign(campaign);
		}
	}

	
}
