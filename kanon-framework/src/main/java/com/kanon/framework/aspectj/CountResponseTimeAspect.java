package com.kanon.framework.aspectj;


import com.kanon.common.utils.TimeUuidUtil;
import com.kanon.framework.manager.AsyncManager;
import com.kanon.framework.manager.factory.AsyncFactory;
import com.kanon.framework.system.entity.SystemCostTime;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 计算统计服务器响应时间
 *
 * @author lws
 */
@Aspect
@Component
public class CountResponseTimeAspect {

    public static final int CONDITION_TIME = 500;
    ThreadLocal<Long> startTime = new ThreadLocal<>();
    String className = null;
    String methodName = null;

    // 配置织入点
    //第一个*代表任意返回值；controller..代表controller包下及子包下；第二个*代表所有类；*(..)代表任意参数的所有方法
    @Pointcut("execution(* com.kanon..controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        className = joinPoint.getTarget().getClass().getName();
        methodName = joinPoint.getSignature().getName() + "()";
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        long spendTime = System.currentTimeMillis() - startTime.get();
        if (spendTime > CONDITION_TIME) {
            SystemCostTime systemCostTime = new SystemCostTime();
            systemCostTime.setId(TimeUuidUtil.get16UUID());
            systemCostTime.setClassName(className);
            systemCostTime.setMethodName(methodName);
            systemCostTime.setSpendTime(spendTime);
            AsyncManager.me().execute(AsyncFactory.recordCostTime(systemCostTime));
        }
    }
}
