<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/5/7
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <!--引用基础路径-->
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>标题</title>
    <!--引入layui的样式文件-->
    <link rel="stylesheet" href="static/lib/layui/css/layui.css">
    <style type="text/css">
        .layui-table td{
            height: 60px;
        }
        .layui-table td img{
            width:60px;
            height: 60px;
        }
    </style>
    <!--引入layui的js文件-->
    <script src="static/lib/layui/layui.js"></script>
</head>
<body>
<div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>入住信息显示</legend>
    </fieldset>
    <div align="center">
        <!--入住信息数据显示的容器-->
        <table id="demo" lay-filter="test"></table>
    </div>
    <!--将退房界面静态包含进来-->
    <jsp:include page="exitRooms.jsp"/>
</div>
        <!--引入layui的js文件-->
        <script src="static/js/inRoomInfo/showInRoomInfo.js"></script>
        <!--自定义工具条-->
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-xs" lay-event="query"><i class="layui-icon">&#xe615;</i>查看</a>
            {{#  if(d.outRoomStatus == 0){ }}
            <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="exitRoom"><i class="layui-icon">&#xe642;</i>退房</a>
            {{#  } else { }}
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</a>
            {{#  } }}
        </script>
        <!--性别的自定义模板-->
        <script type="text/html" id="genderTpl">
            {{#  if(d.gender == 1){ }}
            <font color="blue">男</font>
            {{#  } else { }}
            <font color="#ff1493">女</font>
            {{#  } }}
        </script>
        <!--是否会员的自定义模板-->
        <script type="text/html" id="isVipTpl">
            {{#  if(d.isVip == 1){ }}
            <font color="green">是</font>
            {{#  } else { }}
            <font color="red">否</font>
            {{#  } }}
        </script>
        <!--入住信息状态的自定义模板-->
        <script type="text/html" id="outRoomStatusTpl">
            {{#  if(d.outRoomStatus == 1){ }}
            <font color="green">已退房</font>
            {{#  } else { }}
            <font color="red">未退房</font>
            {{#  } }}
        </script>
</body>
</html>
