package com.example.fsyw.ui.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fsync.R;
import com.example.fsync.databinding.FragmentLogoutBinding;
import com.example.fsyw.MainActivity; // Assurez-vous que le chemin est correct

public class LogoutFragment extends Fragment {

    private FragmentLogoutBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LogoutViewModel logoutViewModel =
                new ViewModelProvider(this).get(LogoutViewModel.class);

        binding = FragmentLogoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textLogout; // Assurez-vous que l'identifiant est correct
        logoutViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Button buttonLogout = binding.buttonLogout; // Assurez-vous que l'identifiant est correct
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ajoutez ici la logique de déconnexion si nécessaire (ex: supprimer les données utilisateur)

                // Démarrez MainActivity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Cette ligne nettoie la pile d'activités
                startActivity(intent);
                getActivity().finish(); // Terminez l'activité actuelle
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
