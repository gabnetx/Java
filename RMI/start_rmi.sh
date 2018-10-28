#!/bin/sh
# Puerto de registro del RMI
PORT=2001
#Si no existe registro del RMI se procede al inicio
if [ `./search_rmi.sh $PORT | wc -l | cut -f1` -ge 1 ]
then
        date
        echo "Ya existe un registro en el puerto ${PORT}"
        exit
else
        #Se inicia el registro del RMI
        nohup date
        nohup rmiregistry $PORT &
        nohup java -Xmx16M -Djava.rmi.server.hostname=192.168.0.10 -Djava.security.policy=java.policy ejemploRMI.Server $PORT &
fi

exit
