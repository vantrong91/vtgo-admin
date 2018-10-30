/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtgo.vn.admin.userinfo.controller;

import com.aerospike.client.Value;
import com.aerospike.client.query.ResultSet;
import com.vtgo.vn.admin.aerospike.AerospikeFactory;
import com.vtgo.vn.admin.base.BaseController;
import com.vtgo.vn.admin.base.BaseResponse;
import com.vtgo.vn.admin.constant.DatabaseConstants;
import com.vtgo.vn.admin.constant.ResponseConstants;
import com.vtgo.vn.admin.userinfo.BO.Category;
import com.vtgo.vn.admin.userinfo.request.SearchRequest;
import com.vtgo.vn.admin.userinfo.service.CategoryService;
import java.util.ArrayList;
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
 * @author tvhdh
 */
@Service
@AllArgsConstructor

public class CategoryController extends BaseController implements CategoryService{
    private static final Logger logger = Logger.getLogger(CategoryController.class.getName());

    @Override
    public ResponseEntity searchCategory(SearchRequest request) {
        BaseResponse response = new BaseResponse();
        List<Category> lstCategory = new ArrayList<>();
        try {
            Map<String, Object> argument = new HashMap<>();
            List<Value.MapValue> argumentFilter = new ArrayList<>();
            String searchValue = request.getSearchParam();
            if(searchValue != null && !searchValue.isEmpty()){
                Map<String, Object> f = new HashMap<>();
                f.put("field", "Type");
                f.put("value", searchValue);
                f.put("operator", "contain");
                argumentFilter.add(new Value.MapValue(f));
            }
            
            argument.put("filters", new Value.ListValue(argumentFilter));
            ResultSet resultSet = AerospikeFactory.getInstance().aggregate(AerospikeFactory.getInstance().queryPolicy,
                    DatabaseConstants.NAMESPACE, DatabaseConstants.CATEGORY_SET,
                    "FILTER_RECORD", "FILTER_RECORD", Value.get(argument)
                    );
            if(resultSet != null){
                Iterator<Object> objectIterator = resultSet.iterator();
                while(objectIterator.hasNext()){
                    ArrayList arrayList = (ArrayList) objectIterator.next();
                    for(Object o : arrayList){
                        Category category = new Category();
                        if(category.parse((Map)o)){
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