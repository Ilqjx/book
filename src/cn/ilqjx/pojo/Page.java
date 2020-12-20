package cn.ilqjx.pojo;

import java.util.List;

/**
 * 分页的业务数据模型
 *
 * @param <T> 当前页数据的类型的泛型
 * @author upfly
 * @create 2020-12-12 10:14
 */
public class Page<T> {
    /**
     * 每页显示 4 条数据
     */
    public static final Integer PAGE_SIZE = 4;

    /**
     * 页码
     */
    private Integer pageNo;
    /**
     * 总页码
     */
    private Integer pageTotal;
    /**
     * 总记录数
     */
    private Integer pageTotalCount;
    /**
     * 每页显示数量
     */
    private Integer pageSize;
    /**
     * 当前页数据
     */
    private List<T> items;
    /**
     * 分页条的请求地址
     */
    private String url;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        // 数据边界的有效检查
        if (pageNo < 1) {
            pageNo = 1;
        } else if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }

        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", pageSize=" + pageSize +
                ", items=" + items +
                '}';
    }
}
