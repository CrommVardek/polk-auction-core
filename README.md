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

## Run nodes and sidecar API

### Polkadot :

 - Create the volume  if it does not exists already :

`docker volume create polkadot-node`

 - Update and run the containers

`docker-compose -f "docker\polkadot\docker-compose.yml" up -d --build`

### Kusama:

 - Create the volume  if it does not exists already :

`docker volume create kusama-node`

 - Update and run the containers

`docker-compose -f "docker\kusama\docker-compose.yml" up -d --build`

## Run Database

`docker-compose -f "docker\database\docker-compose.yml" up -d --build`