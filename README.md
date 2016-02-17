# Instructions

- 1)Get OpenAL https://www.openal.org/downloads/
- 2)Get latest version of SOX
	- git clone https://github.com/Distrotech/sox
	- or 
    - brew install sox 
- 3)Install SOX
  	- http://sourceforge.net/projects/sox/files/sox/
- 4)Put Sox and OpenAL on your Environment PATH (Windows)
- 5)Convert sound file from wav to raw 
	- sox footsteps-4.wav -b 16 footsteps.raw channels 1 rate 44100
- 6) Compile with:
	- gcc -framework OpenAL -framework Cocoa -o footsteps footsteps.c (Mac?) 
	- or 
    - gcc -o footsteps footsteps.c -lopenal (Windows)
- 7) Run by typing:
    	- ./footsteps

### Program expected inputs 
- Input file String example: "footsteps.raw"
- Begin Point float examople: 0 [Enter] 0 [Enter] 0 [Enter] 
- End Point float examople: 1 [Enter] 1 [Enter] 1 [Enter] 

#### The program will walk from user [Begin]->[End] point and then walk to other locations randomly from then on.  
