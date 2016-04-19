/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joal;

import com.jogamp.openal.AL;
import com.jogamp.openal.ALException;
import com.jogamp.openal.ALFactory;
import com.jogamp.openal.util.ALut;
import java.nio.ByteBuffer;

/**
 *
 * @author Xiaoxi
 */
public class soundProcessAL {
    
  private AL al;
  // Buffers hold sound data.
  private int[] buffer;
  // Sources are points emitting sound.
  private int[] source;
  // Position of the source sound.
    //{ 0.0f, 0.0f, 0.0f };
  public float[] sourcePos;
  
  private float x;
  private float y;
  private float z;
  
  public String audioFileNameAL; 
  
    // Position of the listener.
  private float[] listenerPos = { 0.0f, 0.0f, 0.0f };
  // Velocity of the listener.
  private float[] listenerVel = { 0.0f, 0.0f, 0.0f };

  // Orientation of the listener. (first 3 elems are "at", second 3 are "up")
  private float[] listenerOri = { 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f };
  private boolean initialized = false;
  
  //constructor
  public soundProcessAL(float x_, float y_, float z_){
      /* initialize OpenAL context, asking for 44.1kHz to match HRIR data */
//    AL.ALCint contextAttr[] = {al.ALC_FREQUENCY,44100,0};
//    AL.ALCdevice* device = alcOpenDevice( NULL );
//    AL.ALCcontext* context = alcCreateContext( device, contextAttr );
//    alcMakeContextCurrent( context );
      
      source = new int[1];
      buffer = new int[1];
      
      //setListenerValues();
      
      sourcePos = new float [3];
      for(int i=0;i<3;i++){
          sourcePos[i] = 0.0f;
      } 
      x = x_;
      y = y_;
      z = z_;
      //audioFileNameAL = file_; 
      
       if (!initialize(x,y,z))
              System.exit(1);
            //initialize(x,y,z);
       
            //al.alSourcePlay(source[0]);
      
     // initialize(x,y,z); 
      
      //al.alSourcePlay(source[0]);

  }
  
  public void playAL(float x_,float y_, float z_,String file_){
      audioFileNameAL = file_;
      // Load the wav data.
    try {
      if (loadALData(x_,y_,z_) == AL.AL_FALSE)
          return;
        //return false;
    } catch (ALException e) {
      e.printStackTrace();
      //return false;
    }

    setListenerValues();
    
    al.alSourcePlay(source[0]);
  }
  //overloaded playAL to tweak freq
   public void playAL(float x_,float y_, float z_,String file_, float pitch_, float gain_){
      audioFileNameAL = file_;
      // Load the wav data.
    try {
      if (loadALData(x_,y_,z_,pitch_,gain_) == AL.AL_FALSE)
          return;
        //return false;
    } catch (ALException e) {
      e.printStackTrace();
      //return false;
    }

    setListenerValues();
    
    al.alSourcePlay(source[0]);
  }
  void setSourcePos(float x, float y, float z) {
      sourcePos[0] = x;
      sourcePos[1] = y;
      sourcePos[2] = z; 
      al.alSource3f(source[0], AL.AL_POSITION, x, y, z);
      //float angle;
      //angle += 20 * 3.14 * 0.5;
         
  }
  // Velocity of the source sound.
  private float[] sourceVel = { 0.0f, 0.0f, 0.0f };
  private boolean initialize() {
      return initialize(0,0,0); 
  }
  private boolean initialize(float x, float y, float z) {
    if (initialized) {
      return true;
    }
    // Initialize OpenAL and clear the error bit.
    try {
      ALut.alutInit();
      al = ALFactory.getAL();
      al.alGetError();
    } catch (ALException e) {
      e.printStackTrace();
      return false;
    }
    
    // Load the wav data.
  /*
    try {
      if (loadALData(x,y,z) == AL.AL_FALSE)
        return false;
    } catch (ALException e) {
      e.printStackTrace();
      return false;
    }

    setListenerValues();
  */
    initialized = true;
    return true;
  }
  
  private int loadALData(float x, float y, float z) {
    // variables to load into
    int[] format = new int[1];
    int[] size = new int[1];
    ByteBuffer[] data = new ByteBuffer[1];
    int[] freq = new int[1];
    int[] loop = new int[1];

    // Load wav data into a buffer.
    al.alGenBuffers(1, buffer, 0);
    if (al.alGetError() != AL.AL_NO_ERROR)
      throw new ALException("Error generating OpenAL buffers");
//"FancyPants.wav"
    ALut.alutLoadWAVFile(
      SingleStaticSource.class.getClassLoader().getResourceAsStream(audioFileNameAL),
      format,
      data,
      size,
      freq,
      loop);
    if (data[0] == null) {
      throw new RuntimeException("Error loading WAV file");
    }
    System.out.println("sound size = " + size[0]);
    System.out.println("sound freq = " + freq[0]);
    
    //al.alBufferData(buffer[0], format[0], data[0], size[0], 44100);

    al.alBufferData(buffer[0], format[0], data[0], size[0], freq[0]);

    // Bind buffer with a source.

    al.alGenSources(1, source, 0);

    if (al.alGetError() != AL.AL_NO_ERROR)
      throw new ALException("Error generating OpenAL source");
    setSourcePos(x,y,z);
    System.out.println("sourcepos coords are "+ sourcePos[0] + " " + sourcePos[1] + " " + sourcePos[2]);
    al.alSourcei(source[0], AL.AL_BUFFER, buffer[0]);
    al.alSourcef(source[0], AL.AL_PITCH, 1.0f);
    al.alSourcef(source[0], AL.AL_GAIN, 1.0f);
    al.alSourcei(source[0], AL.AL_LOOPING, loop[0]);

    // Do another error check
    if (al.alGetError() != AL.AL_NO_ERROR)
      throw new ALException("Error seltting up OpenAL source");

    // Note: for some reason the following two calls are producing an
    // error on one machine with NVidia's OpenAL implementation. This
    // appears to be harmless, so just continue past the error if one
    // occurs.
    
    //al.alSourcefv(source[0], AL.AL_POSITION, sourcePos, 0);
    //al.alSourcefv(source[0], AL.AL_VELOCITY, sourceVel, 0);
    
    al.alSource3f(source[0], AL.AL_POSITION, sourcePos[0],sourcePos[1],sourcePos[2]);
    al.alSource3f(source[0], AL.AL_VELOCITY, sourceVel[0], sourceVel[1],sourceVel[2]);

    return AL.AL_TRUE;
  }
  
  private int loadALData(float x, float y, float z, float pitch_, float gain_) {
    // variables to load into
    int[] format = new int[1];
    int[] size = new int[1];
    ByteBuffer[] data = new ByteBuffer[1];
    int[] freq = new int[1];
    int[] loop = new int[1];

    // Load wav data into a buffer.
    al.alGenBuffers(1, buffer, 0);
    if (al.alGetError() != AL.AL_NO_ERROR)
      throw new ALException("Error generating OpenAL buffers");
//"FancyPants.wav"
    ALut.alutLoadWAVFile(
      SingleStaticSource.class.getClassLoader().getResourceAsStream(audioFileNameAL),
      format,
      data,
      size,
      freq,
      loop);
    if (data[0] == null) {
      throw new RuntimeException("Error loading WAV file");
    }
    
    //al.alBufferData(buffer[0], format[0], data[0], size[0], 44100);
    //freq[0] = freq_; 
    al.alBufferData(buffer[0], format[0], data[0], size[0], freq[0]);
    System.out.println("sound size = " + size[0]);
    System.out.println("sound freq = " + freq[0]);
    
    // Bind buffer with a source.

    al.alGenSources(1, source, 0);

    if (al.alGetError() != AL.AL_NO_ERROR)
      throw new ALException("Error generating OpenAL source");
    setSourcePos(x,y,z);
    System.out.println("sourcepos coords are "+ sourcePos[0] + " " + sourcePos[1] + " " + sourcePos[2]);
    al.alSourcei(source[0], AL.AL_BUFFER, buffer[0]);
    al.alSourcef(source[0], AL.AL_PITCH, pitch_);
    al.alSourcef(source[0], AL.AL_GAIN, gain_);
    al.alSourcei(source[0], AL.AL_LOOPING, loop[0]);

    // Do another error check
    if (al.alGetError() != AL.AL_NO_ERROR)
      throw new ALException("Error seltting up OpenAL source");

    // Note: for some reason the following two calls are producing an
    // error on one machine with NVidia's OpenAL implementation. This
    // appears to be harmless, so just continue past the error if one
    // occurs.
    
    //al.alSourcefv(source[0], AL.AL_POSITION, sourcePos, 0);
    //al.alSourcefv(source[0], AL.AL_VELOCITY, sourceVel, 0);
    
    al.alSource3f(source[0], AL.AL_POSITION, sourcePos[0],sourcePos[1],sourcePos[2]);
    al.alSource3f(source[0], AL.AL_VELOCITY, sourceVel[0], sourceVel[1],sourceVel[2]);

    return AL.AL_TRUE;
  }

  private void setListenerValues() {
    al.alListener3f(AL.AL_POSITION, 0,1.5f,0);
    al.alListener3f(AL.AL_VELOCITY, 0,0, 0);
    al.alListenerfv(AL.AL_ORIENTATION, listenerOri, 1);
  }

  private void killAllData() {
    al.alDeleteBuffers(1, buffer, 0);
    al.alDeleteSources(1, source, 0);
  }
}
