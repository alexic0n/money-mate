#!/bin/bash

# This script is used to run the tasks in the background

script_dir=$(dirname "$0")
cd "${script_dir}"

set -e

function usage {
    echo "Usage: $0 <task>"
    echo "Available tasks:"
    echo "$0 start-mongo"
    echo "$0 clear-mongo"
    exit 1
}

if [ "$#" -lt 1 ]; then
    usage
fi

task=$1
shift

case $task in
    start-mongo)
        docker run -it --rm -p 27017:27017 -v money-mate-mongo:/data/db --name money-mate-mongo mongo:5.0.24
        ;;
    clear-mongo)
        docker volume rm money-mate-mongo
        ;;
    *)
        usage
        ;;
esac