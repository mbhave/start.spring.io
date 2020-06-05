#!/bin/bash
set -e

repository=$(pwd)/distribution-repository
pushd git-repo
./mvnw clean deploy -DskipTests -U -DaltDeploymentRepository=distribution::default::file://${repository}
popd