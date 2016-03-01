# Instructions


#### You can read this file formatted here: https://github.com/VSTPlugger/3D_VST_Plugin 

- 1)Please clone this repository to your [Desktop]. All path is preconfigured that way.
- 2)This Repository contains:
	- JUCE Framework codes [juce-grapefruit-osx]
	- Project Specific Codes [3DAudioVST]
    - Audio Plugin Host provided by JUCE for testing [Audio Plugin Host]. 
- 3)To Test for the functionality of our VST: 
  	- Navigate into [audio plugin host]->[Builds]->[Select Your IDE]->[Run/Compile respecitively]
    - Press "Cmd-P" (or going to Options > Edit) to search for the list of available plug-in on the system
    - If the list is empty, [Opions]->[Scan for new or updated VST Plugins]
    - Go back to GUI, Right click and select [VSTPlugger]->[3DAudioVST].
    - Connect nodes to input & outputs respectively. 
    - Double click on [3DAudioVST] to view the VST GUI for sound Manipulation

### Our Project Source Code Folder: 
- [3DAudioVST]
