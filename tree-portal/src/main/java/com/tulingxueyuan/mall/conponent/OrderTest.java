package com.tulingxueyuan.mall.conponent;

import com.tulingxueyuan.mall.modules.oms.service.OmsOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 订单超时定时任务
 */
@Component
@Slf4j
public class OrderTest {
    @Autowired
    private OmsOrderService orderService;
    @Scheduled(cron = "0 0/1 * ? * ?")
    private void orderOverTime() {
        log.info("------------------------定时任务开始！！！！-------------------");
        orderService.orderOverTimeHandle();
        log.info("------------------------定时任务结束！！！！-------------------");
    }
}
