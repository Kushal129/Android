package com.example.cie1_c;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class ProductList extends Fragment {

    String[] ProductName = {
            "Smart Watch For Kids",
            "sekyo S1 Smartwatch Kids AGPS",
            "Wearfit Champ 2G Flash Kids",
            "TrailO ™ iSecureRelyCam",
            "Noise Scout Kids Smartwatch"
    };
    String[] Description = {
        "Exciting New Gadget for Kids: The V2A smart watch for kids girls and boys is an excellent accessory for curious kids who want to have tons of fun and enjoy a child-friendly gadget. This kids smart watch has a slim and modern design in cool colors that looks amazing in any outfit.",
        "With our SEKYO app, you can easily and privately find your child anywhere in the from your phone.; Smartwatch Features- SOS, Voice Calling, Geo-fencing, Camera, Remote Voice Monitoring, Micro Sim",
        "IP67 Waterproof Smart Watch Phone：The watch uses advanced waterproof design of IP67 so that you don't need to worry about the watch will be damaged, Make life easier for your children.",
        "SOS Call Function (Emergency Button): if kids are in danger situation, they can long press SOS button for 3s, It circularly dials 3 sos numbers for two turns. It will stop when call is answered.",
        "Parent video calling - Parents can video call their child to know that they are in a safe environment. Kids can only interact with parent approved contacts"
    };

    int[] Images={
            R.drawable.watch1,
            R.drawable.watch2,
            R.drawable.watch3,
            R.drawable.watch4,
            R.drawable.watch5
    };
    public ProductList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        ListView lstProductList = view.findViewById(R.id.lstProductList);
        CustomAdapter adapter = new CustomAdapter(getActivity(),ProductName,Description,Images);
        lstProductList.setAdapter(adapter);
        return view;
    }
}