# PolkAuctionCore

## Introduction

## Run nodes and sidecar API

For Polkadot :

 - Create the volume  if it does not exists already :

`docker volume create polkadot-node`

 - Update and run the containers

`docker-compose -f "docker\polkadot\docker-compose.yml" up -d --build`

For Kusama:

 - Create the volume  if it does not exists already :

`docker volume create kusama-node`

 - Update and run the containers

`docker-compose -f "docker\kusama\docker-compose.yml" up -d --build`