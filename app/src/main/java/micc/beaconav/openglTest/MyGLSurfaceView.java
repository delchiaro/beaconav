package micc.beaconav.openglTest;


import android.content.Context;
import android.opengl.GLSurfaceView;


class MyGLSurfaceView extends GLSurfaceView
{

    public MyGLSurfaceView(Context context)
    {
        super(context);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(new MyRenderer());
        //setEGLContextClientVersion(2);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}