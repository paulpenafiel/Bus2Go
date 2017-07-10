package com.example.usuario.proyectofinal;

/**
 * Created by Usuario on 05/07/2017.
 */

import java.util.List;



        import com.google.android.gms.maps.model.LatLng;

        import java.util.ArrayList;
        import java.util.List;
        import com.example.usuario.proyectofinal.Route;



public interface EncuentraDireccionListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}