package com.yczuoxin.nacos.init;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.yczuoxin.nacos.domain.NacosConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Component
public class InitConfiguration {

    @Autowired
    private NacosConfig config;

    @PostConstruct
    public String getConfig(){
        String content = "";
        try {
            String serverAddr = config.getServerAddr();
            String dataId = config.getDataId();
            String group = config.getGroup();
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            content = configService.getConfig(dataId, group, 5000);
            System.out.println(content);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return content;
    }
}
