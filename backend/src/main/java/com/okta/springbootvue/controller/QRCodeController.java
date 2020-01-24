package com.okta.springbootvue.controller;


import java.util.concurrent.TimeUnit;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import reactor.core.publisher.Mono;

import com.okta.springbootvue.services.QRCodeService;

@Controller
public class QRCodeController {

  @Autowired
  private QRCodeService qrCodeService;

	@GetMapping(value = "/image/{text}", produces = MediaType.IMAGE_JPEG_VALUE)
  public Mono<ResponseEntity<byte[]>> getQRCode(
    @PathVariable String text
    ) {
		return qrCodeService.generateQRCode(text, 256, 256).map(imageBuff ->  
			ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES)).body(imageBuff)
		);
	}
  

}