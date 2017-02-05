package com.android.example.neighbours;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.microsoft.bing.speech.SpeechClientStatus;
import com.microsoft.cognitiveservices.speechrecognition.ISpeechRecognitionServerEvents;
import com.microsoft.cognitiveservices.speechrecognition.MicrophoneRecognitionClient;
import com.microsoft.cognitiveservices.speechrecognition.RecognitionResult;
import com.microsoft.cognitiveservices.speechrecognition.RecognitionStatus;
import com.microsoft.cognitiveservices.speechrecognition.SpeechRecognitionMode;
import com.microsoft.cognitiveservices.speechrecognition.SpeechRecognitionServiceFactory;

public class Testing extends Activity implements ISpeechRecognitionServerEvents {
    int m_waitSeconds = 0;
    MicrophoneRecognitionClient micClient = null;
    Testing.FinalResponseStatus isReceivedResponse = Testing.FinalResponseStatus.NotReceived;
    EditText _logText;
    Button _startButton;

    @Override
    public void onPartialResponseReceived(final String s) {
        this.WriteLine("--- Partial result received by onPartialResponseReceived() ---");
        this.WriteLine(s);
        this.WriteLine();
    }

    @Override
    public void onFinalResponseReceived(final RecognitionResult response) {
        boolean isFinalDicationMessage = response.RecognitionStatus == RecognitionStatus.EndOfDictation ||
                response.RecognitionStatus == RecognitionStatus.DictationEndSilenceTimeout;
        if (null != this.micClient  && isFinalDicationMessage) {
            // we got the final result, so it we can end the mic reco.  No need to do this
            // for dataReco, since we already called endAudio() on it as soon as we were done
            // sending all the data.
            this.micClient.endMicAndRecognition();
        }
        if (isFinalDicationMessage) {
            this.isReceivedResponse = Testing.FinalResponseStatus.OK;
        }

        if (!isFinalDicationMessage) {
            this.WriteLine("********* Final n-BEST Results *********");
            for (int i = 0; i < response.Results.length; i++) {
                this.WriteLine("[" + i + "]" + " Confidence=" + response.Results[i].Confidence +
                        " Text=\"" + response.Results[i].DisplayText + "\"");
            }
            this.WriteLine();
        }
    }

    @Override
    public void onIntentReceived(final String s) {
        this.WriteLine("--- Intent received by onIntentReceived() ---");
        this.WriteLine(s);
        this.WriteLine();
    }

    @Override
    public void onError(final int i, final String s) {
        this._startButton.setEnabled(true);
        this.WriteLine("--- Error received by onError() ---");
        this.WriteLine("Error code: " + SpeechClientStatus.fromInt(i) + " " + i);
        this.WriteLine("Error text: " + s);
        this.WriteLine();
    }

    @Override
    public void onAudioEvent(boolean b) {
        this.WriteLine("--- Microphone status change received by onAudioEvent() ---");
        this.WriteLine("********* Microphone status: " + b + " *********");
        if (b) {
            this.WriteLine("Please start speaking.");
        }

        WriteLine();
        if (!b) {
            this.micClient.endMicAndRecognition();
        }
    }

    public enum FinalResponseStatus { NotReceived, OK, Timeout }

    /**
     * Gets the primary subscription key
     */
    private SpeechRecognitionMode getMode() {
        return SpeechRecognitionMode.LongDictation;
    }

    public String getPrimaryKey() {
        return this.getString(R.string.primaryKey);
    }

    private String getDefaultLocale() {
        return "en-us";
    }

    private String getAuthenticationUri() {
        return this.getString(R.string.authenticationUri);
    }
    private void WriteLine() {
        this.WriteLine("");
    }
    private void WriteLine(String text) {
        this._logText.append(text + "\n");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        _startButton=(Button)findViewById(R.id.button1);
        _logText=(EditText)findViewById(R.id.editText1);

        if (getString(R.string.primaryKey).startsWith("Please")) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.add_subscription_key_tip_title))
                    .setMessage(getString(R.string.add_subscription_key_tip))
                    .setCancelable(false)
                    .show();
        }
        final Testing This = this;
        this._startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                This.StartButton_Click(arg0);
            }
        });

    }

    private void StartButton_Click(View arg0) {
        this.m_waitSeconds = 200;
        if (this.micClient == null) {
            this.micClient = SpeechRecognitionServiceFactory.createMicrophoneClient(
                    this,
                    this.getMode(),
                    this.getDefaultLocale(),
                    this,
                    this.getPrimaryKey());


            this.micClient.setAuthenticationUri(this.getAuthenticationUri());
        }

        this.micClient.startMicAndRecognition();


    }


}
