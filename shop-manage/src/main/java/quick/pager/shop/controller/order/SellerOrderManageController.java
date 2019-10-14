package quick.pager.shop.controller.order;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import quick.pager.shop.BindingResultUtils;
import quick.pager.shop.constants.Constants;
import quick.pager.shop.constants.ResponseStatus;
import quick.pager.shop.response.Response;
import quick.pager.shop.client.OrderClient;
import quick.pager.shop.dto.SellerOrderDTO;

/**
 * 商家订单管理
 *
 * @author siguiyang
 */
@Api(description = "商家订单管理")
@RestController
@RequestMapping(Constants.Module.MANAGE)
public class SellerOrderManageController {

    @Autowired
    private OrderClient orderClient;

    @ApiOperation("商户订单")
    @PostMapping("/order/seller")
    public Response sellerOrder(@Valid SellerOrderDTO request, BindingResult bindingResult) {
        BindingResultUtils.getFieldErrorMessage(bindingResult);
        return orderClient.sellerOrders(request);
    }

    @ApiOperation("商户订单详情")
    @PostMapping("/order/seller/info")
    public Response sellerOrderInfo(@RequestParam("sellerOrderId") Long sellerOrderId) {
        return orderClient.sellerOrderInfo(sellerOrderId);
    }
}
