package com.kuang.mapper;

import com.kuang.dto.UsersDTO;
import com.kuang.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersMapper{

    /**
     * 查询所有用户
     * @return 所有信息
     */
    List<Users> queryUserList();

    /**
     * 根据id查询单个用户
     * @param id 传入
     * @return 返回单个用户信息
     */
    Users queryUserById(int id);

    /**
     * 添加用户信息
     * @param id id
     * @param name name
     * @param password password
     * @param email email
     * @return 成功返回 1 失败返回 0
     */
    Integer addUser(Integer id, String name, String password, String email);

    /**
     * 修改一个用户
     * @param id id
     * @param name name
     * @param password password
     * @param email email
     * @return 成功返回 1 失败返回 0
     */
    Integer updateUser(Integer id, String name, String password, String email);

    /**
     * 根据id删除一个用户
     * @param id 删除id
     * @return 成功返回 1 失败返回 0
     */
    Integer deleteUser(int id);
}