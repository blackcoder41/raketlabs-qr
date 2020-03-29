#!/bin/bash
set -e

export YUI=~/Downloads/yuicompressor-2.4.8.jar
export PROJECT_PATH=$(pwd)

echo "PROJECT PATH IS: " $PROJECT_PATH

java -jar $YUI "$PROJECT_PATH/src/main/resources/static/js/qrcode.js" -o "$PROJECT_PATH/src/main/resources/static/js/qrcode.min.js"
java -jar $YUI "$PROJECT_PATH/src/main/resources/static/js/main.js" -o "$PROJECT_PATH/src/main/resources/static/js/main.min.js"
java -jar $YUI "/Users/mrkorl/Documents/Dev/Spring/core/src/main/resources/static/css/main.css" -o "/Users/mrkorl/Documents/Dev/Spring/core/src/main/resources/static/css/main.min.css"

echo "======================================================"
echo "                    minified"
echo "======================================================"