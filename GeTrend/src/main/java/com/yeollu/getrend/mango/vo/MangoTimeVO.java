package com.yeollu.getrend.mango.vo;

import lombok.Data;

/**
 * @Class 	: MangoTimeVO.java
 * @Package	: com.yeollu.getrend.mango.vo
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 19.
 * @Version	: 1.0
 * @Desc	: mango_times 테이블의 VO 역할을 수행한다.
 */
@Data
public class MangoTimeVO {
	private String store_no;
	private String mango_start;
	private String mango_end;
	private String mango_indate;
}
