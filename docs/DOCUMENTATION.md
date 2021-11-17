#Documentation

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