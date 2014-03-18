package pl.killeraps.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import pl.killeraps.highlighter.EditTextMarkdown;

public class MainActivity extends Activity {

  /**
   * Called when the activity is first created.
   *
   * @param savedInstanceState
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    EditText editText = (EditText) findViewById(R.id.editor);

    (new Thread(new EditTextMarkdown(editText))).start();
  }
}
