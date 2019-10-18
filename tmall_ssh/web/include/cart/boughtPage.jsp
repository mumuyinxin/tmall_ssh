<%@ page language="java" contentType="text/html;charset=UTF-8"	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<script>
	var deleteOrder = false;
	var deleteOrderid = 0;

	$(function(){
		$("a[orderStatus]").click(function(){
			var orderStatus = $(this).attr("orderStatus");
			if('all'==orderStatus){
				$("table[orderStatus]").show();
			}
			else{
				$("table[orderStatus]").hide();
				$("table[orderStatus="+orderStatus+"]").show();
			}

			$("div.orderType div").removeClass("selectedOrderType");
			$(this).parent("div").addClass("selectedOrderType");
		});

		$("a.deleteOrderLink").click(function(){
			deleteOrderid = $(this).attr("oid");
			deleteOrder = false;
			$("#deleteConfirmModal").modal("show");
		});

		$("button.deleteConfirmButton").click(function(){
			deleteOrder = true;
			$("#deleteConfirmModal").modal('hide');
		});

		// $('#deleteConfirmModal').on('hidden.bs.modal', function (e) {
		// 	if(deleteOrder){
		// 		var page="order/delete_Order.action";
		// 		$.post(
		// 				page,
		// 				{"order.id":deleteOrderid},
		// 				function(result){
		// 					if("success"==result){
		// 						$("table.orderListItemTable[oid="+deleteOrderid+"]").hide();
		// 					}
		// 					else{
		// 						location.href="login.jsp";
		// 					}
		// 				}
		// 			);
		// 	}
		// });

		$(".ask2delivery").click(function(){
			var link = $(this).attr("link");
			$(this).hide();
			page = link;
			$.ajax({
				   url: page,
				   success: function(result){
					alert("卖家已秒发，刷新当前页面，即可进行确认收货")
				   }
				});

		});
	});
</script>
	
<div class="boughtDiv">
	<div class="orderType">
		<div class="selectedOrderType"><a orderStatus="all" href="frontBought_Front.action">所有订单</a></div>
		<div><a  orderStatus="待付款" href="#" onclick="return false;">待付款</a></div>
		<div><a  orderStatus="待发货" href="#" onclick="return false;">待发货</a></div>
		<div><a  orderStatus="待收货" href="#" onclick="return false;">待收货</a></div>
		<div><a  orderStatus="待评价" href="#" onclick="return false;" class="noRightborder">待评价</a></div>
		<div class="orderTypeLastOne"><a class="noRightborder">&nbsp;</a></div>
	</div>
	<div style="clear:both"></div>
	<div class="orderListTitle">
		<table class="orderListTitleTable">
			<tr>
				<td>宝贝</td>
				<td width="100px">单价</td>
				<td width="100px">数量</td>
				<td width="120px">实付款</td>
				<td width="100px">交易操作</td>
			</tr>
		</table>
	</div>
	
	<div class="orderListItem">
		<c:forEach items="${orderList}" var="o">
			<table class="orderListItemTable" orderStatus="${o.status}" oid="${o.id}">
				<tr class="orderListItemFirstTR">
					<td colspan="2">
					<b><fmt:formatDate value="${o.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></b> 
					<span>订单号: ${o.orderCode} 
					</span>
					</td>
					<td  colspan="2"><img width="13px" src="img/site/orderItemTmall.png">天猫商场</td>
					<td colspan="1">
						<a class="wangwanglink" href="#nowhere">
							<div class="orderItemWangWangGif"></div>
						</a>
					</td>
					<td class="orderItemDeleteTD">
						<a class="deleteOrderLink" oid="${o.id}" href="order/delete_Order.action?order.id=${o.id}">
							<span  class="orderListItemDelete glyphicon glyphicon-trash"></span>
						</a>
					</td>
				</tr>
				<c:forEach items="${o.orderItems}" var="oi" varStatus="st">
					<tr class="orderItemProductInfoPartTR" >
						<td class="orderItemProductInfoPartTD"><img width="80" height="80" src="img/productSingle_middle/${oi.product.firstImage.id}.jpg"></td>
						<td class="orderItemProductInfoPartTD">
							<div class="orderListItemProductLinkOutDiv">
								<a href="frontProduct_Front.action?product.id=${oi.product.id}">${oi.product.name}</a>
								<div class="orderListItemProductLinkInnerDiv">
									<img src="img/site/creditcard.png" title="支持信用卡支付">
									<img src="img/site/7day.png" title="消费者保障服务,承诺7天退货">
									<img src="img/site/promise.png" title="消费者保障服务,承诺如实描述">
								</div>
							</div>
						</td>
						<td  class="orderItemProductInfoPartTD" width="100px">
							<div class="orderListItemProductOriginalPrice">￥<fmt:formatNumber type="number" value="${oi.product.originalPrice}" minFractionDigits="2"/></div>
							<div class="orderListItemProductPrice">￥<fmt:formatNumber type="number" value="${oi.product.promotePrice}" minFractionDigits="2"/></div>
						</td>
						<c:if test="${st.count==1}">
							<td valign="top" rowspan="${fn:length(o.orderItems)}" class="orderListItemNumberTD orderItemOrderInfoPartTD" width="100px">
								<span class="orderListItemNumber">${o.totalNumber}</span>
							</td>
							<td valign="top" rowspan="${fn:length(o.orderItems)}" width="120px" class="orderListItemProductRealPriceTD orderItemOrderInfoPartTD">
								<div class="orderListItemProductRealPrice">￥<fmt:formatNumber  minFractionDigits="2"  maxFractionDigits="2" type="number" value="${o.totalPrice}"/></div>
								<div class="orderListItemPriceWithTransport">(含运费：￥0.00)</div>
							</td>
							<td valign="top" rowspan="${fn:length(o.orderItems)}" class="orderListItemButtonTD orderItemOrderInfoPartTD" width="100px">
							<c:if test="${o.status=='待付款' }">
								<a href="frontPay_Front.action?order.id=${o.id}&order.totalPrice=${o.totalPrice}">
									<button class="orderListItemConfirm">付款</button>
								</a>
							</c:if>
							<c:if test="${o.status=='待发货' }">
								<span>待发货</span>
								<button class="btn btn-info btn-sm ask2delivery" link="frontOrderDelivery_Front.action?order.id=${o.id}">催卖家发货</button>
							</c:if>
							<c:if test="${o.status=='待收货' }">
								<a href="frontConfirmPay_Front.action?order.id=${o.id}">
									<button class="orderListItemConfirm">确认收货</button>
								</a>
							</c:if>
							<c:if test="${o.status=='待评价' }">
								<a href="frontReview_Front.action?order.id=${o.id}">
									<button class="orderListItemReview">评价</button>
								</a>
							</c:if>
							<c:if test="${o.status=='完成' }">
								<span>完成</span>
							</c:if>
							</td>						
						</c:if>
					</tr>
				</c:forEach>
			</table>
		</c:forEach>
	</div>
</div>