/*加载当前页面时候的初始化工作*/
$(function() {
	// 中间右侧的高度与左侧同步
	if (parseInt($("#centerLeft").css("height").replace("px", "")) > 40) {
		$("#centerRight").css("height", $("#centerLeft").css("height"));
	} else {
		$("#centerRight").css("display", "none");
	}
	// 为上传按钮绑定鼠标移入事件
	$("#upload img").bind("mouseover", {
		img : $("#upload img")
	}, function(events) {
		events.data.img.attr("src", "images/uploadBtnHover.png");
	}).bind("mouseleave", {
		img : $("#upload img")
	}, function(events) {
		events.data.img.attr("src", "images/uploadBtn.png");
	});
	// 修改类型
	$("#"+extension).attr("checked","checked");
//	$(".extension[id='"+extension+"']").attr("checked","checked");
//	$(".extension[id!='"+extension+"']").attr("checked","");
	// 最强相关性
	$("#sortOption ul li").eq(0).css("border-top", "3px solid #25b686");
	// 页数
	$("#pages").width(0);
	if (0 == totalPages||"" == totalPages) { 
		return;
	}
	if (totalPages >= 11) {
		if (fromPage < 6) {
			if (fromPage != 1) {
				appendPage2("上一页", "previous");
			}
			for ( var i = 0; i < 11; ++i) {
				appendPage(i);
			}
			appendPage2("下一页", "next");
		} else if (fromPage > totalPages - 6) {
			appendPage2("上一页", "previous");
			for ( var i = totalPages - 11; i < totalPages; ++i) {
				appendPage(i);
			}
			if (fromPage != totalPages) {
				appendPage2("下一页", "next");
			}
		} else {
			appendPage2("上一页", "previous");
			for ( var i = fromPage - 6; i < fromPage + 5; ++i) {
				appendPage(i);
			}
			appendPage2("下一页", "next");
		}
	} else {
		if (fromPage != 1) {
			appendPage2("上一页", "previous");
		}
		for ( var i = 0; i < totalPages; ++i) {
			appendPage(i);
		}
		if (fromPage != totalPages) {
			appendPage2("下一页", "next");
		}
	}
});
/* 切换页码 */
function pageChange(btn) {
	$("#fromPage").attr("value", $(btn).val());
	$("#searchForm").submit();
}
function appendPage(i) {
	var pageBtn = $("<input type='button' value='"
			+ (i + 1)
			+ "' onclick='pageChange(this);' style='float:left;margin:3px;padding:3px 5px;'/>");
	if (fromPage == i + 1) {
		pageBtn.css("background-color", "#25b686").css("color", "white");
	}
	$("#pages").append(pageBtn);
	if ($("#pages").width() < $("#centerLeft").width()) {
		$("#pages").width(parseInt($("#pages").width()) + 30);
	}
}
function appendPage2(text, fun) {
	var pageBtn = $("<input type='button' value='" + text + "' onclick='" + fun
			+ "(this);' style='float:left;margin:3px;padding:3px 2px;'/>");
	$("#pages").append(pageBtn);
	if ($("#pages").width() < $("#centerLeft").width()) {
		$("#pages").width(parseInt($("#pages").width()) + 100);
	}
}
function previous(btn) {
	$("#fromPage").attr("value", fromPage - 1);
	$("#searchForm").submit();
}
function next(btn) {
	$("#fromPage").attr("value", fromPage + 1);
	$("#searchForm").submit();
}
function changeExt(radio) {
	$("#searchForm").submit();
}