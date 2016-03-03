# Instructions


#### You can read this file formatted here: https://github.com/VSTPlugger/3D_VST_Plugin 

- 1)Set up
	- Please clone this repository to your [Desktop]
	- Getting Started with JUCE
		- Get to know JUCE:
			- Reference: https://www.juce.com/doc/tutorial_new_projucer_project 
		- Make sure to configure the Path for VST and/or VST3 for .jucer extention files to point to /../Desktop/3D_VST_Plugin/juce-grapefruit-osx/VST3 SDK
			- Reference: Audio Plugin Setup, https://www.juce.com/doc/tutorial_create_projucer_basic_plugin
	- Make sure you have installed CMake
		- https://cmake.org/download/
	- Make sure you preconfigured Aquila with Cmake properly (Aquila exist in this repo already)
		- Reference: http://aquila-dsp.org/download/
- 2)This Repository contains:
	- JUCE Framework codes [juce-grapefruit-osx]
	- Project Specific Codes [3DAudioVST]
    - Audio Plugin Host provided by JUCE for testing [Audio Plugin Host]
    - Source files of Aquila DSP Library [aquila-build]
    - Source of inspirations[DSPFilters]
- 3)To Test for the functionality of our VST: 
  	- Navigate into [audio plugin host]->[Builds]->[Select Your IDE]->[Run/Compile respecitively]
    - Press "Cmd-P" (or going to Options > Edit) to search for the list of available plug-in on the system
    - If the list is empty, [Opions]->[Scan for new or updated VST Plugins]
    - Go back to GUI, Right click and select [VSTPlugger]->[3DAudioVST].
    - Connect nodes to input & outputs respectively. 
    - Double click on [3DAudioVST] to view the VST GUI for sound Manipulation

### Our Project Source Code Folder: 
- [3DAudioVST]
