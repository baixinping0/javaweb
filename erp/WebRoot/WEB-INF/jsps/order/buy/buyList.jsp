<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/Calendar.js"></script>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			//$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">进货订单管理</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="order_buyList" method="post"> 
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="30">订单状态</td>
						<td> 
							<s:select name="orderq.type" list="@com.bxp.erp.invoice.order.vo.OrderModel@buyTypeMap" headerKey="-1" headerValue="--请-选-择--"/>
						</td>
						<td>下单人:</td>
						<td>
						<s:textfield name="orderq.creater.name" size="10" />
						</td>
						<td>总量:</td>
						<td>
							<s:textfield name="orderq.totalNum" size="10" />
						</td>
						<td>到 </td>
						<td>&nbsp;&nbsp;<s:textfield name="orderq.totalNum1" size="10" /></td>
						<td><a id="query"> 
							<img src="images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
					<tr>
						<td>下单时间:</td>
						<td><input type="text" size="8"
							onfocus="c.showMoreDay=false;c.show(this);" readonly="true"
							value="${orderq.createTimeView}" /> <s:hidden name="orderq.createTime" />
						</td>
						<td>到</td>
						<td><input type="text" size="8"
							onfocus="c.showMoreDay=false;c.show(this);" readonly="true"
							value="${orderq.createTime1View}" /> <s:hidden name="orderq.createTime1" />
						</td>
						<td>总额:</td>
						<td><s:textfield name="orderq.totalPrice" size="10" /></td>
						<td>到</td>
						<td>&nbsp;&nbsp;<s:textfield name="orderq.totalPrice1" size="10" /></td>
						<td>
							<a href="order_buyInput">
								<img src="images/can_b_02.gif" border="0" /> 
							</a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">订单号</td>
						<td width="9%">供应商</td>
						<td width="10%">制单人</td>
						<td width="20%">制单时间</td>
						<td width="10%">订单商品总量</td>
						<td width="12%">订单总金额</td>
						<td width="5%">详情</td>
						<td width="9%">订单状态</td>
					</tr>
						
						<s:iterator value="orderList">
							<tr align="center" bgcolor="#FFFFFF">
							<td width="13%" height="30">${orderNum}</td>
							<td>${supplier.name}</td>
							<td>${creater.name}</td>
							<td>${createTimeView}</td>
							<td>${totalNum}</td>
							<td align="right">${totalPriceView} 元</td>
							<td>
								<s:a action="order_buyDetailList">详情
									<s:param name="order.uuid" value="uuid"/>
								</s:a>
							</td>
							<td>未审核</td>
							</tr>
						</s:iterator>
				</table>
			</div>
		</s:form>
	</div>
	<div class="content-bbg"></div>
</div>
