<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主页</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			var sn_para = {
				appMethod : 'suning.custom.product.get',
				appRequestTime : '',
				format : 'json',
				appKey : '',
				versionNo : 'v1.2',
				signInfo : '',
				access_token : ''
			}; 
			
			
			
			$.ajax({
				type: 'POST',
			    url: 'http://apipre.cnsuning.com/api/http/sopRequest' ,
			    data:{    
					appMethod : 'suning.custom.product.get',    
		            selOperatorsCode : selOperatorsCode,    
		             PROVINCECODE : PROVINCECODE,    
		             pass2 : pass2    
		   		},  
// 			    dataType: 'text',
			    success: function(data) { 
			    	
			    }
			});
		});
	</script>
</head>
<body>
	<form id="searchForm" action="${ctx}/gtg/search" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li>
				<input type="text" name="keyword" value="" placeholder="开始比价之旅" class="input-medium" >
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
</body>
</html>