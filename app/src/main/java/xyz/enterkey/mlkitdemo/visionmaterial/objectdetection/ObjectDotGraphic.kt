/*
 * Copyright 2020 Enterkey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.enterkey.mlkitdemo.visionmaterial.objectdetection

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Style
import android.graphics.PointF
import xyz.enterkey.mlkitdemo.visionmaterial.camera.GraphicOverlay
import xyz.enterkey.mlkitdemo.visionmaterial.camera.GraphicOverlay.Graphic
import xyz.enterkey.mlkitdemo.visionmaterial.R

/** A dot to indicate a detected object used by multiple objects detection mode.  */
internal class ObjectDotGraphic(
    overlay: GraphicOverlay,
    detectedObject: DetectedObject,
    private val animator: ObjectDotAnimator
) : Graphic(overlay) {
    private val paint: Paint
    private val center: PointF
    private val dotRadius: Int
    private val dotAlpha: Int

    init {

        val box = detectedObject.boundingBox
        center = PointF(
                overlay.translateX((box.left + box.right) / 2f),
                overlay.translateY((box.top + box.bottom) / 2f)
        )

        paint = Paint().apply {
            style = Style.FILL
            color = Color.WHITE
        }

        dotRadius = context.resources.getDimensionPixelOffset(R.dimen.object_dot_radius)
        dotAlpha = paint.alpha
    }

    override fun draw(canvas: Canvas) {
        paint.alpha = (dotAlpha * animator.alphaScale).toInt()
        canvas.drawCircle(center.x, center.y, dotRadius * animator.radiusScale, paint)
    }
}
