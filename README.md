# PolkAuction Core

## Introduction

### What is PolkAuction Core ?
PolkAuction core is an HTTP API providing a set of endpoints providing data from Polkadot and Kusama relay-chains. Those data are focused on comprehensive parachain, crowdloan and auction information for any app, user, client to use.
The data provided by the API are both from on-chain and off-chain. 

### Technical stack

- Kotlin
- Ktor
- Koin
- Exposed

## Requirements

### To be installed
 - Docker (version 19.03+)
 - Java (version 14+ recommended)
 - Kotlin (1.4+)
 - Maven (3.6+)

## Contributions, issues and PR

We are open to contributions, ideas, issues, etc. Feel free to open a PR or an issue.

For both the issue and the PR, a context is necessary.

## Run

In order to run Polk-auction-core, a few things need to be running before:

### Polkadot Node and sidecar API :

 - Create the volume  if it does not exist already :

`docker volume create polkadot-node`

 - Update and run the containers

`docker-compose -f "docker\polkadot\docker-compose.yml" up -d --build`

### Kusama Node and sidecar API :

 - Create the volume  if it does not exist already :

`docker volume create kusama-node`

 - Update and run the containers

`docker-compose -f "docker\kusama\docker-compose.yml" up -d --build`

### Database

`docker-compose -f "docker\database\docker-compose.yml" up -d --build`

## Build

### Package locally

`mvn clean install`

### Generate a docker image

`docker build -t polk-auction-core .`

### Run the image

`docker run --rm -it -p "127.0.1.1:8080:8080" polk-auction-core`