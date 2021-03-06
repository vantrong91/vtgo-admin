/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtgo.vn.admin.userinfo.service;

import com.vtgo.vn.admin.userinfo.BO.Category;
import com.vtgo.vn.admin.userinfo.request.SearchRequest;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author tvhdh
 */
public interface CategoryService {

    public ResponseEntity searchCategory(SearchRequest request);

    public ResponseEntity searchString(SearchRequest request);

    public ResponseEntity getById(Category request);

    public ResponseEntity update(Category request);

    public ResponseEntity create(Category request);

    public ResponseEntity delete(Category request);
}
