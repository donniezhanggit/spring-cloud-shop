package quick.pager.shop.controller.risk;

import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import quick.pager.shop.BindingResultUtils;
import quick.pager.shop.constants.Constants;
import quick.pager.shop.constants.ResponseStatus;
import quick.pager.shop.dto.BlackListDTO;
import quick.pager.shop.service.risk.BlackListService;
import quick.pager.shop.response.Response;


/**
 * @author siguiyang
 */
@RestController
@RequestMapping(Constants.Module.MANAGE)
public class BlackListController {

    @Autowired
    private BlackListService blackListService;

    /**
     * 列表
     */
    @PostMapping(value = "/blackList/list")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Response getBlackLists(@Valid BlackListDTO dto, BindingResult bindingResult) {
        BindingResultUtils.getFieldErrorMessage(bindingResult);
        return blackListService.getBlackLists(dto);
    }

    /**
     * 新增
     */
    @PostMapping(value = "/blackList")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Response addBlackLists(@Valid BlackListDTO dto, BindingResult bindingResult) {
        BindingResultUtils.getFieldErrorMessage(bindingResult);
        return blackListService.addBlackLists(dto);
    }

    /**
     * 修改
     */
    @PutMapping(value = "/blackList")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Response modifyBlackLists(@Valid BlackListDTO dto, BindingResult bindingResult) {
        BindingResultUtils.getFieldErrorMessage(bindingResult);
        return blackListService.modifyBlackLists(dto);
    }

    /**
     * 删除
     */
    @DeleteMapping(value = "/blackList/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Response delBlackLists(@PathVariable("id") Long id) {
        return blackListService.delBlackLists(id);
    }
}
