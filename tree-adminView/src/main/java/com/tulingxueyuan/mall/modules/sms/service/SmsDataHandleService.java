package com.tulingxueyuan.mall.modules.sms.service;

import com.tulingxueyuan.mall.modules.sms.model.dto.AnalysisDTO;
import com.tulingxueyuan.mall.modules.sms.model.dto.DataQueryDTO;
import com.tulingxueyuan.mall.modules.sms.model.dto.DataStatisticsDTO;

import java.util.List;

public interface SmsDataHandleService {
    List<DataStatisticsDTO> forecastShopSale(DataQueryDTO queryDTO);

    AnalysisDTO analysisShop(DataQueryDTO queryDTO);
}
