[spring-boot-maven-plugin插件的作用](https://www.cnblogs.com/acm-bingzi/p/mavenSpringBootPlugin.html)

###1 带 spring-boot-maven-plugin 插件打的包(3KB)

```
first-spring-boot-application-0.0.1-SNAPSHOT
├── META-INF
│   ├── MANIFEST.MF
│   └── maven
│       └── com.majm
│           └── first-spring-boot-application
│               ├── pom.properties
│               └── pom.xml
├── application.properties
└── com
    └── majm
        └── firstspringbootapplication
            └── FirstSpringBootApplication.class

7 directories, 5 files

```

- `BOOT-INF/classes`  存放编译后的 class文件
- `BOOT-INF/lib`  存放依赖的 jar
- `META-INF/`      存放应用相关的元信息
- `org/`          存放springBoot相关的class文件

###2 带 spring-boot-maven-plugin 插件打的包(17.5MB)

```
first-spring-boot-application-0.0.1-SNAPSHOT
├── BOOT-INF
│  ├── classes
│  │  ├── application.properties
│  │  └── com
│  │      └── majm
│  │          └── firstspringbootapplication
│  │              └── FirstSpringBootApplication.class
│  ├── classpath.idx
│  ├── layers.idx
│  └── lib
│      ├── hamcrest-2.2.jar
│      ├── hamcrest-core-2.2.jar
│      ├── jackson-annotations-2.11.4.jar
│      ├── jackson-core-2.11.4.jar
│      ├── jackson-databind-2.11.4.jar
│      ├── jackson-datatype-jdk8-2.11.4.jar
│      ├── jackson-datatype-jsr310-2.11.4.jar
│      ├── jackson-module-parameter-names-2.11.4.jar
│      ├── jakarta.annotation-api-1.3.5.jar
│      ├── jakarta.el-3.0.3.jar
│      ├── jul-to-slf4j-1.7.30.jar
│      ├── junit-4.12.jar
│      ├── log4j-api-2.13.3.jar
│      ├── log4j-to-slf4j-2.13.3.jar
│      ├── logback-classic-1.2.3.jar
│      ├── logback-core-1.2.3.jar
│      ├── slf4j-api-1.7.30.jar
│      ├── snakeyaml-1.27.jar
│      ├── spring-aop-5.3.6.jar
│      ├── spring-beans-5.3.6.jar
│      ├── spring-boot-2.4.5.jar
│      ├── spring-boot-autoconfigure-2.4.5.jar
│      ├── spring-boot-jarmode-layertools-2.4.5.jar
│      ├── spring-context-5.3.6.jar
│      ├── spring-core-5.3.6.jar
│      ├── spring-expression-5.3.6.jar
│      ├── spring-jcl-5.3.6.jar
│      ├── spring-web-5.3.6.jar
│      ├── spring-webmvc-5.3.6.jar
│      ├── tomcat-embed-core-9.0.45.jar
│      └── tomcat-embed-websocket-9.0.45.jar
├── META-INF
│  ├── MANIFEST.MF
│  └── maven
│      └── com.majm
│          └── first-spring-boot-application
│              ├── pom.properties
│              └── pom.xml
└── org
    └── springframework
        └── boot
            └── loader
                ├── ClassPathIndexFile.class
                ├── ExecutableArchiveLauncher.class
                ├── JarLauncher.class
                ├── LaunchedURLClassLoader$DefinePackageCallType.class
                ├── LaunchedURLClassLoader$UseFastConnectionExceptionsEnumeration.class
                ├── LaunchedURLClassLoader.class
                ├── Launcher.class
                ├── MainMethodRunner.class
                ├── PropertiesLauncher$1.class
                ├── PropertiesLauncher$ArchiveEntryFilter.class
                ├── PropertiesLauncher$ClassPathArchives.class
                ├── PropertiesLauncher$PrefixMatchingArchiveFilter.class
                ├── PropertiesLauncher.class
                ├── WarLauncher.class
                ├── archive
                │  ├── Archive$Entry.class
                │  ├── Archive$EntryFilter.class
                │  ├── Archive.class
                │  ├── ExplodedArchive$AbstractIterator.class
                │  ├── ExplodedArchive$ArchiveIterator.class
                │  ├── ExplodedArchive$EntryIterator.class
                │  ├── ExplodedArchive$FileEntry.class
                │  ├── ExplodedArchive$SimpleJarFileArchive.class
                │  ├── ExplodedArchive.class
                │  ├── JarFileArchive$AbstractIterator.class
                │  ├── JarFileArchive$EntryIterator.class
                │  ├── JarFileArchive$JarFileEntry.class
                │  ├── JarFileArchive$NestedArchiveIterator.class
                │  └── JarFileArchive.class
                ├── data
                │  ├── RandomAccessData.class
                │  ├── RandomAccessDataFile$1.class
                │  ├── RandomAccessDataFile$DataInputStream.class
                │  ├── RandomAccessDataFile$FileAccess.class
                │  └── RandomAccessDataFile.class
                ├── jar
                │  ├── AbstractJarFile$JarFileType.class
                │  ├── AbstractJarFile.class
                │  ├── AsciiBytes.class
                │  ├── Bytes.class
                │  ├── CentralDirectoryEndRecord$1.class
                │  ├── CentralDirectoryEndRecord$Zip64End.class
                │  ├── CentralDirectoryEndRecord$Zip64Locator.class
                │  ├── CentralDirectoryEndRecord.class
                │  ├── CentralDirectoryFileHeader.class
                │  ├── CentralDirectoryParser.class
                │  ├── CentralDirectoryVisitor.class
                │  ├── FileHeader.class
                │  ├── Handler.class
                │  ├── JarEntry.class
                │  ├── JarEntryCertification.class
                │  ├── JarEntryFilter.class
                │  ├── JarFile$1.class
                │  ├── JarFile$JarEntryEnumeration.class
                │  ├── JarFile.class
                │  ├── JarFileEntries$1.class
                │  ├── JarFileEntries$EntryIterator.class
                │  ├── JarFileEntries.class
                │  ├── JarFileWrapper.class
                │  ├── JarURLConnection$1.class
                │  ├── JarURLConnection$JarEntryName.class
                │  ├── JarURLConnection.class
                │  ├── StringSequence.class
                │  └── ZipInflaterInputStream.class
                ├── jarmode
                │  ├── JarMode.class
                │  ├── JarModeLauncher.class
                │  └── TestJarMode.class
                └── util
                    └── SystemPropertyUtils.class

19 directories, 103 files

```


###3 FatJar 和 war执行spring-boot-loader模块 

spring-boot-loader



## spring-boot-start-parent &  spring-boot-dependencies



