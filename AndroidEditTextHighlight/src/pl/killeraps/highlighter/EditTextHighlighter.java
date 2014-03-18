/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.killeraps.highlighter;

import android.graphics.Color;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author zosia
 */
public abstract class EditTextHighlighter implements Runnable {
  
  Map<Integer, List<ForegroundColorSpan>> spans = new HashMap<Integer, List<ForegroundColorSpan>>();
  
  private final EditText editText;
  private final HighlighterTheme theme;
  
  public EditTextHighlighter(EditText editText) {
    this.editText = editText;
    this.theme = new HighlighterTheme();
  }
  
  public EditTextHighlighter(EditText editText, HighlighterTheme theme) {
    this.editText = editText;
    this.theme = theme;
  }
  
  public void run() {
    
    editText.addTextChangedListener(new TextWatcher() {
      
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }
      
      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
      }
      
      @Override
      public void afterTextChanged(Editable editable) {
        parse_and_span(editable);
      }
    });
  }
  
  void parse_and_span(Editable editable) {
    
    String content = editable.toString();
    int element;
    Pattern pattern;
    for (Map.Entry<Integer, Pattern> rule : rules.entrySet()) {
      element = rule.getKey();
      pattern = rule.getValue();
      
      Matcher matcher = pattern.matcher(content);
      while (matcher.find()) {
        ForegroundColorSpan span = new ForegroundColorSpan(theme.getColor(element));
        editable.setSpan(
                span,
                matcher.start(), matcher.end(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (!spans.containsKey(element)) {
          spans.put(element, new ArrayList<ForegroundColorSpan>());
        }
        spans.get(element).add(span);
      }
    }
    for (Map.Entry<Integer, List<ForegroundColorSpan>> spanning : spans.entrySet()) {
      element = spanning.getKey();
      pattern = rules.get(element);
      for (ForegroundColorSpan span : spanning.getValue()) {
        int span_start = editable.getSpanStart(span);
        int span_end = editable.getSpanEnd(span);
        if (0 < span_start && span_start < span_end) {
          String highlightion = editable.subSequence(span_start, span_end).toString();
          if (!pattern.matcher(highlightion).matches()) {
            editable.removeSpan(span);
          }
        }
      }
    }
  }
  
  Map<Integer, Pattern> rules;
}
