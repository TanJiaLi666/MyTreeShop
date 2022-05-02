package com.tulingxueyuan.mall.modules.sms.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderItem;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnApply;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderItemService;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderReturnApplyService;
import com.tulingxueyuan.mall.modules.pms.service.PmsCommentService;
import com.tulingxueyuan.mall.modules.sms.model.dto.AnalysisDTO;
import com.tulingxueyuan.mall.modules.sms.model.dto.DataQueryDTO;
import com.tulingxueyuan.mall.modules.sms.model.dto.DataStatisticsDTO;
import com.tulingxueyuan.mall.modules.sms.service.SmsDataHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class SmsDataHandleServiceImpl implements SmsDataHandleService {
    @Autowired
    PmsCommentService commentService;
    @Autowired
    OmsOrderItemService orderItemService;
    @Autowired
    OmsOrderReturnApplyService returnApplyService;
    @Override
    public List<DataStatisticsDTO> forecastShopSale(DataQueryDTO queryDTO) {
        Long productId = queryDTO.getId();
        //todo 1.查询商品前30天销量、商品相应的评分
        Double score = commentService.getScore(productId);//商品评分
        List<DataStatisticsDTO> order = orderItemService.getOrderProduct(productId);
        order = order.stream().map(o->{
            o.setY(score*o.getSale());
            o.setX(DateUtil.parse(o.getTime()).getTime());
            return o;
        }).collect(Collectors.toList());
        //todo 2.获取直线回归方程
        Long xSum = 0l;
        Double ySum = 0d;
        for (DataStatisticsDTO dto : order) {
            xSum = xSum + dto.getX();
            ySum = ySum + dto.getY();
        }
        Long _x = xSum/order.size();
        Double _y = ySum/order.size();
        Double b1 = 0d;
        Long b2 = 0l;
        for (DataStatisticsDTO dto : order) {
            b1 =b1 + (dto.getY()-_y)*((dto.getX()-_x));
            b2 = b2 + (dto.getX()-_x)*(dto.getX()-_x);
        }
        Double b = b1/b2;
        Double a = _y-(b*_x);
        DateTime date = DateUtil.date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        DateTime forecastTime = DateUtil.parse(time, "yyyy-MM-dd");
        for(int i = 1; i<= 7;i++){
            DataStatisticsDTO dto = new DataStatisticsDTO();
            Double forecastSale = getXforY(a, b, DateUtil.offsetDay(forecastTime, i), score);
            dto.setTime(DateUtil.format(DateUtil.offsetDay(forecastTime, i),"yyyy-MM-dd"));
            dto.setSale(forecastSale.intValue());
            order.add(dto);
        }
        //todo 3.根据方程获得未来7天销量
        return order;
    }

    @Override
    public AnalysisDTO analysisShop(DataQueryDTO queryDTO) {
        Long id = queryDTO.getId();
        //销量
        Map<String, Object> sum = queryOrderSum(null);//总销量
        Map<String, Object> shopSum = queryOrderSum(id);//商品销量
        //销售额
        Map<String, Object> price = queryOrderSale(null);//销售额
        Map<String, Object> shopPrice = queryOrderSale(id);//商品销售额
        //订单退货量
        Map<String, Object> returnSumCount = queryOrderReturn(null);//退货数
        Map<String, Object> shopReturnSumCount = queryOrderReturn(id);//商品退货数
        Object sumCount = sum.get("sumCount");
        Object shopSumCount = shopSum.get("sumCount");
        Object sumPrice = price.get("sumPrice");
        Object shopSumPrice = shopPrice.get("sumPrice");
        Object returnSum = returnSumCount.get("returnSum");
        Object shopReturnSum = shopReturnSumCount.get("returnSum");
        AnalysisDTO analysisDTO = new AnalysisDTO();
        analysisDTO.setSumCount(Integer.parseInt(sumCount.toString()));
        analysisDTO.setShopSumCount(Integer.parseInt(shopSumCount.toString()));
        analysisDTO.setSumPrice(new BigDecimal(sumPrice.toString()));
        analysisDTO.setShopSumPrice(new BigDecimal(shopSumPrice.toString()));
        analysisDTO.setReturnSum(Integer.parseInt(returnSum.toString()));
        analysisDTO.setShopReturnSum(Integer.parseInt(shopReturnSum.toString()));
        return analysisDTO;
    }
    private Map<String, Object> queryOrderReturn(Long id) {
        if (id == null) {
            QueryWrapper<OmsOrderReturnApply> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("COUNT(*) returnSum");
            return returnApplyService.getMap(queryWrapper);
        }else {
            QueryWrapper<OmsOrderReturnApply> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("COUNT(*) returnSum").lambda().eq(OmsOrderReturnApply::getProductId, id);
            return returnApplyService.getMap(queryWrapper);
        }
    }
    private Map<String, Object> queryOrderSale(Long id) {
        QueryWrapper<OmsOrderItem> queryWrapper = new QueryWrapper<>();
        if (id == null) {
            queryWrapper.select("SUM(`product_price`) sumPrice");
            Map<String, Object> sum = orderItemService.getMap(queryWrapper);
            return sum;
        } else {
            queryWrapper.select("SUM(`product_price`) sumPrice").lambda().eq(OmsOrderItem::getProductId,id);
            Map<String, Object> shopSum = orderItemService.getMap(queryWrapper);
            return shopSum;
        }
    }
    private Map<String, Object> queryOrderSum(Long id) {
        QueryWrapper<OmsOrderItem> queryWrapper = new QueryWrapper<>();
        if (id == null) {
            queryWrapper.select("SUM(`product_quantity`) sumCount");
            Map<String, Object> sum = orderItemService.getMap(queryWrapper);
            return sum;
        } else {
            queryWrapper.select("SUM(`product_quantity`) sumCount").lambda().eq(OmsOrderItem::getProductId,id);
            Map<String, Object> shopSum = orderItemService.getMap(queryWrapper);
            return shopSum;
        }
    }
    private Double getXforY(Double a,Double b, DateTime time, Double score){
        Double num = b*time.getTime()+a;
        if (num==0){
            return num;
        }
        return num/score;
    }
}
