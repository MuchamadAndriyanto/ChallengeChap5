package com.example.bismillah.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.bismillah.MainActivity
import com.example.bismillah.R
import com.example.bismillah.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.Locale


class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var sharedpref: SharedPreferences
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedpref = requireActivity().getSharedPreferences("Register", Context.MODE_PRIVATE)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.btnReg.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnIndo.setOnClickListener {
            setIndonesia("id")
        }

        binding.btnEng.setOnClickListener {
            setEnglish("eng")
        }

    }

    fun login(){
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEdiText.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(context,"Login Telah Berhasil", Toast.LENGTH_LONG).show()
                    Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_homeFragment)
                }else{
                    Toast.makeText(context, "Email atau Password Kamu Ada Yang Salah",Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun setIndonesia(indonesia: String) {
        var locale : Locale = Locale("id")
        Locale.setDefault(locale)

        var config: Configuration = Configuration()
        config.locale = locale

        val res = resources
        res.updateConfiguration(config, res.displayMetrics)
        val intent = Intent(activity, MainActivity::class.java)
        requireActivity().startActivity(intent)

    }
    private fun setEnglish(english: String) {
        var locale : Locale = Locale("eng")
        Locale.setDefault(locale)

        var config: Configuration = Configuration()
        config.locale = locale

        val res = resources
        res.updateConfiguration(config, res.displayMetrics)
        val intent = Intent(activity, MainActivity::class.java)
        requireActivity().startActivity(intent)

    }


}