#Development rules

###1. General
- When you want to create a new method called from a servlet, you must first create it in the interface before implements it in the class

###2. Models development rules
- All models must be located in the package *models*
- All models must be named as *_TableName_Dao*
- All models must extends *CommonDao*
- All models must implements his own interface named as *_TableNameDao_Interface*
- All interface must extends *CommonDaoInterface<_TableName_>*
- The connection is accessible via *this.driverManager.getConnection()*
- All models class must implements annotation *@ApplicationScoped* and *@Default*

###3. Manager development rules
- All models must be located in the package *manager*
- All managers must be named as *_TableName_Manager*
- All managers must implements his own interface named as *_TableNameManager_Interface*
- All models class must implements annotation *@ApplicationScoped* and *@Default*

###4. Beans development rules
- All beans must be located in the package *beans*
- All models must implements the interface *Serializable*

###5. Servlet development rules
- All servlets must be located in the package *servlets*
