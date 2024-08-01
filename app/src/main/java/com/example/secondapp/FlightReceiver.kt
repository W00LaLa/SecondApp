package com.example.secondapp

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log

class FlightReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.getAction().equals("ihave.flight")) {
            Log.i("Flight", "receive a flight broadcast")
        }
    }
}