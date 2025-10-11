/* (C)2023 */
package dev.springpr.springpr.base.logging;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class SpringPrApplicationMdcAspect extends ApplicationMDCAspect {

    public SpringPrApplicationMdcAspect(Log4jMDCSetter log4jMdcSetter) {
        super(log4jMdcSetter);
    }
}
