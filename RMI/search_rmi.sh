#!/bin/sh
# Busca algun proceso RMI registrado
ps -fea | egrep '(java|rmiregistry)' | grep $1 | grep -v grep
