/**
 * 
 */
package com.comcast.campaign.db;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.comcast.campaign.model.AdCampaign;

public class CampaignData {

	private static Map<String, List<AdCampaign>> campaigns=new ConcurrentHashMap<String, List<AdCampaign>>();
	
	/**
	 * @return the campaigns
	 */
	public static Map<String, List<AdCampaign>> getAdCampaigns() {
		return campaigns;
	}
	
}
