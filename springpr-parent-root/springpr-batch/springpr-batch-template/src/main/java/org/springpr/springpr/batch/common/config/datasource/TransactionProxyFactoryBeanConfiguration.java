/* (C)2024 */
package org.springpr.springpr.batch.common.config.datasource;

import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class TransactionProxyFactoryBeanConfiguration {

    //    @Bean
    //    TransactionProxyFactoryBean baseProxy(
    //            JobRepository jobRepository,
    //            @Qualifier("transactionManager") PlatformTransactionManager transactionManager) {
    //        TransactionProxyFactoryBean transactionProxyFactoryBean = new
    // TransactionProxyFactoryBean();
    //        Properties transactionAttributes = new Properties();
    //        transactionAttributes.setProperty("*", "PROPAGATION_REQUIRED");
    //        transactionProxyFactoryBean.setTransactionAttributes(transactionAttributes);
    //        transactionProxyFactoryBean.setTarget(jobRepository);
    //        transactionProxyFactoryBean.setTransactionManager(transactionManager);
    //        return transactionProxyFactoryBean;
    //    }
}
