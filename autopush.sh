#!/bin/bash


message="$1"
if [ -z "$message" ]; then
    echo "$0: missing commit message"
    exit 1
fi

git add .
git commit -m "$message"
git push -u origin master