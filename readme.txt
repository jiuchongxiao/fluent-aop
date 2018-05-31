此项目以aop的形式添加fluent 日志

sample介绍
spring-mvc-aop-helloworld 为spring mvc aop condition
toolcommontest 为spring boot aop condition

0.将项目打包上传maven私服

1.spring boot和spring 添加依赖
   <dependency>
        <groupId>com.taiyue.tool.common</groupId>
        <artifactId>tool-common</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
2.添加application.properties
	1）位置
	spring boot添加到resources下
	spring mvc添加到 WEB-INFO下classes文件夹下
    2) 详情
		#是否支持 fluent日志   false否   true是(默认)
		#即aop注解总开关
		fluent.aopStatus=true
		#当前服务名称
		project.name=toolcommontest
		#fluent 连接信息   
		#非istio项目
		fluent.url=192.168.181.99
		fluent.port=30224
		#istio项目
		#fluent.url=fluentd-es.logging
		#fluent.port=24224
3.上下文引入aop类
	1）spring boot
	启动类引入
	@Import({ControllerProcessAop.class, DaoProcessAop.class, ServiceProcessAop.class})
	2)spring
	.xml
	<context:component-scan base-package="com.taiyue.tool.common.process" />
	<aop:aspectj-autoproxy proxy-target-class="true"/>
4.使用
    1）aop添加fluent日志    spring boot/spring
    
    controller类添加注解@DinfoUrlTag
    service类添加注解@DinfoServiceTag
    dao类添加注解@DinfoDaoTag

   注意：可以在application.properties中通过配置fluent.aopStatus统一控制aop生效与否

   2）业务代码添加fluent日志

    假如配置了application.properties可以直接通过FluentProperties.getLog()获取fluent服务对象。

   如：

    FluentProperties.getLog().log("hello","testMethod","helloworld");
   log方法详解：

log(String tag, String key, Object value)

log(String tag, String key, Object value, long timestamp)

log(String tag, Map<String, Object> data)

log(String tag, Map<String, Object> data, long timestamp)

	
	
