# persistence-task

This is the solution for the task that can be found in the root directory with the name "Interview Task.docx"

## instructions

The REST server can be started as any other java application.
Main can be located in PersistenceTaskApplication class.

### default configuration
endpoint: localhost:8080

##
### REST service locations
##
-upload snapshots data: @POST localhost:8080/localhost:8080/post-snapshots

example request body structured as .csv file:

"PRIMARY_KEY,NAME,DESCRIPTION,UPDATED_TIMESTAMP
1,user1,details1,23456789
2,user2,details2,23456789
1,user1,details15,23456789
1,user1,details16,23456789
3,user3,details3,23456789
"

example request:

curl --location --request POST 'localhost:8080/post-snapshots' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'snapshotCsv=PRIMARY_KEY,NAME,DESCRIPTION,UPDATED_TIMESTAMP
1,user1,details1,23456789
2,user2,details2,23456789
1,user1,details15,23456789
1,user1,details16,23456789
3,user3,details3,23456789
'

-get single snapshot by id: @GET localhost:8080/snapshot/{id}

example request:

curl --location --request GET 'localhost:8080/snapshot/1'

-get multiple snapshots centered at the current time with specified duration: @GET localhost:8080/snapshots/{durationSeconds}?page={page} (if a page value is pass only up to 10 results will be return for the requested page)

example requests:

curl --location --request GET 'localhost:8080/snapshots/500000'

curl --location --request GET 'localhost:8080/snapshots/500000?page=1'

-delete snapshot by id: @DELETE localhost:8080/deleteSnapshot

example request:

curl --location --request DELETE 'http://localhost:8080/snapshot?id=1'
