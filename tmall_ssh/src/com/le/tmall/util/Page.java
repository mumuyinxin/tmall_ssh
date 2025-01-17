package com.le.tmall.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Page<T> {
    int start;
    int count;
    int total;
    String param = "";
    List<T> list;

    public boolean isHasPreviouse() {
        return start != 0;

    }

    public boolean isHasNext() {
        return start != getLast();
    }

    public int getTotalPage() {
        int totalPage;
        // 假设总数是50，是能够被5整除的，那么就有10页
        if (0 == total % count)
            totalPage = total / count;
            // 假设总数是51，不能够被5整除的，那么就有11页
        else
            totalPage = total / count + 1;

        if (0 == totalPage)
            totalPage = 1;
        return totalPage;

    }

    public int getLast() {
        int last;
        // 假设总数是50，是能够被5整除的，那么最后一页的开始就是45
        if (0 == total % count)
            last = total - count;
            // 假设总数是51，不能够被5整除的，那么最后一页的开始就是50
        else
            last = total - total % count;

        last = last < 0 ? 0 : last;
        return last;
    }
}
