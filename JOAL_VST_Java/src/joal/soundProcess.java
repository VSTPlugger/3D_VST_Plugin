/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joal;
import java.util.Scanner; 

import java.io.*;
import java.nio.ByteBuffer;

import com.jogamp.openal.*;
import com.jogamp.openal.util.*;
import com.jogamp.openal.sound3d.*; 

/**
 *
 * @author Xiaoxi
 */
public class soundProcess {
    private AL al;
    private float XtoProcess;
    private float YtoProcess;
    private float ZtoProcess;
    
    //"FancyPants.wav"
    private String audioFileName;
    
    private String deviceName;
    private Device device;
    private Buffer audioBuffer;
    private Source src;
    
    
    public soundProcess(){
        //default construcotr to make things happy
         //initialize some numbers for location to play
        XtoProcess = 0.0f;
        YtoProcess = 0.0f;
        ZtoProcess = 0.0f; 
        
        //Initialize openAL context
        AudioSystem3D.init();
        deviceName = null; 
        //deviceName = "DirectSound3D"; 
        device = AudioSystem3D.openDevice(deviceName);
        //Device device = al.alcGetContextsDevice(al.alcGetCurrentContext());
        System.out.println("Opened device"); 
        Context context = AudioSystem3D.createContext(device); 
        AudioSystem3D.makeContextCurrent(context);
        
        //audioFileName = "FancyPants.wav"; //default audioFile to make things happy
        //src = new AL.Source();
    }
    // takes in x, y, z from mouse click
    public void loadNPlay(float x, float y, float z, String audioFile ){
        //set up Buffer and Generate Source
        audioFileName = audioFile;
        loadBuffer(audioFile); 
        generateSrcNlistener(XtoProcess,YtoProcess,ZtoProcess);
        
        //Play
        playAudio();
          
//          int[] contextAttr = {ALC.ALC_FREQUENCY, 44100, 0}; 
//          ALCdevice device = ALC.alcOpenDevice(devices[0]); 
    }   
    private void loadBuffer(String audioFileName_){
        //Load Buffer
        try {
            audioBuffer = AudioSystem3D.loadBuffer(audioFileName_);
        } catch(UnsupportedAudioFileException e) {
            System.out.println("Unsupported file "); 
            audioBuffer = null;
        } catch(IOException e) {
            System.out.println("I/o exeption"); 
            audioBuffer = null;
        }
        
    }
    private void generateSrcNlistener(float x_, float y_, float z_){
        System.out.println(audioBuffer);
        src = AudioSystem3D.generateSource(audioBuffer);
        //Store Listener
        Listener lis = AudioSystem3D.getListener(); 
        lis.setPosition(XtoProcess, XtoProcess, XtoProcess);
        //Set Position of Source
        //src.setPosition(0,0,0); 
         src.setPosition(x_,y_,z_);
    }
    private void playAudio(){
        src.play(); 
        while(src.isPlaying()) {
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}