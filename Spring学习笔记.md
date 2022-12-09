# Spring简介

Spring框架可以创建性能好、易于测试、可重用的代码

 ## Spring Framework 五大功能模块

1. Core Container：核心容器，在Spring环境下使用任何功能都必须基于IOC容器
2. AOP&Aspects：面向切面编程
3. Testing：提供了对junit或TestNG测试框架的整合
4. Data Access/Integration：提供了对数据访问/集成的功能
5. Spring MVC：提供了面对Web程序的集成功能

## IOC容器

IOC思想：Inversion of Control **反转控制**

### IOC容器在Spring中的实现

- BeanFactory
- ApplicationContext

### IOC实现步骤

#### 1.Maven引入Spring框架

``` xml
 <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.3.22</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

#### 2.Resource中创建applicationContext.xml并配置bean对象

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="helloWorld" class="com.example.spring.pojo.helloSpring"></bean>
</beans>
```

beans标签后面是约束

bean标签id是唯一标识，class对应具体类的位置

#### 3.运用ioc得到类 

- 根据id获取类

``` java
//文件名记得带后缀.xml
ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

//ioc.getBean方法根据id得到类，需要强制转换
HelloSpring spring = (HelloSpring) ioc.getBean("helloWorld");

spring.sayHello();
```

**类需要有无惨构造，否则类中有属性且bean中没配置时会无法实例化**

- 根据class获取类

``` java
ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

Student studentOne = ioc.getBean(Student.class);
```

**根据类获取bean时，要求ioc容器中有且只有一个类型匹配的bean**

- 根据id和class获取类

``` java
Student studentOne = ioc.getBean("studentOne",Student.class);
```

### 依赖注入

依赖注入就是为类中属性赋值

- 通过property标签，运用成员变量的***set方法***进行赋值

``` XML
<bean id="studentTwo" class="com.example.spring.pojo.Student">
        <property name="sname" value="evan"></property>
        <property name="sid" value="1"></property>
        <property name="gender" value="男"></property>
        <property name="age" value="23"></property>
 </bean>
```

- 通过构造器赋值

``` xml
<bean id="studentThree" class="com.example.spring.pojo.Student">
        <constructor-arg value="1001"></constructor-arg>
        <constructor-arg value="李四"></constructor-arg>
        <constructor-arg value="24"></constructor-arg>
        <constructor-arg value="女"></constructor-arg>
</bean>
```

可以通过name指定赋值的成员变量

``` xml
<constructor-arg value="女" name="gender"></constructor-arg>
```



### 赋值

#### 特殊值处理

1. 字面量

直接用value赋值

2. null

运用null标签

``` xml
<bean id="studentFour" class="com.example.spring.pojo.Student">
        <property name="sname" value="evan"></property>
        <property name="sid" value="1"></property>
        <property name="gender">
            <null/>
        </property>
        <property name="age" value="23"></property>
</bean>
```

3. xml实体

``` xml
<property name="expression" value="a &lt; b"></property>
<property name="sname">
  <value><![CDATA[<王五>]]></value>
</property>
```

```
<: &lt;
>: &gt;
CDATA区，idea直接写CD按回车，可以自动生成
```



#### 类类型属性赋值

1. 运用ref引用对应类的bean id

``` xml
    <bean id="CS" class="com.example.spring.pojo.Department">
        <property name="id" value="1"></property>
        <property name="name" value="计算机专业"></property>
    </bean>
    <bean id="studentFive" class="com.example.spring.pojo.Student">
        <property name="sname" value="赵六"></property>
        <property name="age" value="21"></property>
        <property name="gender" value="男"></property>
        <property name="sid" value="5"></property>
        <property name="department" ref="CS"></property>
    </bean>
```

2. 通过级联的方式 用.访问

**在用.访问之前一定要用ref给class先赋值，本质就是对class内对属性又进行了修改，因此这种方式用的情景不多**

``` xml
<bean id="studentFive" class="com.example.spring.pojo.Student">
        <property name="sname" value="赵六"></property>
        <property name="age" value="21"></property>
        <property name="gender" value="男"></property>
        <property name="sid" value="5"></property>
  			<property name="department" ref="CS"></property>
        <property name="department.name" value="Japanese"></property>
        <property name="department.id" value="2"></property >
 </bean>
```

3. 类property中加bean标签

```xml
<bean id="studentFive" class="com.example.spring.pojo.Student">
        <property name="department">
            <bean id="departmentInner" class="com.example.spring.pojo.Department">
                <property name="id" value="3"></property>
                <property name="name" value="Chemistry"></property>
            </bean>
        </property>
</bean>
```

内部的bean不能被ioc直接获取



#### 数组

1. 运用array标签，数组中存放的是字面量，则直接用value类型，否则用ref标签

```xml
<property name="hobbies">
            <array>
                <value>抽烟</value>
                <value>喝酒</value>
                <value>烫头</value>
            </array>
  </property>
```

2. list集合存储的数据用list标签,list里面存对象用ref标签

```XML
<bean id="CS" class="com.example.spring.pojo.Department">
        <property name="id" value="1"></property>
        <property name="name" value="计算机专业"></property>
        <property name="students">
            <list>
                <ref bean="studentFive"></ref>
                <ref bean="studentOne"></ref>
                <ref bean="studentTwo"></ref>
            </list>
        </property>
</bean>
```

3. 用util约束配置一个集合类型的bean

```XML
<!--    配置一个集合类型的bean需要加util约束-->
    <util:list id="studentList">
        <ref bean="studentFive"></ref>
        <ref bean="studentOne"></ref>
        <ref bean="studentTwo"></ref>
    </util:list>
    <bean id="CS" class="com.example.spring.pojo.Department">
        <property name="id" value="1"></property>
        <property name="name" value="计算机专业"></property>
        <property name="students" ref="studentList"></property>
    </bean>
```

#### Map

1. 直接用map标签

```XML
<property name="teacherMap">
		<map>
				<entry key="1001" value-ref="teacherOne"></entry>
        <entry key="1002" value-ref="teacherTwo"></entry>
    </map>
</property>
```

2. 用util:map

```xml
    <util:map id="TeacherMap">
        <entry key="1001" value-ref="teacherOne"></entry>
        <entry key="1002" value-ref="teacherTwo"></entry>
    </util:map>
<!-- bean中直接引用 --> 
<property name="teacherMap" ref="TeacherMap"></property>
```

### ioc得到druid数据源

1. maven引入mysql-connector-java和druid

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.29</version>
</dependency>
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.2.15</version>
</dependency>
```

2. 新建.properties文件，将数据库的连接配置写入

``` properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql:///db1?useSSL=false&useServerPrepStmts=true
jdbc.username=root
```

3. xml文件用bean标签得到druidDataSource

```xml

<context:property-placeholder location="jdbc.properties"></context:property-placeholder>
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="${jdbc.driver}"></property>
    <property name="url" value="${jdbc.url}"></property>
    <property name="username" value="${jdbc.username}"></property>
</bean>

```

4. ioc获取

``` java
ApplicationContext ioc = new ClassPathXmlApplicationContext("Spring-dataSource.xml");
DruidDataSource dataSource = ioc.getBean(DruidDataSource.class);

System.out.println(dataSource);
```

### bean的作用域

bean默认是单例模式，单例模式bean所获取的对象都是同一个 

如果想要多例，则需要在bean标签用scope属性赋值为prototype



### bean生命周期

1. 实例化
2. 依赖注入 (set方法赋值)
3. 初始化 需要用bean的init-method来指定初始化方法
4. 销毁（ioc容器关闭时）需要用bean的destroy-method来指定



### FactoryBean

1. 类实现FactoryBean接口需要写getObject和getObjectType方法

```java
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
```



2. bean标签class得到的实际是class对应getObject得到的实例

```xml
<!-- 得到的是UserFactoryBean中getObject返回的实例 -->
<bean class="com.example.spring.factory.UserFactoryBean"></bean>
```

3. ioc直接用getBean(User.class)得到对象

```java
ApplicationContext ioc = new ClassPathXmlApplicationContext("spring-factory.xml");
User user = ioc.getBean(User.class);
System.out.println(user);
```

### Controller、Service、Dao

DAO层：
DAO层叫数据访问层，全称为data access object，属于一种比较底层，比较基础的操作，具体到对于某个表的增删改查，也就是说某个DAO一定是和数据库的某一张表一一对应的，其中封装了增删改查基本操作，建议DAO只做原子操作，增删改查。

Service层：
Service层叫服务层，被称为服务，粗略的理解就是对一个或多个DAO进行的再次封装，封装成一个服务，所以这里也就不会是一个原子操作了，需要事物控制。

Controler层：
Controler负责请求转发，接受页面过来的参数，传给Service处理，接到返回值，再传给页面。

总结：
个人理解DAO面向表，Service面向业务。后端开发时先数据库设计出所有表，然后对每一张表设计出DAO层，然后根据具体的业务逻辑进一步封装DAO层成一个Service层，对外提供成一个服务。

### 基于xml自动装配

UserController层有userService属性，UserService属性有userDao属性，两个属性都用bean标签配置

自动装配的策略：

- no：表示不装配
- default：表示不装配
- byType: 根据要赋值的属性的类型自动匹配到合适的bean赋值
- byName： 将要赋值的属性的属性名作为bean的id在IOC容器中匹配某一个bean，为属性赋值

### 基于注解管理bean

标识组件的常用注解

- @Component 将类标识位普通组件
- @Controller 将类标识位控制层组件
- @Service 将类标识为业务层组件
- @Respository 将类标识持久层组件

#### 注解➕扫描

扫描组件用 context:component-scan标签扫描

``` XML
<context:component-scan base-package="com.example.spring"></context:component-scan>
```

根据注解类型排除(常用)

``` XML
<context:component-scan base-package="com.example.spring">
    <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>
```

根据类排除

``` XMl
<context:component-scan base-package="com.example.spring">
    <context:exclude-filter type="assignable" expression="com.example.spring.controller.UserController"/>
</context:component-scan>
```

包含用context:include-filter标签，要设置use-default-filters="false"

#### 注解的方法

用@Autowired实现自动装配功能的注解

## 代理

 ### 静态代理

 静态代理类中有一个target成员变量

```java
//target类
public class CalculatorImpl implements Calculator{
    @Override
    public int add(int i, int j) {
        int result = i+j;
        System.out.println("方法内部：result="+result);
        return result;
    }
}

//静态代理类
public class CalculatorStaticProxy implements Calculator{
    private Calculator target;

    public CalculatorStaticProxy(Calculator target) {
        this.target = target;
    }

    @Override
    public int add(int i, int j) {
        System.out.println("日志，方法：add，参数："+i+","+j);
        int result = target.add(i,j);
        System.out.println("日志：方法：add，结果："+result);
        return result;
    }
}

//使用方法
public void testProxy() {
     CalculatorStaticProxy calculatorStaticProxy = new CalculatorStaticProxy(new CalculatorImpl());
     calculatorStaticProxy.add(1,2);
}
```

缺点：不具备灵活性

### 动态代理

1. 代理工厂类，需要getProxy方法

   Proxy.newProxyInstance(loader,interfaces,h);三个参数

```java
public class ProxyFactory  {
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        /**
         * ClassLoader loader: 指定加载动态生成的代理类的类加载器
         * Class[] interfaces: 获取目标对象实现的所有接口的Class对象的数组
         * InvocationHandler h: 设置代理类中的抽象方法如何重写
         */
        ClassLoader loader = this.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("日志，方法："+method.getName()+",参数："+ Arrays.toString(args));
                //proxy表示代理对象，method表示要执行的方法，args表示要执行的方法的参数列表
                Object result = method.invoke(target,args);
                return result;
            }
        };
        return Proxy.newProxyInstance(loader,interfaces,h);
    }
}
```

2. 调用代理工厂类，强转

```java
 ProxyFactory proxyFactory = new ProxyFactory(new CalculatorImpl());
 Calculator propxy = (Calculator) proxyFactory.getProxy();
 propxy.add(1,2);
```

## AOP

面向切面编程

### 注解的方式面向切面编程

1. xml配置 先扫描注解再打开自动代理

``` XML
    <context:component-scan base-package="com.example.spring.aop.annotation"></context:component-scan>
    <aop:aspectj-autoproxy />
```

2. 目标类加上注解

```java
//加上Component注解
@Component
public class CalculatorImpl implements Calculator {...}
```

3. 切面类加上注解，并设置相关方法。想要得到连接点的信息可以用joinPoint参数

``` java
@Component //加上注解，用于扫描
@Aspect //标记为切面类
public class LoggerAspect {
//    @Before("execution(public int com.example.spring.aop.annotation.CalculatorImpl.add(int, int))")
    @Before("execution(* com.example.spring.aop.annotation.CalculatorImpl.*(..))") //类中所有方法用*
    public void beforeAdviceMethod(JoinPoint joinPoint) {
      //通过连接点得到方法名
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
       //通过连接点得到方法参数
        System.out.println("方法名："+signature+",参数："+ Arrays.toString(args));
        System.out.println("Logger Aspect：前置通知");
    }
}
```

4. 运用ioc运行相关方法

```java
    public void testAOP() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("aop-annotation.xml");
        Calculator calculator = ioc.getBean(Calculator.class);
        calculator.add(1,6);
    }
```

***注意这里getBean传入的类型不是目标类，因为ioc通过代理来完成，如果是目标类的话会报noSuchBeanDefintion的错，虽然我们不知道代理类的类型，但是代理类和目标类实现了相同的接口，因此可以用接口类型***

重用切入点表达式，可以用@PointCut注解一个void pointCut()空方法，后续@Before、@After里都可以用pointCut()实现重用 

### 通知类型

AOP通知共分为5种类型

- 前置通知：在切入点方法执行之前执行 **@Before**
- 后置通知：在切入点方法执行之后执行，无论切入点方法内部是否出现异常，后置通知都会执行。**@After**
- 环绕通知(重点)：手动调用切入点方法并对其进行增强的通知方式。**@Around**
- 返回后通知：在切入点方法执行之后执行，如果切入点方法内部出现异常将不会执行。 **@AfterReturning**
- 抛出异常后通知：在切入点方法执行之后执行，只有当切入点方法内部出现异常之后才执 **@AfterThrowing**

环绕通知使用方法,有点类似动态代理

```java
@Around("pointCut()")
public Object aroundAdviceMethod(ProceedingJoinPoint joinPoint) {
    Object result = null;
    try {
        System.out.println("环绕通知：前置通知");
        result = joinPoint.proceed();
        System.out.println("环绕通知：返回通知");
    } catch (Throwable throwable){
        throwable.printStackTrace();
        System.out.println("环绕通知：异常通知");
    } finally {
        System.out.println("环绕通知：后置通知");
    }
    return result;
}
```

**环绕通知的方法要和目标方法的返回值一致**

### XML方式面向切面编程

 ```xml
 <context:component-scan base-package="com.example.spring.aop.xml"></context:component-scan>
 <aop:config>
     <aop:aspect ref="loggerAspect">
         <aop:pointcut id="pointCut" expression="execution(* com.example.spring.aop.xml.CalculatorImpl.*(..))" />
         <aop:before method="beforeAdviceMethod" pointcut-ref="pointCut"></aop:before>
         <aop:after method="afterAdviceMethod" pointcut-ref="pointCut"></aop:after>
         <aop:after-returning method="afterReturningAdviceMethod" pointcut-ref="pointCut" returning="result"></aop:after-returning>
         <aop:after-throwing method="afterThrowingAdviceMethod" throwing="ex" pointcut-ref="pointCut"></aop:after-throwing>
         <aop:around method="aroundAdviceMethod" pointcut-ref="pointCut"></aop:around>
     </aop:aspect>
     <aop:aspect ref="validateAspect" order="1">
         <aop:before method="beforeMethod" pointcut-ref="pointCut" />
     </aop:aspect>
 </aop:config>
 ```

## 声明式事务

配置步骤

1. 在Spring的配置文件中配置事务管理器

```xml
</bean>
<!-- 配置事务管理器 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="druidDataSource"></property>
</bean>

```

2. 开启事务的注解驱动

```xml
<!-- 开启事务的注解驱动 -->
<tx:annotation-driven transaction-manager="transactionManager"/>
```

3. 在要被事务管理的方法上添加@Transactional注解，该方法就会被事务管理

```java
@Transactional
public void buyBook(Integer userId, Integer bookId) {
    Integer price = bookDao.getPriceByBookId(bookId);
    bookDao.updateStock(bookId);
    bookDao.updateBalance(userId, price);
}
```

