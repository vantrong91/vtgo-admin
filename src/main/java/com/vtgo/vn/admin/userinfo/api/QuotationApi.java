/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtgo.vn.admin.userinfo.api;

import com.vtgo.vn.admin.userinfo.BO.Quotation;
import com.vtgo.vn.admin.userinfo.request.SearchRequest;
import com.vtgo.vn.admin.userinfo.service.QuotationService;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */

@RestController
@RequestMapping("v1/quotation")
@AllArgsConstructor
public class QuotationApi {

    private static final Logger LOGGER = Logger.getLogger(QuotationApi.class.getName());
    private final QuotationService quotationService;

    @PostMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity search(@RequestBody SearchRequest request) {
        LOGGER.debug("request /searchQuotation: " + request);
        return quotationService.searchQuotation(request);
    }

    @PostMapping(value = "/get-by-id", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getById(@RequestBody Quotation request) {
        LOGGER.debug("request /getById: " + request);
        return quotationService.getQuotationId(request);
    }

    @PostMapping(value = "/get-by-order-id", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getByOrderId(@RequestBody SearchRequest request) {
        LOGGER.debug("request/ getQuotationByOrderId: " + request.toString());
        return quotationService.getQuotationByOrderId(request);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity update(@RequestBody Quotation request) {
        LOGGER.debug("request /update: " + request);
        return quotationService.update(request);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity create(@RequestBody Quotation request) {
        LOGGER.debug("request /create: " + request);
        return quotationService.create(request);
    }

    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(@RequestBody Quotation request) {
        LOGGER.debug("request /delete: " + request);
        return quotationService.delete(request);
    }

}
