cd ..

# shellcheck disable=SC2162
read -p "New version number: " version

rm -r /Users/"$USER"/.m2/repository/com/tink/core/"$version"

./gradlew clean
./gradlew assemble
./gradlew publishToMavenLocal

echo Maven Local Files
echo Check if every file has a signed file with the same filename, \
including extension, and an additional .asc file extension
ls -R /Users/"$USER"/.m2/repository/com/tink/core/"$version"