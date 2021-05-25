layui.use(['jquery','layer', 'table','form','laydate'], function() {
    var $ = layui.jquery    //引入jquery模块
        , layer = layui.layer  //引用layer弹出层模块
        , table = layui.table  //引用table数据表格模块
        , form = layui.form  //引用form表单模块
        , laydate = layui.laydate;  //引用日期模块

    //执行一个laydate实例
    laydate.render({
        elem: '#createDate' //指定元素的id
        ,type:'datetime'  //日期格式
        ,format:'yyyy/MM/dd HH:mm:ss'  //日期字符串格式
      //  ,value:new Date()  //初始值为系统当前时间
        ,min:0  //表示只能选则当前数据之后的时间
    });

    //加载所有空闲的客房
    loadRoomsByRoomStatus("0");


    //监听会员非会员的选择
    form.on('radio(isVip)', function(data){
        $("form").eq(0).find('input:text').val("");  //清空表单之前的数据
        if(data.value=='1'){
            //会员页面操作
            isVipTrue();
        }else {
            //非会员页面操作
            isVipFalse();
        }
    });
    //自定义验证
    form.verify({  //做表单提交时的验证
        money:function (value) { //value：表单的值、item：表单的DOM对象
            if(value<100 || value>2000){
                return '押金范围在100-2000元之内';
            }
        }
    });

    //监听提交完成入住信息添加
    form.on('submit(demo1)', function (data) {
        var saveJsonInRoomInfo = data.field;
        saveJsonInRoomInfo['outRoomStatus'] = '0';
        saveJsonInRoomInfo['status'] = '1';
        //执行添加，1.入住信息添加  2.客房状态由0（空闲）---->1（已入住）
        saveInRoomInfo(saveJsonInRoomInfo);
        return false;  //阻止表单跳转提交
    });

    /**********************************自定义绑定事件*************************************/
    //给会员卡号输入框失去焦点事件
    $("#vip_num").blur(function () {
        //失去焦点时会有卡号验证
        var vipNum = $(this).val();
        //验证会员卡号的格式
        if((/(^[1-9]\d*$)/.test(vipNum))){
            if(vipNum.length==16){  //验证长度
                //发送ajax请求，查询会员卡号
                loadVipByVipNum(vipNum);  //根据会员卡号加载单个会员数据
            }else {
                //吸附框  会员卡号长度必须位16位：提示内容  ，#vip_num吸附的标签
                //{tips: [2,'green'],time:2000}  弹出位置（上右下左1-4）   背景颜色  显示时间
                layer.tips('会员卡号长度必须位16位','#vip_num', {tips: [2,'red'],time:2000});
            }
        }else {
            layer.tips('会员卡号必须为正整数','#vip_num', {tips: [2,'red'],time:2000});
        }
    });

    /**********************************自定义函数*************************************/
    //是会员的表单操作
    function isVipTrue() {
        $("#vip_num").removeAttr("disabled")  //将会员卡号输入框可用
        $("#vip_num").attr("lay-verify","required|number|vip_num"); //添加验证的属性值
        //将客人姓名，手机号，身份证号，性别均不可用
        $("#customerName").attr("disabled","disabled");
        $("input[name=gender]").attr("disabled","disabled");
        $("#idcard").attr("disabled","disabled");
        $("#phone").attr("disabled","disabled");
    }
    //非会员的表单操作
    function isVipFalse() {
        $("#vip_num").attr("disabled","disabled"); //将会员卡号输入框不可用
        $("#vip_num").removeAttr("lay-verify")  //移除验证的属性值
        //将客人姓名，手机号，身份证号，性别均可用
        $("#customerName").removeAttr("disabled");
        $("input[name=gender]").removeAttr("disabled");
        $("#idcard").removeAttr("disabled");
        $("#phone").removeAttr("disabled");
    }

    //根据会员卡号加载单个会员数据
    function loadVipByVipNum(vipNum){
        $.post(
            "/vip/loadTByParams", //调用的是base系列的方法，只需要改mapper.xml文件
            {"vipNum":vipNum},
            function (data){
                console.log(data);
                if(!$.isEmptyObject(data)){
                    //给表单赋值(基础属性回显)
                    form.val("example", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                        "customerName": data.customerName // "name": "value"
                        ,"gender": data.gender
                        ,"idcard": data.idcard
                        ,"phone": data.phone
                    });
                    layer.tips('已查出此会员数据','#vip_num', {tips: [2,'green'],time:2000});
                }else{
                    layer.tips('没有此会员数据','#vip_num', {tips: [2,'red'],time:2000});
                }
            },"json"
        ).error(function (){
            layer.msg("服务器异常！！！",{icon: 3,time:2000,anim: 6,shade:0.5});
        })
    }

    //加载所有空闲的客房
    function loadRoomsByRoomStatus(roomStatus){
        $.post(
            "/rooms/loadManyByParams", //调用的是base系列的方法，只需要改mapper.xml文件
            {"roomStatus":roomStatus},
            function (data){
                console.log(data);
                //这里使用了模板字符串，注意：模板字符串不要换行
                var roomStr = `<option value="">---请选择房间---</option>`;
                $.each(data,function (i,room){
                    roomStr += `<option value="${room.id}">${room.roomNum} - ${room.roomType.roomTypeName} - ${room.roomType.roomPrice}</option>`;
                })
                $("#selRoomNumId").html(roomStr);
                form.render("select"); //渲染
            },"json"
        ).error(function (){
            layer.msg("服务器异常！！！",{icon: 3,time:2000,anim: 6,shade:0.5});
        })
    }

    //添加入住信息
    function saveInRoomInfo(saveJsonInRoomInfo){
        $.post(
            "/inRoomInfo/save", //调用的是base系列的方法，只需要改mapper.xml文件
            saveJsonInRoomInfo,
            function (data){
                if(data === 'success'){
                    layer.msg("入住信息添加成功！",{icon: 1,time:2000,anim: 4,shade:0.5});
                    //重新加载数据，加载当前页的数据
                    //定时器，2s后跳转到入住信息显示页面
                    setTimeout("window.location='model/toShowInRoomInfo'",2000);
                }else{
                    layer.msg("入住信息添加失败！",{icon: 2,time:2000,anim: 4,shade:0.5});
                }
            },"text"
        ).error(function (){
            layer.msg("服务器异常！！！",{icon: 3,time:2000,anim: 6,shade:0.5});
        })
    }

});