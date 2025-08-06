#!/bin/sh

source /p/POC/MT_Prod_Cons/bin/copy.sh

copiar

for items in `seq 1000 1000 10000` ; do
    echo `date +%T` - Ejecutando 01_thread para $items items con 1 hilos
    java -jar ./02_simple_fat.jar --items $items --threads 1

    for threads in `seq 2 2 10`    ; do
        echo `date +%T` - Ejecutando 01_thread para $items items con $threads hilos
        java -jar ./02_simple_fat.jar --items $items --threads $threads
    done
done
