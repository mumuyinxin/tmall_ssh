<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    $(function () {
        $("ul.pagination li.disabled a").click(function () {
            return false;
        });
    });

</script>

<nav>
    <ul class="pagination">
        <li <c:if test="${!page.isHasPreviouse()}">class="disabled"</c:if>>
            <a href="?page.start=0&${page.param}" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>

        <li <c:if test="${!page.isHasPreviouse()}">class="disabled"</c:if>>
            <a href="?page.start=${page.start-page.count}&${page.param}" aria-label="Previous">
                <span aria-hidden="true">&lsaquo;</span>
            </a>
        </li>

        <c:forEach begin="0" end="${page.getTotalPage()-1}" varStatus="status">
            <c:if test="${status.count*page.count-page.start<=20 && status.count*page.count-page.start>=-10}">
                <li <c:if test="${status.index*page.count==page.start}">class="disabled"</c:if>>
                    <a href="?page.start=${status.index*page.count}&${page.param}"
                            <c:if test="${status.index*page.count==page.start}">class="current"</c:if>
                    >${status.count}</a>

                </li>
            </c:if>
        </c:forEach>

        <li <c:if test="${!page.isHasNext()}">class="disabled"</c:if>>
            <a href="?page.start=${page.start+page.count}&${page.param}" aria-label="Next">
                <span aria-hidden="true">&rsaquo;</span>
            </a>
        </li>

        <li <c:if test="${!page.isHasNext()}">class="disabled"</c:if>>
            <a href="?page.start=${page.getLast()}" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>

    </ul>
</nav>
