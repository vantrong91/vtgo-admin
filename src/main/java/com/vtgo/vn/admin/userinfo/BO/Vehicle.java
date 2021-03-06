/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtgo.vn.admin.userinfo.BO;

import com.aerospike.client.Bin;
import com.aerospike.client.Record;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtgo.vn.admin.base.BaseObject;
import com.vtgo.vn.admin.base.BaseRequest;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author HP
 */
public class Vehicle extends BaseRequest<Object> implements BaseObject {

    private static final Logger logger = Logger.getLogger(Vehicle.class);
    private Long vehicleId;
    private Long ownerId;
    private String vehicleCode;
    private Long route;
    private Integer vehicleType;
    private String licencePlate;
    private Long weight;
    private String licence;
    private Long licenceIssueDate;
    private String licenceIssueBy;
    private String registrationNo;
    private Long registrationIssueDate;
    private Long registrationExpDate;
    private String civilInsurance;
    private Long civilInsuranceIssueDate;
    private Long civilInsuranceExpDate;
    private String cargoInsurance;
    private Long cargoInsuranceIssueDate;
    private Long cargoInsuranceExpDate;
    private String itineraryMonitoring;
    private Long itineraryMonitoringIssueDate;
    private Long itineraryMonitoringExpDate;
    private String carBadges; //Phu hieu xe
    private Long carBaIssDate;
    private Long carBaExpDate;
    private Long state;
    private Long driverId;
    private String driverName;
    private Map<String, List<String>> attachProperties;
    private String ipMonitoring;
    private String password; //Mat khau ket noi voi lai xe

    public String getCarBadges() {
        return carBadges;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCarBadges(String carBadges) {
        this.carBadges = carBadges;
    }

    public Long getCarBaIssDate() {
        return carBaIssDate;
    }

    public void setCarBaIssDate(Long carBaIssDate) {
        this.carBaIssDate = carBaIssDate;
    }

    public Long getCarBaExpDate() {
        return carBaExpDate;
    }

    public void setCarBaExpDate(Long carBaExpDate) {
        this.carBaExpDate = carBaExpDate;
    }

    public String getIpMonitoring() {
        return ipMonitoring;
    }

    public void setIpMonitoring(String ipMonitoring) {
        this.ipMonitoring = ipMonitoring;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public Long getLicenceIssueDate() {
        return licenceIssueDate;
    }

    public void setLicenceIssueDate(Long licenceIssueDate) {
        this.licenceIssueDate = licenceIssueDate;
    }

    public String getLicenceIssueBy() {
        return licenceIssueBy;
    }

    public void setLicenceIssueBy(String licenceIssueBy) {
        this.licenceIssueBy = licenceIssueBy;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public Long getRegistrationIssueDate() {
        return registrationIssueDate;
    }

    public void setRegistrationIssueDate(Long registrationIssueDate) {
        this.registrationIssueDate = registrationIssueDate;
    }

    public String getCivilInsurance() {
        return civilInsurance;
    }

    public void setCivilInsurance(String civilInsurance) {
        this.civilInsurance = civilInsurance;
    }

    public Long getCivilInsuranceIssueDate() {
        return civilInsuranceIssueDate;
    }

    public void setCivilInsuranceIssueDate(Long civilInsuranceIssueDate) {
        this.civilInsuranceIssueDate = civilInsuranceIssueDate;
    }

    public Long getCivilInsuranceExpDate() {
        return civilInsuranceExpDate;
    }

    public void setCivilInsuranceExpDate(Long civilInsuranceExpDate) {
        this.civilInsuranceExpDate = civilInsuranceExpDate;
    }

    public String getCargoInsurance() {
        return cargoInsurance;
    }

    public void setCargoInsurance(String cargoInsurance) {
        this.cargoInsurance = cargoInsurance;
    }

    public Long getCargoInsuranceIssueDate() {
        return cargoInsuranceIssueDate;
    }

    public void setCargoInsuranceIssueDate(Long cargoInsuranceIssueDate) {
        this.cargoInsuranceIssueDate = cargoInsuranceIssueDate;
    }

    public Long getCargoInsuranceExpDate() {
        return cargoInsuranceExpDate;
    }

    public void setCargoInsuranceExpDate(Long cargoInsuranceExpDate) {
        this.cargoInsuranceExpDate = cargoInsuranceExpDate;
    }

    public String getItineraryMonitoring() {
        return itineraryMonitoring;
    }

    public void setItineraryMonitoring(String itineraryMonitoring) {
        this.itineraryMonitoring = itineraryMonitoring;
    }

    public Long getItineraryMonitoringIssueDate() {
        return itineraryMonitoringIssueDate;
    }

    public void setItineraryMonitoringIssueDate(Long itineraryMonitoringIssueDate) {
        this.itineraryMonitoringIssueDate = itineraryMonitoringIssueDate;
    }

    public Long getItineraryMonitoringExpDate() {
        return itineraryMonitoringExpDate;
    }

    public void setItineraryMonitoringExpDate(Long itineraryMonitoringExpDate) {
        this.itineraryMonitoringExpDate = itineraryMonitoringExpDate;
    }

    public Long getRoute() {
        return route;
    }

    public void setRoute(Long route) {
        this.route = route;
    }

    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getRegistrationExpDate() {
        return registrationExpDate;
    }

    public void setRegistrationExpDate(Long registrationExpDate) {
        this.registrationExpDate = registrationExpDate;
    }

    public Map<String, List<String>> getAttachProperties() {
        return attachProperties;
    }

    public void setAttachProperties(Map<String, List<String>> attachProperties) {
        this.attachProperties = attachProperties;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @JsonIgnore
    @Override
    public Bin[] toBins() {
        List<Bin> bins = new ArrayList<>();
        bins.add(new Bin("VehicleId", vehicleId));
        bins.add(new Bin("OwnerId", ownerId));
        bins.add(new Bin("VehicleCode", vehicleCode));
        bins.add(new Bin("Route", route));
        bins.add(new Bin("VehicleType", vehicleType));
        bins.add(new Bin("LicencePlate", licencePlate));
        bins.add(new Bin("Weight", weight));
        bins.add(new Bin("Licence", licence));
        bins.add(new Bin("LicIssueBy", licenceIssueBy));
        bins.add(new Bin("LicIssueDate", licenceIssueDate));
        bins.add(new Bin("RegNo", registrationNo));
        bins.add(new Bin("RegIssueDate", registrationIssueDate));
        bins.add(new Bin("RegExpDate", registrationExpDate));
        bins.add(new Bin("CargoInsurance", cargoInsurance));
        bins.add(new Bin("InsuranIssDate", cargoInsuranceIssueDate));
        bins.add(new Bin("InsuranExpDate", cargoInsuranceExpDate));
        bins.add(new Bin("CivInsurance", civilInsurance));
        bins.add(new Bin("CivInsuIssDate", civilInsuranceExpDate));
        bins.add(new Bin("CivInsuExpDate", civilInsuranceIssueDate));
        bins.add(new Bin("ItinerMonitor", itineraryMonitoring));
        bins.add(new Bin("MonitorIssDate", itineraryMonitoringExpDate));
        bins.add(new Bin("MonitorExpDate", itineraryMonitoringIssueDate));
        bins.add(new Bin("DriverId", driverId));
        bins.add(new Bin("DriverName", driverName));
        bins.add(new Bin("State", state));
        bins.add(new Bin("AttachProp", attachProperties));
        bins.add(new Bin("IpMonitoring", ipMonitoring));
        bins.add(new Bin("CarBadges", carBadges));
        bins.add(new Bin("CarBaIssDate", carBaIssDate));
        bins.add(new Bin("CarBaExpDate", carBaExpDate));
        bins.add(new Bin("Password", password));
        return bins.toArray(new Bin[bins.size()]);
    }

    @JsonIgnore
    @Override
    public boolean parse(Record record) {
        try {
            this.vehicleId = record.getLong("VehicleId");
            this.ownerId = record.getLong("OwnerId");
            this.vehicleCode = record.getString("VehicleCode");
            this.route = record.getLong("Route");
            this.vehicleType = (Integer) record.getInt("VehicleType");
            this.licencePlate = record.getString("LicencePlate");
            this.weight = record.getLong("Weight");
            this.licence = record.getString("Licence");
            this.licenceIssueBy = record.getString("LicIssueBy");
            this.licenceIssueDate = record.getLong("LicIssueDate");
            this.registrationNo = record.getString("RegNo");
            this.registrationIssueDate = record.getLong("RegIssueDate");
            this.registrationExpDate = record.getLong("RegExpDate");
            this.cargoInsurance = record.getString("CargoInsurance");
            this.cargoInsuranceIssueDate = record.getLong("InsuranIssDate");
            this.cargoInsuranceExpDate = record.getLong("InsuranExpDate");
            this.civilInsurance = record.getString("CivInsurance");
            this.civilInsuranceExpDate = record.getLong("CivInsuIssDate");
            this.civilInsuranceIssueDate = record.getLong("CivInsuExpDate");
            this.itineraryMonitoring = record.getString("ItinerMonitor");
            this.itineraryMonitoringExpDate = record.getLong("MonitorIssDate");
            this.itineraryMonitoringIssueDate = record.getLong("MonitorExpDate");
            this.driverId = record.getLong("DriverId");
            this.driverName = record.getString("DriverName");
            this.state = record.getLong("State");
            this.attachProperties = (Map<String, List<String>>) record.getMap("AttachProp");
            this.ipMonitoring = record.getString("IpMonitoring");
            this.carBadges = record.getString("CarBadges");
            this.carBaExpDate = record.getLong("CarBaExpDate");
            this.carBaIssDate = record.getLong("CarBaIssDate");
            this.password = record.getString("Password");
        } catch (Exception ex) {
            logger.error(ex, ex);
            return false;
        }
        return true;
    }

    @JsonIgnore
    @Override
    public boolean parse(Map map) {
        try {
            this.vehicleId = (Long) map.get("VehicleId");
            this.ownerId = (Long) map.get("OwnerId");
            this.vehicleCode = (String) map.get("VehicleCode");
            this.route = (Long) map.get("Route");
            if (map.get("VehicleType") != null) {
                if (map.get("VehicleType") instanceof Integer) {
                    this.vehicleType = (Integer) map.get("VehicleType");
                } else if (map.get("VehicleType") instanceof Long) {
                    String vehi = (Long) map.get("VehicleType") + "";
                    this.vehicleType = Integer.valueOf(vehi);
                }
            }
            this.licencePlate = (String) map.get("LicencePlate");
            this.weight = (Long) map.get("Weight");
            this.licence = (String) map.get("Licence");
            this.licenceIssueBy = (String) map.get("LicIssueBy");
            this.licenceIssueDate = (Long) map.get("LicIssueDate");
            this.registrationNo = (String) map.get("RegNo");
            this.registrationIssueDate = (Long) map.get("RegIssueDate");
            this.registrationExpDate = (Long) map.get("RegExpDate");
            this.cargoInsurance = (String) map.get("CargoInsurance");
            this.cargoInsuranceIssueDate = (Long) map.get("InsuranIssDate");
            this.cargoInsuranceExpDate = (Long) map.get("InsuranExpDate");
            this.civilInsurance = (String) map.get("CivInsurance");
            this.civilInsuranceExpDate = (Long) map.get("CivInsuIssDate");
            this.civilInsuranceIssueDate = (Long) map.get("CivInsuExpDate");
            this.itineraryMonitoring = (String) map.get("ItinerMonitor");
            this.itineraryMonitoringExpDate = (Long) map.get("MonitorExpDate");
            this.itineraryMonitoringIssueDate = (Long) map.get("MonitorIssDate");
            this.driverId = (Long) map.get("DriverId");
            this.driverName = (String) map.get("DriverName");
            this.state = (Long) map.get("State");
            this.attachProperties = (Map<String, List<String>>) map.get("AttachProp");
            this.ipMonitoring = (String) map.get("IpMonitoring");
            this.carBadges = (String) map.get("CarBadges");
            this.carBaExpDate = (Long) map.get("CarBaExpDate");
            this.carBaIssDate = (Long) map.get("CarBaIssDate");
            this.password = (String) map.get("Password");
            return true;
        } catch (Exception ex) {
            logger.error(ex, ex);
            return false;
        }
    }
}