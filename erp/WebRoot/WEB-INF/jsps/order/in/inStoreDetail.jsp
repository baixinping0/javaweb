<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	
	$(function(){
		
		var storeUuids = new Array();
		var storeNames = new Array();
		var i = 0;
		<s:iterator value="storeList">
			storeUuids[i] = ${uuid};
			storeNames[i] = "${name}";
			i++;
		</s:iterator>
		
		$(".oper").click(function(){
			$nowTr = $(this).parent().parent();
			var orderDetailUuid = $(this).attr("orderDetailUuid");
			//发送ajax请求获取剩余数量
			$.post("orderDetail_ajaxGetSurplus",{"orderDetail.uuid":orderDetailUuid},function(data){
				
				$(".in").remove();
				
				$tr = $('<tr class="in"></tr>');
				
				$td1 = $('<td align="right">仓库：</td>');
				$tr.append($td1);
				
				$td2 = $('<td height="30"></td>');
				$select = $('<select id="store" style="width:200px"></select>');
				for(var i = 0; i < storeUuids.length; i++){
					$option = $('<option id="store" value='+storeUuids[i]+' >'+storeNames[i]+'</option>');
					$select.append($option);
				}
				$td2.append($select);
				$tr.append($td2);
				
				$td3 = $('<td align="right">入库量：</td>');
				$tr.append($td3);
				
				$td4 = $('<td><input type="text" id="inNum" value="'+data.orderDetail.surplus+'" /></td>');
				$tr.append($td4);
				
				$td5 = $('<td align="center"><a class="ajaxIn xiu" href="javascript:void(0)"><img src="images/icon_3.gif">确定</a></td>');
				$tr.append($td5);
				
				$nowTr.after($tr);
			
			});
		});
		
		$(".ajaxIn").live("click", function(){ 
			//什么叫做入库？
			/*
				时间，人，事情（入库，出库），操作的是什么，数量多少？
				参数：
					数量，
					仓库
					订单明细
			*/
			
			
			$nowTr = $(this).parent().parent();
			$preTr = $nowTr.prev();
			var jsonParam = {};
			jsonParam["num"] = $("#inNum").val();
			jsonParam["storeUuid"] = $("#store").val();
			jsonParam["orderDetailUuid"] = $(this).parent().parent().prev().attr("orderDetailUuid");
			$.post("order_ajaxInGoods",jsonParam, function(data){
				
				var surplus = data.orderDetail.surplus;
				var num = data.orderDetail.num;
				
				//如果剩余一条数据，并且剩余数量为0
				if($(".ins").length == 1 && surplus == 0){
					$("#allInTitle").show();
					$("#return").show();
					
					$(".ins").hide();
					$(".in").hide();								
				}
				
				
				
				//如果当前项已经入库完毕，删除当前的信息
				if(surplus == 0){
					$nowTr.remove();
					$preTr.remove();
				}
				 //修改已经入库数量
				 $preTr.children("td:eq(2)").html(num - surplus);
				 //修改剩余数量，
				 $preTr.children("td:eq(3)").html(surplus);
				 //修改本次入库数量
				 $nowTr.children("td:eq(3)").children("input").val(surplus);
			});
	
		});
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">入库</span>
		</div>
	</div>
	<div class="content-text">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>订 单 号:</td>
						<td class="order_show_msg">${order.orderNum}</td>
						<td>商品总量:</td>
						<td class="order_show_msg">${order.totalNum}</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<center id="inOrderTitle" style="text-decoration:underline;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;单&nbsp;&nbsp;据&nbsp;&nbsp;明&nbsp;&nbsp;细&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<br/>
				<table id="inOrder" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">商品名称</td>
						<td width="30%">总数量</td>
						<td width="10%">已入库数量</td>
						<td width="30%">剩余数量</td>
						<td width="10%">入库</td>
					</tr>
					<s:iterator value="order.orderDetails">
						<s:if test="surplus > 0">
							<tr class="ins" orderDetailUuid="${uuid}" align="center" bgcolor="#FFFFFF">
								<td height="30">${goods.name}</td>
								<td>${num}</td>
								<td>${num-surplus}</td>
								<td>${surplus}</td>
								<td><a orderDetailUuid="${uuid}" href="javascript:void(0)" class="oper xiu"><img src="images/icon_3.gif" />入库</a></td>
							</tr>
						</s:if>
					</s:iterator>
				</table>
				<center id="allInTitle" style="display:none;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;全&nbsp;&nbsp;部&nbsp;&nbsp;入&nbsp;&nbsp;库&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<table id="return" style="display:none" >
					<tr>
						<td>&nbsp;</td>
						<td width="100%" align="center">
							<a action="javascript:back" style="color:#f00;font-size:20px;padding-top:2px;font-weight:bold;text-decoration:none;width:82px;height:28px;display:block;background:url(images/btn_bg.jpg)">
								返回
							</a>
						</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
	</div>
	<div class="content-bbg"></div>
</div>
