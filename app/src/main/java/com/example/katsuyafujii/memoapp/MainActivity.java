package com.example.katsuyafujii.memoapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /** タイトル入力用エディットテキスト */
    private EditText titleEditText;
    /** コメント入力用エディットテキスト */
    private EditText commentEditText;

    //電話番号入力欄
    private EditText phoneEditText;

    //プリファレンス
    private SharedPreferences preferences;

    /** タイトル保存用キー */
    private static final String KEY_TITLE = "title";
    /** コメント保存用キー */
    private static final String KEY_COMMENT = "comment";

    //電話番号保存用キー
    private static final String KEY_PHONE = "phone";
    //何も保存されていない時に返す文字列
    private static final String NOT_FOUND_DATA = "データが見つかりません。";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // レイアウトよりEditTextを取得
        titleEditText = (EditText) findViewById(R.id.main_title_etx);
        commentEditText = (EditText) findViewById(R.id.main_comment_etx);
        phoneEditText = (EditText) findViewById(R.id.main_phone_etx);


        // プリファレンスをデフォルト名で作成(保存用のファイルを作成）
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        //プリファレンスにデータが保存されていれば，保存されているデータをセットする
        //タイトル
        String title = preferences.getString(KEY_TITLE,NOT_FOUND_DATA);

        //取得したタイトルが「データが見つかりません」でなかった場合 ＝ データが保存されていた場合
        if(title.equals(NOT_FOUND_DATA)== false) {
            titleEditText.setText(title);
        }
        //コメント
        String comment = preferences.getString(KEY_COMMENT,NOT_FOUND_DATA);
        //取得したコメントが「データが見つかりません」でなかった場合
        if (comment.equals(NOT_FOUND_DATA)==false) {
            commentEditText.setText(comment);

        }
        String phone = preferences.getString(KEY_PHONE,NOT_FOUND_DATA);
        if(phone.equals(NOT_FOUND_DATA)==false){
            phoneEditText.setText(phone);
        }
        //レイアウトより保存ボタンを取得
        Button saveBtn = (Button) findViewById(R.id.main_save_btn);
        saveBtn.setOnClickListener(this);

        //レイアウトより削除ボタンを取得
        Button deleteBtn = (Button) findViewById(R.id.main_delete_btn);
        deleteBtn.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {

        // プリファレンスエディタの初期化
        SharedPreferences.Editor editor = preferences.edit();
        //getId()    押されたビューのIDを取得
        switch(v.getId()) {
                //保村ボタンが押された場合
            case R.id.main_save_btn:

                // 入力されているデータを取得
                String title =titleEditText.getText().toString();
                String comment = commentEditText.getText().toString();
                String phone = phoneEditText.getText().toString();

                // プリファレンスにデータを書き込む
                editor.putString(KEY_TITLE,title);
                editor.putString(KEY_COMMENT,comment);
                editor.putString(KEY_PHONE,phone);
                editor.commit();//上書き保存

                // Toastを表示し、保存が完了した旨を通知する
                Toast.makeText(this, "保存しました。", Toast.LENGTH_SHORT).show();


                break;
            //削除ボタンが押された場合
            case R.id.main_delete_btn:

                //プリファレンスからデータを削除
                editor.remove(KEY_TITLE);
                editor.remove(KEY_COMMENT);
                editor.remove(KEY_PHONE);
                editor.commit();//上書き保存

                //入力欄を空にする
                titleEditText.setText("");
                commentEditText.setText("");
                phoneEditText.setText("");

                // Toastを表示し、削除した旨を通知する
                Toast.makeText(this, "削除しました", Toast.LENGTH_SHORT).show();


                break;
        }
    }
}
