//存放主要交互逻辑的js代码
// javascript 模块化(package.类.方法)

var seckill = {
    //封杀秒杀相关ajax的url
    URL : {
        now : function () {
            return '/seckill/time/now';
        },
        exposer:function (seckillId) {
            return '/seckill/'+seckillId+'/exposer';
        },
        executon:function (seckillId,md5) {
            return '/seckill/'+seckillId+'/'+md5+'/execution';
        }
    },
    //验证手机号
    validatePhone:function (phone) {
        if(phone && phone.length == 11 && !isNaN(phone)){
            return true;
        }else {
            return false;
        }
    },
    handlerSeckill:function (seckillId,node) {
        //执行秒杀逻辑
        console.log("handlerSeckill-seckillId:"+seckillId);
        node.hide()
            .html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId),{},function (result) {
            if(result && result['success']){
                var exposer =  result['data'];
                if(exposer['exposed']){
                    //开启秒杀
                    var md5 = exposer['md5'];
                    var killUrl = seckill.URL.executon(seckillId,md5);
                    console.log("seckillUrl"+killUrl);
                    //绑定一次点击事件
                    $('#killBtn').one('click',function () {
                        //绑定执行秒杀请求操作
                        //1:先禁用按钮
                        $(this).addClass('disabled');
                        //2：发送秒杀请求
                        $.post(killUrl,{},function (result) {
                            if(result && result['success']){
                                var killResult = result['data'];
                                var state = killResult['state'];
                                var stateInfo = killResult['stateInfo'];
                                //3:显示秒杀结果
                                node.html('<span class="label label-success">'+stateInfo+'</span>');
                            }
                        });
                    });
                    node.show();
                }else{
                    //未开启秒杀，计时误差
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    seckillId.countDown(seckillId,now,start,end);
                }
            }else{
                console.log('result:'+result);
            }
            
        });
    },
    countDown:function (seckillId,nowTime,startTime,endTime) {
        console.log(seckillId + '_' + nowTime + '_' + startTime + '_' + endTime);
        var seckillBox = $('#seckill-box');
        if(nowTime>endTime){
            //秒杀结束
            seckillBox.html('秒杀结束！');
        }else if(nowTime<startTime){
            //秒杀未开始，计时事件绑定
            var killTime = new Date(startTime-0+1000);
            //回调函数
            seckillBox.countdown(killTime,function (event) {
                //时间格式
                var format = event.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒 ');
                seckillBox.html(format);
            }).on('finish.countdown',function () {
                //时间完成后回调事件
                //获取秒杀地址,控制现实逻辑,执行秒杀
                console.log('______fininsh.countdown');
                seckill.handlerSeckill(seckillId, seckillBox);
            });
        }else{
            seckill.handlerSeckill(seckillId, seckillBox);
        }
    },
        

    //详情页秒杀逻辑
    detail:{
        //详情页初始化
        init : function (params) {
            //手机验证和登录，计时交互
            //规划交互流程
            //在cookie中查找手机号
            var killPhone = $.cookie('killPhone');

            //验证手机号
            if(!seckill.validatePhone(killPhone)){
                //绑定phone
                //控制输出，根据获取模块节点id
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show:true,//显示弹出层
                    backdrop:'static',//禁止位置关闭
                    keyboard:false//关闭键盘事件
                });
                $('#killPhoneBtn').click(function () {
                   var inputPhone = $('#killPhoneKey').val();
                    console.log("inputPhone: " + inputPhone);
                    if(seckill.validatePhone(inputPhone)){
                        //电话写入cookie
                        $.cookie('killPhone',inputPhone,{expires:7,path:'/seckill'});
                        //刷新页面
                        window.location.reload();
                    }else{
                        $('#killPhoneMessage').hide().html('<lable class="label label-danger">手机号错误！</lable>').show(300);
                    }
                });
            }
            //已经登录
            //计时交互，也是异步获取系统时间,get的第二个参数是回调函数，result拿到获取的数据
            var seckillId = params['seckillId']
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            $.get(seckill.URL.now(),{},function (result) {
                if(result && result['success']) {
                    var nowTime = result['data'];
                    //时间判断
                    seckill.countDown(seckillId,nowTime,startTime,endTime)
                }else{
                    console.log('result:'+result);
                    alert('result: ' + result);
                }

            });

        }
    }


}