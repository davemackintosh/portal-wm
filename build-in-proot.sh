#!/bin/bash

set -eof

yes | sdkmanager --licenses

cd /root/www/kotlin/desk && ./gradlew assemble
