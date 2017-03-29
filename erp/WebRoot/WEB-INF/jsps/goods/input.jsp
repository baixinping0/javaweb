<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/Calendar.js"></script>
<script type="text/javascript">
	/* $(function() {
		$("#all").click(
				function() {
					$("[name=roles]:checkbox").attr("checked",
							$("#all").attr("checked") == "checked");
				});
		$("#reverse").click(function() {
			$("[name=roles]:checkbox").each(function() {
				$(this).attr("checked", !$(this).attr("checked"));
			});
		});
		$("#supplier")
				.change(
						function() {
							$
									.post(
											"goodsTypeAction_getAll.action",
											{
												"gm.supplier.uuid" : $(this)
														.val()
											},
											function(data) {
												$("#goodsType").empty();
												for (var i = 0; i < data.gtList.length; i++) {
													var goodsType = data.gtList[i];
													var $option = $("<option value='"+goodsType.uuid+"'>"
															+ goodsType.goodsTypeName
															+ "</option>"); //创建option对象(jQuery格式)
													$("#goodsType").append(
															$option); //将option对象添加到select组件中
												}
											});
						});
	}); */
	$(function(){
		/*对供应商的数据改变进行监听
			将供应商的uuid发送到后台
			struts对请求进行处理，处理后将数据进行返回
			
			发送ajax请求：
				参数1：URL 	String
				参数1：发送请求参数 JSON
				参数1：请求完毕后执行的内容	function（data：数据， status：jquery状态）
		*/
		
		
		$("#supplier").change(function(){
			var supplierUuid = $(this).val();
			$.post("goodsType_ajaxGetBySupplierUuid", {"goodsType.supplier.uuid":supplierUuid}, function(data){
				//将原有数据清空
				$("#goodsType").empty();
				var goodsTypeList = data.goodsTypeList;
				for(var i = 0; i < goodsTypeList.length; i++){
					var goodsType = goodsTypeList[i];
					$option = $("<option value='"+goodsType.uuid+"'>"+goodsType.name+"</option>");
					$("#goodsType").append($option);
				}
			});
		});
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<s:form action="goods_save" method="post">
			<s:hidden name="goods.uuid"/>
				<div style="border:1px solid #cecece;">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr bgcolor="#FFFFFF">
							<td>&nbsp;</td>
						</tr>
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr bgcolor="#FFFFFF">
							<td width="18%" height="30" align="center">供&nbsp;应&nbsp;商</td>
							<td width="32%"><s:select id="supplier" cssStyle="width:190px"
									list="supplierList" listKey="uuid" listValue="name" /></td>
							<td width="18%" align="center">商品类别</td>
							<td width="32%"><s:select id="goodsType" name="goods.goodsType.uuid"
									cssStyle="width:190px" list="goodsTypeList" listKey="uuid"
									listValue="name" /></td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td align="center">商品名称</td>
							<td><s:textfield name="goods.name" size="25" /></td>
							<td align="center">产&nbsp;&nbsp;&nbsp;&nbsp;地</td>
							<td><s:textfield name="goods.origin" size="25" /></td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td height="30" align="center">生产厂家</td>
							<td><s:textfield name="goods.producer" />
							<td align="center">单&nbsp;&nbsp;&nbsp;&nbsp;位</td>
							<td><s:textfield name="goods.unit" size="25" /></td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td height="30" align="center">进货单价</td>
							<td><s:textfield name="goods.inPrice" size="25" /></td>
							<td align="center">销售单价</td>
							<td><s:textfield name="goods.outPrice" size="25" /></td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td height="30" align="center">体&nbsp;&nbsp;&nbsp;&nbsp;积</td>
							<td><input type="text" size="25" /></td>
							<td align="center">&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td colspan="4">&nbsp;</td>
						</tr>
					</table>
				</div>
				<div class="order-botton">
					<div style="margin:1px auto auto 1px;">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td><a href="javascript:document.forms[0].submit()"><img
										src="images/order_tuo.gif" border="0" /></a></td>
								<td>&nbsp;</td>
								<td><a href="#"><img src="images/order_tuo.gif"
										border="0" /></a></td>
								<td>&nbsp;</td>
								<td><a href="#"><img src="images/order_tuo.gif"
										border="0" /></a></td>
							</tr>
						</table>
					</div>
				</div>
			</s:form>
		</div>
		<!--"square-order"end-->
	</div>
	<!--"content-text"end-->
	<div class="content-bbg">
		<img src="images/content_bbg.jpg" />
	</div>
</div>
