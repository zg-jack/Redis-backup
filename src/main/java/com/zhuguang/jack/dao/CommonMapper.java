package com.zhuguang.jack.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhuguang.jack.bean.ConsultConfigArea;
import com.zhuguang.jack.bean.ConsultContent;
import com.zhuguang.jack.bean.ConsultContract;
import com.zhuguang.jack.bean.ConsultIdCardInfo;
import com.zhuguang.jack.bean.ConsultRecord;
import com.zhuguang.jack.bean.ConsultRecordCount;

@Repository
public interface CommonMapper {

	int saveArea(ConsultConfigArea area);
	
	int deleteArea(Map param);
	
	int deleteAreaAll();
	
	int updateArea(ConsultConfigArea area);
	
	List<ConsultConfigArea> queryAreaByAreaCode(Map param);
	
	List<ConsultRecord> queryConsultRecords(Map param);
	
	List<Map> queryUserByPsptId(Map param);
	
	int saveUser(ConsultIdCardInfo record);
	
	int saveRecord(ConsultRecord record);
	
	int saveRecordCount(ConsultRecordCount recordCount);
	
	List<ConsultRecord> queryRecords(Map param);
	
	List<ConsultRecord> queryRecordshaveH(Map param);
	
	List<ConsultContent> queryContent(Map param);
	
	int updateRecords(Map param);
	
	List<ConsultRecordCount> queryRecordCount(Map param);
	
	int updateRecordCount(Map param);
	
	List<ConsultConfigArea> qryArea(Map param);
	
	List<ConsultContract> qryContracts(Map param);
	
	int saveContracts(List<ConsultContract> contracts);
}
