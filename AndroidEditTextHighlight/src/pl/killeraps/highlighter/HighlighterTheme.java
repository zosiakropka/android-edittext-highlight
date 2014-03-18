/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.killeraps.highlighter;

import android.graphics.Color;
import java.util.HashMap;

/**
 *
 * @author zosia
 */
public class HighlighterTheme {

  private final boolean light = true;

  private final int COLOR_LIGHT = Color.WHITE;
  private final int COLOR_DARK = Color.BLACK;

  /**
   * Maps elements to colors numbers
   */
  HashMap<Integer, Integer> colors = new HashMap<Integer, Integer>();

  public HighlighterTheme() {
    colors.put(HighlighterElements.HEADER, Color.parseColor("green"));
    colors.put(HighlighterElements.LINK, Color.parseColor("blue"));
    colors.put(HighlighterElements.BOLD, Color.parseColor("gray"));
    colors.put(HighlighterElements.EMPHASIS, Color.parseColor("magenta"));
    colors.put(HighlighterElements.DEL, Color.parseColor("yellow"));
    colors.put(HighlighterElements.QUOTE, Color.parseColor("cyan"));
    colors.put(HighlighterElements.INLINE_CODE, Color.parseColor("yellow"));
    colors.put(HighlighterElements.UL_LIST, Color.parseColor("cyan"));
    colors.put(HighlighterElements.OL_LIST, Color.parseColor("magenta"));
    colors.put(HighlighterElements.BLOCKQUOTES, Color.parseColor("black"));
    colors.put(HighlighterElements.HORIZONTAL_RULE, Color.parseColor("yellow"));
    colors.put(HighlighterElements.ADD_PARAGRAPHS, Color.parseColor("blue"));
    colors.put(HighlighterElements.FIX_EXTRA_UL, Color.parseColor("blue"));
    colors.put(HighlighterElements.FIX_EXTRA_OL, Color.parseColor("green"));
    colors.put(HighlighterElements.FIX_EXTRA_BLOCKQUOTE, Color.parseColor("magenta"));

  }

  public int getColor(int style) {
    Integer color = colors.get(style);
    if (color != null) {
      return color;
    }
    color = colors.get(HighlighterElements.FOREGROUND);
    if (color != null) {
      return color;
    }
    if (light) {
      return COLOR_DARK;
    } else {
      return COLOR_LIGHT;
    }
  }
}
