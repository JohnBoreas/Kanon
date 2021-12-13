package com.kanon.framework.system.entity;

import com.kanon.common.core.entity.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 系统响应时间统计记录（方便对响应时间长的进行优化）
 * @date 2018-10-25
 */
@Data
public class SystemCostTime extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 类名
     */
    private String className;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 消耗的时间
     */
    private Long spendTime;

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("className", getClassName())
                .append("methodName", getMethodName())
                .append("spendTime", getSpendTime())
                .toString();
    }
}
