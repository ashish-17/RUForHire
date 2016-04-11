/**
 * 
 */
package com.ruforhire.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ashish
 *
 */
@Entity
@Table(name="EMPLOYER")
public class Employer {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int pk;
	
	private int id;
	
	private String name;
	
	private String website;
	
	private boolean isEEP;
	
	private String industry;
	
	private String logoUrl;
	
	private Double overallRating;
	
	private Double cultureAndValuesRating;
	
	private Double seniorLeadershipRating;
	
	private Double compensationAndBenefitsRating;
	
	private Double careerOpportunitiesRating;
	
	private Double workLifeBalanceRating;

	private int numberOfRatings;
	
	public Employer() {
		super();
	}

	public Employer(int id, String name, String website, boolean isEEP, String industry, String logoUrl,
			Double overallRating, Double cultureAndValuesRating, Double seniorLeadershipRating,
			Double compensationAndBenefitsRating, Double careerOpportunitiesRating, Double workLifeBalanceRating,
			int numberOfRatings) {
		super();
		this.id = id;
		this.name = name;
		this.website = website;
		this.isEEP = isEEP;
		this.industry = industry;
		this.logoUrl = logoUrl;
		this.overallRating = overallRating;
		this.cultureAndValuesRating = cultureAndValuesRating;
		this.seniorLeadershipRating = seniorLeadershipRating;
		this.compensationAndBenefitsRating = compensationAndBenefitsRating;
		this.careerOpportunitiesRating = careerOpportunitiesRating;
		this.workLifeBalanceRating = workLifeBalanceRating;
		this.numberOfRatings = numberOfRatings;
	}

	/**
	 * @return the pk
	 */
	public int getPk() {
		return pk;
	}

	/**
	 * @param pk the pk to set
	 */
	public void setPk(int pk) {
		this.pk = pk;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @return the isEEP
	 */
	public boolean isEEP() {
		return isEEP;
	}

	/**
	 * @param isEEP the isEEP to set
	 */
	public void setEEP(boolean isEEP) {
		this.isEEP = isEEP;
	}

	/**
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * @param industry the industry to set
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * @return the logoUrl
	 */
	public String getLogoUrl() {
		return logoUrl;
	}

	/**
	 * @param logoUrl the logoUrl to set
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	/**
	 * @return the overallRating
	 */
	public Double getOverallRating() {
		return overallRating;
	}

	/**
	 * @param overallRating the overallRating to set
	 */
	public void setOverallRating(Double overallRating) {
		this.overallRating = overallRating;
	}

	/**
	 * @return the cultureAndValuesRating
	 */
	public Double getCultureAndValuesRating() {
		return cultureAndValuesRating;
	}

	/**
	 * @param cultureAndValuesRating the cultureAndValuesRating to set
	 */
	public void setCultureAndValuesRating(Double cultureAndValuesRating) {
		this.cultureAndValuesRating = cultureAndValuesRating;
	}

	/**
	 * @return the seniorLeadershipRating
	 */
	public Double getSeniorLeadershipRating() {
		return seniorLeadershipRating;
	}

	/**
	 * @param seniorLeadershipRating the seniorLeadershipRating to set
	 */
	public void setSeniorLeadershipRating(Double seniorLeadershipRating) {
		this.seniorLeadershipRating = seniorLeadershipRating;
	}

	/**
	 * @return the compensationAndBenefitsRating
	 */
	public Double getCompensationAndBenefitsRating() {
		return compensationAndBenefitsRating;
	}

	/**
	 * @param compensationAndBenefitsRating the compensationAndBenefitsRating to set
	 */
	public void setCompensationAndBenefitsRating(Double compensationAndBenefitsRating) {
		this.compensationAndBenefitsRating = compensationAndBenefitsRating;
	}

	/**
	 * @return the careerOpportunitiesRating
	 */
	public Double getCareerOpportunitiesRating() {
		return careerOpportunitiesRating;
	}

	/**
	 * @param careerOpportunitiesRating the careerOpportunitiesRating to set
	 */
	public void setCareerOpportunitiesRating(Double careerOpportunitiesRating) {
		this.careerOpportunitiesRating = careerOpportunitiesRating;
	}

	/**
	 * @return the workLifeBalanceRating
	 */
	public Double getWorkLifeBalanceRating() {
		return workLifeBalanceRating;
	}

	/**
	 * @param workLifeBalanceRating the workLifeBalanceRating to set
	 */
	public void setWorkLifeBalanceRating(Double workLifeBalanceRating) {
		this.workLifeBalanceRating = workLifeBalanceRating;
	}

	/**
	 * @return the numberOfRatings
	 */
	public int getNumberOfRatings() {
		return numberOfRatings;
	}

	/**
	 * @param numberOfRatings the numberOfRatings to set
	 */
	public void setNumberOfRatings(int numberOfRatings) {
		this.numberOfRatings = numberOfRatings;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employer [id=" + id + ", name=" + name + ", website=" + website + ", isEEP=" + isEEP + ", industry="
				+ industry + ", logoUrl=" + logoUrl + ", overallRating=" + overallRating + ", cultureAndValuesRating="
				+ cultureAndValuesRating + ", seniorLeadershipRating=" + seniorLeadershipRating
				+ ", compensationAndBenefitsRating=" + compensationAndBenefitsRating + ", careerOpportunitiesRating="
				+ careerOpportunitiesRating + ", workLifeBalanceRating=" + workLifeBalanceRating + ", numberOfRatings="
				+ numberOfRatings + "]";
	}

}
