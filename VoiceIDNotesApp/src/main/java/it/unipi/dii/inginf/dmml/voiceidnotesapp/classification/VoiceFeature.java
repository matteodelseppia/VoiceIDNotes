package it.unipi.dii.inginf.dmml.voiceidnotesapp.classification;

import it.unipi.dii.inginf.dmml.voiceidnotesapp.config.Config;
import it.unipi.dii.inginf.dmml.voiceidnotesapp.utils.Utils;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class VoiceFeature {
    public final static int NUMBER_MFCC_DELTA_DELTADELTA = 13;
    private double mfcc[];
    private double delta[];
    private double deltadelta[];

    public VoiceFeature(double mfcc[], double delta[], double deltadelta[]){
        this.mfcc = new double[NUMBER_MFCC_DELTA_DELTADELTA];
        this.delta = new double[NUMBER_MFCC_DELTA_DELTADELTA];
        this.deltadelta = new double[NUMBER_MFCC_DELTA_DELTADELTA];
        for(int i = 0; i < NUMBER_MFCC_DELTA_DELTADELTA; i++){
            this.mfcc[i] = mfcc[i];
            this.delta[i] = delta[i];
            this.deltadelta[i] = deltadelta[i];
        }
    }

    public VoiceFeature(){}

    public double[] getMfcc() {
        return mfcc;
    }

    public void setMfcc(double[] mfcc) {
        this.mfcc = mfcc;
    }

    public double[] getDelta() {
        return delta;
    }

    public void setDelta(double[] delta) {
        this.delta = delta;
    }

    public double[] getDeltadelta() {
        return deltadelta;
    }

    public void setDeltadelta(double[] deltadelta) {
        this.deltadelta = deltadelta;
    }

    public Instances toInstance() {
        Instances instanceDataset = null;
        try {
            instanceDataset = Utils.loadDataset(Config.getInstance().getDatasetPath());
            double[] attributesValues = new double[NUMBER_MFCC_DELTA_DELTADELTA * 3 + 1];
            for (int i = 0; i < NUMBER_MFCC_DELTA_DELTADELTA; i++) {
                attributesValues[i] = mfcc[i];
                attributesValues[i + NUMBER_MFCC_DELTA_DELTADELTA] = delta[i];
                attributesValues[i + (NUMBER_MFCC_DELTA_DELTADELTA * 2)] = deltadelta[i];
            }
            Instance singleInstance = new DenseInstance(1.0, attributesValues);
            singleInstance.setDataset(instanceDataset);
            singleInstance.setClassMissing();
            instanceDataset.delete();
            instanceDataset.add(singleInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instanceDataset;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < NUMBER_MFCC_DELTA_DELTADELTA; i++) {
            result += String.valueOf(mfcc[i]) + " ";
        }
        for (int i = 0; i < NUMBER_MFCC_DELTA_DELTADELTA; i++) {
            result += String.valueOf(delta[i]) + " ";
        }
        for (int i = 0; i < NUMBER_MFCC_DELTA_DELTADELTA; i++) {
            result += String.valueOf(deltadelta[i]) + " ";
        }
        return result;
    }
}