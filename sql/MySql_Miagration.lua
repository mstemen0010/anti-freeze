-- ------------------------------------------------------------------
-- MySQL Migration Toolkit - Migration script
-- ------------------------------------------------------------------

-- ------------------------------------------------------------------
-- Initialize the migration environment

Migration:initMigration()

-- set options
doWriteCreateScript= false
doWriteInsertScript= false
grtV.setGlobal("/migration/applicationData/reverseEngineerOnlyTableObjects", 0)


-- ------------------------------------------------------------------
-- checkpoint 0
-- Set source and target connection

do
  -- Set source connection
  print("Set source connection.")

  grtV.setGlobal("/migration/sourceConnection", {
    name= "sourceConn",
    _id= grt.newGuid(),
    driver= "{A8F2E8C2-415A-48C5-B8F8-95EE6E7D4FDB}",
    parameterValues= {
      databaseFile= "G:\\dev\\java\\Projects\\AntiFrieze\\Newer_RTE\\RT.MDB",
      jdbcConnStr= "",
      password= "",
      username= ""
    },
    modules= {
      MigrationModule= "MigrationAccess",
      ReverseEngineeringModule= "ReverseEngineeringAccess",
      TransformationModule= ""
    }
  });

  -- set struct and types
  grtS.set(grtV.getGlobal("/migration/sourceConnection"), "db.mgmt.Connection")
  grtV.setContentType(grtV.getGlobal("/migration/sourceConnection/parameterValues"), "string")
  grtV.setContentType(grtV.getGlobal("/migration/sourceConnection/modules"), "string")

  sourceConn= grtV.getGlobal("/migration/sourceConnection")
  sourceRdbmsName= grtV.toLua(sourceConn.driver.owner.name)

  -- Set target connection
  print("Set target connection.")

  grtV.setGlobal("/migration/targetConnection", {
    name= "targetConn",
    _id= grt.newGuid(),
    driver= "{8E33CDBA-2B8D-4221-96C4-506D398BC377}",
    parameterValues= {
      host= "localhost",
      jdbcConnStr= "",
      password= "udt",
      port= "3306",
      username= "udt"
    },
    modules= {
      MigrationModule= "MigrationMysql",
      ReverseEngineeringModule= "ReverseEngineeringMysqlJdbc",
      TransformationModule= "TransformationMysqlJdbc"
    }
  });

  -- set struct and types
  grtS.set(grtV.getGlobal("/migration/targetConnection"), "db.mgmt.Connection")
  grtV.setContentType(grtV.getGlobal("/migration/targetConnection/parameterValues"), "string")
  grtV.setContentType(grtV.getGlobal("/migration/targetConnection/modules"), "string")

  targetConn= grtV.getGlobal("/migration/targetConnection")
  targetRdbmsName= grtV.toLua(targetConn.driver.owner.name)


  -- Test connections
  print("Test source connection to " .. sourceRdbmsName .. " ...")

  res= grtM.callFunction(grtV.toLua(sourceConn.modules.ReverseEngineeringModule), "getVersion", sourceConn)
  grt.exitOnError("The connection to the source " .. sourceRdbmsName .. " database could not be established.")


  print("Test target connection to " .. targetRdbmsName .. " ...")

  res= grtM.callFunction(grtV.toLua(targetConn.modules.ReverseEngineeringModule), "getVersion", targetConn)
  grt.exitOnError("The connection to the target " .. targetRdbmsName .. " database could not be established.")

  -- store target version for the migration process
  grtV.setGlobal("/migration/targetVersion", res)
end

-- ------------------------------------------------------------------
-- checkpoint 1
-- Do the reverse engineering

do
  print("Reverse engineering " .. sourceRdbmsName .. " ...")

  res= grtM.callFunction(grtV.toLua(sourceConn.modules.ReverseEngineeringModule), "reverseEngineer",
    {sourceConn, {"RT"}}
  )
  grt.exitOnError("The source " .. sourceRdbmsName .. " database could not be reverse engineered")

  grtV.setGlobal("/migration/sourceCatalog", res)
end

-- ------------------------------------------------------------------
-- checkpoint 2
-- Migration methods and ignore list

do
  print("Get available migration methods.")

  res= grtM.callFunction(grtV.toLua(sourceConn.modules.MigrationModule), "migrationMethods", nil)
  grt.exitOnError("The migration methods cannot be fetched.")

  grtV.setGlobal("/migration/migrationMethods", res)


  -- generate an ignore list
  print("Setting up ignore list.")

  grtV.setGlobal("/migration/ignoreList", {""})
end

-- ------------------------------------------------------------------
-- checkpoint 3
-- Set object mappings and to migration

do
  print("Set object mappings.")

  grtV.setGlobal("/migration/mappingDefaults", {
    {
        _id = "{CC4A84DC-49A0-4651-852E-0A235BA0FBD4}", 
        method = "11d1def534ea1be0:-3e85c1e4:122fcec5ac0:-7640-3e85c1e4:122fcec5ac0:-763f", 
        methodName = "migrateTableToMysql", 
        moduleName = "MigrationGeneric", 
        paramGroupName = "Data consistency", 
        params = {
          addAutoincrement = "yes", 
          engine = "INNODB"
        }, 
        sourceStructName = "db.Table"
      },
    {
        _id = "{D7D4031B-4CB3-4DCC-9D69-BAD0903D2252}", 
        method = "11d1def534ea1be0:-3e85c1e4:122fcec5ac0:-7646-3e85c1e4:122fcec5ac0:-7645", 
        methodName = "migrateSchemaToMysql", 
        moduleName = "MigrationGeneric", 
        paramGroupName = "Latin1", 
        params = {
          charset = "latin1", 
          collation = "latin1_swedish_ci"
        }, 
        sourceStructName = "db.Schema"
      }  })

  grtV.setContentType(grtV.getGlobal("/migration/mappingDefaults"), "dict", "db.migration.Mapping")

  grtS.set(grtV.getGlobal("/migration/mappingDefaults/0"), "db.migration.Mapping")
  grtV.setContentType(grtV.getGlobal("/migration/mappingDefaults/0/params"), "string")
  grtS.set(grtV.getGlobal("/migration/mappingDefaults/1"), "db.migration.Mapping")
  grtV.setContentType(grtV.getGlobal("/migration/mappingDefaults/1/params"), "string")


  -- update _ids
  local mappingDefaults= grtV.getGlobal("/migration/mappingDefaults")
  local migrationMethods= grtV.getGlobal("/migration/migrationMethods")
  for i= 1, grtV.getn(mappingDefaults) do
    for j= 1, grtV.getn(migrationMethods) do
      if grtV.toLua(mappingDefaults[i].moduleName) == grtV.toLua(migrationMethods[j].moduleName) and
        grtV.toLua(mappingDefaults[i].moduleName) == grtV.toLua(migrationMethods[j].moduleName) then
          mappingDefaults[i].method= migrationMethods[j]
      end
    end
  end

  print("Do the migration.")

  grtM.callFunction(
    grtV.toLua(sourceConn.modules.MigrationModule), "migrate",
    {"global::/migration", "global::/rdbmsMgmt/rdbms/" .. grtV.toLua(targetConn.driver.owner.name),
      "global::/migration/targetVersion"
    }
  )
  grt.exitOnError("The source objects cannot be migrated.")
end

-- ------------------------------------------------------------------
-- checkpoint 4
-- Generate and execute sql create statements

do
  print("Generate sql create statements.")

  -- Set migration options.
  grtV.setGlobal("/migration/objectCreationParams/AppName", "MySQL Migration Toolkit")
  grtV.setGlobal("/migration/objectCreationParams/GenerateUseSchemaCommand", "yes")
  grtV.setGlobal("/migration/objectCreationParams/KeepSchema", "yes")
  grtV.setGlobal("/migration/objectCreationParams/ScriptType", "SQL Create Script")

  grtM.callFunction(
    grtV.toLua(targetConn.modules.TransformationModule), "generateSqlCreateStatements",
    {"global::/migration/targetCatalog",
      grtV.getGlobal("/migration/objectCreationParams")
    }
  )
  grt.exitOnError("The SQL create statements cannot be created.")

  -- write sql create script to file
  if doWriteCreateScript then
    print("Write create script.")

    res= grtM.callFunction(
      grtV.toLua(targetConn.modules.TransformationModule), "getSqlScript",
      {"global::/migration/targetCatalog"}
    )
    grt.exitOnError("The SQL create script cannot be created.")

    local f= io.open("creates.sql", "w+")
    if f ~= nil then
      f:write(grtV.toLua(res))

      f:flush()
      f:close()
    end
  end

  -- create database objects online
  print("Create database objects.")

  grtM.callFunction(
    grtV.toLua(targetConn.modules.TransformationModule), "executeSqlStatements",
    {targetConn, "global::/migration/targetCatalog", "global::/migration/creationLog"}
  )
  grt.exitOnError("The SQL create script cannot be executed.")
end


-- ------------------------------------------------------------------
-- checkpoint 5
-- Bulk data transfer

do
  -- set transfer parameters
  grtV.setGlobal("/migration/dataBulkTransferParams", {
      CreateScript= doWriteInsertScript and "yes" or "no",
      ScriptFileName="inserts.sql"
    }
  )

  grtV.setContentType(grtV.getGlobal("/migration/dataBulkTransferParams"), "string")

  print("Execute bulk data transfer")

  grtM.callFunction(
    grtV.toLua(sourceConn.modules.MigrationModule), "dataBulkTransfer",
    {sourceConn, "global::/migration/sourceCatalog",
      targetConn, "global::/migration/targetCatalog",
      "global::/migration/dataBulkTransferParams",
      "global::/migration/dataTransferLog"
    }
  )
  grt.exitOnError("The bulk data transfer returned an error.")

  print("Migration completed.")
end

-- ------------------------------------------------------------------
