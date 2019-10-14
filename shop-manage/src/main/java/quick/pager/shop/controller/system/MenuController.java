package quick.pager.shop.controller.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import quick.pager.shop.dto.MenuDTO;
import quick.pager.shop.response.Response;
import quick.pager.shop.service.system.MenuService;

@Api(description = "菜单管理")
@RestController
@RequestMapping(Constants.Module.MANAGE)
public class MenuController {


    @Autowired
    private MenuService menuService;


    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @ApiOperation("系统菜单列表")
    @PostMapping("/menu/list")
    public Response systemMenuList() {
        MenuDTO dto = new MenuDTO();
        dto.setEvent(Constants.Event.LIST);
        return menuService.doService(dto);
    }


    /**
     * 新增
     */
    @PostMapping(value = "/menu")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Response addMenus(@Valid MenuDTO dto, BindingResult bindingResult) {
        BindingResultUtils.getFieldErrorMessage(bindingResult);
        dto.setEvent(Constants.Event.ADD);
        return menuService.doService(dto);
    }

    /**
     * 修改
     */
    @PutMapping(value = "/menu")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Response modifyMenus(@Valid MenuDTO dto, BindingResult bindingResult) {
        BindingResultUtils.getFieldErrorMessage(bindingResult);
        dto.setEvent(Constants.Event.MODIFY);
        return menuService.doService(dto);
    }

    /**
     * 删除
     */
    @DeleteMapping(value = "/menu/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Response delMenus(@PathVariable("id") Long id) {
        MenuDTO dto = new MenuDTO();
        dto.setEvent(Constants.Event.DELETE);
        dto.setId(id);
        return menuService.doService(dto);
    }

}
