一、相关技术
1、扫码
（1）wyuan 网源设备，libs对应cw-deviceapi20191022.jar和IGLBarDecoder.jar
（2）海康设备，广播方式

2、XUI

3、XAOP
https://github.com/xuexiangjys/XAOP

4、MVVM结构

二、adb命令
adb connect 127.0.0.1:7775

三、流程
1、滑动界面
    判断本地是否有token，有则直接传递用户名称到Main，没有则跳转登录界面
2、扫描登录界面（请求的Mo接口）
    （1）使用androidId去mo获取二维码信息并展示（此时轮询是否扫描接口，一旦扫描成功则跳转到Main界面）
    （2）使用钉钉自定义的【扫码登录】功能扫描二维码，登录成功获取到用户名称传递给Main
3、Main界面（这里面的login是请求的sm接口）
    （1）检查是否有token
        a.有则登陆过，直接使用sp内的token、用户名、激活的库区 -> 登录成功、激活库区
        b.没有则还没登录,调用login -> (存token、用户名、激活的库区) 登录成功、激活库区
     (2)登录成功后
        a.获取配置信息
     (3)库区激活后
        a.查询任务信息
        b.连接websocket