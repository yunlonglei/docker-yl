# docker_yl
集成Docker的一个简单练习项目，将上传github
## docker运行命令
1 运行一个镜像，来生成一个容器  
```docker
docker run -p 8082:8080 --name my-docker_yl_hello leiyunlong/docker_yl:latest 
```
2 打包一个文件生成镜像，然后运行这个镜像
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