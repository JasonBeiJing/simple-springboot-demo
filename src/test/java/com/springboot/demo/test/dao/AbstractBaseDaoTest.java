package com.springboot.demo.test.dao;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("test")//通过test来指定测试的数据源
public abstract class AbstractBaseDaoTest {
	
}
