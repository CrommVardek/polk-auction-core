[![Apache 2.0 license](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](./LICENSE)

<img align="right" width="420" src="https://raw.githubusercontent.com/w3f/General-Grants-Program/master/src/badge_black.svg">

# PolkAuction Core

## Introduction

### What is PolkAuction Core ?
PolkAuction core is an HTTP API exposing a set of endpoints providing data from Polkadot and Kusama relay-chains. Those data are focused on comprehensive parachain, crowdloan and auction information for any app, user, client to use.
The data provided by the API are both from on-chain and off-chain. 

### Technical stack

- Kotlin
- Ktor
- Koin
- Exposed
- MySQL

## Requirements

### To be installed

 - Docker (version 19.03+)
 - Java (version 17+)
 - Kotlin (1.6+)
 - Maven (3.8+ recommended)

## Contributions, issues and PR

We are open to contributions, ideas, issues, etc. Feel free to open a PR or an issue.

For both the issue and the PR, a context is necessary.

## Run

In order to run Polk-auction-core, a few things need to be running before:

### Polkadot Node and sidecar API

- Create the network if it does not exist already :

    `docker network create polkadot`

>Note: You can use the sidecar API with either your own node or with a node with a public endpoint. Nodes that are running from a new volume will need quite some time to update the blockchain until its current head block. This is why public nodes are used in the configuration.
For testing and quick usage we recommend using the public node (see [here](#run-with-public-node)).
If you wish to use the sidecar API with your own node instead, refer to [this section](#run-with-own-node).


#### Run with public node

- Update and run the container

  `docker-compose -f "docker/polkadot/docker-compose.yml" up -d --build sidecar`

#### Run with own node

 - Create the volume if it does not exist already :

    `docker volume create polkadot-node`

 - Change the value of the `SAS_SUBSTRATE_WS_URL` environment variable to `ws://polkadot-node:9944` (located [here](./docker/polkadot/docker-compose.yml))

 - Update and run the containers

    `docker-compose -f "docker/polkadot/docker-compose.yml" up -d --build`

### Kusama Node and sidecar API

- Create the network if it does not exist already :

    `docker network create kusama`

>Note: You can use the sidecar API with either your own node or with a node with a public endpoint. Nodes that are running from a new volume will need quite some time to update the blockchain until its current head block. This is why public nodes are used in the configuration.
For testing and quick usage we recommend using the public node (see [here](#run-with-public-node-1)).
If you wish to use the sidecar API with your own node instead, refer to [this section](#run-with-own-node-1).

#### Run with public node

- Update and run the containers
    
  `docker-compose -f "docker/kusama/docker-compose.yml" up -d --build sidecar`

#### Run with own node

 - Create the volume  if it does not exist already :

     `docker volume create kusama-node`

 - Change the value of the `SAS_SUBSTRATE_WS_URL` environment variable to `ws://kusama-node:9944` (located [here](./docker/kusama/docker-compose.yml))

 - Update and run the containers

   `docker-compose -f "docker/kusama/docker-compose.yml" up -d --build`

### Database

- Create the network if it does not exist already :

    `docker network create database`

- Update and run the containers

   `docker-compose -f "docker/database/docker-compose.yml" up -d --build`

### SSL Configuration

Before running locally, you will need to generate an SSL certificate. With the current configuration you can run the following command to generate a self-signed certificate :

`keytool -keystore keystore.jks -alias polkauctionStore -keypass foobar -storepass foobar -genkeypair -keyalg RSA -keysize 4096 -validity 36500 -dname 'CN=localhost, OU=ktor, O=ktor, L=Unspecified, ST=Unspecified, C=US'`

## Build

### With Docker
#### Build and Generate a docker image

`docker build -t polk-auction-core .`

#### Run the image

`docker-compose -f "docker/polkauction-core/docker-compose.yml" up -d --build`

### Locally

`mvn clean install`

## Tests

To run the test, run the following command :

`mvn test`

## Usage

Once everything is running fine, you can access the API using the following url : http://localhost:8080 (if running locally or on docker - can be changed in the application configuration or using an environment variable). Refer to the documentation for the available endpoints.

### Documentation

[See Documentation file](./docs/DOCUMENTATION.md)
