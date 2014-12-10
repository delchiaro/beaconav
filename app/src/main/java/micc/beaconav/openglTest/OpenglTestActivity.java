package micc.beaconav.openglTest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.opengl.GLSurfaceView;
import micc.beaconav.R;

public class OpenglTestActivity extends ActionBarActivity {

    private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);
    }


}
