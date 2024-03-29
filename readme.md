# Address Decoder


This code takes address string and separates street name from house number.

All cases can not be automated. Code can work with the simple ones:

 - Street name and house number;
 - Street name and house number with letter;
 - Street number with name and house number.


### Test Data
Test data are stored in JSON file (data/test_data.json)
 - input - the address string before decoding;
 - street and housenumber - expected results after decoding;
 
Code takes the input string, decodes it and compares with the expected relevant values from 
test_data.json.

Test data are feeded one by one to the testcase and processed.
There are 2 failing cases added on purpose to verify if the code works properly.

    Example of test data for the testcase:
    
    ```
    {
      "input": "Winterallee 3",
      "street": "Winterallee",
      "housenumber": "3"
    }
    ```
    
    
### Reports
Reports are generated automatically and stored in
 target/surefire-reports/emailable-report.html
    
    
### Tools used:
 - Java 8
 - TestNg
 - Maven
 - Json, Gson