/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.codingdojo.students;

import fr.rphstudio.codingdojo.game.Pod;
import fr.rphstudio.codingdojo.game.PodPlugIn;

/**
 *
 * @author Romuald GRIGNON
 */
public class Student4 extends PodPlugIn {
    public Student4(Pod p){
        super(p);
    }
    
    //-------------------------------------------------------
    // DECLARE YOUR OWN VARIABLES AND FUNCTIONS HERE



    float Distance(float yc, float xc, float yv, float xv){
        float d=sqrt((xc-xv)*(xc-xv)+(yc-yv)*(yc-yv));

        return d;
    }
    float angle(float vx,float vy, float cx, float cy){
        float A=cx-vx;
        float O=cy-vy;
        float a=atan2(O,A);
        a=(a*180)/PI;
        if (a<0){
            a=a+360;
        }
        else{

        }
        return a;

    }
    //float angle();



    

    // END OF VARIABLES/FUNCTIONS AREA
    //-------------------------------------------------------
    
    @Override
    public void process(int delta)
    {   
        //-------------------------------------------------------
        // WRITE YOUR OWN CODE HERE




        setPlayerName("4-Juan t'es beau putain <3");
        selectShip(20);
        setPlayerColor(121,248,248,255);
        float Y_checkpoint;
        float X_checkpoint;
        float X_vaisseau=getShipPositionX();
        float Y_vaisseau=getShipPositionY();
        int Checkpoint_Traversé=getNbValidCheckPoints();
        int NBCheckpoint_race=getNbRaceCheckPoints();
        int Checkpoint_suivant=(Checkpoint_Traversé%NBCheckpoint_race)+1;
        float angle_vaisseau;
         if (getShipAngle()<0){
             angle_vaisseau=(getShipAngle()+360);
         }
         else{
             angle_vaisseau=getShipAngle();
         }

        if (Checkpoint_Traversé==NBCheckpoint_race-1){
            Checkpoint_suivant=0;
        }


        X_checkpoint=getCheckPointPositionX(Checkpoint_suivant);
        Y_checkpoint=getCheckPointPositionY(Checkpoint_suivant);
        float alpha2=angle(X_vaisseau,Y_vaisseau,X_checkpoint,Y_checkpoint);
        float distance = Distance(Y_checkpoint,X_checkpoint,Y_vaisseau,X_vaisseau);
        if (getShipBoostLevel()==100 && distance>6){
            useBoost();
        }
        else{

        }
        System.out.println(alpha2);



        // turn
        if (alpha2>angle_vaisseau){
            //turn(3);
            turn(5);
        }
        else if (alpha2<angle_vaisseau){
         //turn(-3);
            turn(-5);

       }
       else{

        }



        float c=getShipSpeed();


        // accel or brake
        if (distance<1 && c<0.5f){
            incSpeed(-0.5f);

        }
        else{
            incSpeed(1f);
        }












        // END OF CODE AREA
        //-------------------------------------------------------
    }

}
