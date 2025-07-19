package cn.helloallen.domain.salary.model.valobj;

import lombok.Getter;

/** 值对象
 * 雇员职位：将雇员表（编号、姓名、级别、称呼）中的级别和称呼字段封装为值对象。
 * 当一个实体对象中的一个值，是有多个范围时候，则需要定义出值对象。
 * 由于此类的值对象更贴近于当前的场景业务，所以一般不会被定义为共用的枚举。
 * 如此此类值范围，都会被定义为值对象
 */

/**
 * @Getter相当于
 *     public String getCode() {
 *         return code;
 *     }
 *
 *     public String getDesc() {
 *         return desc;
 *     }
 */
@Getter
public enum EmployeePostVO {
    T1("T-1", "初级工程师"),
    T2("T-2", "初级工程师"),
    T3("T-3", "中级工程师"),
    T4("T-4", "中级工程师"),
    T5("T-5", "高级工程师"),
    T6("T-6", "高级工程师"),
    T7("T-7", "架构师"),
    T8("T-8", "架构师");

    private final String code;
    private final String desc;

    EmployeePostVO(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
