package com.rq.rubyyaicode.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.rq.rubyyaicode.model.dto.user.UserQueryRequest;
import com.rq.rubyyaicode.model.entity.User;
import com.rq.rubyyaicode.model.vo.UserVO.LoginUserVO;
import com.rq.rubyyaicode.model.vo.UserVO.UserVO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * 用户 服务层。
 *
 */
public interface UserService extends IService<User> {
    /**
     * 注册用户
     *
     * @param userAccount  账号
     * @param userPassword 密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    Long register(String userAccount, String userPassword, String checkPassword);

    /**
     * @description 用户登录
     * @author RQ
     * @date 2025/10/25 下午6:36
     */
     LoginUserVO loginUser(String userAccount, String userPassword, HttpServletRequest request);
     /**
     * @description 用户注销
     * @author RQ
     * @date 2025/10/25 下午6:36
     */
     boolean logout(HttpServletRequest request);
     /**
     * @description 检查用户是否登录
     * @author RQ
     * @date 2025/10/25 下午7:14
     */
     User isLogin(HttpServletRequest request);
/**
 * @description 获取登录用户VO
 * @author RQ
 * @date 2025/10/29 下午12:54
 */
     LoginUserVO getLoginUserVO(User user);

    UserVO getUserVO(User user);

    List<UserVO> getUserVOList(List<User> userList);

    QueryWrapper getQueryWrapper(UserQueryRequest userQueryRequest);

    String encryptPassword(String defaultPassword);
}
