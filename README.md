# Nome do Projeto

Projeto para disciplina de Programação Paralela e Distribuida.

## Sobre

Sistema de alerta de valores diferente da média ideal do sensor.

Exemplo:

No lado do cliente é possível ver os sensores disponível e do lado do sensor é possível cadastrar novos dispositivos.

## Instalação

Se você quiser rodar o projeto localmente, siga os passos abaixo.

### Pré-requisitos

Antes de começar, você precisará ter instalado em sua máquina:

- [ActiveMQ]([https://nodejs.org/](https://activemq.apache.org/))
- [Java](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html) 

### Passos para instalar

1. Clone o repositório para a sua máquina:

   ```bash
   git clone https://github.com/gabrieloliv96/sensoreMOM

2. No NetBeans ou sua ide de preferencia execute o arquivo sensor para abrir um novo sensor ou o arquivo cliente para abrir um cliente que ouvirá o sensor
3. Inicie o ActiveMQ(Windows)

   ```bash
   cd LOCALARQUIVO\apache-activemq-6.1.5\bin
   .\activemq.bat start
