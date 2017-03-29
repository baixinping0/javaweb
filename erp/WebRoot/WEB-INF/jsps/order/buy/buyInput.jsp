<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
</script>
<script type="text/javascript">
	
	$(function(){
		//修改供应商联动
		$("#supplier").change(function(){
			var supplierUuid = $(this).val();
			$.post("order_ajaxGetGoodsTypeBySupplierUuid", {"supplierUuid":supplierUuid}, function(data){
				
				//将原有数据清空
				$(".goodsType").empty();
				$(".goods").empty();
				
				//填充商品类别信息
				var goodsTypeList = data.goodsTypeList;
				for(var i = 0; i < goodsTypeList.length; i++){
					var goodsType = goodsTypeList[i];
					var $option = $("<option value='"+goodsType.uuid+"' >"+goodsType.name+"</option>")
					$(".goodsType").append($option);
				}
				
				//填充商品信息
				var goodsList = data.goodsList;
				for(var i = 0; i < goodsList.length; i++){
					var goods = goodsList[i];
					var $option = $("<option value='"+goods.uuid+"' >"+goods.name+"</option>")
					$(".goods").append($option);
				}
				
				$(".num").val(1);
				
				var goods = data.goods;
				$(".prices").val(goods.inPrice);
				$(".total").html(goods.inPrice);
				
			});
		});
		
		
		//修改商品类别联动
		//$(".goodsType").change(function(){
		$(".goodsType").live("change", function(){
			//使用相对获取，获取控件
			var $tr = $(this).parent().parent();
			var $goods = $tr.children("td:eq(1)").children("select");
			var $num = $tr.children("td:eq(2)").children("input");
			var $price = $tr.children("td:eq(3)").children("input");
			var $total = $tr.children("td:eq(4)");
			
			var goodsArr = $(".goods");
			var alreadyUseGoodsUuids = "";
			for(var i = 0; i < goodsArr.length; i++){
				alreadyUseGoodsUuids += "'" + goodsArr[i].value + "',"
			}
			
			var goodsTypeUuid = $(this).val();
			$.post("order_ajaxGetGoodsByGoodsType",{"goodsTypeUuid":goodsTypeUuid, "alreadyUseGoodsUuids":alreadyUseGoodsUuids}, function(data){
				
				//清空原有数据
				$goods.empty();
				var goodsList = data.goodsList;
				for(var i = 0; i < goodsList.length; i++){
					var goods = goodsList[i];
					var $option = $("<option value='"+goods.uuid+"' >"+goods.name+"</option>")
					$goods.append($option);
				}
				
				var goods = data.goods;
				$num.val(1);
				$price.val(goods.inPrice);
				$total.html(goods.inPrice);
			});
		});
		
		//修改商品联动
		//$(".goods").change(function(){
		$(".goods").live("change", function(){
		
			var $tr = $(this).parent().parent();
			var $num = $tr.children("td:eq(2)").children("input");
			var $price = $tr.children("td:eq(3)").children("input");
			var $total = $tr.children("td:eq(4)");
		
			var goodsUuid = $(this).val();
			$.post("order_ajaxGetGoodsByGoodsUuid",{"goodsUuid":goodsUuid},function(data){
				var goods = data.goods;
				$num.val(1);
				$price.val(goods.inPrice);
				$total.html(goods.inPrice);
			});
		});
		
		
		var addFlag = true;
		//点击新建，创建新的一行
		$("#add").click(function(){
		
			//设置供应商不能被改变
			$("#supplier").attr("disabled", true);
			//设置已经添加的订单项不能被改变
			$(".goodsType").attr("disabled", true);
			$(".goods").attr("disabled", true);
			
			
			/*
			为避免网络延迟多次发送请求，使用标志位对请求进行控制
			*/
			if(!addFlag){
				return;
			}
			addFlag = false;
			/*
			需求：动态生成行，添加到指定的位置。
			 1、需要动态生成数据，ajax
			 2、添加到的位置为最后一行（总计 id = finalTr）的上方
			 
			 发送请求获取数据的时候，需要将已经选择过的商品传递到后台对于数据进行过滤
			*/
			var goodsArr = $(".goods");
			var alreadyUseGoodsUuids = "";
			for(var i = 0; i < goodsArr.length; i++){
				alreadyUseGoodsUuids += "'" + goodsArr[i].value + "',"
			}
			
			var supplierUuid = $("#supplier").val();
			$.post("order_ajaxGetGoodsTypeBySupplierUuid2", {"supplierUuid":supplierUuid, "alreadyUseGoodsUuids":alreadyUseGoodsUuids}, function(data){
				
				//在此处进行判断，如果此时商品和商品类型均已经使用完毕(商品数量和商品类型的数量均为1)
				//此时应该控制点击事件,将新建按钮隐藏
				if(data.goodsTypeList.length == 1 && data.goodsList.length == 1){
					$("#add").css("display", "none");
				}
				
				//商品类别
				var goodsTypeList = data.goodsTypeList;
				var $tr = $('<tr align="center" bgcolor="#FFFFFF"></tr>');
				var $td1 = $('<td height="30"></td>');
				var $gtmSelect = $('<select name="" class="goodsType" style="width:200px"></select>');
				for(var i = 0; i < goodsTypeList.length; i++){
					var goodsType = goodsTypeList[i];
					var $option = $('<option value="'+goodsType.uuid+'">'+goodsType.name+'</option>');
					$gtmSelect.append($option);
				}
				$td1.append($gtmSelect);
				$tr.append($td1);
				$("#finalTr").before($tr);
				
				//商品
				var goodsList = data.goodsList;
				var $td2 = $('<td height="30"></td>');
				var $gtSelect = $('<select name="goodsUuids" class="goods" style="width:200px"></select>');
				for(var i = 0; i < goodsList.length; i++){
					var goods = goodsList[i];
					var $option = $(' <option value="'+goods.uuid+'">'+goods.name+'</option>');
					$gtSelect.append($option);
				}
				$td2.append($gtSelect);
				$tr.append($td2);
				
				//数量
				var $td3 = $('<td><input name="nums" class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px" value="1" type="text"></td>');
				$tr.append($td3);
				
				//单价
				var goods = data.goods;
				var $td4 = $('<td><input name="prices" class="prices order_num" style="width:93px;border:1px solid black;text-align:right;padding:2px" value="'+goods.inPrice+'" type="text"> 元</td>');
				$tr.append($td4);
				
				//合计
				var $td5 = $('<td class="total" align="right">'+goods.inPrice+'&nbsp;元</td>');
				$tr.append($td5);
				
				var $td6 = $('<td><a class="deleteBtn delete xiu" value="4"><img src="images/icon_04.gif"> 删除</a></td>');
				$tr.append($td6);
				
				$("#finalTr").before($tr);
				
				//修改标志位
				addFlag = true;
				
				allPrice();
			});
		});
		
		
		//点击删除事件
		//$(".deleteBtn").click(function(){
		$(".deleteBtn").live("click",function(){
			//如果此时只有一条数据，则不能进行删除
			if($(".deleteBtn").length == 1)
				return;
			$(this).parent().parent().remove();
			//将新建按钮显示
			$("#add").css("display", "inline");
		});
		
		//修改商品数量绑定事件
		$(".num").live("keyup", function(){
			requestInputNumber(this);
			//获取tr
			var $tr = $(this).parent().parent();
			//获取数量控件
			var $num = $(this);
			//获取单价控件
			var $price = $tr.children("td:eq(3)").children("input");
			//获取合计控件
			var $total = $tr.children("td:eq(4)");
			//获取总计价格
			var total = $num.val() * $price.val();
			//赋值
			$total.html(intToFloat(total));
			
			allPrice();
		});
		
		//修改单价绑定事件
		$(".prices").live("keyup", function(){
			requestInputNumber(this);
			//获取tr
			var $tr = $(this).parent().parent();
			//获取单价控件
			var $price = $(this);
			//获取数量控件
			var $num = $tr.children("td:eq(2)").children("input");
			//获取合计控件
			var $total = $tr.children("td:eq(4)");
			//获取总计价格
			var total = $num.val() * $price.val();
			//赋值
			$total.html(intToFloat(total));
			
			allPrice();
		});
		
		function allPrice(){
			//计算合计
			//获取所有数量
			var $nums = $(".num");
			//获取所有的价格
			var $prices = $(".prices");
			var total = 0;
			for(var i = 0; i < $nums.length; i++){
				total += $nums[i].value * $prices[i].value;
			}
			$(".all").html(intToFloat(total));
		}
		
		//保留两位小数
		function intToFloat(val){
			return new Number(val).toFixed(2);
		}
		
		//只能输入数字
		function requestInputNumber(obj){
			$(obj).val($(obj).val().replace(/[^\d.]/g,""));
			$(obj).val($(obj).val().replace(/^\./g,"0."));
			$(obj).val($(obj).val().replace(/\.{2,}/g,"."));
			$(obj).val($(obj).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
			
		}
		
		//保存按钮点击事件
		$("#submitOrder").click(function(){
			$("#supplier").attr("disabled", false);
			$(".goods").attr("disabled", false);
			$("form:first").submit();
		});
	});
	
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">订单管理</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="order_buySave" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="24">&nbsp;</td>
					</tr>
					<tr>
						<td width="68px" height="30">供应商：</td>
						<td width="648px"><s:select name="order.supplier.uuid" id="supplier" list="supplierList"
								listKey="uuid" listValue="name" cssClass="kuan"
								cssStyle="width:190px" /></td>
						<td height="30"><a id="add"><img
								src="images/can_b_02.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table id="order" width="100%" border="1" cellpadding="0"
					cellspacing="0">
					<tr align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">商品类别</td>
						<td width="25%">商品名称</td>
						<td width="10%">采购数量</td>
						<td width="15%">单价</td>
						<td width="15%">合计</td>
						<td width="10%">操作</td>
					</tr>
					<tr align="center" bgcolor="#FFFFFF">
						<td height="30">
						<s:select cssClass="goodsType" cssStyle="width:200px"
								list="goodsTypeList" listKey="uuid" listValue="name" /></td>
						<td>
						<s:select name="goodsUuids" cssClass="goods" list="goodsList" cssStyle="width:200px"
								listKey="uuid" listValue="name" /></td>
						<td>
						<input name="nums" class="num order_num"
							style="width:67px;border:1px solid black;text-align:right;padding:2px"
							type="text" value="1" /></td>
						<td>
						<input name="prices" class="prices order_num"
							style="width:93px;border:1px solid black;text-align:right;padding:2px"
							type="text" value="100" /> 元</td>
						<td class="total" align="right">${goodsList[0].inPrice}&nbsp;元</td>
						<td><a class="deleteBtn delete xiu" value="4"><img
								src="images/icon_04.gif" /> 删除</a></td>
					</tr>
					<tr id="finalTr" align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td height="30" colspan="4" align="right">总&nbsp;计:&nbsp;</td>
						<td class="all" width="16%" align="right">${goodsList[0].inPrice}&nbsp;元</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<div class="order-botton">
					<div style="margin:1px auto auto 1px;">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td><a href="javascript:void(0)" id="submitOrder"><img
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
			</div>
		</s:form>
	</div>

	<div class="content-bbg"></div>
</div>
