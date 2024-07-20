#!/bin/bash

build_image() {
    docker build -t java-dev .
}

run_container() {
    docker run -it --rm -v "$(pwd)":/usr/src/app java-dev
}

if [ "$1" == "build" ]; then
    build_image
    run_container
else
    run_container
fi
