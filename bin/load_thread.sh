#!/bin/sh

source /p/POC/MT_POC/bin/copy.sh

copiar

for items in `seq 1000 1000 10000` ; do
    for threads in 0 1    ; do
        echo `date +%T` - Ejecutando 01_thread para $items items con $threads hilos
        java -jar ./01_thread_fat.jar --items $items --threads $threads
    done
    for threads in `seq 2 2 10`    ; do
        echo `date +%T` - Ejecutando 01_thread para $items items con $threads hilos
        java -jar ./01_thread_fat.jar --items $items --threads $threads
    done
done

