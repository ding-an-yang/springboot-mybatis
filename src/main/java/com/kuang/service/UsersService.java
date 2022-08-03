package com.kuang.service;

import com.kuang.dto.UsersDTO;
import com.kuang.model.Users;

import java.util.List;

/**
 * @author ：xxx
 * @date ：2022/8/3 下午9:46
 * @description：业务接口层
 * @version:
 */
public interface UsersService {
    /**
     * 查询所有用户
     * @return 所有信息
     */
    List<Users> queryUserList();

    /**
     * 根据id查询单个用户
     * @param userId 传入
     * @return 返回单个用户信息
     */
    Users queryUserById(Integer userId);

    /**
     * 添加用户信息
     * @param userDTO 修改参数
     * @return 成功返回 1 失败返回 0
     */
    Integer addUser(UsersDTO userDTO);

    /**
     * 修改一个用户
     * @param userDTO 修改参数
     * @return 成功返回 1 失败返回 0
     */
    Integer updateUser(UsersDTO userDTO);

    /**
     * 根据id删除一个用户
     * @param userId 删除id
     * @return 成功返回 1 失败返回 0
     */
    Integer deleteUser(Integer userId);
}