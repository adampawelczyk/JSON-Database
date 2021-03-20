# Project Name
> JSON-Database

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Code Example](#code-example)
* [Status](#status)

## General info
JSON is a ubiquitous format for exchanging data between web servers and browsers. Its simple design and flexibility make it easy to understand and use in the programming language of your choice. I will create a client-server application that allows the clients to store their data on the server in JSON format.

## Technologies
* java - version 15
* gradle - version 6.7

## Code Example
Starting the server:

```text
> java Main
Server started!
```
Starting the clients:
```text
> java Main -t get -k 1
Client started!
Send: {"type":"get","key":"1"}
Received: {"response":"ERROR","reason":"No such key"}

> java Main -t set -k 1 -v HelloWorld! 
Client started!
Send: {"type":"set","value":"HelloWorld!","key":"1"}
Received: {"response":"OK"}

> java Main -t get -k 1 
Client started!
Send: {"type":"get","key":"1"}
Received: {"response":"OK","value":"HelloWorld!"}

> java Main -t set -k name -v Kate
Client started!
Send: {"type":"set","value":"Kate","key":"name"}
Received: {"response":"OK"}

> java Main -t get -k name
Client started!
Send: {"type":"get","key":"name"}
Received: {"response":"OK","value":"Kate"}

> java Main -t delete -k name
Client started!
Send: {"type":"delete","key":"name"}
Received: {"response":"OK"}

> java Main -t delete -k name
Client started!
Send: {"type":"delete","key":"name"}
Received: {"response":"ERROR","reason":"No such key"}
```

## Status
Project is: _finished_