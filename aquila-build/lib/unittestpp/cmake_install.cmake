# Install script for directory: /Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp

# Set the install prefix
if(NOT DEFINED CMAKE_INSTALL_PREFIX)
  set(CMAKE_INSTALL_PREFIX "/usr/local")
endif()
string(REGEX REPLACE "/$" "" CMAKE_INSTALL_PREFIX "${CMAKE_INSTALL_PREFIX}")

# Set the install configuration name.
if(NOT DEFINED CMAKE_INSTALL_CONFIG_NAME)
  if(BUILD_TYPE)
    string(REGEX REPLACE "^[^A-Za-z0-9_]+" ""
           CMAKE_INSTALL_CONFIG_NAME "${BUILD_TYPE}")
  else()
    set(CMAKE_INSTALL_CONFIG_NAME "")
  endif()
  message(STATUS "Install configuration: \"${CMAKE_INSTALL_CONFIG_NAME}\"")
endif()

# Set the component getting installed.
if(NOT CMAKE_INSTALL_COMPONENT)
  if(COMPONENT)
    message(STATUS "Install component: \"${COMPONENT}\"")
    set(CMAKE_INSTALL_COMPONENT "${COMPONENT}")
  else()
    set(CMAKE_INSTALL_COMPONENT)
  endif()
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/lib" TYPE STATIC_LIBRARY FILES "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/lib/unittestpp/libUnitTest++.a")
  if(EXISTS "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/libUnitTest++.a" AND
     NOT IS_SYMLINK "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/libUnitTest++.a")
    execute_process(COMMAND "/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/ranlib" "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/libUnitTest++.a")
  endif()
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/include/UnitTest++" TYPE FILE FILES
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/AssertException.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/CheckMacros.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/Checks.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/CompositeTestReporter.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/Config.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/CurrentTest.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/DeferredTestReporter.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/DeferredTestResult.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/ExceptionMacros.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/ExecuteTest.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/HelperMacros.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/MemoryOutStream.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/ReportAssert.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/ReportAssertImpl.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/Test.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/TestDetails.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/TestList.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/TestMacros.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/TestReporter.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/TestReporterStdout.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/TestResults.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/TestRunner.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/TestSuite.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/TimeConstraint.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/TimeHelpers.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/UnitTest++.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/UnitTestPP.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/XmlTestReporter.h"
    )
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/include/UnitTest++/Posix" TYPE FILE FILES
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/Posix/SignalTranslator.h"
    "/Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/lib/unittestpp/UnitTest++/Posix/TimeHelpers.h"
    )
endif()

