package com.kuang.mapper;

import com.kuang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper//添加了这个注解，表示了这是mybatis的mapper类
@Repository//说明被Spring整合了
public interface UserMapper {
//    查询所有用户
    List<User> queryUserList();
//    查询单个用户
    User queryUserById(int id);
//    添加一个用户
    int addUser(User user);
//    修改一个用户
    int UpdateUser(User user);
//    删除一个用户
    int deleteUser(int id);
}
