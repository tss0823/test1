package com.zhushou.test1.service;

import com.zhushou.test1.dal.config.ApplicationConfig;
import com.zhushou.test1.dal.config.DalConfig;
import com.zhushou.test1.service.config.ServiceConfig;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@TransactionConfiguration(defaultRollback = false)
@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = {DalConfig.class, ServiceConfig.class, ApplicationConfig.class})
public class BaseServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    public Logger logger = LoggerFactory.getLogger(this.getClass());
}
