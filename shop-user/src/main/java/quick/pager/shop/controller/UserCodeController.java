package quick.pager.shop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import quick.pager.shop.constants.Constants;
import quick.pager.shop.constants.RedisKeys;
import quick.pager.shop.constants.ResponseStatus;
import quick.pager.shop.dto.SmsDTO;
import quick.pager.shop.response.Response;
import quick.pager.shop.service.RedisService;
import quick.pager.shop.service.SMSCodeService;
import quick.pager.shop.utils.VerifyCodeUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户验证码方面的接口
 *
 * @author siguiyang
 */
@RestController
@Api(description = "用户验证码方面的接口")
@RequestMapping(Constants.Module.USER)
public class UserCodeController {

    @Autowired
    private RedisService redisService;
    @Autowired
    private SMSCodeService smsCodeService;

    /**
     * 发送短信验证码
     */
    @RequestMapping(value = "/code/sendSMS", method = RequestMethod.POST)
    @ApiOperation("发送短信验证码")
    public Response sendSMS(@RequestBody SmsDTO dto) {



        return smsCodeService.doService(dto);
    }


    /**
     * 发送图形验证码
     *
     * @param phone    手机号码
     * @param response response
     */
    @RequestMapping(value = "/send/code/graphic", method = RequestMethod.GET)
    @ApiOperation("发送图形验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, dataType = "Long", paramType = "query")})
    public void sendGraphic(@RequestParam String phone, HttpServletResponse response) throws IOException {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //实例生成验证码对象
        VerifyCodeUtils instance = new VerifyCodeUtils();
        //将验证码存入session
        redisService.set(RedisKeys.UserKeys.SHOP_GRAPHICS_CODE + phone, instance.getCode(), 2 * 60);
        //向页面输出验证码图片
        instance.write(response.getOutputStream());

    }
}
