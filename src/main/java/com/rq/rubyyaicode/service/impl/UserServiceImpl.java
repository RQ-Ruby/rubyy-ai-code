package com.rq.rubyyaicode.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.rq.rubyyaicode.exception.BusinessException;
import com.rq.rubyyaicode.exception.ErrorCode;
import com.rq.rubyyaicode.model.dto.user.UserQueryRequest;
import com.rq.rubyyaicode.model.entity.User;
import com.rq.rubyyaicode.mapper.UserMapper;
import com.rq.rubyyaicode.model.enums.UserRoleEnum;
import com.rq.rubyyaicode.model.vo.UserVO.LoginUserVO;
import com.rq.rubyyaicode.model.vo.UserVO.UserVO;
import com.rq.rubyyaicode.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.rq.rubyyaicode.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户 服务层实现。
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements UserService{
/**
 * @description 注册用户
 * @author RQ
 * @date 2025/10/25 下午3:33
 */
    @Override
    public Long register(String userAccount, String userPassword, String checkPassword) {
        //1.校验
        if(StrUtil.isBlank(userAccount) || StrUtil.isBlank(userPassword) || StrUtil.isBlank(checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号密码不能为空");
        }
        if(!userPassword.equals(checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"两次输入密码不一致");
        }
        if(!userAccount.matches("^[a-zA-Z0-9_]+$")){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号只能包含字母、数字和下划线");
        }
        if(userAccount.length()<4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号长度不能小于4位");
        }
        if(userPassword.length()<8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码长度不能小于8位");
        }
        //2.检查用户是否重复
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userAccount",userAccount);
        long count = this.mapper.selectCountByQuery(queryWrapper);
        if(count>0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号重复");
        }
        //3.加密
        String encryptPassword = encryptPassword(userPassword);
        //4.保存用户
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setUserName("用户"+userAccount);
        user.setUserProfile("");
        user.setUserRole(UserRoleEnum.USER.getValue());
        boolean isSuccess = this.save(user);
        if(!isSuccess){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"注册失败");
        }
        return user.getId();
    }
/**
 * @description 加密密码
 * @author RQ
 * @date 2025/10/25 下午3:33
 */
    public String encryptPassword(String userPassword) {
        //加盐，混淆密码
        String salt = "ruby";
        return DigestUtils.md5DigestAsHex((userPassword + salt).getBytes());
    }
    /**
     * @description 获得脱敏后的数据
     * @author RQ
     * @date 2025/10/25 下午6:31
     */
    public LoginUserVO getLoginUserVO(User user){
        if(user==null){
            return null;
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtils.copyProperties(user,loginUserVO);
        return loginUserVO;
    }
    /**
     * @description 用户登录
     * @author RQ
     * @date 2025/10/25 下午6:36
     */
    @Override
    public LoginUserVO loginUser(String userAccount, String userPassword, HttpServletRequest request) {
        //1.校验
        if(StrUtil.isBlank(userAccount) || StrUtil.isBlank(userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号密码不能为空");
        }
        if(!userAccount.matches("^[a-zA-Z0-9_]+$")){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号只能包含字母、数字和下划线");
        }
        if(userAccount.length()<4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号长度不能小于4位");
        }
        if(userPassword.length()<8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码长度不能小于8位");
        }
        //2.检查用户是否存在
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userAccount",userAccount);
        User user = this.mapper.selectOneByQuery(queryWrapper);
        if(user==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号不存在");
        }
        //3.校验密码
        String encryptPassword = encryptPassword(userPassword);
        //4.校验密码是否正确
        if(!user.getUserPassword().equals(encryptPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码错误");
        }
        //5.如果成功登录，将用户信息保存到session
        request.getSession().setAttribute(USER_LOGIN_STATE,user);
        //6.返回脱敏后的数据
        return this.getLoginUserVO(user);
    }
/**
 * @description 用户注销
 * @author RQ
 * @date 2025/10/25 下午7:14
 */
    @Override
    public boolean logout(HttpServletRequest request) {
        //1.校验用户是否登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        if(userObj==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户未登录");
        }
        //2.注销用户
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }
/**
 * @description 检查用户是否登录
 * @author RQ
 * @date 2025/10/25 下午7:15
 */
    @Override
    public User isLogin(HttpServletRequest request) {
        //1.校验用户是否登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        if(userObj==null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR,"用户未登录");
        }
        //2.从数据库中查询用户信息
        User user = (User) userObj;
        Long userId = user.getId();
        user = this.getById(userId);
        if(user==null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR,"用户未登录");
        }

        return user;
    }
    @Override
    public UserVO getUserVO(User user) {
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public List<UserVO> getUserVOList(List<User> userList) {
        if (CollUtil.isEmpty(userList)) {
            return new ArrayList<>();
        }
        return userList.stream().map(this::getUserVO).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper getQueryWrapper(UserQueryRequest userQueryRequest) {
        if (userQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = userQueryRequest.getId();
        String userAccount = userQueryRequest.getUserAccount();
        String userName = userQueryRequest.getUserName();
        String userProfile = userQueryRequest.getUserProfile();
        String userRole = userQueryRequest.getUserRole();
        String sortField = userQueryRequest.getSortField();
        String sortOrder = userQueryRequest.getSortOrder();
        return QueryWrapper.create()
                .eq("id", id)
                .eq("userRole", userRole)
                .like("userAccount", userAccount)
                .like("userName", userName)
                .like("userProfile", userProfile)
                .orderBy(sortField, "ascend".equals(sortOrder));
    }


}
