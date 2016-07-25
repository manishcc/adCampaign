package com.comcast.campaign.model;

import java.util.Calendar;


public class AdCampaign {

	private String partnerId;
	private int duration;
	private String adContent;
	private Calendar createdOn;
	private boolean active;
	
	
	public AdCampaign() {
		super();
	}

	/**
	 * @param partnerId
	 * @param duration
	 * @param adContent
	 */
	public AdCampaign(String partnerId, int duration, String adContent) {
		this.partnerId = partnerId;
		this.duration = duration;
		this.adContent = adContent;
		this.createdOn = Calendar.getInstance();
		this.active = true;
	}

	/**
	 * @return the partnerId
	 */
	public String getPartnerId() {
		return partnerId;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @return the adContent
	 */
	public String getAdContent() {
		return adContent;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}
	

	public boolean isActive() {
		if((Calendar.getInstance().getTimeInMillis() - this.createdOn.getTimeInMillis()) >= duration*1000 ){
			this.active = false;
		}else {
			this.active = true;
		}
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

//	public String getStatus() {
//		if((Calendar.getInstance().getTimeInMillis() - this.createdOn.getTimeInMillis()) >= duration*1000 ){
//			this.active = false;
//		}else {
//			this.status = "Active";
//		}
//		return this.status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
	
	
}
