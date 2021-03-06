/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtgo.vn.admin.userinfo.controller;

import com.aerospike.client.AerospikeException;
import com.aerospike.client.Bin;
import com.aerospike.client.Record;
import com.aerospike.client.Value;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.ResultSet;
import com.vtgo.vn.admin.aerospike.AerospikeFactory;
import com.vtgo.vn.admin.base.BaseController;
import com.vtgo.vn.admin.base.BaseResponse;
import com.vtgo.vn.admin.constant.AccountType;
import com.vtgo.vn.admin.constant.DatabaseConstants;
import com.vtgo.vn.admin.constant.ResponseConstants;
import com.vtgo.vn.admin.userinfo.BO.Account;
import com.vtgo.vn.admin.userinfo.BO.Driver;
import com.vtgo.vn.admin.userinfo.BO.GoodOwner;
import com.vtgo.vn.admin.userinfo.BO.VehicleOwner;
import com.vtgo.vn.admin.userinfo.BO.Quotation;
import com.vtgo.vn.admin.userinfo.request.SearchRequest;
import com.vtgo.vn.admin.userinfo.request.VehicleOwnerSearchRequest;
import com.vtgo.vn.admin.userinfo.service.GoodOwnerService;
import com.vtgo.vn.admin.userinfo.service.QuotationService;
import com.vtgo.vn.admin.util.SecurityUtils;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HP
 */
@Service
@AllArgsConstructor
public class QuotationController extends BaseController implements QuotationService {

    private static final Logger logger = Logger.getLogger(QuotationController.class.getName());

    @Override
    public ResponseEntity searchQuotation(SearchRequest request) {
        BaseResponse response = new BaseResponse();
        List<Quotation> quotation = new ArrayList<>();
        try {
            Map<String, Object> argument = new HashMap<>();
            List<Value.MapValue> argumentFilter = new ArrayList<>();

            String searchValue = request.getSearchParam();
            if (searchValue != null && !searchValue.isEmpty()) {
                Map<String, Object> f = new HashMap<>();
                f.put("field", "QuotationId");
                f.put("value", searchValue);
                f.put("operator", "contain");
                argumentFilter.add(new Value.MapValue(f));
            }
            List<Value.MapValue> argumentSorters = new ArrayList<>();
            Map<String, Object> s1 = new HashMap<>();
            s1.put("sort_key", "QuotationId");
            s1.put("order", "DESC");
            s1.put("type", "STRING");
            argumentSorters.add(new Value.MapValue(s1));

            argument.put("sorters", new Value.ListValue(argumentSorters));
            argument.put("filters", new Value.ListValue(argumentFilter));
            ResultSet resultSet = AerospikeFactory.getInstance()
                    .aggregate(AerospikeFactory.getInstance().queryPolicy, DatabaseConstants.NAMESPACE, DatabaseConstants.QUOTATION, "FILTER_RECORD", "FILTER_RECORD", Value.get(argument));
            if (resultSet != null) {
                Iterator<Object> objectIterator = resultSet.iterator();
                while (objectIterator.hasNext()) {
                    ArrayList arrayList = (ArrayList) objectIterator.next();
                    for (Object o : arrayList) {
                        Quotation temp = new Quotation();
                        if (temp.parse((Map) o)) {
                            quotation.add(temp);
                        }
                    }
                }
            }
            response.setData(quotation);
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
    public ResponseEntity getQuotationId(Quotation request) {
        BaseResponse response = new BaseResponse();
        try {
            Record rec = getById(DatabaseConstants.NAMESPACE, DatabaseConstants.QUOTATION, request.getQuotationId());
//        (DatabaseConstants.NAMESPACE, DatabaseConstants.QUOTATION, Long.parseLong(request.getQuotationId()));
            response.setStatus(ResponseConstants.SUCCESS);
            response.setMessage(ResponseConstants.SERVICE_SUCCESS_DESC);
            if (rec != null) {
                Quotation quotation = new Quotation();
                quotation.parse(rec);
                response.setData(Arrays.asList(quotation));
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
    public ResponseEntity update(Quotation request) {
        return null;
    }

    @Override
    public ResponseEntity create(Quotation request) {
        return null;
    }

    @Override
    public ResponseEntity delete(Quotation request) {
        return null;
    }

    @Override
    public ResponseEntity getQuotationByOrderId(SearchRequest request) {

        BaseResponse response = new BaseResponse();
        List<Quotation> quotation = new ArrayList<>();
        try {
            Map<String, Object> argument = new HashMap<>();
            List<Value.MapValue> argumentFilter = new ArrayList<>();

            String searchValue = request.getSearchParam();
            
            Map<String, Object> f = new HashMap<>();
            f.put("field", "OrderId");
            f.put("value", searchValue);
            f.put("operator", "=");
            argumentFilter.add(new Value.MapValue(f));

            List<Value.MapValue> argumentSorters = new ArrayList<>();
            Map<String, Object> s1 = new HashMap<>();
            s1.put("sort_key", "QuotationID");
            s1.put("order", "DESC");
            s1.put("type", "STRING");
            argumentSorters.add(new Value.MapValue(s1));

            argument.put("sorters", new Value.ListValue(argumentSorters));
            argument.put("filters", new Value.ListValue(argumentFilter));
            ResultSet resultSet = AerospikeFactory.getInstance()
                    .aggregate(AerospikeFactory.getInstance().queryPolicy, DatabaseConstants.NAMESPACE, DatabaseConstants.QUOTATION, "FILTER_RECORD", "FILTER_RECORD", Value.get(argument));
            if (resultSet != null) {
                Iterator<Object> objectIterator = resultSet.iterator();
                while (objectIterator.hasNext()) {
                    ArrayList arrayList = (ArrayList) objectIterator.next();
                    for (Object o : arrayList) {
                        Quotation temp = new Quotation();
                        if (temp.parse((Map) o)) {
                            quotation.add(temp);
                        }
                    }
                }
            }
            response.setData(quotation);
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

}
