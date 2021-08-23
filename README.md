[![Apache 2.0 license](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](./LICENSE)

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
 - Java (version 14+ recommended)
 - Kotlin (1.4+)
 - Maven (3.6+)

## Contributions, issues and PR

We are open to contributions, ideas, issues, etc. Feel free to open a PR or an issue.

For both the issue and the PR, a context is necessary.

## Run

In order to run Polk-auction-core, a few things need to be running before:

### Polkadot Node and sidecar API :

Note: nodes that are running from a new volume will need quite some time to update the blockchain until its current head block. 
If you wish to use the sidecar API with an up-to-date node, change the `SAS_SUBSTRATE_WS_URL` parameter in the docker-compose files ([kusama docker-compose](./docker/kusama/docker-compose.yml), [polkadot docker-compose](./docker/polkadot/docker-compose.yml)) to another node endpoint.

 - Create the volume if it does not exist already :

`docker volume create polkadot-node`

- Create the network if it does not exist already :

`docker network create polkadot`

 - Update and run the containers

`docker-compose -f "docker\polkadot\docker-compose.yml" up -d --build`

### Kusama Node and sidecar API :

 - Create the volume  if it does not exist already :

`docker volume create kusama-node`

- Create the network if it does not exist already :

`docker network create kusama`

 - Update and run the containers

`docker-compose -f "docker\kusama\docker-compose.yml" up -d --build`

### Database

- Create the network if it does not exist already :

`docker network create database`

- Update and run the containers

`docker-compose -f "docker\database\docker-compose.yml" up -d --build`

## Build

### With Docker
#### Build and Generate a docker image

`docker build -t polk-auction-core .`

#### Run the image

`docker-compose -f "docker\polkauction-core\docker-compose.yml" up -d --build`

### Locally

`mvn clean install`

## Tests

To run the test, run the following command :

`mvn test`

## Usage

Once everything is running fine, you can access the API using the following url : http://localhost:8080 (can be changed in the application configuration or using an environment variable)

### Available endpoints

- (GET) `parachain\{chain}` :
{chain} parameter is a valid and supported relay chain of the Polkadot EcoSystem. Currently only Polkadot and Kusama are valid parameters.
Returns an array following the schema: 
```
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "array",
  "items": [
    {
      "type": "object",
      "properties": {
        "parachain": {
          "type": "object",
          "properties": {
            "paraId": {
              "type": "integer"
            },
            "parachainLifeCycle": {
              "type": "string"
            },
            "onboardingAs": {
              "type": "string"
            },
            "currentLeases": {
              "type": "array",
              "items": [
                {
                  "type": "object",
                  "properties": {
                    "leaseIndexPeriod": {
                      "type": "string"
                    },
                    "account": {
                      "type": "string"
                    },
                    "deposit": {
                      "type": "number"
                    }
                  }
                }
              ]
            }
          },
        },
        "parachainName": {
          "type": ["string", "null"]
        },
        "website": {
          "type": ["string", "null"]
        },
        "polkadotJsExplorerUrl": {
          "type": ["string", "null"]
        },
        "relayChainName": {
          "type": ["string", "null"]
        }
      },
    }
  ]
}
```

- (GET) `parachain\{chain}\{id}` :
  {chain} parameter is a valid and supported relay chain of the Polkadot EcoSystem. Currently only Polkadot and Kusama are valid parameters.
  {id} parameter is a valid existing parachainId (example within the Kusama ecosystem, 1000 is the parachain id of Statemine)
  Returns an object following the schema: 
  
```
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "properties": {
    "parachain": {
      "type": "object",
      "properties": {
        "paraId": {
          "type": "integer"
        },
        "parachainLifeCycle": {
          "type": "string"
        },
        "onboardingAs": {
          "type": "string"
        },
        "currentLeases": {
          "type": "array",
          "items": [
            {
              "type": "object",
              "properties": {
                "leaseIndexPeriod": {
                  "type": "string"
                },
                "account": {
                  "type": "string"
                },
                "deposit": {
                  "type": "number"
                }
              }
            }
          ]
        }
      },
    },
    "parachainName": {
      "type": ["string", "null"]
    },
    "website": {
      "type": ["string", "null"]
    },
    "polkadotJsExplorerUrl": {
      "type": ["string", "null"]
    },
    "relayChainName": {
      "type": ["string", "null"]
    }
  }
}
```

- (GET) `crowdloan\{chain}` :
  {chain} parameter is a valid and supported relay chain of the Polkadot EcoSystem. Currently only Polkadot and Kusama are valid parameters.
  Returns an object following the schema:

```
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "properties": {
    "funds": {
      "type": "array",
      "items": [
        {
          "type": "object",
          "properties": {
            "parachainId": {
              "type": "integer"
            },
            "parachainName": {
              "type": ["string", "null"]
            },
            "website": {
              "type": ["string", "null"]
            },
            "polkadotJsExplorerUrl": {
              "type": ["string", "null"]
            },
            "fundInfo": {
              "type": "object",
              "properties": {
                "depositor": {
                  "type": "string"
                },
                "verifier": {
                  "type": ["string", "null"]
                },
                "deposit": {
                  "type": "integer"
                },
                "raised": {
                  "type": "integer"
                },
                "end": {
                  "type": "integer"
                },
                "cap": {
                  "type": "integer"
                },
                "firstPeriod": {
                  "type": "string"
                },
                "lastPeriod": {
                  "type": "string"
                },
                "trieIndex": {
                  "type": "string"
                }
              }
            }
          }
        }
      ]
    }
  }
}
```

- (GET) `auction\{chain}` :
  {chain} parameter is a valid and supported relay chain of the Polkadot EcoSystem. Currently only Polkadot and Kusama are valid parameters.
  Returns an object following the schema: 

```
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "properties": {
    "beginEnd": {
      "type": "string"
    },
    "finishEnd": {
      "type": "string"
    },
    "phase": {
      "type": "string"
    },
    "auctionIndex": {
      "type": "integer"
    },
    "leasePeriods": {
      "type": "array",
      "items": [
        {
          "type": "object",
          "properties": {
            "leaseIndexPeriod": {
              "type": "string"
            },
            "account": {
              "type": "string"
            },
            "deposit": {
              "type": "number"
            }
          }
        }
      ]
    },
    "currentWinning": {
      "type": "array",
      "items": [
        {
          "type": "object",
          "properties": {
            "description": {
              "type": "string"
            },
            "bid": {
              "type": "object",
              "properties": {
                "accountId": {
                  "type": "string"
                },
                "parachainId": {
                  "type": "integer"
                },                
                "amount": {
                  "type": "number"
                },                
                "parachainName": {
                  "type": ["string", "null"]
                },                
                "website": {
                  "type": ["string", "null"]
                },                 
                "polkadotJsExplorerUrl": {
                  "type": ["string", "null"]
                }, 
              }
            },
            "leases": {
              "type": "array",
              "items": [
                {
                  "type": "string"
                }
              ]
            }
          }
        }
      ]
    }
  }
}
```

- (GET) `runtime\{chain}` :
  {chain} parameter is a valid and supported relay chain of the Polkadot EcoSystem. Currently only Polkadot and Kusama are valid parameters.
  Returns an object following the schema: 
  
```
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "properties": {
    "name": {
      "type": "string"
    },
    "version": {
      "type": "string"
    },
    "properties": {
      "type": "object",
      "properties": {
        "tokenDecimal": {
          "type": "integer"
        },
        "tokenSymbol": {
          "type": "string"
        }
      }
    }
  }
}
```