package com.kuang.mapper;

import com.kuang.pojo.UStudent;
import com.kuang.pojo.UStudent2;
import com.kuang.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository//说明被Spring整合了
public interface UsersMapper{
//    查询所有用户
    List<Users> queryUserList();
//    查询单个用户
    Users queryUserById(int id);
//    添加一个用户
    int addUser(Users user);
//    修改一个用户
    int UpdateUser(Users user);
//    删除一个用户
    int deleteUser(int id);

    @Select("SELECT u.id uid,u.name uname,u.password upassword,u.email uemail,s.sid,s.age sage,s.name sname,s.arddss sarddss from users u join student s on u.id=s.sid")
    List<UStudent> findUsersAndStudent();
    @Select(
            "   select count(s.sid) studentCount,count(u.id) userCount from users u join student s on u.id=s.sid "
                    + "   where s.name like concat('%',#{sname},'%') and u.name like concat('%',#{uname},'%') and 1=1")
    List<UStudent2> findCount(String sname, String uname);

    @Select("<script>" +
            "   select u.id uid,u.name uname,u.password upassword,u.email uemail,s.sid,s.age sage,s.name sname,s.arddss sarddss from users u join student s on u.id=s.sid" +
            "   <where>" +
            "       <if test = 'ssname != null and ssname.size() != 0'>" +
            "           and s.name in" +
            "           <foreach item='name' collection='ssname' open='(' separator=',' close=')'>" +
            "                #{name}" +
            "           </foreach>" +
            "      </if>" +
            "       <if test = 'uuname != null and uuname.size() != 0'>" +
            "           and u.name in" +
            "           <foreach item='name' collection='uuname' open='(' separator=',' close=')'>" +
            "                #{name}" +
            "           </foreach>" +
            "      </if>" +
            "      and 1=1" +
            "   </where>" +
            "</script>")
    List<UStudent> findCount2(List<String> ssname, List<String> uuname);
}