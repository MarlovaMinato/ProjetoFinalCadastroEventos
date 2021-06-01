package br.senai.sc.projeto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.time.LocalDate;

import br.senai.sc.projeto.modelo.Evento;

public class CadastroEventoActivity extends AppCompatActivity {

    private final int RESULT_CODE_NOVO_EVENTO = 10;
    private final int RESULT_CODE_EVENTO_EDITADO = 11;

    private boolean edição = false:
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Cadastro de Evento");
        carregarEvento();

    }

    private void carregarEvento() {

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("eventoEdição") != null) {
            Evento evento = (Evento) intent.getExtras().get("produtoEdição");
            EditText editTextNome = findViewById(R.id.editText_nome);
            EditText editTextData = findViewById(R.id.editText_data);
            EditText editTextLocal = findViewById(R.id.editText_local);
            editTextNome.setText(evento.getNome());
            editTextData.setText(String.valueOf(evento.getData()));
            editTextLocal.setText(evento.getLocal());
            edição = true;
            id = evento.getId();


        }

    }

    public void onClickVoltar(View view) {
        finish();
    }

    public void onClickSalvar(View view) {
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextData = findViewById(R.id.editText_data);
        EditText editTextLocal = findViewById(R.id.editText_local);

        String nome = editTextNome.getText().toString();
        LocalDate data = LocalDate.parse(editTextData.getText().toString());
        String local = editTextLocal.getText().toString();

        Evento evento = new Evento(id, nome, data, local);
        Intent intent = new Intent();
        if (edição) {
            intent.putExtra("eventoEditado", evento);
            setResult(RESULT_CODE_EVENTO_EDITADO, intent);
        } else {
            intent.putExtra("novoEvento", evento);
            setResult(RESULT_CODE_NOVO_EVENTO, intent);
        }

        finish();


    }
}