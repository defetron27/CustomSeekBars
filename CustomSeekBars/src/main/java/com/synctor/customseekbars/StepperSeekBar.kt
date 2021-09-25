package com.synctor.customseekbars

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSeekBar

class StepperSeekBar(context: Context, attributeSet: AttributeSet) :
    AppCompatSeekBar(context, attributeSet) {
    private val bgPaint: Paint = Paint()
    private val activePaint: Paint = Paint()
    private val bgPath = Path()
    private val activePath = Path()

    init {
        bgPaint.color = Color.RED
        bgPaint.strokeWidth = 5f

        activePaint.color = Color.BLUE
        activePaint.strokeWidth = 5f
    }

    @Synchronized
    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
        // Now get size of seek bar.
        val width = width.toFloat()
        val height = height.toFloat()

        // Calculate where to start printing text.
        val position = width / max * progress

        val rectWidth = width / 6

        bgPath.apply {
//            addRect(0f, 0f, width, 10f, Path.Direction.CW)

            addCircle(15f, 0f, 15f, Path.Direction.CW)
            addCircle(rectWidth, 0f, 15f, Path.Direction.CW)
            addCircle(2 * rectWidth, 0f, 15f, Path.Direction.CW)
            addCircle(3 * rectWidth, 0f, 15f, Path.Direction.CW)
            addCircle(4 * rectWidth, 0f, 15f, Path.Direction.CW)
            addCircle(5 * rectWidth, 0f, 15f, Path.Direction.CW)
            addCircle(width - 15f, 0f, 15f, Path.Direction.CW)

            close()
        }

        canvas.translate(0f, height / 2)
        canvas.drawLine(0F, 0F, width, 0F, bgPaint)
        canvas.drawLine(0F, 0F, position, 0F, activePaint)
        canvas.drawPath(bgPath, bgPaint)
        canvas.drawPath(activePath, activePaint)
        canvas.apply {
            drawCircle(15f, 0f, 15f, activePaint)

            var fillPosition = rectWidth
            if (position >= fillPosition) {
                drawCircle(fillPosition, 0f, 15f, activePaint)
            }
            fillPosition = rectWidth * 2
            if (position >= fillPosition) {
                drawCircle(fillPosition, 0f, 15f, activePaint)
            }
            fillPosition = rectWidth * 3
            if (position >= fillPosition) {
                drawCircle(fillPosition, 0f, 15f, activePaint)
            }
            fillPosition = rectWidth * 4
            if (position >= fillPosition) {
                drawCircle(fillPosition, 0f, 15f, activePaint)
            }
            fillPosition = rectWidth * 5
            if (position >= fillPosition) {
                drawCircle(fillPosition, 0f, 15f, activePaint)
            }
            fillPosition = width - 15f
            if (position >= fillPosition) {
                drawCircle(fillPosition, 0f, 15f, activePaint)
            }
        }
        bgPaint.textSize = 50f
        canvas.drawText(position.toString(), (width / 2) - 20, 100f, bgPaint)
    }
}