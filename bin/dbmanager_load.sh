#!/bin/sh

source /p/POC/MT_POC/bin/copy.sh

copiar

for rows in `seq 100 100 1000`; do
    for threads in `seq 1 25` ; do
        for chunk in `seq 1 10 100` ; do
            echo Cargando $rows M datos con $threads hilos;
            java -jar 15_db_manager.jar -init
            java -jar 15_db_manager.jar -load --rows $rows --threads $threads --chunk $chunk
        done
    done
done
