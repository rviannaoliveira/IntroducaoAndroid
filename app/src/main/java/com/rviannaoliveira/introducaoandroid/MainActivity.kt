package com.rviannaoliveira.introducaoandroid

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var nome : EditText
    private lateinit var botaoBemVindo : Button
    private lateinit var botaoCompartilhar : Button
    private lateinit var botaoEmail: Button
    private lateinit var botaoCamera: Button
    private lateinit var botaoOK: Button
    private lateinit var imagem: ImageView
    private lateinit var textoNome: TextView

    companion object {
        val REQUEST_IMAGE_CAPTURE = 1
        val SAVE_TEXT = "SAVE_TEXT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViews()
        setListeners()

        if(savedInstanceState != null){
            textoNome.text = savedInstanceState.getString(SAVE_TEXT)
        }
    }

    private fun setViews() {
        nome = findViewById(R.id.nome)
        botaoBemVindo = findViewById(R.id.btn_bem_vindo)
        botaoCompartilhar = findViewById(R.id.btn_compartilhar)
        botaoEmail = findViewById(R.id.btn_email)
        botaoCamera = findViewById(R.id.btn_camera)
        botaoOK = findViewById(R.id.btn_ok)
        imagem = findViewById(R.id.imagem)
        textoNome = findViewById(R.id.txt_nome)
    }

    private fun setListeners() {
        botaoBemVindo.setOnClickListener { acaoBotaoBemVindo() }
        botaoCompartilhar.setOnClickListener { acaoCompartilhar() }
        botaoEmail.setOnClickListener { acaoBotaoEmail() }
        botaoCamera.setOnClickListener { acaoBotaoCamera() }
        botaoOK.setOnClickListener { acaoBotaoOk() }
        imagem.setOnClickListener { v -> v.visibility = View.GONE }
    }

    private fun acaoBotaoOk() {
        textoNome.text = nome.text
    }

    private fun acaoBotaoBemVindo() {
        val intent = Intent(this,HelloWorldActivity::class.java)
        intent.putExtra(HelloWorldActivity.KEY,nome.text.toString())
        startActivity(intent)
    }

    private fun acaoCompartilhar() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(HelloWorldActivity.KEY,nome.text)
        intent.putExtra(Intent.EXTRA_TEXT, nome.text)
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent, "Escolha a opção"))
    }
    private fun acaoBotaoEmail() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, "rodrigo.vianna.oliveira@gmail.com")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Meu Primeiro Email")
        intent.putExtra(Intent.EXTRA_TEXT, "Texto do email será: "+ nome.text)
        startActivity(intent)
    }
    private fun acaoBotaoCamera() {
        val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            REQUEST_IMAGE_CAPTURE -> {
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        imagem.visibility = View.VISIBLE
                        imagem.setImageBitmap(data?.extras?.get("data") as Bitmap)
                    } catch (e: Exception) {
                        Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else -> {
                Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(SAVE_TEXT,textoNome.text.toString())
    }
}
