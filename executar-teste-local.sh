#!/usr/bin/bash

# Use este script para executar testes locais

RESULTS_WORKSPACE="$(pwd)/user-files/results"
GATLING_BIN_DIR=$(pwd)/load-test/gatling/bin
GATLING_WORKSPACE="$(pwd)/load-test/user-files/simulations"

echo GATLING_BIN_DIR  = $GATLING_BIN_DIR
echo RESULTS_WORKSPACE =  $RESULTS_WORKSPACE
echo GATLING_BIN_DIR = $GATLING_BIN_DIR

runGatling() {
    sh $GATLING_BIN_DIR/gatling.sh -rm local -s RinhaBackendCrebitosSimulation \
        -rd "Rinha de Backend - 2024/Q1: Crébito" \
        -rf $RESULTS_WORKSPACE \
        -sf "$GATLING_WORKSPACE"
}

startTest() {
    for i in {1..20}; do
        # 2 requests to wake the 2 api instances up :)
        curl --fail http://localhost:9999/clientes/1/extrato && \
        echo "" && \
        curl --fail http://localhost:9999/clientes/1/extrato && \
        echo "" && \
        runGatling && \
        break || sleep 2;
    done
}

startTest