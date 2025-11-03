@rem ----------------------------------------------------------------------------
@rem Licensed to the Apache Software Foundation (ASF) under one
@rem or more contributor license agreements.  See the NOTICE file
@rem distributed with this work for additional information
@rem regarding copyright ownership.  The ASF licenses this file
@rem to you under the Apache License, Version 2.0 (the
@rem "License"); you may not use this file except in compliance
@rem with the License.  You may obtain a copy of the License at
@rem
@rem    https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing,
@rem software distributed under the License is distributed on an
@rem "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@rem KIND, either express or implied.  See the License for the
@rem specific language governing permissions and limitations
@rem under the License.
@rem ----------------------------------------------------------------------------

@echo off
setlocal

set ERROR_CODE=0

set "MAVEN_PROJECTBASEDIR=%cd%"
if exist "%MAVEN_PROJECTBASEDIR%\.mvn" goto endDetectBaseDir
for /f "usebackq delims=" %%i in (`cd`) do set "MAVEN_PROJECTBASEDIR=%%i"
:endDetectBaseDir

if not exist "%MAVEN_PROJECTBASEDIR%\.mvn\jvm.config" goto endReadAdditionalConfig
for /F "usebackq delims=" %%a in ("%MAVEN_PROJECTBASEDIR%\.mvn\jvm.config") do set JVM_CONFIG_MAVEN_PROPS=!JVM_CONFIG_MAVEN_PROPS! %%a
:endReadAdditionalConfig

set WRAPPER_JAR="%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar"
set WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

if not defined JAVA_HOME (
  echo.
  echo Error: JAVA_HOME is not set.
  echo.
  goto end
)

"%JAVA_HOME%\bin\java.exe" %JVM_CONFIG_MAVEN_PROPS% %MAVEN_OPTS% -classpath %WRAPPER_JAR% "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" %WRAPPER_LAUNCHER% %*
if ERRORLEVEL 1 set ERROR_CODE=1

:end
@endlocal & exit /B %ERROR_CODE%
