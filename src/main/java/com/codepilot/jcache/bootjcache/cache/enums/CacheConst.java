package com.codepilot.jcache.bootjcache.cache.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@ApiModel(description = "캐시 공통 상수")
public class CacheConst  {
	
	@Getter
	@ApiModel(description = "캐시명")
	@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
	public enum CacheNames {

		USER("User", "유저 캐시"), 
		TEST("Test", "TEST 캐시");

		private String value;
		private String desc;
	
		public static List<CacheNames> findAllCacheNames() {
			return Arrays.stream(CacheNames.values()).collect(Collectors.toList());
		}
	}
	
//	@Getter
//	@ApiModel(description = "캐시명")
//	@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
//	public enum Const {
//		CHASING_MAX_BUCKET(10000,"캐시의 용량"), 
//		CHASING_ITEM_LIMIT_SZIE(1000, "캐시에 저장되는 아이템들의 사이즈 (단위:BYTE)"),
//		CHASING_TIME_TO_IDLE(10,"마지막 캐시 요청 이후 IDLE 타임 동안 재요청이 없는 경우 evic (단위:초)"), 
//		CHASING_TIME_TO_LIVE(20,"최초 캐시 입력 후 LIVE 타임 동안 저장 (단위:초)"),;
//		
//		private Object value;
//		private String desc;
//	}
	
	
	
}