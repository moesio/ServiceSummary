package com.anowit.servicesummary.model;

import com.seimos.android.dbhelper.annotation.Enumerated;
import com.seimos.android.dbhelper.annotation.Id;
import com.seimos.android.dbhelper.database.BaseEntity;
import com.seimos.android.dbhelper.database.EnumType;

/**
 * @author moesio @ gmail.com
 * @date Nov 13, 2016 2:51:07 PM
 */
public class Report extends BaseEntity {

	@Id
	private Long id;
//	@Enumerated(EnumType.CARDINAL)
//	private Profile profile;
	private String name;
	private Double hours;
	private Integer placements;
	private Integer videoShowings;
	private Integer returnVisits;
	private Integer studies;

	public Long getId() {
		return id;
	}

	public Report setId(Long id) {
		this.id = id;
		return this;
	}

//	public Profile getProfile() {
//		return profile;
//	}
//
//	public Report setProfile(Profile profile) {
//		this.profile = profile;
//		return this;
//	}

	public String getName() {
		return name;
	}

	public Report setName(String name) {
		this.name = name;
		return this;
	}

	public Double getHours() {
		return hours;
	}

	public Report setHours(Double hours) {
		this.hours = hours;
		return this;
	}

	public Integer getPlacements() {
		return placements;
	}

	public Report setPlacements(Integer placements) {
		this.placements = placements;
		return this;
	}

	public Integer getVideoShowings() {
		return videoShowings;
	}

	public Report setVideoShowings(Integer videoShowings) {
		this.videoShowings = videoShowings;
		return this;
	}

	public Integer getReturnVisits() {
		return returnVisits;
	}

	public Report setReturnVisits(Integer returnVisits) {
		this.returnVisits = returnVisits;
		return this;
	}

	public Integer getStudies() {
		return studies;
	}

	public Report setStudies(Integer studies) {
		this.studies = studies;
		return this;
	}

	@Override
	public String toString() {
		return name;
	}

}
