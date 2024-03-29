/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.codingdojo.students;

import fr.rphstudio.codingdojo.game.Pod;
import fr.rphstudio.codingdojo.game.PodPlugIn;

/**
 * @author Romuald GRIGNON
 */
public class Student4 extends PodPlugIn {
    public Student4(Pod p) {
        super(p);
    }

    //-------------------------------------------------------
    // DECLARE YOUR OWN VARIABLES AND FUNCTIONS HERE


    float Distance(float yc, float xc, float yv, float xv) {
        float d = sqrt((xc - xv) * (xc - xv) + (yc - yv) * (yc - yv));

        return d;
    }

    float angle(float vx, float vy, float cx, float cy) {
        float A = cx - vx;
        float O = cy - vy;
        float a = atan2(O, A);
        a = (a * 180) / PI;
        if (a < 0) {
            a = a + 360;
        } else {

        }
        return a;

    }
    boolean charge=false;
    //float angle();


    // END OF VARIABLES/FUNCTIONS AREA
    //-------------------------------------------------------

    @Override
    public void process(int delta) {
        //-------------------------------------------------------
        // WRITE YOUR OWN CODE HERE


        setPlayerName("4-Juan t'es beau putain <3");
        selectShip(20);
        setPlayerColor(121, 248, 248, 255);
        //variable
        float Y_checkpoint;
        float X_checkpoint;
        float X_vaisseau = getShipPositionX();
        float Y_vaisseau = getShipPositionY();
        int Checkpoint_Traversé = getNbValidCheckPoints();
        int NBCheckpoint_race = getNbRaceCheckPoints();
        int Checkpoint_suivant = (Checkpoint_Traversé % NBCheckpoint_race) + 1;
        float X_next_checkpoint;
        float Y_next_checkpoint;
        float angle_vaisseau;
        X_checkpoint = getCheckPointPositionX(Checkpoint_suivant);
        Y_checkpoint = getCheckPointPositionY(Checkpoint_suivant);
        float alpha2 = angle(X_vaisseau, Y_vaisseau, X_checkpoint, Y_checkpoint);
        float distance = Distance(Y_checkpoint, X_checkpoint, Y_vaisseau, X_vaisseau);
        int Previous_checkpoint;
        float distance_between;
        float speedX = getShipSpeedX();
        float speedY = getShipSpeedY();
        float gamma = angle(speedX, speedY, X_vaisseau, Y_vaisseau);
        float c = getShipSpeed();
        int x;

        if(getShipBatteryLevel()<=30) {
            charge=true;
            incSpeed(0.1f);




            if (getShipBatteryLevel()<=30 && charge==true) {

                x = 0;
                while (x == 0) {
                    boolean y = isCheckPointCharging(Checkpoint_suivant);
                    if (y == true) {
                        x = 1;
                    } else {
                        Checkpoint_suivant = Checkpoint_suivant + 1;
                        if (Checkpoint_suivant == NBCheckpoint_race) {
                            Checkpoint_suivant = 0;
                        } else {

                        }
                    }
                }
                angle_vaisseau = getShipAngle();
                if (getShipAngle() < 0) {
                    angle_vaisseau = (getShipAngle() + 360);
                } else {
                    angle_vaisseau = getShipAngle();
                }
                float kappa;
                X_checkpoint = getCheckPointPositionX(Checkpoint_suivant);
                Y_checkpoint = getCheckPointPositionY(Checkpoint_suivant);
                kappa = angle(X_vaisseau, Y_vaisseau, X_checkpoint, Y_checkpoint);
                // turn
                if (kappa > angle_vaisseau) {
                    //turn(3);
                    turn(5);
                } else if (kappa < angle_vaisseau) {
                    //turn(-3);
                    turn(-5);

                } else {
                }

            }
        }
            else if(charge==false && getShipBatteryLevel()>30){
            //conditions (checkpoint)
            if (Checkpoint_suivant == NBCheckpoint_race) {
                Checkpoint_suivant = 0;
                Y_next_checkpoint = getCheckPointPositionY(Checkpoint_suivant);
                X_next_checkpoint = getCheckPointPositionX(Checkpoint_suivant);


            } else {
                Y_next_checkpoint = getCheckPointPositionY(Checkpoint_suivant + 1);
                X_next_checkpoint = getCheckPointPositionX(Checkpoint_suivant + 1);
            }
            float distance_next_checkpoint = Distance(X_next_checkpoint, Y_next_checkpoint, X_vaisseau, Y_vaisseau);
            if (getShipAngle() < 0) {
                angle_vaisseau = (getShipAngle() + 360);
            } else {
                angle_vaisseau = getShipAngle();
            }

            if (Checkpoint_Traversé == NBCheckpoint_race - 1) {
                Checkpoint_suivant = 0;
            }


            if (Checkpoint_Traversé == NBCheckpoint_race - 1) {
                Previous_checkpoint = NBCheckpoint_race - 1;
            } else {
                Previous_checkpoint = (Checkpoint_Traversé % NBCheckpoint_race) - 1;
                if (Previous_checkpoint == -1) {
                    Previous_checkpoint = NBCheckpoint_race - 1;

                } else {

                }
            }
            //variables
            float X_previous_checkpoint = getCheckPointPositionX(Previous_checkpoint);
            float Y_previous_checkpoint = getCheckPointPositionY(Previous_checkpoint);
            distance_between = Distance(X_previous_checkpoint, Y_previous_checkpoint, X_checkpoint, Y_checkpoint);
            float Beta = angle(X_next_checkpoint, Y_next_checkpoint, X_vaisseau, Y_vaisseau);


            //boost
//            if (getShipBoostLevel() == 100 && distance > 6) {
//                useBoost();
//            } else {
//
//            }
            // turn
            if (alpha2 > angle_vaisseau) {
                //turn(3);
                turn(5);
            } else if (alpha2 < angle_vaisseau) {
                //turn(-3);
                turn(-5);

            } else {
            }
            // accel or brake
            if (distance < 1 && c < 0.5f) {
                incSpeed(-0.5f);

            } else {
                incSpeed(1f);
            }
        }
        else if (getShipBatteryLevel()>80){
            charge=false;
        }





        // END OF CODE AREA
        //-------------------------------------------------------
    }

}
