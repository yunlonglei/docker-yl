# docker_yl
集成Docker的一个简单练习项目，将上传github
## docker运行命令
* 运行一个镜像，来生成一个容器（在用maven插件运行的时候maven会自动上传docker镜像，我们只需要启动就好）  
```docker
docker run -p 8082:8080 --name my-docker_yl_hello leiyunlong/docker_yl:latest 
```
* 打包一个文件生成镜像，然后运行这个镜像（用idea的docker插件时，会自动上传镜像并运行生成容器）
```docker
docker build -t leiyunlong/docker_yl:1.0 .
&& docker run
-p 8081:8080
--name my-docker_yl_hello_dockerfile
leiyunlong/docker_yl:1.0 
```

## 可参考的Dockerfile
```dockerfile
FROM java:8
VOLUME /tmp
ADD /target/docker_yl.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8082
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar" ]
```
## 可参考的maven的docker插件
```xml
<build>
        <finalName>${project.artifactId}</finalName>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork>
                </configuration>
            </plugin>
            <!-- 跳过单元测试 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <configuration>
                    <skipTests>true</skipTests>
                </configuration>
            </plugin>
            <!--使用docker-maven-plugin插件-->
            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>docker-maven-plugin</artifactId>
                <version>1.2.2</version>
                <!--将插件绑定在某个phase执行-->
                <executions>
                    <execution>
                        <id>build-image</id>
                        <!--用户只需执行mvn package ，就会自动执行mvn docker:build-->
                        <phase>package</phase>
                        <goals>
                            <goal>build</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <!--指定生成的镜像名-->
                    <imageName>leiyunlong/${project.artifactId}</imageName>
                    <!--指定标签-->
                    <imageTags>
                        <imageTag>latest</imageTag>
                    </imageTags>
                    <!-- 指定 Dockerfile 路径-->
                    <dockerDirectory>${project.basedir}</dockerDirectory>
                    <!--指定远程 docker api地址-->
                    <dockerHost>http://39.96.27.148:2375</dockerHost>
                    <!-- 这里是复制 jar 包到 docker 容器指定目录配置 -->
                    <resources>
                        <resource>
                            <targetPath>/</targetPath>
                            <!--jar 包所在的路径  此处配置的 即对应 target 目录-->
                            <directory>${project.build.directory}</directory>
                            <!-- 需要包含的 jar包 ，这里对应的是 Dockerfile中添加的文件名　-->
                            <include>${project.build.finalName}.jar</include>
                        </resource>
                    </resources>
                </configuration>
            </plugin>
        </plugins>
    </build>
```
```xml
<build>
        <finalName>${project.artifactId}</finalName>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
</build>
```

**备注：**  
1.第一种运行方式。用户只需在idea执行mvn package ，就会自动执行mvn docker:build,然后执行idea上面的第一个运行镜像命令就可以  
2.第二种运行方式。直接在idea运行dockerfile，就是在idea执行第二个命令就可以打包并且运行（打包之前运行的是mvn install命令）
3.注意事项：需要注意docker服务器的缓存，插件的权限，dockerfile文件的位置，maven插件的版本等等，反正挺麻烦的...