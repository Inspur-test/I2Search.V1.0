<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link href="css/index.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<title></title>
<script type="text/javascript">
	/*js判断总页数和当前页数*/
	var totalRecs = <s:property value="totalRecs"/>;
	if ("" != totalRecs) {
		var totalPages = Math.ceil(totalRecs / 8);
	}
	var fromPage = <s:property value="fromPage"/>;
	/*按后缀*/
	var extension = '<s:property value="extension"/>';
</script>
</head>

<body>
	<div id="container">
		<div id="head">
			<form action="SearchAction" method="post" id="searchForm">
				<div id="logo">
					<img src="images/logo.png" alt="" />
				</div>
				<div id="login">
					<a href="#">登录</a>|<a href="#">注册</a>|<a href="#">反馈</a>
				</div>
				<div id="searchDiv" style="font-family: 微软雅黑;">
					<input id="content" type="text" name="content"
						value='<s:property value="content"/>' style="font-family: 微软雅黑;" />
					<input id="search" type="submit" value="搜&nbsp;&nbsp;索"
						style="font-family: 微软雅黑;" /><br /> <input id="all"
						class="extension" type="radio" name="extension" value="all"
						checked="checked" onchange="changeExt(this);"/><label for="all">全部</label> <input id="doc"
						class="extension" type="radio" name="extension" value="doc"
						onchange="changeExt(this);" /><label for="doc">DOC</label> <input
						id="ppt" class="extension" type="radio" name="extension"
						value="ppt" onchange="changeExt(this);" /><label for="ppt">PPT</label>
					<input id="txt" class="extension" type="radio" name="extension"
						value="txt" onchange="changeExt(this);" /><label for="txt">TXT</label>
					<input id="pdf" class="extension" type="radio" name="extension"
						value="pdf" onchange="changeExt(this);" /><label for="pdf">PDF</label>
					<input id="xls" class="extension" type="radio" name="extension"
						value="xls" onchange="changeExt(this);" /><label for="xls">XLS</label>
				</div>
				<input type="hidden" value="1" name="fromPage" id="fromPage" />
			</form>
		</div>
		<div id="center">
			<div id="centerLeft">
				<div id="sortOption" style="font-family: 微软雅黑;">
					<ul>
						<li><a href="#">最强相关性</a></li>
						<li><a href="#">最多下载</a></li>
						<li><a href="#">最新上传</a></li>
						<li><a href="#">最受好评</a></li>
					</ul>
				</div>
				<div id="result" style="font-family: 微软雅黑;">
					<s:iterator value="docs">
						<div class="doc">
							<span class="ext"><img
								src='images/<s:property value="extension" escape="false"/>.jpg'
								alt="" /> </span> <span class="title">文章标题：<a
								href='<s:property value="url" escape="false"/>'><s:property
										value="title" escape="false" /> </a> </span><br /> <span
								class="Category">分类：<s:property value="Category" />
							</span><br /> <span
								class="keywords">关键词：<s:property value="keywordsInStr" />
							</span><br /> <span class="summary">文章摘要：<s:property
									value="summary" escape="false" /> </span><br /> <span class="infos"><span
								class="info">上传时间：<s:property value="uploadedTime"
										escape="false" /> </span> <span class="info">|</span> <span
								class="info">上传者：<s:property value="author"
										escape="false" /> </span> </span>
						</div>
					</s:iterator>
					<div id="pagesDiv">
						<div id="pages"></div>
					</div>
				</div>
			</div>
			<div id="centerRight">
				<div id="upload">
					<img src="images/uploadBtn.png" alt="" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>
