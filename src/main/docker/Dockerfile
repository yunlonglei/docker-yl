FROM java:8
VOLUME /tmp
ADD docker_yl.jar docker_yl_app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8082
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/docker_yl_app.jar" ]