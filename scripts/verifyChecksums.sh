#!/bin/bash
# This is a script to verify the checksums of the generated binaries.
# Run this after running the 'assemble' task, and compare it with versions uploaded on bintray.

# CORE
echo "Enter the checksum for core aar"
read coreChecksum

actualCoreChecksum=($(shasum -a 256 core/build/outputs/aar/core-release.aar))

if [ "$coreChecksum" = "$actualCoreChecksum" ]; then
  echo "VALID: core checksum"
else
  echo "INVALID: core checksum. Expected: $actualCoreChecksum"
fi

echo ""

# SERVICE
echo "Enter the checksum for service aar"
read serviceChecksum

actualServiceChecksum=($(shasum -a 256 service/build/outputs/aar/service-release.aar))

if [ "$serviceChecksum" = "$actualServiceChecksum" ]; then
  echo "VALID: service checksum"
else
  echo "INVALID: service checksum. Expected: $actualServiceChecksum"
fi

echo ""

# REST
echo "Enter the checksum for rest aar"
read restChecksum

actualRestChecksum=($(shasum -a 256 rest/build/libs/rest.jar))

if [ "$restChecksum" = "$actualRestChecksum" ]; then
  echo "VALID: rest checksum"
else
  echo "INVALID: rest checksum. Expected: $actualRestChecksum"
fi

echo ""

# MODELS
echo "Enter the checksum for models aar"
read modelsChecksum

actualModelsChecksum=($(shasum -a 256 models/build/outputs/aar/models-release.aar))

if [ "$modelsChecksum" = "$actualModelsChecksum" ]; then
  echo "VALID: models checksum"
else
  echo "INVALID: models checksum. Expected: $actualModelsChecksum"
fi

exit 0
