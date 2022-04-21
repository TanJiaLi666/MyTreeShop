package com.tulingxueyuan.mall.modules.home.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tulingxueyuan.mall.modules.home.dto.*;
import com.tulingxueyuan.mall.modules.home.model.UmsMember;
import com.tulingxueyuan.mall.modules.home.service.HomeOrderService;
import com.tulingxueyuan.mall.modules.home.service.UmsMemberService;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnApply;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderReturnApplyService;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderService;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HomeOrderServiceImpl implements HomeOrderService {

    @Autowired
    private OmsOrderService orderService;
    @Autowired
    private OmsOrderReturnApplyService orderReturnApplyService;
    @Autowired
    private PmsProductService productService;
    @Autowired
    private UmsMemberService memberService;
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public OrderListDTO orderStatistics() {
        OrderListDTO dto = new OrderListDTO();
        //todo 获取时间，精确得到日期

        DateTime date = DateUtil.date();//当前时间
        DateTime yesterday = DateUtil.yesterday();//昨天
        DateTime recentDay = DateUtil.lastWeek();//七天前
        DateTime weekBegin = DateUtil.beginOfWeek(date);
        DateTime monthBegin = DateUtil.beginOfMonth(date);
        DateTime lastMonthBegin = DateUtil.beginOfMonth(DateUtil.lastMonth());
        DateTime lastWeekBegin = DateUtil.beginOfWeek(DateUtil.lastWeek());
        //todo 计算统计订单数据
        //查询订单全表
        List<OmsOrder> orderList = orderService.list();
        //查询今天的订单
        List<OmsOrder> todayOrderList = new ArrayList<>();
        BigDecimal todaySaleSum = countOrder(orderList, date, todayOrderList, format);
        //查询昨天的订单
        ArrayList<OmsOrder> yesterdayOrderList = new ArrayList<>();
        BigDecimal yesterdaySaleSum = countOrder(orderList, yesterday, yesterdayOrderList, format);
        //查询七天内的订单
        List<OmsOrder> recentOrderList = orderList.stream().map(o -> {
            if ((format.format(recentDay).compareTo(format.format(o.getCreateTime())) <= 0)) {
                return o;
            }
            return null;
        }).filter(o -> o != null).collect(Collectors.toList());
        BigDecimal recentSaleSum = recentOrderList.stream().map(OmsOrder::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        //查询周信息
        List<OmsOrder> weekOrderList = orderList.stream().map(o -> {
            if ((format.format(weekBegin).compareTo(format.format(o.getCreateTime())) <= 0)) {
                return o;
            }
            return null;
        }).filter(o -> o != null).collect(Collectors.toList());
        BigDecimal weekSaleSum = weekOrderList.stream().map(OmsOrder::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        List<OmsOrder> lastWeekOrderList = orderList.stream().map(o -> {
            if (StrUtil.compare(format.format(o.getCreateTime()),format.format(weekBegin), true) <= 0
                   /* (format.format(o.getCreateTime()).compareTo(format.format(weekBegin)) >= 0)*/) {
                if ((format.format(lastWeekBegin).compareTo(format.format(o.getCreateTime())) <= 0)) {
                    return o;
                }
            }
            return null;
        }).filter(o -> o != null).collect(Collectors.toList());
        BigDecimal lastWeekSaleSum = lastWeekOrderList.stream().map(OmsOrder::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        //查询月信息
        List<OmsOrder> monthOrderList = orderList.stream().map(o -> {
            if ((format.format(monthBegin).compareTo(format.format(o.getCreateTime())) <= 0)) {
                return o;
            }
            return null;
        }).filter(o -> o != null).collect(Collectors.toList());
        BigDecimal monthSaleSum = monthOrderList.stream().map(OmsOrder::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        List<OmsOrder> lastMonthOrderList = orderList.stream().map(o -> {
            if ((format.format(monthBegin).compareTo(format.format(o.getCreateTime())) >= 0)) {
                if ((format.format(lastMonthBegin).compareTo(format.format(o.getCreateTime())) <= 0)) {
                    return o;
                }
            }
            return null;
        }).filter(o -> o != null).collect(Collectors.toList());
        BigDecimal lastMonthSaleSum = lastMonthOrderList.stream().map(OmsOrder::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        //组装统计结果
        dto.setSum(orderList.size());
        dto.setTodaySum(todayOrderList.size());
        dto.setTodaySaleSum(todaySaleSum);
        dto.setYesterdaySaleSum(yesterdaySaleSum);
        dto.setRecentSaleSum(recentSaleSum);
        dto.setA(statusAmount(0,orderList));
        dto.setB(statusAmount(1,orderList));
        dto.setC(statusAmount(2,orderList));
        dto.setD(statusAmount(3,orderList));
        dto.setE(statusAmount(4,orderList));
        dto.setF(statusAmount(5,orderList));
        dto.setWeekOrderSum(weekOrderList.size());
        dto.setWeekOrderSale(weekSaleSum);
        dto.setLastWeekOrderSum(lastWeekOrderList.size());
        dto.setLastWeekOrderSale(lastWeekSaleSum);
        dto.setMonthOrderSum(monthOrderList.size());
        dto.setMonthOrderSale(monthSaleSum);
        dto.setLastMonthOrderSum(lastMonthOrderList.size());
        dto.setLastMonthOrderSale(lastMonthSaleSum);
        return dto;
    }

    @Override
    public ReturnOrderDTO returnOrder() {
        ReturnOrderDTO returnOrderDTO = new ReturnOrderDTO();
        List<OmsOrderReturnApply> applyList = orderReturnApplyService.list();
        returnOrderDTO.setReturn_a(returnStatusAmount(0, applyList));
        returnOrderDTO.setReturn_b(returnStatusAmount(1, applyList));
        returnOrderDTO.setReturn_c(returnStatusAmount(2, applyList));
        returnOrderDTO.setReturn_d(returnStatusAmount(3, applyList));
        return returnOrderDTO;
    }

    @Override
    public ProductDTO shopStatistics() {
        ProductDTO dto = new ProductDTO();
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsProduct::getDeleteStatus, 0);
        List<PmsProduct> productList = productService.list(queryWrapper);
        //空库存的商品数
        Long stockSum = productList.stream().map(o -> {
            if (o.getStock() == 0) {
                return o;
            }
            return null;
        }).filter(o -> o != null).count();
        Long downProductSum = productList.stream().map(o -> {
            if (o.getPublishStatus() == 0) {
                return o;
            }
            return null;
        }).filter(o -> o != null).count();
        Long upProductSum = productList.stream().map(o -> {
            if (o.getPublishStatus() == 1) {
                return o;
            }
            return null;
        }).filter(o -> o != null).count();
        dto.setProductSum(productList.size());
        dto.setStockLowSum(stockSum.intValue());
        dto.setDownProductSum(downProductSum.intValue());
        dto.setUpProductSum(upProductSum.intValue());
        return dto;
    }

    @Override
    public AdminDTO adminStatistics() {
        AdminDTO dto = new AdminDTO();
        //todo 获取时间，精确得到日期
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar yesterdayCalendar = Calendar.getInstance();
        yesterdayCalendar.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterday = yesterdayCalendar.getTime();//昨天
        List<UmsMember> list = memberService.list();
        int year = DateUtil.year(date);
        int month = DateUtil.month(date);
        Long yesterdayMemberSum = list.stream().map(o -> {
            if ((format.format(yesterday).compareTo(format.format(o.getCreateTime())) == -1)) {
                return o;
            }
            return null;
        }).filter(o -> o != null).count();
        Long todayMemberSum = list.stream().map(o -> {
            if ((format.format(date).compareTo(format.format(o.getCreateTime())) == -1)) {
                return o;
            }
            return null;
        }).filter(o -> o != null).count();
        Long monthMemberSum = list.stream().map(o -> {
            if (DateUtil.year(o.getCreateTime())== year) {
                if (DateUtil.month(o.getCreateTime()) == month) {
                    return o;
                }
            }
            return null;
        }).filter(o -> o != null).count();
        dto.setMemberSum(list.size());
        dto.setYesterdayMemberSum(yesterdayMemberSum.intValue());
        dto.setTodayMemberSum(todayMemberSum.intValue());
        dto.setMonthMemberSum(monthMemberSum.intValue());
        return dto;
    }

    @Override
    public OrderListDTO mapStatistics(String start, String end) {
        OrderListDTO dto = new OrderListDTO();
        List<OrderVO> orderVOList = new ArrayList<>();
        QueryWrapper<OmsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().between(OmsOrder::getCreateTime,start,end);
        List<OmsOrder> orderList = orderService.list(queryWrapper);
        List<OmsOrder> list = orderList.stream()
                .map(o -> {
                    o.setCreateTime(DateUtil.parse(format.format(o.getCreateTime())));
                    return o;
                }).collect(Collectors.toList());
        LinkedHashMap<Date, List<OmsOrder>> map = list.stream()
                .sorted(Comparator.comparing(OmsOrder::getCreateTime, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.groupingBy(OmsOrder::getCreateTime, LinkedHashMap::new, Collectors.toList()));
        map.forEach((k, v)->{
            OrderVO vo = new OrderVO();
            vo.setDate(format.format(k));
            vo.setOrderCount(v.size());
            vo.setOrderAmount(v.stream().map(OmsOrder::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add));
            orderVOList.add(vo);
        });
        dto.setOrderList(orderVOList);
        return dto;
    }

    /**
     * 计算当日或昨日的订单
     * @param orderList
     * @param date
     * @param newOrderList
     * @return
     */
    private BigDecimal countOrder(List<OmsOrder> orderList, Date date,List<OmsOrder> newOrderList, SimpleDateFormat format) {
        newOrderList = orderList.stream().map(o -> {
            if (format.format(date).equals(format.format(o.getCreateTime()))) {
                return o;
            }
            return null;
        }).filter(o -> o != null).collect(Collectors.toList());
        BigDecimal saleSum = newOrderList.stream().map(OmsOrder::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        return saleSum;
    }
    private Integer statusAmount(Integer status, List<OmsOrder> list) {
        list = list.stream().map(o->{
            if (o.getStatus() == status) {
                return o;
            }
            return null;
        }).filter(o->o!=null).collect(Collectors.toList());
        return list.size();
    }
    private Integer returnStatusAmount(Integer status, List<OmsOrderReturnApply> list) {
        list = list.stream().map(o->{
            if (o.getStatus() == status) {
                return o;
            }
            return null;
        }).filter(o->o!=null).collect(Collectors.toList());
        return list.size();
    }

}
