#!/bin/bash

#Atualiza a lista de pacotes e atualiza o sistema
# sudo apt update && sudo apt upgrade -y

#Verifica a versão do Java

curl -O -L "https://github.com/Grupo09-Pixel-Watchers/Pixel-Watchers/raw/main/Sentinel/target/Sentinel-1.0-jar-with-dependencies.jar"

Verifica se o Java não está instalado e, se não estiver, solicita a instalação
if [$? = 0]; 
    then
        echo "Java instalado"
    else
        echo "Java não está instalado. Deseja instalar? (y/n)"
        read get 
        if [ $? == 'y' ]; then
            sudo apt install openjdk-17-jre -y
        fi
fi 

sudo apt install docker.io -y
sudo systemctl start docker
sudo systemctl enable docker

cd /Pixel-Watchers/Sentinel/IaC/mysql/
docker build -t guiscarabelli/bancodedados:latest .

cd ../../
docker build -t guiscarabelli/jarexec:latest .


docker run -d -p 3306:3306 guiscarabelli/bancodedados:latest
sleep 5 
docker run -it guiscarabelli/jarexec:latest

#Baixa o arquivo .jar do meu grupo de PI

#executa o arquivo .jar do meu grupo de PI
java -jar Sentinel-1.0-jar-with-dependencies.jar

chmod +x scriptProjeto.sh
