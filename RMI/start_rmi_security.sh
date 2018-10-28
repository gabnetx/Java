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
        nohup java -Xmx16M -Djava.rmi.server.hostname=192.168.0.7 \
-Djava.security.policy=java.policy \
-Dcom.sun.management.jmxremote \
-Dcom.sun.management.jmxremote.local.only=false \
-Dcom.sun.management.jmxremote.port=9005 \
-Dcom.sun.management.jmxremote.password.file=/home/pi/RMI/jmxremote.password \
ejemploRMI.Server $PORT &

fi

exit