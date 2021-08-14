/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

/**
 *
 * @author Edson García
 */
public class Thread1 extends Thread {

    boolean eventFlag = Main.gui.getEventFlag();

    int dekkerNo = 0;
    int turn = 0;

    public void Dekker(int No) {
        dekkerNo = No;
        turn = Main.gui.getTurn();
        Main.gui.setEventFlag(true);
        eventFlag = true;
    }

    @Override
    public void run() {
        while (true && !this.isInterrupted()) {
            while (eventFlag) {
                switch (dekkerNo) {
                    case 1:
                        dekker1();
                        break;
                    case 2:
                        dekker2();
                        break;
                    case 3:
                        dekker3();
                        break;
                    case 4:
                        dekker4();
                        break;
                    case 5:
                        dekker5();
                        break;
                    case 6:
                        peterson();
                    default:
                        break;
                }
                eventFlag = Main.gui.getEventFlag();
            }
        }
    }

    private void dekker1() {
        // Hace tasks
        doTasks("initial", 2);
        // Espera a que la región crítica se desocupe
        while (Main.gui.getTurn() == 1) {
            waitcs();
        }
        // Accede a Región Crítica
        criticalSection(3);
        // Hace final tasks
        doTasks("final", 3);
        Main.gui.setTurn(1);
    }

    private void dekker2() {
        doTasks("initial", 2);
        Main.gui.setP1qe(true);
        while (Main.gui.getP2qe()) {
            waitcs();
        }
        criticalSection(3);
        Main.gui.setP1qe(false);
        doTasks("final", 2);
    }

    private void dekker3() {
        doTasks("initial", 2);
        while (Main.gui.getP2qe()) {
            waitcs();
        }
        Main.gui.setP1qe(true);
        criticalSection(3);
        Main.gui.setP1qe(false);
        doTasks("final", 3);
    }

    private void dekker4() {
        doTasks("initial", 2);
        Main.gui.setP1qe(true);
        while (Main.gui.getP2qe()) {
            Main.gui.setP1qe(false);
            try {
                waitcs();
                sleep(((int) ((Math.random() * 2) + 2)) * 1000);
            } catch (InterruptedException e) {
            }
            Main.gui.setP1qe(true);
        }
        criticalSection(3);
        Main.gui.setP1qe(false);
        doTasks("final", 3);
    }

    private void dekker5() {
        doTasks("initial", 3);
        Main.gui.setP1qe(true);
        while (Main.gui.getP2qe()) {
            if (Main.gui.getTurn() == 2) {
                Main.gui.setP1qe(false);
                try {
                    waitcs();
                    sleep(((int) ((Math.random() * 2) + 2)) * 1000);
                } catch (InterruptedException e) {
                }
                Main.gui.setP1qe(true);
            }
        }
        criticalSection(4);
        Main.gui.setTurn(2);
        Main.gui.setP1qe(false);
        doTasks("final", 3);
    }

    private void peterson() {
        doTasks("initial", 3);
        criticalSectionPeterson(4, 1);
        exitCriticalSectionPeterson(0);
        doTasks("final", 3);
    }

    private void doTasks(String s, int time) {
        try {
            Main.gui.setVisibleProgressBarP1("show");
            Main.gui.updateStatusP1("Doing " + s + " tasks...");
            Main.gui.setOperationProgressBarP1("working");
            sleep(time * 1000);
        } catch (InterruptedException e) {
        }
    }

    private void criticalSection(int time) {
        try {
            Main.gui.setVisibleProgressBarP1("show");
            Main.gui.setOperationProgressBarP1("bussy");
            Main.gui.updateStatusP1("Using critical section...");
            //sleep((int)((Math.random() * 2) + 2) * 1000);
            sleep(time * 1000);
        } catch (InterruptedException e) {
        }
    }

    private void criticalSectionPeterson(int time, int process) {
        int other;
        other = 3 - process;
        Main.gui.setInterested(process - 1, true);
        Main.gui.setTurn(process);
        while (Main.gui.getTurn() == process && Main.gui.getInterested()[other - 1]) {
            try {
                waitcs();
                sleep(((int) ((Math.random() * 2) + 2)) * 1000);
            } catch (InterruptedException e) {}
           
        }
        criticalSection(time);
    }

    private void exitCriticalSectionPeterson(int process) {
        Main.gui.setInterested(process, false);
    }

    private void waitcs() {
        Main.gui.updateStatusP1("Waiting");
        Main.gui.setOperationProgressBarP1("wait");
        Main.gui.setVisibleProgressBarP1("show");
    }
}
