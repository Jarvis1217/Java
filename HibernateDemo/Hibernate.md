# Hibernate

> pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.encoding>UTF-8</maven.compiler.encoding>
        <java.version>11</java.version>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>

    <groupId>com.myself.demo</groupId>
    <artifactId>GUIProject</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <!-- hibernate 依赖 -->
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>5.4.25.Final</version>
        </dependency>

        <!-- mySQL 依赖 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.21</version>
        </dependency>
    </dependencies>

    <build>
        <!-- 读取配置文件 -->
        <resources>
            <resource>
                <directory>src/main/resources</directory>
            </resource>

            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
    </build>

</project>
```



> hibernate.cfg.xml

```xml
<?xml version='1.0' encoding='utf-8'?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <!-- 初始化连接 -->
        <property name="connection.driver_class">com.mysql.jdbc.Driver</property>
        <property name="connection.url">jdbc:mysql://localhost:3306/test?serverTimezone=UTC</property>
        <property name="connection.username">root</property>
        <property name="connection.password">root</property>

        <!-- 方言 -->
        <property name="dialect">org.hibernate.dialect.MySQL57Dialect</property>
        <!-- 自动更新 -->
        <property name="hbm2ddl.auto">update</property>
        <!-- 打印sql -->
        <property name="show_sql">true</property>
        <!-- 格式化sql -->
        <property name="format_sql">true</property>
        <!-- 对象映射文件 -->
        <mapping resource="com/myself/Bean/User.hbm.xml"/>
    </session-factory>
</hibernate-configuration>
```



> JavaBean

```java
import java.io.Serializable;

public class User implements Serializable {
    private int ID;
    private String Name;
    private String Passwd;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPasswd() {
        return Passwd;
    }

    public void setPasswd(String passwd) {
        Passwd = passwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Passwd='" + Passwd + '\'' +
                '}';
    }
}

```



> JavaBean.hbm.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-mapping PUBLIC
        "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
<hibernate-mapping>
    <class name="com.myself.Bean.User" table="tb_user">
        <!-- 主键自增 -->
        <id name="ID">
            <generator class="native"/>
        </id>

        <!-- 属性映射 -->
        <property name="Name" column="user_name"/>
        <property name="Passwd" column="user_passwd"/>
    </class>
</hibernate-mapping>

```



> sqlDB

```java
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class sqlDB {
    private static StandardServiceRegistry registry = null;
    private static SessionFactory sessionFactory = null;
    private static Session session = null;
    private static Transaction tx = null;

    public static Session init() {
        // 初始化注册服务对象
        registry = new StandardServiceRegistryBuilder().configure().build();
        // 获取session工厂
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        // 创建session连接
        session = sessionFactory.openSession();
        return session;
    }

    public static void destroy() {
        try {

            if(session != null && session.isOpen())
                session.close();
            if(sessionFactory != null)
                sessionFactory.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        sqlDB.init();
        sqlDB.destroy();
    }
}
```

