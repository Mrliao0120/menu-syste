package com.menu.controller.background;

import com.menu.service.AccountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName menu-system
 * @Author LHB
 * @Data 2019/10/28 13:00
 * @Version 1.0
 * @Description
 */
@RequestMapping(value = "/account")
@RestController
public class AccountUserController {

    @Autowired
    AccountUserService accountUserService;
}
