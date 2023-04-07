package com.cat.picture_search.domain.storage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cat.picture_search.api.CatPictureOpenFeignService;
import com.cat.picture_search.domain.dto.api.CatPictureDetail;
import com.cat.picture_search.domain.storage.repository.CatPictureRepository;

@Service
public class CatPictureSaveService {

	private final CatPictureRepository catPictureRepository;
	private final CatPictureOpenFeignService catPictureOpenFeignService;

	public CatPictureSaveService(CatPictureRepository catPictureRepository,
		CatPictureOpenFeignService catPictureOpenFeignService) {
		this.catPictureRepository = catPictureRepository;
		this.catPictureOpenFeignService = catPictureOpenFeignService;
	}

	public void savePictures() {
		List<CatPictureDetail> catPictureSimples = catPictureOpenFeignService.getRandom50();
		for (CatPictureDetail catPictureSimple : catPictureSimples) {
			if (!catPictureRepository.existsById(catPictureSimple.id())) {
				savePicture(catPictureSimple);
			}
		}
	}

	private void savePicture(CatPictureDetail catPictureDetail) {
		catPictureRepository.save(
			catPictureDetail.toEntity()
		);
	}
}