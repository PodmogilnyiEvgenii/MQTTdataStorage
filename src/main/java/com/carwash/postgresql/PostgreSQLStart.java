package com.carwash.postgresql;

import com.carwash.main.App;
import com.carwash.options.OptionsGet;


public class PostgreSQLStart {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PostgreSQLWriteCashData.class);

    public static void start() {
        PostgreSQLConnect postgreSQLConnect = new PostgreSQLConnect();
        App.postgreSQLConnection = postgreSQLConnect.connect(OptionsGet.optionsMap);

        PostgreSQLObjectId.carwashMap = PostgreSQLGetObjectId.getCarwashIdMap(App.postgreSQLConnection);
        //PostgreSQLObjectId.printCarwashMap();
        PostgreSQLObjectId.ownerMap = PostgreSQLGetObjectId.getOwnerIdMap(App.postgreSQLConnection);
        //PostgreSQLObjectId.printOwnerMap();
    }
}
