#!/bin/bash

set -e

ROOT_DIR=$(cd $(dirname $(dirname $0)) && pwd)
cf uninstall-plugin AutoScaler
cf install-plugin $ROOT_DIR/out/as_plugin