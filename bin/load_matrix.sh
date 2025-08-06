#!/bin/sh

source /p/POC/MT_POC/bin/copy.sh

copiar

for size in `seq 100 100 500`; do
    for threads in `seq 1 10` ; do
        echo Procesando matrix de $size con $threads hilos;
        java -jar 03_matrix_fat.jar --rows $size --threads $threads
    done
done
