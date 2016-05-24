#!/bin/sh

##rmiregistry 1098 -Djava.rmi.server.codebase=http://localhos  -Djava.rmi.server.useCodebaseOnly=false 
rmiregistry 1098 -J-Djava.rmi.server.codebase=htt/localhost:1097/  -J-Djava.rmi.server.useCodebaseOnly=false
