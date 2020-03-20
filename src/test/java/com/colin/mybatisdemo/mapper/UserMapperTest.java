package com.colin.mybatisdemo.mapper;


import com.colin.mybatisdemo.entiry.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class UserMapperTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void testBefore() throws IOException {
        //--------------------第一阶段---------------------------
        // 1.读取mybatis配置文件创SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 1.读取mybatis配置文件创SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();
    }

    @Test
    public void testGetOneById(){
        //--------------------第二阶段---------------------------
        // 2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获取对应mapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //--------------------第三阶段---------------------------
        // 4.执行查询语句并返回单条数据
        User user = userMapper.getOneById(1);
        System.out.println(user.getUserName() + ":" + user.getMobile());
    }
}
