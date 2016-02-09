# Instruction

  - Get OpenAL https://www.openal.org/downloads/
  - Get latest version of SOX 
  -     git clone git://git.code.sf.net/p/sox/code sox
  - Install SOX
  -     brew install sox
  -     http://sourceforge.net/projects/sox/files/sox/
  - Put Sox and OpenAL on your Environment PATH (Windows)
  - Convert sound file from wav to raw 
  -     sox footsteps-4.wav -b 16 footsteps.raw channels 1 rate 44100
  - Compile with:
  -     gcc -framework OpenAL -framework Cocoa -o footsteps footsteps.c (Mac?)
  -     gcc -o footsteps footsteps.c -lopenal (Windows)
  - Run by typing:
  - ./footsteps 