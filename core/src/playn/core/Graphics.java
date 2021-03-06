/**
 * Copyright 2010 The PlayN Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package playn.core;

import playn.core.gl.GL20;
import playn.core.gl.GLContext;

/**
 * Main 2D graphics interface. This interface can be used to create and load
 * graphics objects used with {@link Canvas}.
 */
public interface Graphics {

  /**
   * Returns the root of the scene graph. When layers are added to this layer, they become visible
   * on the screen.
   */
  GroupLayer rootLayer();

  /**
   * Creates a group layer.
   */
  GroupLayer createGroupLayer();

  /**
   * Creates a clipped group layer, with the initial specified size.
   *
   * <p><em>NOTE:</em> clipping regions <em>do not</em> nest. If you place a clipped
   * ImmediateLayer, or another clipped group, inside this clipped group it will behave
   * unpredictably. Don't do that.</p>
   */
  GroupLayer.Clipped createGroupLayer(float width, float height);

  /**
   * Creates an immediate layer that is clipped to the specified rectangular region.
   *
   * @param width the horizontal extent of the layer's drawable region.
   * @param height the vertical extent of the layer's drawable region.
   */
  ImmediateLayer.Clipped createImmediateLayer(
      int width, int height, ImmediateLayer.Renderer renderer);

  /**
   * Creates an unclipped immediate layer. This layer may draw anywhere on the framebuffer, though
   * its rendering operations will be transformed appropriately, based on the layer's current
   * transform.
   */
  ImmediateLayer createImmediateLayer(ImmediateLayer.Renderer renderer);

  /**
   * TODO
   */
  SurfaceLayer createSurfaceLayer(float width, float height);

  /**
   * TODO
   */
  ImageLayer createImageLayer();

  /**
   * TODO
   */
  ImageLayer createImageLayer(Image image);

  /**
   * Creates an image that can be painted using the {@link Canvas} interface.
   */
  CanvasImage createImage(float width, float height);

  /**
   * Creates a linear gradient fill pattern. (x0, y0) and (x1, y1) specify the
   * start and end positions, while (colors, positions) specifies the list of
   * color stops.
   */
  Gradient createLinearGradient(float x0, float y0, float x1, float y1,
      int colors[], float positions[]);

  /**
   * Creates a radial gradient fill pattern. (x0, y0, r) specifies the circle
   * covered by this gradient, while (colors, positions) specifies the list of
   * color stops.
   */
  Gradient createRadialGradient(float x, float y, float r, int colors[],
      float positions[]);

  /**
   * Creates a font with the specified configuration.
   */
  Font createFont(String name, Font.Style style, float size);

  /**
   * Lays out the supplied text using the specified format. The text may subsequently be rendered
   * on a canvas via {@link Canvas#fillText(TextLayout,float,float)}.
   */
  TextLayout layoutText(String text, TextFormat format);

  /**
   * Gets the height of the available screen real-estate, in pixels.
   */
  int screenHeight();

  /**
   * Gets the width of the available screen real-estate, in pixels.
   */
  int screenWidth();

  /**
   * Gets the width of the drawable surface, in pixels.
   */
  int width();

  /**
   * Gets the height of the drawable surface, in pixels.
   */
  int height();

  /**
   * Sets the size of the drawable surface, in pixels.
   */
  void setSize(int width, int height);

  /**
   * Returns the display scale factor. This will be 1 except on HiDPI devices that have been
   * configured to use HiDPI mode, where it will probably be 2, but could be some other scale
   * depending on how things were configured when initializing the platform.
   */
  float scaleFactor();

  /**
   * <b>WARNING</b>: this is a totally experimental, untested feature. It only works on Android. It
   * may change completely. Consider yourself warned.
   *
   * <p> Returns a reference to the GL context. </p>
   */
  GL20 gl20();

  /**
   * Returns the GL context on platforms that use GL, null otherwise. This is used for creating
   * custom shaders.
   */
  GLContext ctx();
}
