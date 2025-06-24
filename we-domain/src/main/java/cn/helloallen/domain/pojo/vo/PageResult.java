package cn.helloallen.domain.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    /**
     * 页码
     */
    @Schema(description = "页码")
    public Long pageIndex;

    /**
     * 每页显示数量
     */
    @Schema(description = "每页显示数量")
    public Long pageSize;

    /**
     * 总条数
     */
    @Schema(description = "总条数")
    public Long totalRecords;

    /**
     * 总页数
     */
    @Schema(description = "总页数")
    public Long totalPages;

    /**
     * 查询结果集
     */
    @Schema(description = "查询结果集")
    public List<T> records;

    public PageResult(long totalRecords, long pageIndex, long pageSize, List<T> records) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalRecords = totalRecords;
        this.records = records;
        this.totalPages = (totalRecords + pageIndex - 1) / pageSize;
    }

    public PageResult(long totalRecords,long totalPages, long pageIndex, long pageSize, List<T> records) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalRecords = totalRecords;
        this.records = records;
        this.totalPages = totalPages;
    }

}
