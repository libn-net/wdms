<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>主页</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
</script>
</head>
<body>
	<form id="searchForm" action="${ctx}/gtg/search" method="post"
		class="breadcrumb form-search">
		<ul class="ul-form">
			<li><input type="text" name="keyword" value="${keyword}"
				placeholder="开始比价之旅" class="input-medium"></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="搜索" /></li>
			<li class="clearfix"></li>
		</ul>
	</form>

	<sys:message content="${message}" />

	<c:if test="${not empty jdGoods}">
	<div class="comments"  style="padding:10px;">
		<div class="row-fluid">
			<!-- 电商比价 strat -->
			<!-- 京东 -->
			<div class="span6">
				<div class="eb-header">京东</div>
				
				<c:forEach var="g" items="${jdGoods}">
				<div class="row-fluid" style="margin-top: 10px;">
					<div class="span2">
						<img alt="" src="${g.imgUrl}" width="80" height="80" />
					</div>
					<div class="span3">
						<span>${g.name}</span>
					</div>
					<div class="span3">
						<ul>
							<li>好评(${g.goodCount})${g.goodRateShow}</li>
							<li>中评(${g.generalCount})${g.generalRateShow}</li>
							<li>差评(${g.poorCount})${g.poorRateShow}</li>
						</ul>
					</div>
					<div class="span3">
						<ul>
							<li>${g.price}</li>
<!-- 							<li>京东通信用户送9个通信B</li> -->
						</ul>
					</div>
				</div>
				</c:forEach>
			</div>
			
			<!-- 苏宁 -->
			<div class="span6">
				<div class="eb-header">苏宁</div>
				<c:forEach var="g" items="${snGoods}">
				<div class="row-fluid" style="margin-top: 10px;">
					<div class="span2">
						<img alt="" src="${g.imgUrl}" width="80" height="80" />
					</div>
					<div class="span3">
						<span>${g.name}</span>
					</div>
					<div class="span3">
						<ul>
							<li>好评(${g.goodCount})${g.goodRateShow}</li>
							<li>中评(${g.generalCount})${g.generalRateShow}</li>
							<li>差评(${g.poorCount})${g.poorRateShow}</li>
						</ul>
					</div>
					<div class="span3">
						<ul>
							<li>${g.price}</li>
<!-- 							<li>京东通信用户送9个通信B</li> -->
						</ul>
					</div>
				</div>
				</c:forEach>
			</div>
		</div>
	</div>
	</c:if>
</body>
</html>