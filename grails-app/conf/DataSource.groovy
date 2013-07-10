dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
    username = "root"  
    password = ""
}
mongo {
	host = "localhost"
	port = 27107
	username = ""
	password=""
	databaseName = ""
  }
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
//            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            dbCreate = "create-drop"
            url = "jdbc:mysql://127.0.0.1:3306/search"
        }
    }
    test {
        dataSource {
          //  dbCreate = "update"
            dbCreate = "create-drop"
            //url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            url = "jdbc:mysql://127.0.0.1:3306/search"
        }
    }
    production {
        dataSource {
//            dbCreate = "update"
            dbCreate = "create-drop"
            //url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            url = "jdbc:mysql://127.0.0.1:3306/search"
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}