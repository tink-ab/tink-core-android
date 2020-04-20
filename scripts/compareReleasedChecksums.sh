#!/bin/bash
echo "input version: "
read version

names=(core models service rest)
artifacts=(aar aar aar jar)

for index in ${!names[*]}; do
  name=${names[$index]}
  a=${artifacts[$index]}
  github=$(curl -L -s https://github.com/tink-ab/tink-core-android/releases/download/$version/$name.sha256)
  bintray=$(curl -s https://dl.bintray.com/tink/tink-core/com/tink/$name/$version/$name-$version.$a.sha256)

  echo "$name checksum on github: $github"
  echo "$name checksum on bintray: $bintray"

  if [ "$github" == "$bintray" ]; then
    echo "👍 Checksums for $name match"
  else
    echo "❗️Checksums for $name don't match"
    exit 1
  fi
done
