#!/bin/sh

# multiple records for test VIN

curl \
    -i \
    -H "Content-type: application/json" \
    -X POST \
    -d '{"driveid": 1, "timestamp":1234567890, "latitude":40.0, "longitude":-8.1, "readings":{"speed":"55","rpm":"3000"}}' \
    "http://localhost:8080/newreadingPOST"
