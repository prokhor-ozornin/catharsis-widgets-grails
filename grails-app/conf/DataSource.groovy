dataSource
{
  pooled = true
  driverClassName = 'org.h2.Driver'
  username = 'sa'
  password = ''

  dbCreate = 'create-drop'
  url = 'jdbc:h2:mem:db;MVCC=TRUE;LOCK_TIMEOUT=10000'
}