package com.rq.rubyyaicode.aop;

import com.rq.rubyyaicode.annotation.AuthCheck;
import com.rq.rubyyaicode.exception.BusinessException;
import com.rq.rubyyaicode.exception.ErrorCode;
import com.rq.rubyyaicode.model.entity.User;
import com.rq.rubyyaicode.model.enums.UserRoleEnum;
import com.rq.rubyyaicode.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuthInterceptor {
      @Resource
      private UserService userService;
    public Object doIntercept(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        User user = userService.isLogin(request);
        String userRole = user.getUserRole();
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(userRole);
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        //不需要权限放行
        if(mustRoleEnum==null){
            return joinPoint.proceed();
        }
        //权限不够
        if(userRoleEnum==null){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        //需要管理员权限
        if(!userRoleEnum.equals(UserRoleEnum.ADMIN)&&UserRoleEnum.ADMIN.equals(mustRoleEnum)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        return joinPoint.proceed();
    }
}
