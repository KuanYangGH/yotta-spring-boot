package com.xjtu.common.domain;

/**
 * 枚举管理所有失败原因
 * Created by yuanhao on 2017/8/9.
 */
public enum ResultEnum {
    SUCCESS(200, "成功"),
    UNKONW_ERROR(-1, "未知错误"),

    // 数据源
    SOURCE_INSERT_ERROR(100, "数据源插入失败：数据源名不存在或者为空"),
    SOURCE_INSERT_ERROR_1(101, "数据源插入失败：插入已经存在的数据源"),
    SOURCE_INSERT_ERROR_2(102, "数据源插入失败：数据库插入语句失败"),
    SOURCE_DELETE_ERROR(103, "数据源删除失败"),
    SOURCE_UPDATE_ERROR(104, "数据源更新失败"),
    SOURCE_SEARCH_ERROR(105, "数据源查询失败：没有数据源记录"),
    SOURCE_SEARCH_ERROR_1(106, "数据源分页查询失败：没有数据源记录"),
    SOURCE_SEARCH_ERROR_2(107, "数据源分页查询失败：查询的页数超过最大页数"),

    //学科
    SUBJECT_INSERT_ERROR(108, "学科信息插入失败：学科名不存在或者为空"),
    SUBJECT_INSERT_ERROR_1(109, "学科信息插入失败：插入已经存在的学科"),
    SUBJECT_INSERT_ERROR_2(110, "学科信息插入失败：数据库插入语句失败"),
    SUBJECT_DELETE_ERROR(111, "学科删除失败"),
    SUBJECT_UPDATE_ERROR(112, "学科更新失败"),
    SUBJECT_SEARCH_ERROR(113, "学科查询失败：没有学科信息记录"),
    SUBJECT_SEARCH_ERROR_1(114, "学科分页查询失败：没有该条学科信息记录"),
    SUBJECT_SEARCH_ERROR_2(115, "学科分页查询失败：查询的页数超过最大页数"),

    //课程
    Domain_INSERT_ERROR(116, "课程信息插入失败：课程名不存在或者为空"),
    Domain_INSERT_ERROR_1(117, "课程信息插入失败：插入已经存在的课程"),
    Domain_INSERT_ERROR_2(118, "课程信息插入失败：数据库插入语句失败"),
    Domain_DELETE_ERROR(119, "课程删除失败"),
    Domain_UPDATE_ERROR(120, "课程更新失败：更新语句执行失败"),
    Domain_UPDATE_ERROR_1(121, "课程更新失败：课程名不存在或者为空"),
    Domain_SEARCH_ERROR(122, "课程查询失败：没有课程信息记录"),
    Domain_SEARCH_ERROR_1(123, "课程分页查询失败：没有课程信息记录"),
    Domain_SEARCH_ERROR_2(124, "课程分页查询失败：查询的页数超过最大页数"),

    //主题
    TOPIC_INSERT_ERROR(125, "主题信息插入失败：主题名不存在或者为空"),
    TOPIC_INSERT_ERROR_1(126, "主题信息插入失败：插入已经存在的主题"),
    TOPIC_INSERT_ERROR_2(127, "主题信息插入失败：数据库插入语句失败"),
    TOPIC_DELETE_ERROR(128, "主题删除失败"),
    TOPIC_DELETE_ERROR_1(135, "主题删除失败:没有主题对应的课程"),
    TOPIC_DELETE_ERROR_2(136, "主题删除失败:主题数据不存在"),
    TOPIC_UPDATE_ERROR(129, "主题更新失败：更新语句执行失败"),
    TOPIC_UPDATE_ERROR_1(130, "主题更新失败：主题名不存在或者为空"),
    TOPIC_UPDATE_ERROR_2(131, "主题更新失败：课程不存在"),
    TOPIC_UPDATE_ERROR_3(137, "主题更新失败：原主题不存在"),
    TOPIC_SEARCH_ERROR(132, "主题查询失败：没有主题信息记录"),
    TOPIC_SEARCH_ERROR_1(133, "主题查询失败：课程下没有主题信息记录"),
    TOPIC_SEARCH_ERROR_2(134, "主题分页查询失败：查询的页数超过最大页数"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
