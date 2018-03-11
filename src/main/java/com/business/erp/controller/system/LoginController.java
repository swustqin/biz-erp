package com.business.erp.controller.system;

import com.business.erp.controller.AbstractCommonController;
import com.business.erp.support.ResponseData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/sys")
public class LoginController extends AbstractCommonController {

    private Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData login(HttpServletRequest request, String username, String password) {

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            subject.getSession().setTimeout(1800000);
            log.info(username + ACCOUNT_MSG_SUCCESS_CHECKPASS);
        } catch (UnknownAccountException e) {
            return ResponseData.build(FAIL_CODE, FAIL, ACCOUNT_MSG_ERROR_NOTFOUND);
        } catch (IncorrectCredentialsException e) {
            return ResponseData.build(FAIL_CODE, FAIL, ACCOUNT_MSG_ERROR_CHECKFAIL);
        } catch (LockedAccountException e) {
            return ResponseData.build(FAIL_CODE, FAIL, ACCOUNT_MSG_ERROR_LOCK);
        } catch (AuthenticationException e) {
            return ResponseData.build(FAIL_CODE, FAIL, ACCOUNT_MSG_ERROR_AUTHENTICATION);
        }

        return ResponseData.ok();
    }

}
