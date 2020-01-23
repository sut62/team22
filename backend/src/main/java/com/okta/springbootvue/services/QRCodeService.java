package com.okta.springbootvue.services;

import java.io.*;
import org.slf4j.*;
import org.springframework.cache.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.google.zxing.*;
import com.google.zxing.client.j2se.*;
import com.google.zxing.common.BitMatrix;
import reactor.core.publisher.Mono;

@Service
@Cacheable(cacheNames = "qr-code-cache", sync = true)
public class QRCodeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(QRCodeService.class);

	public Mono<byte[]> generateQRCode(String text, int width, int height) {

		Assert.hasText(text, "text must not be empty");
		Assert.isTrue(width > 0, "width must be greater than zero");
		Assert.isTrue(height > 0, "height must be greater than zero");
		
		return Mono.create(sink -> {
			LOGGER.info("Will generate image  text=[{}], width=[{}], height=[{}]", text, width, height);
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
				BitMatrix matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
				MatrixToImageWriter.writeToStream(matrix, MediaType.IMAGE_JPEG.getSubtype(), baos, new MatrixToImageConfig());
				sink.success(baos.toByteArray());
			} catch (IOException | WriterException ex) {
				sink.error(ex);
			}
		});
	}

	@CacheEvict(cacheNames = "qr-code-cache", allEntries = true)
	public void purgeCache() {
		LOGGER.info("Purging cache");
  }
  

}