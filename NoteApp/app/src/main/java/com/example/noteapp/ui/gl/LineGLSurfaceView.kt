package com.example.noteapp.ui.gl

import android.content.Context
import android.opengl.GLSurfaceView
import android.view.MotionEvent
import com.example.noteapp.util.DEVLogger
import com.example.noteapp.view_model.OpenGLViewModel

class LineGLSurfaceView(context: Context) : GLSurfaceView(context) {

    private val renderer: LineGLRenderer
    private var viewModel: OpenGLViewModel? = null
    fun setViewModel(viewModel: OpenGLViewModel) {
        this.viewModel = viewModel
        renderer.setViewModel(viewModel)
    }

    init {
        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2)
        renderer = LineGLRenderer()
        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(renderer)
        // Render the view only when there is a change in the drawing data.
        // To allow the triangle to rotate automatically, this line is commented out:
        renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }

    var isStart = true

    override fun onTouchEvent(e: MotionEvent): Boolean {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        val x: Float = e.x
        val y: Float = e.y

        when (e.action) {
            MotionEvent.ACTION_MOVE -> {
                val tempPoint = floatArrayOf(
                    x, y, 0f
                )
                viewModel?.updateLines(tempPoint, isStart)
                isStart = false
            }
            MotionEvent.ACTION_UP -> {
                isStart = true
            }
        }
        return true
    }

    fun drawLine(isNewLine: Boolean) {
        DEVLogger.d("drawLine() $isNewLine")
        if (isNewLine) {
            renderer.setIsNewLine()
        }
        requestRender()
    }

}
