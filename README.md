PostalCodeSorter
================

Takes a large database of postalcodes (~750, 000) and sorts them four different ways (By city, code, latitude, and longitude). Exercise in O(n) concerns and jUnit testing.

Classes-

FXLauncer:
Handles the visual presentation of the sort, displays them in four observableLists. 

PostalCode:
Used to store one individual postal code, with all it's variables. 

PostalCodeIndex:
Creates PostalCode objects, parses each line in the raw .csv files, and then adds that object to a collection. 
