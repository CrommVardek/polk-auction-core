# TODO

## Build

## DB

- Remove leasePeriod, as the dateTime (starts and end) can be derived either from the block timeStamp itself or (for future blocks) from the head block difference. 

## Design

## Features

- Use Unix Epoch time as timestamp for leasePeriod starts and end (is starts necessary ?)
- Use leasePeriod in Parachain and Auction models

## Misc

- Shift the deposit, raised, etc. values (expressed in planck) to their token decimal values (?)
