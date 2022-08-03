package com.kuang.service.serviceImpl;

import com.kuang.dto.UsersDTO;
import com.kuang.mapper.UsersMapper;
import com.kuang.model.Users;
import com.kuang.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：yangan
 * @date ：2022/8/3 下午9:27
 * @description：
 * @version:
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    UsersMapper usersMapper;

    @Override
    public List<Users> queryUserList() {
        return usersMapper.queryUserList();
    }

    @Override
    public Users queryUserById(Integer userId) {
        return usersMapper.queryUserById(userId);
    }

    @Override
    public Integer addUser(UsersDTO userDTO) {
        return usersMapper.addUser(userDTO.getId(), userDTO.getName(), userDTO.getPassword(), userDTO.getEmail());
    }

    @Override
    public Integer updateUser(UsersDTO userDTO) {
        return usersMapper.updateUser(userDTO.getId(), userDTO.getName(), userDTO.getPassword(), userDTO.getEmail());
    }

    @Override
    public Integer deleteUser(Integer userId) {
        return usersMapper.deleteUser(userId);
    }
}