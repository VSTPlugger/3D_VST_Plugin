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
include examples/fft_comparison/CMakeFiles/fft_comparison.dir/depend.make

# Include the progress variables for this target.
include examples/fft_comparison/CMakeFiles/fft_comparison.dir/progress.make

# Include the compile flags for this target's objects.
include examples/fft_comparison/CMakeFiles/fft_comparison.dir/flags.make

examples/fft_comparison/CMakeFiles/fft_comparison.dir/fft_comparison.cpp.o: examples/fft_comparison/CMakeFiles/fft_comparison.dir/flags.make
examples/fft_comparison/CMakeFiles/fft_comparison.dir/fft_comparison.cpp.o: aquila-src/examples/fft_comparison/fft_comparison.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/admin/Desktop/3D_VST_Plugin/aquila-build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object examples/fft_comparison/CMakeFiles/fft_comparison.dir/fft_comparison.cpp.o"
	cd /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/fft_comparison && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++   $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/fft_comparison.dir/fft_comparison.cpp.o -c /Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/examples/fft_comparison/fft_comparison.cpp

examples/fft_comparison/CMakeFiles/fft_comparison.dir/fft_comparison.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/fft_comparison.dir/fft_comparison.cpp.i"
	cd /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/fft_comparison && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/examples/fft_comparison/fft_comparison.cpp > CMakeFiles/fft_comparison.dir/fft_comparison.cpp.i

examples/fft_comparison/CMakeFiles/fft_comparison.dir/fft_comparison.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/fft_comparison.dir/fft_comparison.cpp.s"
	cd /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/fft_comparison && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/examples/fft_comparison/fft_comparison.cpp -o CMakeFiles/fft_comparison.dir/fft_comparison.cpp.s

examples/fft_comparison/CMakeFiles/fft_comparison.dir/fft_comparison.cpp.o.requires:

.PHONY : examples/fft_comparison/CMakeFiles/fft_comparison.dir/fft_comparison.cpp.o.requires

examples/fft_comparison/CMakeFiles/fft_comparison.dir/fft_comparison.cpp.o.provides: examples/fft_comparison/CMakeFiles/fft_comparison.dir/fft_comparison.cpp.o.requires
	$(MAKE) -f examples/fft_comparison/CMakeFiles/fft_comparison.dir/build.make examples/fft_comparison/CMakeFiles/fft_comparison.dir/fft_comparison.cpp.o.provides.build
.PHONY : examples/fft_comparison/CMakeFiles/fft_comparison.dir/fft_comparison.cpp.o.provides

examples/fft_comparison/CMakeFiles/fft_comparison.dir/fft_comparison.cpp.o.provides.build: examples/fft_comparison/CMakeFiles/fft_comparison.dir/fft_comparison.cpp.o


# Object files for target fft_comparison
fft_comparison_OBJECTS = \
"CMakeFiles/fft_comparison.dir/fft_comparison.cpp.o"

# External object files for target fft_comparison
fft_comparison_EXTERNAL_OBJECTS =

examples/fft_comparison/fft_comparison: examples/fft_comparison/CMakeFiles/fft_comparison.dir/fft_comparison.cpp.o
examples/fft_comparison/fft_comparison: examples/fft_comparison/CMakeFiles/fft_comparison.dir/build.make
examples/fft_comparison/fft_comparison: libAquila.a
examples/fft_comparison/fft_comparison: lib/libOoura_fft.a
examples/fft_comparison/fft_comparison: examples/fft_comparison/CMakeFiles/fft_comparison.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/admin/Desktop/3D_VST_Plugin/aquila-build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable fft_comparison"
	cd /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/fft_comparison && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/fft_comparison.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
examples/fft_comparison/CMakeFiles/fft_comparison.dir/build: examples/fft_comparison/fft_comparison

.PHONY : examples/fft_comparison/CMakeFiles/fft_comparison.dir/build

examples/fft_comparison/CMakeFiles/fft_comparison.dir/requires: examples/fft_comparison/CMakeFiles/fft_comparison.dir/fft_comparison.cpp.o.requires

.PHONY : examples/fft_comparison/CMakeFiles/fft_comparison.dir/requires

examples/fft_comparison/CMakeFiles/fft_comparison.dir/clean:
	cd /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/fft_comparison && $(CMAKE_COMMAND) -P CMakeFiles/fft_comparison.dir/cmake_clean.cmake
.PHONY : examples/fft_comparison/CMakeFiles/fft_comparison.dir/clean

examples/fft_comparison/CMakeFiles/fft_comparison.dir/depend:
	cd /Users/admin/Desktop/3D_VST_Plugin/aquila-build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src /Users/admin/Desktop/3D_VST_Plugin/aquila-build/aquila-src/examples/fft_comparison /Users/admin/Desktop/3D_VST_Plugin/aquila-build /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/fft_comparison /Users/admin/Desktop/3D_VST_Plugin/aquila-build/examples/fft_comparison/CMakeFiles/fft_comparison.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : examples/fft_comparison/CMakeFiles/fft_comparison.dir/depend

