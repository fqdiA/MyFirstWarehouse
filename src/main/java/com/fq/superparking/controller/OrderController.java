package com.fq.superparking.controller;

import com.fq.superparking.common.R;
import com.fq.superparking.entity.Order;
import com.fq.superparking.entity.vo.OrderVo;
import com.fq.superparking.service.OrderService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("order")
@Api(tags = "财务管理")
public class OrderController {

    final OrderService orderService;


    /**
     * 页面列表
     *
     * @param pageNum    当前页
     * @param pageSize   页面大小
     * @param orgId      合作单位 id
     * @param manageId   停车场id
     * @param plateNumber 车牌号码
     * @return {@link R}<{@link PageInfo}<{@link Order}>>
     */
    @ApiOperation("分页模糊查询订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页"),
            @ApiImplicitParam(name = "pageSize",value = "页面大小"),
            @ApiImplicitParam(name = "orgId",value = "合作单位"),
            @ApiImplicitParam(name = "manageId",value = "停车场id"),
            @ApiImplicitParam(name = "plateNumber",value = "车牌号码"),
    })
    @RequestMapping(value = "page",method = {RequestMethod.POST,RequestMethod.GET})
    public R pageList(
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
            Integer orgId,
            Integer manageId,
            String plateNumber){
        R<PageInfo<OrderVo>> pageInfoR = orderService.pageList(pageNum, pageSize, orgId, manageId, plateNumber);
        if (pageInfoR != null) {
            return pageInfoR;
        }
        return R.fail();
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除订单信息")
    public R<String> delete(@PathVariable("id") Long id){
        return orderService.deleteOrderById(id);
    }

    @GetMapping("orderWechatAmount")
    @ApiOperation("查询微信最近七天订单金额")
    public R<List<Order>> queryWechatOrderAmount(){
        R<List<Order>> listR = orderService.queryWechatOrderAmount();
        return listR;
    }

    @GetMapping("orderAlipayAmount")
    @ApiOperation("查询支付宝最近七天订单金额")
    public R<List<Order>> queryAlipayOrderAmount(){
        R<List<Order>> listR = orderService.queryAlipayOrderAmount();
        return listR;
    }

    @GetMapping("queryAlipayCount")
    @ApiOperation("查询支付宝总记录数")
    public R queryAlipayCount(){
        return R.ok(orderService.queryAlipayCount());
    }

    @GetMapping("queryWeChatCount")
    @ApiOperation("查询微信总记录数")
    public R queryWeChatCount(){
        return R.ok(orderService.queryWeChatCount());
    }

    @GetMapping("count")
    @ApiOperation("收益总额")
    public R getCountMoney(){
        return R.ok(orderService.getCountMoney());
    }
}
