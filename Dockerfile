FROM openjdk:8

RUN gpg --keyserver pgp.mit.edu --recv-keys 6b1b9be54c155617 && chmod -R 777 /root/.gnupg

WORKDIR /opt