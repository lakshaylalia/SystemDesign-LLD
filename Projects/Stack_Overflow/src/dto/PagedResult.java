package dto;

import java.util.List;

public class PagedResult<T> {
    private List<T> data;
    private int currentPage;
    private int pageSize;
    private long totalItems;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;

    public PagedResult(List<T> data, int currentPage, int pageSize, long totalItems) {
        this.data = data;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.totalPages = (int) Math.ceil((double) totalItems / pageSize);
        this.hasNext = currentPage < totalPages - 1;
        this.hasPrevious = currentPage > 0;
    }

    public List<T> getData() {
        return data;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }
}