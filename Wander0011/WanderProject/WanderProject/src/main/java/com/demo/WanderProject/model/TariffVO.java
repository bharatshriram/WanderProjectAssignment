/**
 * 
 */
package com.demo.WanderProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Bharat Kumar
 *
 */

@Entity
@Table(name = "tariff")
public class TariffVO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tariff_id")
	private int tariffId;
	@Column(name = "tariff")
	private String tariffRate;
	@Column(name = "emr_credit")
	private String emergencyCredit;
	@Column(name = "alarm_credit")
	private String alarmCredit;
	@Column(name = "last_date")
	private String lastdate;
	@Column(name = "convfactor")
	private String convFactor;
	@Column(name = "FixedCharges")
	private String fixedCharges;
	@Column(name = "tariffType")
	private String tariffType;
	public int getTariffId() {
		return tariffId;
	}
	public void setTariffId(int tariffId) {
		this.tariffId = tariffId;
	}
	public String getTariffRate() {
		return tariffRate;
	}
	public void setTariffRate(String tariffRate) {
		this.tariffRate = tariffRate;
	}
	public String getEmergencyCredit() {
		return emergencyCredit;
	}
	public void setEmergencyCredit(String emergencyCredit) {
		this.emergencyCredit = emergencyCredit;
	}
	public String getAlarmCredit() {
		return alarmCredit;
	}
	public void setAlarmCredit(String alarmCredit) {
		this.alarmCredit = alarmCredit;
	}
	public String getLastdate() {
		return lastdate;
	}
	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}
	public String getConvFactor() {
		return convFactor;
	}
	public void setConvFactor(String convFactor) {
		this.convFactor = convFactor;
	}
	public String getFixedCharges() {
		return fixedCharges;
	}
	public void setFixedCharges(String fixedCharges) {
		this.fixedCharges = fixedCharges;
	}
	public String getTariffType() {
		return tariffType;
	}
	public void setTariffType(String tariffType) {
		this.tariffType = tariffType;
	}
}
