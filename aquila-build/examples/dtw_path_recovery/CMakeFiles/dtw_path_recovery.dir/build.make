# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.5

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = "/Applications/CMake 2.app/Contents/bin/cmake"

# The command to remove a file.
RM = "/Applications/CMake 2.app/Contents/bin/cmake" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/admin/Desktop/3D_VST_Plugin/aquila-build

# Include any dependencies generated for this target.
include examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/depend.make

# Include the progress variables for this target.
include examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/progress.make

# Include the compile flags for this target's objects.
include examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/flags.make

examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.o: examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/flags.make
examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.o: aquila-src/examples/dtw_path_recovery/dtw_path_recovery.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/admin/Desktop/3D_VST_Plugin/aquila-build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.o"
	cd /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/dtw_path_recovery && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++   $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.o -c /Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/examples/dtw_path_recovery/dtw_path_recovery.cpp

examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.i"
	cd /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/dtw_path_recovery && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/examples/dtw_path_recovery/dtw_path_recovery.cpp > CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.i

examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.s"
	cd /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/dtw_path_recovery && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/examples/dtw_path_recovery/dtw_path_recovery.cpp -o CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.s

examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.o.requires:

.PHONY : examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.o.requires

examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.o.provides: examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.o.requires
	$(MAKE) -f examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/build.make examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.o.provides.build
.PHONY : examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.o.provides

examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.o.provides.build: examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.o


# Object files for target dtw_path_recovery
dtw_path_recovery_OBJECTS = \
"CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.o"

# External object files for target dtw_path_recovery
dtw_path_recovery_EXTERNAL_OBJECTS =

examples/dtw_path_recovery/dtw_path_recovery: examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.o
examples/dtw_path_recovery/dtw_path_recovery: examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/build.make
examples/dtw_path_recovery/dtw_path_recovery: libAquila.a
examples/dtw_path_recovery/dtw_path_recovery: lib/libOoura_fft.a
examples/dtw_path_recovery/dtw_path_recovery: examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/admin/Desktop/3D_VST_Plugin/aquila-build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable dtw_path_recovery"
	cd /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/dtw_path_recovery && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/dtw_path_recovery.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/build: examples/dtw_path_recovery/dtw_path_recovery

.PHONY : examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/build

examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/requires: examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/dtw_path_recovery.cpp.o.requires

.PHONY : examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/requires

examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/clean:
	cd /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/dtw_path_recovery && $(CMAKE_COMMAND) -P CMakeFiles/dtw_path_recovery.dir/cmake_clean.cmake
.PHONY : examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/clean

examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/depend:
	cd /Users/admin/Desktop/3D_VST_Plugin/aquila-build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src /Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/examples/dtw_path_recovery /Users/admin/Desktop/3D_VST_Plugin/aquila-build /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/dtw_path_recovery /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : examples/dtw_path_recovery/CMakeFiles/dtw_path_recovery.dir/depend
