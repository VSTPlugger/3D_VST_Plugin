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
include examples/sine_generator/CMakeFiles/sine_generator.dir/depend.make

# Include the progress variables for this target.
include examples/sine_generator/CMakeFiles/sine_generator.dir/progress.make

# Include the compile flags for this target's objects.
include examples/sine_generator/CMakeFiles/sine_generator.dir/flags.make

examples/sine_generator/CMakeFiles/sine_generator.dir/sine_generator.cpp.o: examples/sine_generator/CMakeFiles/sine_generator.dir/flags.make
examples/sine_generator/CMakeFiles/sine_generator.dir/sine_generator.cpp.o: aquila-src/examples/sine_generator/sine_generator.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/admin/Desktop/3D_VST_Plugin/aquila-build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object examples/sine_generator/CMakeFiles/sine_generator.dir/sine_generator.cpp.o"
	cd /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/sine_generator && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++   $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/sine_generator.dir/sine_generator.cpp.o -c /Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/examples/sine_generator/sine_generator.cpp

examples/sine_generator/CMakeFiles/sine_generator.dir/sine_generator.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/sine_generator.dir/sine_generator.cpp.i"
	cd /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/sine_generator && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/examples/sine_generator/sine_generator.cpp > CMakeFiles/sine_generator.dir/sine_generator.cpp.i

examples/sine_generator/CMakeFiles/sine_generator.dir/sine_generator.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/sine_generator.dir/sine_generator.cpp.s"
	cd /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/sine_generator && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/examples/sine_generator/sine_generator.cpp -o CMakeFiles/sine_generator.dir/sine_generator.cpp.s

examples/sine_generator/CMakeFiles/sine_generator.dir/sine_generator.cpp.o.requires:

.PHONY : examples/sine_generator/CMakeFiles/sine_generator.dir/sine_generator.cpp.o.requires

examples/sine_generator/CMakeFiles/sine_generator.dir/sine_generator.cpp.o.provides: examples/sine_generator/CMakeFiles/sine_generator.dir/sine_generator.cpp.o.requires
	$(MAKE) -f examples/sine_generator/CMakeFiles/sine_generator.dir/build.make examples/sine_generator/CMakeFiles/sine_generator.dir/sine_generator.cpp.o.provides.build
.PHONY : examples/sine_generator/CMakeFiles/sine_generator.dir/sine_generator.cpp.o.provides

examples/sine_generator/CMakeFiles/sine_generator.dir/sine_generator.cpp.o.provides.build: examples/sine_generator/CMakeFiles/sine_generator.dir/sine_generator.cpp.o


# Object files for target sine_generator
sine_generator_OBJECTS = \
"CMakeFiles/sine_generator.dir/sine_generator.cpp.o"

# External object files for target sine_generator
sine_generator_EXTERNAL_OBJECTS =

examples/sine_generator/sine_generator: examples/sine_generator/CMakeFiles/sine_generator.dir/sine_generator.cpp.o
examples/sine_generator/sine_generator: examples/sine_generator/CMakeFiles/sine_generator.dir/build.make
examples/sine_generator/sine_generator: libAquila.a
examples/sine_generator/sine_generator: lib/libOoura_fft.a
examples/sine_generator/sine_generator: examples/sine_generator/CMakeFiles/sine_generator.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/admin/Desktop/3D_VST_Plugin/aquila-build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable sine_generator"
	cd /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/sine_generator && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/sine_generator.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
examples/sine_generator/CMakeFiles/sine_generator.dir/build: examples/sine_generator/sine_generator

.PHONY : examples/sine_generator/CMakeFiles/sine_generator.dir/build

examples/sine_generator/CMakeFiles/sine_generator.dir/requires: examples/sine_generator/CMakeFiles/sine_generator.dir/sine_generator.cpp.o.requires

.PHONY : examples/sine_generator/CMakeFiles/sine_generator.dir/requires

examples/sine_generator/CMakeFiles/sine_generator.dir/clean:
	cd /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/sine_generator && $(CMAKE_COMMAND) -P CMakeFiles/sine_generator.dir/cmake_clean.cmake
.PHONY : examples/sine_generator/CMakeFiles/sine_generator.dir/clean

examples/sine_generator/CMakeFiles/sine_generator.dir/depend:
	cd /Users/admin/Desktop/3D_VST_Plugin/aquila-build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src /Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/examples/sine_generator /Users/admin/Desktop/3D_VST_Plugin/aquila-build /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/sine_generator /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/sine_generator/CMakeFiles/sine_generator.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : examples/sine_generator/CMakeFiles/sine_generator.dir/depend

