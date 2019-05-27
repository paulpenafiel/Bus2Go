package com.example.usuario.proyectofinal;

/**
 * Created by Usuario on 05/07/2017.
 */

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

//import javax.xml.datatype.Duration;

/**
 * Created by Mai Thanh Hiep on 4/3/2016.
 */
public class Route {
    public Distancia distance;
    //public Duration duration;
    public String endAddress;
    public LatLng endLocation;
    public String startAddress;
    public LatLng startLocation;

    public List<LatLng> points;
}
