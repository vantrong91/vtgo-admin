/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtgo.vn.admin.userinfo.controller;

import com.aerospike.client.Record;
import com.aerospike.client.Value;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.ResultSet;
import com.vtgo.vn.admin.aerospike.AerospikeFactory;
import com.vtgo.vn.admin.base.BaseController;
import com.vtgo.vn.admin.base.BaseResponse;
import com.vtgo.vn.admin.constant.DatabaseConstants;
import com.vtgo.vn.admin.constant.ResponseConstants;
import com.vtgo.vn.admin.userinfo.BO.Category;
import com.vtgo.vn.admin.userinfo.BO.Vehicle;
import com.vtgo.vn.admin.userinfo.BO.VehicleOwner;
import com.vtgo.vn.admin.userinfo.request.SearchRequest;
import com.vtgo.vn.admin.userinfo.service.VehicleService;
import com.vtgo.vn.admin.util.SequenceManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
@AllArgsConstructor
public class VehicleController extends BaseController implements VehicleService {

    private static final Logger logger = Logger.getLogger(VehicleController.class.getName());

    @Override
    public ResponseEntity searchVehicle(SearchRequest request) {
        BaseResponse response = new BaseResponse();
        List<Vehicle> lstVehicleOwners = new ArrayList<>();
        try {
            Map<String, Object> argument = new HashMap<>();
            List<Value.MapValue> argumentFilter = new ArrayList<>();

            String searchValue = request.getSearchParam();
            if (searchValue != null && !searchValue.isEmpty()) {
                Map<String, Object> f = new HashMap<>();
                f.put("field", "LicencePlate");
                f.put("value", searchValue);
                f.put("operator", "contain");
                argumentFilter.add(new Value.MapValue(f));
            }
            List<Value.MapValue> argumentSorters = new ArrayList<>();
            Map<String, Object> s1 = new HashMap<>();
            s1.put("sort_key", "LicencePlate");
            s1.put("order", "ASC");
            s1.put("type", "STRING");
            argumentSorters.add(new Value.MapValue(s1));

            argument.put("sorters", new Value.ListValue(argumentSorters));
            argument.put("filters", new Value.ListValue(argumentFilter));
            ResultSet resultSet = AerospikeFactory.getInstance()
                    .aggregate(AerospikeFactory.getInstance().queryPolicy, DatabaseConstants.NAMESPACE, DatabaseConstants.VEHICLE_SET, "FILTER_RECORD", "FILTER_RECORD", Value.get(argument));
            if (resultSet != null) {
                Iterator<Object> objectIterator = resultSet.iterator();
                while (objectIterator.hasNext()) {
                    ArrayList arrayList = (ArrayList) objectIterator.next();
                    for (Object o : arrayList) {
                        Vehicle vehicle = new Vehicle();
                        if (vehicle.parse((Map) o)) {
                            lstVehicleOwners.add(vehicle);
                        }
                    }
                }
            }
            response.setData(lstVehicleOwners);
            response.setStatus(ResponseConstants.SUCCESS);
            response.setMessage(ResponseConstants.SERVICE_SUCCESS_DESC);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response.setStatus(ResponseConstants.SERVICE_FAIL);
            response.setMessage(ResponseConstants.SERVICE_FAIL_DESC);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @Override
    public ResponseEntity getVehicleById(Vehicle request) {
        BaseResponse response = new BaseResponse();
        try {
            Record rec = getById(DatabaseConstants.NAMESPACE, DatabaseConstants.VEHICLE_SET, request.getVehicleId());
            response.setStatus(ResponseConstants.SUCCESS);
            response.setMessage(ResponseConstants.SERVICE_SUCCESS_DESC);
            if (rec != null) {
                Vehicle vehicle = new Vehicle();
                vehicle.parse(rec);
                response.setData(Arrays.asList(vehicle));
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response.setStatus(ResponseConstants.SERVICE_FAIL);
            response.setMessage(ResponseConstants.SERVICE_FAIL_DESC);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @Override
    public ResponseEntity update(Vehicle request) {
        BaseResponse response = new BaseResponse();
        try {
            if (request.getVehicleId() != null) {
                update(AerospikeFactory.getInstance().onlyUpdatePolicy,
                        DatabaseConstants.NAMESPACE, DatabaseConstants.VEHICLE_SET, request.getVehicleId(), request.toBins());
                response.setStatus(ResponseConstants.SUCCESS);
                response.setMessage(ResponseConstants.SERVICE_SUCCESS_DESC);
            } else {
                response.setStatus(ResponseConstants.SERVICE_FAIL);
                response.setMessage(ResponseConstants.SERVICE_FAIL_DESC);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response.setStatus(ResponseConstants.SERVICE_FAIL);
            response.setMessage(ResponseConstants.SERVICE_FAIL_DESC);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

    }

    @Override
    public ResponseEntity create(Vehicle request) {
        BaseResponse response = new BaseResponse();
        try {
            if (request.getLicencePlate() != null) {
                RecordSet rs = AerospikeFactory.getInstance().queryByIndex(DatabaseConstants.NAMESPACE, DatabaseConstants.VEHICLE_SET, "LicencePlate",
                        "LicencePlateIdx", request.getLicencePlate());
                if (rs != null && rs.iterator().hasNext()) {
                    response.setStatus(ResponseConstants.SERVICE_FAIL);
                    response.setMessage("Trùng biển số");
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }
                long vehicleId = SequenceManager.getInstance().getSequence(Vehicle.class.getSimpleName());
                if (vehicleId <= 0) {
                    response.setStatus(ResponseConstants.SERVICE_FAIL);
                    response.setMessage("Get vehicleId sequence error");
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }
                request.setVehicleId(vehicleId);
                update(AerospikeFactory.getInstance().onlyCreatePolicy,
                        DatabaseConstants.NAMESPACE, DatabaseConstants.VEHICLE_SET, request.getVehicleId(), request.toBins());
                response.setData(Arrays.asList(request));
                response.setStatus(ResponseConstants.SUCCESS);
                response.setMessage(ResponseConstants.SERVICE_SUCCESS_DESC);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.setStatus(ResponseConstants.SERVICE_FAIL);
                response.setMessage(ResponseConstants.SERVICE_FAIL_DESC);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response.setStatus(ResponseConstants.SERVICE_FAIL);
            response.setMessage(ResponseConstants.SERVICE_FAIL_DESC);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @Override
    public ResponseEntity delete(Vehicle request) {
        BaseResponse response = new BaseResponse();
        try {
            if (request.getVehicleId() != null) {
                Record rec = getById(DatabaseConstants.NAMESPACE, DatabaseConstants.VEHICLE_SET, request.getVehicleId());
                if (rec != null) {
                    delete(AerospikeFactory.getInstance().writePolicy,
                            DatabaseConstants.NAMESPACE, DatabaseConstants.VEHICLE_SET, request.getVehicleId());
                    response.setStatus(ResponseConstants.SUCCESS);
                    response.setMessage(ResponseConstants.SERVICE_SUCCESS_DESC);
                } else {
                    response.setStatus(ResponseConstants.SERVICE_ERROR);
                    response.setMessage(ResponseConstants.SERVICE_VEHICLE_OWNER_NOT_FOUND);
                }
            } else {
                response.setStatus(ResponseConstants.SERVICE_FAIL);
                response.setMessage(ResponseConstants.SERVICE_FAIL_DESC);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response.setStatus(ResponseConstants.SERVICE_FAIL);
            response.setMessage(ResponseConstants.SERVICE_FAIL_DESC);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @Override
    public ResponseEntity getListVehicleByOwner(Vehicle request) {
        BaseResponse response = new BaseResponse();
        try {
            Record rec = getById(DatabaseConstants.NAMESPACE, DatabaseConstants.VEHICLE_SET, request.getOwnerId());
            response.setStatus(ResponseConstants.SUCCESS);
            response.setMessage(ResponseConstants.SERVICE_SUCCESS_DESC);
            if (rec != null) {
                VehicleOwner vehicleOwner = new VehicleOwner();
                vehicleOwner.parse(rec);
                response.setData(Arrays.asList(vehicleOwner));
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response.setStatus(ResponseConstants.SERVICE_FAIL);
            response.setMessage(ResponseConstants.SERVICE_FAIL_DESC);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @Override
    public ResponseEntity getListVehicleType(SearchRequest request) {
        BaseResponse response = new BaseResponse();
        List<Category> lstCategory = new ArrayList<>();
        try {
            Map<String, Object> argument = new HashMap<>();
            List<Value.MapValue> argumentFilter = new ArrayList<>();

            Long searchValue = request.getSearchParam2();
            if (searchValue != null) {
                Map<String, Object> f = new HashMap<>();
                f.put("field", "ID_CHA");
                f.put("value", searchValue);
                f.put("operator", "=");
                argumentFilter.add(new Value.MapValue(f));
            }
            List<Value.MapValue> argumentSorters = new ArrayList<>();
            Map<String, Object> s1 = new HashMap<>();
            s1.put("sort_key", "PK");
            s1.put("order", "ASC");
            s1.put("type", "LONG");
            argumentSorters.add(new Value.MapValue(s1));

            argument.put("sorters", new Value.ListValue(argumentSorters));
            argument.put("filters", new Value.ListValue(argumentFilter));

            ResultSet rs = AerospikeFactory.getInstance().aggregate(AerospikeFactory.getInstance().queryPolicy, DatabaseConstants.NAMESPACE, DatabaseConstants.CATEGORY_SET, "FILTER_RECORD", "FILTER_RECORD", Value.get(argument));
            if (rs != null) {
                Iterator<Object> obIterator = rs.iterator();
                while (obIterator.hasNext()) {
                    ArrayList arr = (ArrayList) obIterator.next();
                    for (Object o : arr) {
                        Category category = new Category();
                        if (category.parse((Map) o)) {
                            lstCategory.add(category);
                        }
                    }
                }
            }
            response.setData(lstCategory);
            response.setStatus(ResponseConstants.SUCCESS);
            response.setMessage(ResponseConstants.SERVICE_SUCCESS_DESC);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setStatus(ResponseConstants.SERVICE_FAIL);
            response.setMessage(ResponseConstants.SERVICE_FAIL_DESC);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
}
