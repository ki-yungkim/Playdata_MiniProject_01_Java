package com.work.service;

import java.util.ArrayList;

import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.model.dto.CenterList;

public class CenterService {

	/** 센터 정보들을 저장관리하기 위한 자료 저장구조 : Generic*/
	private ArrayList<CenterList> list = new ArrayList<CenterList>();

	/** 기본생성자 : 초기 센터 목록 등록 수행 */
	public CenterService() {}

	
	/**
	 * 센터 리스트에 센터 이름 존재 유무 조회
	 * @param centerName 센터 이름
	 * @return 존재하면 index 번호, 없으면 -1
	 */
	public int existCenterName(String centerName) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getCenterName().equals(centerName)) {
				return index;
			}
		}
		return -1;
	}
	
	/**
	 * 센터 리스트에 시설 이름 존재 유무 조회
	 * @param facilityName 시설 이름
	 * @return 존재하면 index 번호, 없으면 -1
	 */
	public int existFacilityName(String facilityName) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getFacilityName().equals(facilityName)) {
				return index;
			}
		}
		return -1;
	}
	
	/**
	 * 센터 리스트에 우편번호 존재 유무 조회
	 * @param postcode 우편번호
	 * @return 존재하면 index 번호, 없으면 -1
	 */
	public int existPostcode(String postcode) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getPostcode().equals(postcode)) {
				return index;
			}
		}
		return -1;
	}
	
	/**
	 * 센터 리스트에 주소(도, 시, 구 단위까지) 존재 유무 조회
	 * @param address 주소
	 * @return 존재시 저장위치 인덱스번호, 미존재시 -1
	 */
	public int existAddress(String address) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getAddress().equals(address)) {
				return index;
			}
		}
		return -1;
	}
	
	/**
	 * 센터 리스트에 전화번호 존재 유무 조회
	 * @param phoneNumber 센터 전화번호 
	 * @return 존재시 저장위치 인덱스번호, 미존재시 -1
	 */
	public int existPhoneNumber(String phoneNumber) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getPhoneNumber().equals(phoneNumber)) {
				return index;
			}
		}
		return -1;
	}
	
	/**
	 * 센터등록	
	 * @param dto
	 * @throws DuplicateException
	 */
	public void addCenter(CenterList dto) throws DuplicateException  {
		int index = existPhoneNumber(dto.getPhoneNumber());
		if (index >= 0) {
			throw new DuplicateException(dto.getPhoneNumber());
		}

		list.add(dto);
	}

	/**
	 * 초기화 등록 
	 * @return
	 * @throws DuplicateException
	 */
	public int initCenter() throws DuplicateException {
		CenterList dto1 = new CenterList("강원도 평창군 예방접종센터", "평창읍 생활체육관", "25377", "강원도 평창군", "강원도 평창군 평창읍 산책길 38", "033-330-4835");
		CenterList dto2 = new CenterList("경기도 시흥시 예방접종센터", "정왕평생학습관", "15055", "경기도 시흥시", "경기도 시흥시 정왕대로 233번길 21", "031-310-5851");
		CenterList dto3 = new CenterList("경상남도 산청군 예방접종센터", "산청군 실내체육관", "52215", "경상남도 산청군", "경상남도 산청군 금서면 친환경로2631번길 39", "055-970-7548");
		CenterList dto4 = new CenterList("광주광역시 광산구 예방접종센터", "광주보훈병원 재활체육관", "62284", "광주광역시 광산구", "광주광역시 광산구 첨단월봉로 99, 광주보훈병원", "062-960-6862");
		CenterList dto5 = new CenterList(" 중앙 예방접종센터", "국립중앙의료원 D동", "4562", "서울특별시 중구", "서울특별시 중구 을지로 39길 29", "02-2260-7114");
		
		addCenter(dto1);
		addCenter(dto2);
		addCenter(dto3);
		addCenter(dto4);
		addCenter(dto5);
		
		
		return list.size();
	}
	
	
	/**
	 * 센터 추가등록
	 * @param centerName
	 * @param facilityName
	 * @param postcode
	 * @param address
	 * @param addressDetail
	 * @param phoneNumber
	 * @throws DuplicateException 
	 */
	public void addCenter(String centerName, String facilityName, String postcode, String address, String addressDetail, String phoneNumber) throws DuplicateException {
		CenterList dto = new CenterList(centerName, facilityName, postcode, address, addressDetail, phoneNumber);

		addCenter(dto);
	} 	

	
	
	


	
	/**
	 * 전체조회 - 관리자 전용 
	 * @return 전체 목록
	 */
	public ArrayList<CenterList> getList() {
		return list;
	}
	
	/**
	 * 현재 등록 센터 수 조회
	 * @return 등록센터 수
	 */
	public int getCount() {
		return list.size();
	}

	
	/**
	 * 센터명으로 조회
	 * @param centerName
	 * @return 존재하면 센터 정보, 없으면 데이터검색 예외
	 * @throws RecordNotFoundException
	 */
	public CenterList getListCenterName(String centerName) throws RecordNotFoundException {
		int index = existCenterName(centerName);
		if (index >= 0) {
			return (CenterList)list.get(index);
		}
		throw new RecordNotFoundException(centerName);
	}
	
	/**
	 * 시설명으로 조회
	 * @param facilityName
	 * @return 존재하면 센터 정보, 없으면 데이터검색 예외
	 * @throws RecordNotFoundException
	 */
	public CenterList getListFacility(String facilityName) throws RecordNotFoundException {
		int index = existFacilityName(facilityName);
		if (index >= 0) {
			return (CenterList)list.get(index);
		}
		throw new RecordNotFoundException(facilityName);
	}
	
	
	/**
	 * 센터 주소(시 군 구)로 조회
	 * @param address
	 * @return 존재하면 센터 정보, 없으면 데이터검색 예외
	 * @throws RecordNotFoundException
	 */
	public CenterList getListAddress(String address) throws RecordNotFoundException{
		int index = existAddress(address);
		if (index >= 0) {
			return (CenterList)list.get(index);
		}
		throw new RecordNotFoundException(address);
	}
		
	/**
	 * 전화번호로 센터 조회 
	 * @param phoneNumber 
	 * @return 존재하면 센터 정보, 없으면 데이터검색 예외
	 * @throws RecordNotFoundException
	 */
	public CenterList getListPhone(String phoneNumber) throws RecordNotFoundException{
		int index = existPhoneNumber(phoneNumber);
		if (index >= 0) {
			return (CenterList)list.get(index);
		}
		throw new RecordNotFoundException(phoneNumber);
	}


	/**
	 * 센터 리스트 전체 삭제 
	 * @return 리스트 크기 
	 */
	public int removeCenterListAll() {
		list.clear();
		return list.size();
	}
	
	
	/**
	 * 센터 리스트 중 입력한 하나 삭제 
	 * @param centerName 센터 이름
	 * @return 존재하면 삭제, 없으면 에러 
	 * @throws RecordNotFoundException
	 */
	public CenterList removeCenterList(String centerName) throws RecordNotFoundException{
		int index = existCenterName(centerName);
		if (index >= 0) {
			return list.remove(index);
		}
		
		throw new RecordNotFoundException(centerName);
	}
	
	/**
	 * 센터 이름 기준 센터 정보 변경 
	 * @param dto 변경할 정보 
	 * @return 성공시 true, 실패시 false
	 * @throws RecordNotFoundException
	 */
	public boolean setCenterImfo(CenterList dto) throws RecordNotFoundException {
		int index = existCenterName(dto.getCenterName());
		if (index >= 0) {
			list.set(index, dto);
			return true;
		}
		throw new RecordNotFoundException(dto.getCenterName());
	}

	
	


}