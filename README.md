# KeyLocker
private and local password manager tool

CURRENT VERSION: v0.8

IMPORTANT NOTE: To Login, use "username" for username and "password" for password. 
		Requires sqlite-jdbc (jar file included in submission). The jdbc jar
		file needs to be added to the project build path as an external jar file

NOTE: Although the application is not fully functional, all code for missing functionalities
are finished. 80% of the code is finished and most of the work for v1.0 is to put them all together. 

Items to finish before v1.0
- add a new database plus commands
- connect db to the application
- view associated accounts
- add accounts
- registration page 

Items finished at v0.8
- user login
- password class that handles password encryption, decryption, and generation
- database class that handles communication between the application and database
	- creates database on initial run
- account page
- account creator page
- buttons changes current pane
- test for encryption/decryption