/*
 * Regular expressions used in this code are taken from
 * https://gist.github.com/jbroadway/2836900
 *
 * @author zosia
 */
package pl.killeraps.highlighter;

import android.widget.EditText;
import java.util.HashMap;
import java.util.regex.Pattern;

public class EditTextMarkdown extends EditTextHighlighter {

  public EditTextMarkdown(EditText editText) {
    super(editText);

    rules = new HashMap<Integer, Pattern>();

    rules.put(HighlighterElements.HEADER, Pattern.compile("(#+)(.*)"));
    rules.put(HighlighterElements.LINK, Pattern.compile("\\[([^\\[]+)\\]\\(([^\\)]+)\\)"));
    rules.put(HighlighterElements.BOLD, Pattern.compile("(\\*\\*|__)(.*?)\\1"));
    rules.put(HighlighterElements.EMPHASIS, Pattern.compile("(\\*|_)(.*?)\\1"));
    rules.put(HighlighterElements.DEL, Pattern.compile("\\~\\~(.*?)\\~\\~"));
    rules.put(HighlighterElements.QUOTE, Pattern.compile("\\:\\\"(.*?)\\\"\\:"));
    rules.put(HighlighterElements.INLINE_CODE, Pattern.compile("`(.*?)`"));
    rules.put(HighlighterElements.UL_LIST, Pattern.compile("\\n\\*(.*)"));
    rules.put(HighlighterElements.OL_LIST, Pattern.compile("\\n[0-9]+\\.(.*)"));
    rules.put(HighlighterElements.BLOCKQUOTES, Pattern.compile("\\n(&gt;|\\>)(.*)"));
    rules.put(HighlighterElements.HORIZONTAL_RULE, Pattern.compile("\\n-{5,}"));
    rules.put(HighlighterElements.ADD_PARAGRAPHS, Pattern.compile("\\n([^\\n]+)\\n"));
    rules.put(HighlighterElements.FIX_EXTRA_UL, Pattern.compile("<\\/ul>\\s?<ul>"));
    rules.put(HighlighterElements.FIX_EXTRA_OL, Pattern.compile("<\\/ol>\\s?<ol>"));
    rules.put(HighlighterElements.FIX_EXTRA_BLOCKQUOTE, Pattern.compile("<\\/blockquote><blockquote>"));
  }

}
