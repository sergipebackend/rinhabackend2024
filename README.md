## Tecnologias utilizadas

* Java 21
* GraalVM
* Docker
* Spring WebFlux
* Database R2DBC
* Springboot 3.2

## Como executar o projeto

#### 1. Construir a image utilizando GraalVM
```docker
$ mvn -Pnative spring-boot:build-image
```

#### 2. Executando a imagem gerada
```
$ docker run --rm -p 8080:8080 docker.io/library/rinhabackend:0.0.1-SNAPSHOT
```

#### 3. Executando o docker-compose
````
$ docker-compose up
````

#### 4. Como enviar a imagem para o DockerHub
````
$ docker login
$ docker push  [nome da imagem:versao]
````