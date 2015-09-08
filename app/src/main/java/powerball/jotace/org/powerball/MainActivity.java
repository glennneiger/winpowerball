package powerball.jotace.org.powerball;

import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends AppCompatActivity {

    private AudioManager mAudioManager;
    private SoundPool mSoundPool;
    private int mSoundId;
    Button mplayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // AdMob Ad
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Getting custom font
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SkaterGirlsRock.ttf");

        final TextView mainTitle = (TextView) findViewById(R.id.main_title);
        mainTitle.setTypeface(face);

        final TextView ball1 = (TextView)findViewById(R.id.ball1);
        final TextView ball2 = (TextView)findViewById(R.id.ball2);
        final TextView ball3 = (TextView)findViewById(R.id.ball3);
        final TextView ball4 = (TextView)findViewById(R.id.ball4);
        final TextView ball5 = (TextView)findViewById(R.id.ball5);
        final TextView pball = (TextView)findViewById(R.id.pball);

        mplayButton = (Button) findViewById(R.id.play_button);
        mplayButton.setTypeface(face);
        mplayButton.setEnabled(false);

        mplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ball1.setText(BallsUtils.getWinningBall());
                ball2.setText(BallsUtils.getWinningBall());
                ball3.setText(BallsUtils.getWinningBall());
                ball4.setText(BallsUtils.getWinningBall());
                ball5.setText(BallsUtils.getWinningBall());
                pball.setText(BallsUtils.getPowerBall());
                BallsUtils.resetWinningNumbers();

                // play the sound
                mSoundPool.play(mSoundId, 1.0f, 1.0f, 1, 0, 1.0f);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        if (null == mSoundPool) {

            // creating the sound pool
            mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            // loading the sound
            mSoundId = mSoundPool.load(this, R.raw.cash, 1);

            mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    if (status == 0)
                        mplayButton.setEnabled(true);
                }
            });

        }

        super.onResume();
    }

    @Override
    protected void onPause() {
        if (null != mSoundPool) {
            mSoundPool.unload(mSoundId);
            mSoundPool.release();
            mSoundPool = null;
        }
        super.onPause();
    }
}
