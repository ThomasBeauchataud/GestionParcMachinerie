# GestionParcMachinerie

###Advice: Use IntellijIdea IDE

###1. Download and configure the CAS
- If u want to use the authentication on the application, you have to donwload and configure the CAS accessible here : https://github.com/ffcfalcos/CAS 

###2. Download GlassFish
- Download a GlassFish 4.1.2 Full Platform server which is available here : https://javaee.github.io/glassfish/download

###3. Clone project
 - Clone the GestionParcMachinerie project

###4. Create database
- Create a MYSQL Database
- Execute _database_creation.sql_ in _src/main/resources/database_ to create a populate tables

###5. Setup context
- Find _web.xml_ in _web/WEB-INF_
- Modify parameters _db-user_, _db-password_ and _db-url_ for your configuration
- Modify cas-url parameter if u previously deployed the cas
- Modify the application domain url

###6. Setup deployment
- Setup jdk on your IDE
- Setup the glassFish server on your IDE
- Deploy the application

###7. Development
- If you developing or extending the application, please what development rules in src/main/resources/development
