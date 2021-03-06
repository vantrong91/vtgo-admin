/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtgo.vn.admin.userinfo.service;

import com.vtgo.vn.admin.userinfo.BO.AccountManager;
import com.vtgo.vn.admin.userinfo.request.SearchRequest;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author viett
 */
public interface AccountManagerService {

    public ResponseEntity searchAccountMan(SearchRequest request);

    public ResponseEntity searchByEmail(SearchRequest request);

    public ResponseEntity getAccountManById(AccountManager request);

    public ResponseEntity create(AccountManager request);

    public ResponseEntity checkLogin(AccountManager request);

    public ResponseEntity getByAccountCode(AccountManager request);

    public ResponseEntity delete(AccountManager request);

    public ResponseEntity logout(AccountManager request);

    public ResponseEntity update(AccountManager request);

    public ResponseEntity updateInfo(AccountManager request);

    public ResponseEntity changeState(AccountManager request);
}
