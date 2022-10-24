PARENT=$(curl -X GET "https://nexus.incode.work/service/rest/v1/search?sort=version&repository=nightly-builds&group=org.apache.causeway.app&name=causeway-app-starter-parent" -H "accept: application/json" -s | jq '.items[0].version' | sed 's/"//g')
echo "parentVersion = $PARENT"
mvn versions:update-parent -DparentVersion="[$PARENT]"

git add pom.xml
git commit -m "updates parent pom to $PARENT"
