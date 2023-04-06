package com.cat.picture_search.api;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.cat.picture_search.domain.dto.api.CatPictureDetail;
import com.cat.picture_search.domain.dto.api.CatPictureSimple;

@FeignClient(name = "CatePictureOpenFeign", url = "${cat.openapi.url}")
public interface CatPictureOpenFeign {

	String API_KEY_HEADER_NAME = "x-api-key";

	@GetMapping("/search")
	List<CatPictureSimple> getRandom(
		@RequestHeader(name = API_KEY_HEADER_NAME) String apiKey,
		@RequestParam int limit
	);

	@GetMapping("/{id}")
	CatPictureDetail getOne(
		@RequestHeader(name = API_KEY_HEADER_NAME) String apiKey,
		@PathVariable String id
	);
}