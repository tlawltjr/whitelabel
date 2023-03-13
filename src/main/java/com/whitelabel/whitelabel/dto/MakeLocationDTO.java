package com.whitelabel.whitelabel.dto;

import org.json.simple.JSONObject;

public class MakeLocationDTO {

	private WLjmtDTO makeLocationDTO(JSONObject arr) {
		WLjmtDTO dto = WLjmtDTO.builder()
						.SIGUN_NM("SIGUN_NM")
						.RESTRT_NM("RESTRT_NM")
						.REPRSNT_FOOD_NM("REPRSNT_FOOD_NM")
						.TASTFDPLC_TELNO("TASTFDPLC_TELNO")
						.REFINE_LOTNO_ADDR("REFINE_LOTNO_ADDR")
						.REFINE_ROADNM_ADDR("REFINE_ROADNM_ADDR")
						.REFINE_ZIP_CD("REFINE_ZIP_CD")
						.build();
		return dto;
	}
}
